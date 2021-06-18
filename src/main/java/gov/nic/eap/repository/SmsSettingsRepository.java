package gov.nic.eap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.nic.eap.model.SmsSettings;

public interface SmsSettingsRepository extends JpaRepository<SmsSettings, Long> {
	SmsSettings findBySiteId(Long siteid);
}
