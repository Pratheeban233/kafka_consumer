package gov.nic.eap.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eap_sms_sender")
@Getter
@Setter
public class SmsSender implements Serializable {

	private static final long serialVersionUID = 4985250302928896325L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMS_SENDER_SEQ")
	@SequenceGenerator(sequenceName = "seq_sms_sender", allocationSize = 1, name = "SMS_SENDER_SEQ")
	private Long id;

	@Column(name = "siteid")
	private Long siteid;

	@Column(name = "smsid")
	private Long smsid;

	@Column(name = "smsbody")
	private String smsbody;

	@Column(name = "mobilenos")
	private String mobilenos;

	@Column(name = "smsresponseid")
	private String smsresponseid;

	@Column(name = "createdby")
	private Long createdby;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "createddate")
	private LocalDateTime createddate;

	@Column(name = "smssource")
	private String smssource;

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
