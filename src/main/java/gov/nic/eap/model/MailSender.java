package gov.nic.eap.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

@Entity
@Table(name = "eap_mail_sender")
@Data
public class MailSender implements Serializable {

	private static final long serialVersionUID = -2014331764259584526L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MAIL_SENDER_SEQ")
	@SequenceGenerator(sequenceName = "seq_mail_sender", allocationSize = 1, name = "MAIL_SENDER_SEQ")
	private Long id;

	@Column(name = "siteid")
	private Long siteid;

	@Column(name = "mailid")
	private Long mailid;

	@Column(name = "mailsubject")
	private String mailsubject;

	@Column(name = "mailbody")
	private String mailbody;

	@Column(name = "toemailids")
	private String toemailids;

	@Column(name = "ccemailids")
	private String ccemailids;

	@Column(name = "bccemailids")
	private String bccemailids;

	@Column(name = "createdby")
	private Long createdby;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "createddate")
	private LocalDateTime createddate;

	@Column(name = "mailsource")
	private String mailsource;

	@Column(name = "processid")
	private Integer processid;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "initiateddate")
	private LocalDateTime initiateddate;

	@Column(name = "isexecuted")
	private Boolean isexecuted;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "executeddate")
	private LocalDateTime executeddate;

	@Column(name = "remarks")
	private String remarks;
}
