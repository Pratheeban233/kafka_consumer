package gov.nic.eap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.nic.eap.model.MailSettings;

public interface MailSettingsRepository extends JpaRepository<MailSettings, Long> {
	MailSettings findBySiteId(Long siteid);
}
