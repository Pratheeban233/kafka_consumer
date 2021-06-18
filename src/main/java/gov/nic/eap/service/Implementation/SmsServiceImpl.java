package gov.nic.eap.service.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.net.ssl.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import gov.nic.eap.common.constant.SplCharConstants;
import gov.nic.eap.common.exception.ServiceException;
import gov.nic.eap.common.util.Util;
import gov.nic.eap.constant.MailSMSConstants;
import gov.nic.eap.model.Sms;
import gov.nic.eap.model.SmsSender;
import gov.nic.eap.model.SmsSettings;
import gov.nic.eap.repository.SmsRepository;
import gov.nic.eap.repository.SmsSenderRepository;
import gov.nic.eap.repository.SmsSettingsRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsServiceImpl {

	@Autowired
	private Properties smsProperties;

	@Autowired
	private SmsSenderRepository smsSenderRepository;

	@Autowired
	private SmsSettingsRepository smsSettingsRepository;

	@Autowired
	private SmsRepository smsRepository;

	public int processSms(List<SmsSender> smsSenderList, int count) throws JsonProcessingException {

		if (Util.isListNotNullOrEmpty(smsSenderList)) {
			for (SmsSender smsSender : smsSenderList) {
				Optional<SmsSender> optionalSmsSender = smsSenderRepository.findById(smsSender.getId());
				if (!optionalSmsSender.isPresent())
					return count;
				Optional<Sms> optionalSms = smsRepository.findById(smsSender.getSmsid());
				if (optionalSms.isPresent()) {
					Sms sms = optionalSms.get();
					if (getSMSProperties(smsSender, sms)) {
						boolean status = sendSms(smsSender, sms);
						if (status) {
							SmsSender sender = optionalSmsSender.get();
							sender.setProcessid(2);
							sender.setIsexecuted(true);
							sender.setExecuteddate(LocalDateTime.now());
							sender.setRemarks("SMS sent by Kafka");
							smsSenderRepository.save(sender);
							count++;
						}
					}
				}
			}
		}
		return count;
	}

	public Boolean sendSms(SmsSender smsSender, Sms sms) {
		String smsBody = null;
		try {

			if (Util.isNullOrBlank(smsSender.getMobilenos())) {
				throw new ServiceException("To PhoneNos is empty");
			}

			if (Util.isNullOrBlank(smsSender.getSmsbody())) {
				throw new ServiceException("Message is empty");
			}

			String[] mobileNumbersList = smsSender.getMobilenos().split(SplCharConstants.SEMICOLON);

			for (String mobileNumber : mobileNumbersList) {
				if (Util.isNotNullOrBlank(mobileNumber)) {
					smsBody = URLEncoder.encode(smsSender.getSmsbody(), "UTF-8");
				}

				StringBuilder weburl = new StringBuilder();
				weburl.append(smsProperties.getProperty(MailSMSConstants.SMSProperties.SERVERURL) + "?");
				weburl.append("username=" + smsProperties.getProperty(MailSMSConstants.SMSProperties.USER));
				weburl.append("&pin=" + smsProperties.getProperty(MailSMSConstants.SMSProperties.PASSWORD));
				weburl.append("&message=" + smsBody + "&mnumber=" + mobileNumber);
				weburl.append("&signature=" + smsProperties.getProperty(MailSMSConstants.SMSProperties.SENDERID));
				weburl.append("&dlt_entity_id=110100001364");
				weburl.append("&dlt_template_id=" + sms.getTemplateId());
				log.info(weburl.toString());
				String msg = sendSmsSender(weburl.toString());
				log.info("Sms Response : " + msg);
				if (!msg.isEmpty()) {
					return true;
				}
			}

		} catch (Exception e) {
			log.error("Exception : " + e);
		}
		return false;
	}

	public String sendSmsSender(String weburl) {
		String smsResponseDesc = null;
		BufferedReader bufferedReader = null;

		try {

			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

			HttpURLConnection connection = (HttpURLConnection) new URL(weburl).openConnection();
			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) {
				smsResponseDesc = inputLine.replaceAll("\\r|\\n", "");
			}
		} catch (MalformedURLException e) {
			log.error("Exception : " + e);
		} catch (ProtocolException e) {
			log.error("Exception : " + e);
		} catch (Exception e) {
			log.error("Exception : " + e);
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				log.error("Exception : " + e);
			}
		}
		log.info("Sms sent...");
		return smsResponseDesc;
	}

	private boolean getSMSProperties(SmsSender smsSender, Sms sms) {
		SmsSettings smsSettings = smsSettingsRepository.findBySiteId(smsSender.getSiteid());

		if (smsSettings == null)
			return false;

		smsProperties.put(MailSMSConstants.SMSProperties.USER, smsSettings.getSmsUserName());
		smsProperties.put(MailSMSConstants.SMSProperties.PASSWORD, smsSettings.getSmsPasswd());
		smsProperties.put(MailSMSConstants.SMSProperties.SENDERID, smsSettings.getSmsSenderId());
		smsProperties.put(MailSMSConstants.SMSProperties.CONTACTNO, smsSender.getMobilenos());
		smsProperties.put(MailSMSConstants.SMSProperties.SPLITMSGTYPE, sms.getSplitMsgType());
		smsProperties.put(MailSMSConstants.SMSProperties.SERVERURL, smsSettings.getSmsServerUrl());

		if (Util.isNotNullOrZero(smsSettings.getSmsPort()))
			smsProperties.put(MailSMSConstants.SMSProperties.PORT, smsSettings.getSmsPort());

		return true;
	}
}
