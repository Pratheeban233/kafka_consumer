package gov.nic.eap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import gov.nic.eap.service.Implementation.MessageQueueIngester;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {

	@Autowired
	private MessageQueueIngester messageQueueIngester;

	@KafkaListener(topics = { "${kafka.consumer.topic_mail}",
			"${kafka.consumer.topic_sms}" }, containerFactory = "listenerContainerFactory", groupId = "${kafka.consumer.groupid}")
	public void listener(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition_id,
			@Header(KafkaHeaders.OFFSET) List<Long> offsets) {

		log.info("Received Message from kafka : " + message + " and offsets " + offsets + "from partition: " + partition_id);
		try {
			log.info("message consuming process started..");
			messageQueueIngester.processConsumerMessages(message);
			log.info("consuming process completed.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("message consume failed.");
		}
	}
}
