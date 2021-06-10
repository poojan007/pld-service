package bt.gov.oag.elms.pojo;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CaseApiResponse {

	private Long id;    
	private String letterStatus; 
	private Long fileCategoryId; 
	private String fileCategoryName; 
	private Long agencyId;
	private String taskIntanceId;
	private String caseStatus;
	private String formKey;
	private int assignee;
	private Boolean hasFollowedDueProcess;
	
	private String agencyName;   
	private String categoryName; 
	private String processKey;
	
	private String letterNo; 
	private String subject;
	private String senderName;
	private Timestamp letterDate; 
	private String receiptNo; 
	private Timestamp updatedOn;
	private int updatedBy;
	private String updatedByName; 
	private Timestamp addedOn;
	private int addedBy;
	private String addedByName;
	private String proccessId; 
	private int forwardedTo;
	private String forwardedToName; 
	private Timestamp forwardedOn; 
	private int examineFact;
	private int dueProcess;
	private int accessEvidence;
	private int caseUnderReview;   
	
	private String message;
	private HttpStatus status;
	private String exception;
	
	private String jurisdictionName;
	private Long caseDataExist;
}
