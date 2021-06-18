package gov.nic.eap.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "eap_mail_settings")
@Data
public class MailSettings implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MAIL_SETTINGS")
	@SequenceGenerator(sequenceName = "seq_mail_settings", allocationSize = 1, name = "SEQ_MAIL_SETTINGS")
	private Long id;

	@Column(name = "siteid")
	private Long siteId;

	@Column(name = "mailusername")
	private String mailUserName;

	@Column(name = "mailpasswd")
	private String mailPasswd;

	@Column(name = "mailfrom")
	private String mailFrom;

	@Column(name = "mailhost")
	private String mailHost;

	@Column(name = "mailport")
	private Long mailPort;

	@Column(name = "isrelayserver")
	private Boolean isRelayServer;

	@Column(name = "ismailenabled")
	private Boolean isMailEnabled;

	@Column(name = "createdby")
	private Long createdBy;

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "updatedby")
	private Long updatedBy;

	@Column(name = "updateddate")
	private LocalDateTime updatedDate;
}
