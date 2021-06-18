package gov.nic.eap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.nic.eap.model.SmsSender;

public interface SmsSenderRepository extends JpaRepository<SmsSender, Long> {
}
