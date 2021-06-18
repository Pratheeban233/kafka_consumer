package gov.nic.eap.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "eap_sms_settings")
@Data
public class SmsSettings implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SMS_SETTINGS")
	@SequenceGenerator(sequenceName = "seq_sms_settings", allocationSize = 1, name = "SEQ_SMS_SETTINGS")
	private Long id;

	@Column(name = "siteid")
	private Long siteId;

	@Column(name = "smsusername")
	private String smsUserName;

	@Column(name = "smspasswd")
	private String smsPasswd;

	@Column(name = "smssenderid")
	private String smsSenderId;

	@Column(name = "smscontactno")
	private String smsContactNo;

	@Column(name = "smsserverurl")
	private String smsServerUrl;

	@Column(name = "smsport")
	private Long smsPort;

	@Column(name = "issmsenabled")
	private Boolean isSmsEnabled;

	@Column(name = "createdby")
	private Long createdBy;

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "updatedby")
	private Long updatedBy;

	@Column(name = "updateddate")
	private LocalDateTime updatedDate;
}
