package gov.nic.eap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.nic.eap.model.Sms;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Long> {

}
