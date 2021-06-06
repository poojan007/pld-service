package bt.gov.oag.elms.entity; 

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Entity
@Data
public class DefendantInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="incoming_letter_id") 
	private Long incomingLetterId;
	private String defendantName;
	private Date defendantDob;
	private String defendantGender;
	private BigInteger defendantCid;
	private String nationality;
	private int defendantContactNo;
	private String presentAddress;
	private String occupation;
	private String houseNo;
	private String thramNo; 
	@CreatedDate
	private Timestamp updatedOn;
	private int updatedBy; 
	private String updatedByName; 
	@Column(name="dzongkhag_id") 
	private Long dzongkhagId;
	@Column(name="gewog_id") 
	private Long gewogId;
	@Column(name="village_id") 
	private Long villageId;
	
	@OneToOne
	@JoinColumn(name = "dzongkhag_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Dzongkhag getDzongkhag;
	
	@OneToOne
	@JoinColumn(name = "gewog_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Gewog getGewog;
	
	@OneToOne
	@JoinColumn(name = "village_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Village getVillage;  
}
