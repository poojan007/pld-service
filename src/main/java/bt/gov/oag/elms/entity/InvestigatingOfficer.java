package bt.gov.oag.elms.entity; 

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class InvestigatingOfficer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "incoming_letter_id")
	private Long incomingLetterId;
	private String investigatorName;
	private int phoneNo;
	private int mobileNo;
	private String email;
	private int policeStation;
	private Timestamp updatedOn;
	private int updatedBy;
	private String updatedByName;
	
	 
}
