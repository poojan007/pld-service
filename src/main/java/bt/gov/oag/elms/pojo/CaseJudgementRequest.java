package bt.gov.oag.elms.pojo;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class CaseJudgementRequest {

	private Long id;    
	private Long caseId; 
	private String comment;
	private Date judgementDate;
	private String fileName;
	 
	private String convicetedAcquittedDeferred;
	private int updatedBy;
	private String updateByName;
	private List<TaskVariables> taskVariables;
}
