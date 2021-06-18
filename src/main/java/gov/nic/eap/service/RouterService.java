package gov.nic.eap.service;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.nic.eap.model.MailSender;
import gov.nic.eap.model.SmsSender;
import gov.nic.eap.service.Implementation.MailServiceImpl;
import gov.nic.eap.service.Implementation.SmsServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RouterService {

	@Autowired
	private MailServiceImpl mailService;

	@Autowired
	private SmsServiceImpl smsService;

	public void messageRouter(String messages) throws JsonProcessingException {
		int count = 0;
		if (Objects.nonNull(messages)) {
			if (messages.contains("mailid")) {
				MailSender[] listOfMails = new ObjectMapper().readValue(messages, MailSender[].class);
				int processmails = mailService.mailProcess(Arrays.asList(listOfMails), count);
				if (processmails > 0) {
					log.info(processmails + " mails sent and status updated successfully.");
				} else {
					log.error("Mail sending process failed.");
				}

			}
			if (messages.contains("smsid")) {
				SmsSender[] listOfSms = new ObjectMapper().readValue(messages, SmsSender[].class);
				int processSms = smsService.processSms(Arrays.asList(listOfSms), count);
				if (processSms > 0) {
					log.info(processSms + " mails sent and status updated successfully.");
				} else {
					log.error("Sms Sending process failed.");
				}
			}
		}
	}
}
