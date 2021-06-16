package gov.nic.eap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
public class MailSender implements Serializable {

	private static final long serialVersionUID = -2014331764259584526L;

	private Long id;

	private Long siteid;

	private Long mailid;

	private String mailsubject;

	private String mailbody;

	private String toemailids;

	private String ccemailids;

	private String bccemailids;

	private Long createdby;

	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createddate;

	private String mailsource;

	private Integer processid;

	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime initiateddate;

	private Boolean isexecuted;

	@JsonIgnore
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime executeddate;

	private String remarks;
}
