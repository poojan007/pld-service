package bt.gov.oag.elms.pojo;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CaseBriefResponse {
	
	private Long incoming_letter_id;
	private String fact;
	private String issue;
	private String rule;
	private String application;
	private String conclusion; 
	private Timestamp updatedOn;
	private int updatedBy;
	private String updatedByName;   

	private String message;
	private HttpStatus status;
	private String exception;
}
