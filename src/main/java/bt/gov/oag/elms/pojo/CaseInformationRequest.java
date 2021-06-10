package bt.gov.oag.elms.pojo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
public class CaseInformationRequest {

	private Long id;
	private Long jurisdictionId;  
	private Long caseTypeId;  
	private Long incomingLetterId;  
	private String referralCaseNo;
	private String caseName; 
	private Timestamp forwardingDate; 
	private String offence;
	private String evidence; 
	private int remandPeriod;  
	private Timestamp updatedOn;
	private int updatedBy; 
	private Long referringAgencyId;
	private String updatedByName;
	private String filePath;
	private String fileName; 
	private String powerOfAttorney;
	private String JurisdictionName;
	private String agencyName;
	private String caseTypeName;  
	private List<TaskVariables> taskVariables; 
	
}
