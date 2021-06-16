package gov.nic.eap.service;

public interface Ingester {

	void processConsumerMessages(String message) throws Exception;
}
