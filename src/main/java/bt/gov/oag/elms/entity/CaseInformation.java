package bt.gov.oag.elms.entity; 

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Data
public class CaseInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	@Column(name = "jurisdiction_id")
	private Long jurisdictionId; 
	@Column(name = "case_type_id")
	private Long caseTypeId; 
	@Column(name = "incoming_letter_id")
	private Long incomingLetterId;  
	private String referralCaseNo;
	private String caseName;
	@UpdateTimestamp
	private Timestamp forwardingDate; 
	private String offence;
	private String evidence; 
	private int remandPeriod; 
	@UpdateTimestamp
	private Timestamp updatedOn;
	private int updatedBy;
	private String updatedByName;
	@Column(name = "referring_agency_id")
    private Long referringAgencyId;
	
//	 private String fileName;
//	  @ColumnDefault(value = "Request")
//	  @NotNull
//	  private String powerOfAttorney;
	
	@OneToOne
	@JoinColumn(name = "jurisdiction_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Jurisdiction getJurisdiction;  

//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "referring_agency_id")
//	private Agency getAgency;   
	
	 @OneToOne
	  @JoinColumn(name = "referring_agency_id", referencedColumnName = "id", insertable = false, updatable = false)
	  private Agency getAgency;
	
	@OneToOne
	@JoinColumn(name = "referring_agency_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Agency getAgency;      

	@OneToOne
	@JoinColumn(name = "case_type_id", referencedColumnName = "id", insertable = false, updatable = false)
	private CaseType getCaseType;   
}
