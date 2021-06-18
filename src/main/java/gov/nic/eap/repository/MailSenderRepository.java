package gov.nic.eap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.nic.eap.model.MailSender;

public interface MailSenderRepository extends JpaRepository<MailSender, Long> {
}
