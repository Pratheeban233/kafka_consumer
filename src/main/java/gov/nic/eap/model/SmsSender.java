package gov.nic.eap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SmsSender implements Serializable {

	private static final long serialVersionUID = 4985250302928896325L;

	private Long id;

	private Long siteid;

	private Long smsid;

	private String smsbody;

	private String mobilenos;

	private String smsresponseid;

	private Long createdby;

	@JsonIgnore
	private LocalDateTime createddate;

	private String smssource;

	private Integer processid;

	@JsonIgnore
	private LocalDateTime initiateddate;

	private Boolean isexecuted;

	@JsonIgnore
	private LocalDateTime executeddate;

	private String remarks;
}
