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
public class VictimInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long incomingLetterId;
	private String victimName;
	private Date victimDob;
	private String victimGender;
	private BigInteger victimCid;
	private String nationality;
	private int victimContactNo;
	private String presentAddress;
	private String occupation;
	private String houseNo;
	private String thramNo; 
	@Column(name = "gewog_id")
	private Long gewogId;
	@Column(name = "village_id")
	private Long villageId;
	@Column(name = "dzongkhag_id")
	private Long dzongkhagId;
	@CreatedDate
	private Timestamp updatedOn;
	private int updatedBy; 
	private String updatedByName; 
	
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
