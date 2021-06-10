package bt.gov.oag.elms.entity;
 
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class IncomingLetter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;   
	private String letterNo; 
	private String subject;
	private String senderName;
	private Timestamp letterDate; 
	private String receiptNo;
	@CreationTimestamp
	private Timestamp updatedOn;
	private int updatedBy;
	private String updatedByName;
	@CreationTimestamp
	private Timestamp addedOn;
	private int addedBy;
	private String addedByName;
	private String proccessId; 
	private int forwardedTo;
	private String forwardedToName;
	@CreationTimestamp
	private Timestamp forwardedOn; 
	private int examineFact;
	private int dueProcess;
	private int accessEvidence;
	private int caseUnderReview;  
	private String letterStatus;
	@Column(name = "file_category_id")
	private Long fileCategoryId;
	@Column(name = "agency_id")
	private Long agencyId;
	private Long caseDataExit;
	
	@OneToOne
	@JoinColumn(name = "agency_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Agency getAgency;
	
	@OneToOne
	@JoinColumn(name = "file_category_id", referencedColumnName = "id", insertable = false, updatable = false)
	private FileCategory getFileCategory;  
}
