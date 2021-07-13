package gov.nic.eap.service.Implementation;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.nic.eap.common.constant.SplCharConstants;
import gov.nic.eap.common.exception.ServiceException;
import gov.nic.eap.common.util.Util;
import gov.nic.eap.constant.ContentTypeConstants;
import gov.nic.eap.constant.MailSMSConstants;
import gov.nic.eap.model.MailSender;
import gov.nic.eap.model.MailSettings;
import gov.nic.eap.repository.MailSenderRepository;
import gov.nic.eap.repository.MailSettingsRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailServiceImpl {

	@Autowired
	private Properties mailProperties;

	@Autowired
	private MailSenderRepository mailSenderRepository;

	@Autowired

	private MailSettingsRepository mailSettingsRepository;

	public int mailProcess(List<MailSender> mailSenderList, int count) {
		if (Util.isListNotNullOrEmpty(mailSenderList)) {
			for (MailSender mail : mailSenderList) {
				Optional<MailSender> optionalMailSender = mailSenderRepository.findById(mail.getId());
				if (!optionalMailSender.isPresent())
					return count;
				if (getMailProperties(mail)) {
					try {
						boolean status = sendMail(mail);
						if (status) {
							MailSender mailSender = optionalMailSender.get();
							mailSender.setProcessid(2);
							mailSender.setIsexecuted(true);
							mailSender.setExecuteddate(LocalDateTime.now());
							mailSender.setRemarks("Mail sent by kafka");
							mailSenderRepository.save(mailSender);
							count++;
						}
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return count;
	}

	public boolean sendMail(MailSender mailSender) throws ServiceException {
		log.info("Sending Mail...");
		if (Util.isNullOrBlank(mailSender.getToemailids())) {
			throw new ServiceException("To Address is empty");
		}
		if (Util.isNullOrBlank(mailSender.getMailbody())) {
			throw new ServiceException("Body is empty");
		}
		Multipart multiPart;
		Boolean status = true;
		multiPart = new MimeMultipart();
		MimeMessage mineMessage = new MimeMessage(
				(mailProperties.getProperty(MailSMSConstants.MailProperties.PORT).equals(MailSMSConstants.MailProperties.Port.FOURSIXTYFIVE.toString()))
						? getPort465Session()
						: getSession());
		try {
			mineMessage.setFrom(new InternetAddress(mailProperties.getProperty(MailSMSConstants.MailProperties.FROM)));

			mineMessage.setSentDate(new Date());
			mineMessage.setSubject(mailSender.getMailsubject());
			InternetAddress[] toAddress = getInternetAddressForList(mailSender.getToemailids());
			mineMessage.setRecipients(Message.RecipientType.TO, toAddress);
			if (mailSender.getCcemailids() != null) {
				InternetAddress[] ccAddress = getInternetAddressForList(mailSender.getCcemailids());
				mineMessage.setRecipients(Message.RecipientType.CC, ccAddress);
			}
			if (mailSender.getBccemailids() != null) {
				InternetAddress[] bccAddress = getInternetAddressForList(mailSender.getBccemailids());
				mineMessage.setRecipients(Message.RecipientType.BCC, bccAddress);
			}
			MimeBodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(mailSender.getMailbody(), ContentTypeConstants.TEXTHTML);
			multiPart.addBodyPart(bodyPart);
			mineMessage.setContent(multiPart);

			// test - to store the mail to the file
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			mineMessage.writeTo(output);
			try (FileOutputStream fos = new FileOutputStream("D:\\Kafka_Sources\\mail.html")) {
				fos.write(output.toByteArray());
			}

			status = sendTransport(mineMessage);
		} catch (MessagingException | IOException e) {
			log.error("Exception occurs : " + e);
		}

		return status;

	}

	private boolean sendTransport(MimeMessage msg) {
		try {
			log.info("Send transport in mail sender...");
			Transport.send(msg);
			log.info("Sent mail...");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private Session getSession() {
		Session session = Session.getInstance(mailProperties, new PopupAuthenticator());
		session.setDebug(false);
		return session;
	}

	private Session getPort465Session() {
		mailProperties.put(MailSMSConstants.MailProperties.SOCKETPORT, mailProperties.getProperty(MailSMSConstants.MailProperties.PORT));
		mailProperties.put(MailSMSConstants.MailProperties.SOCKETCLASS, MailSMSConstants.MailProperties.DEFAULTSOCKETFACTORY);
		mailProperties.put(MailSMSConstants.MailProperties.SOCKETFALLBACK, MailSMSConstants.MailProperties.DEFAULTSOCKETFALLBACK);
		Session session = Session.getInstance(mailProperties, new PopupAuthenticator());
		session.setDebug(false);
		return session;
	}

	private InternetAddress[] getInternetAddressForList(String emailAddresses) throws AddressException {
		int i = 0;
		String[] emailIds = emailAddresses.split(SplCharConstants.SEMICOLON);
		InternetAddress[] addressTo = new InternetAddress[emailIds.length];
		for (String emailId : emailIds) {
			if (Util.isNotNullOrBlank(emailId)) {
				addressTo[i] = new InternetAddress(emailId);
				i++;
			}
		}
		return addressTo;
	}

	private boolean getMailProperties(MailSender mailSender) {

		MailSettings mailSettings = mailSettingsRepository.findBySiteId(mailSender.getSiteid());

		if (mailSettings == null)
			return false;

		mailProperties.put(MailSMSConstants.MailProperties.USER, mailSettings.getMailUserName());
		mailProperties.put(MailSMSConstants.MailProperties.PASSWORD, mailSettings.getMailPasswd());
		mailProperties.put(MailSMSConstants.MailProperties.AUTH, "true");
		mailProperties.put(MailSMSConstants.MailProperties.STARTTLS, "true");
		mailProperties.put(MailSMSConstants.MailProperties.HOST, mailSettings.getMailHost());
		mailProperties.put(MailSMSConstants.MailProperties.PORT, mailSettings.getMailPort().toString());
		mailProperties.put(MailSMSConstants.MailProperties.FROM, mailSettings.getMailFrom());

		return true;
	}

	private class PopupAuthenticator extends Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(mailProperties.getProperty(MailSMSConstants.MailProperties.USER),
					mailProperties.getProperty(MailSMSConstants.MailProperties.PASSWORD));
		}
	}

}
