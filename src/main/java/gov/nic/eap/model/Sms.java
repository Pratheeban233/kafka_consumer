package gov.nic.eap.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eap_sms")
@Getter
@Setter
public class Sms implements Serializable {

	private static final long serialVersionUID = -810149055801774928L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SEQ")
	@SequenceGenerator(sequenceName = "seq_sms", allocationSize = 1, name = "SMS_SEQ")
	private Long id;

	@Column(name = "messageid")
	private Long messageId;

	@Column(name = "smsbody")
	private String smsBody;

	@Column(name = "splitmsgtype")
	private Long splitMsgType;

	@Column(name = "isoptional")
	private boolean isOptional;

	@Column(name = "templateid")
	private String templateId;

	@Column(name = "isactive")
	private boolean isActive;

	@Column(name = "createdby")
	private Long createdBy;

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "updatedby")
	private Long updatedBy;

	@Column(name = "updateddate")
	private LocalDateTime updatedDate;
}
