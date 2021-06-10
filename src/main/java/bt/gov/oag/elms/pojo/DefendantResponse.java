package bt.gov.oag.elms.pojo;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class DefendantResponse {

	private Long id;
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
	private Timestamp updatedOn;
	private int updatedBy; 
	private String updatedByName;  
	private Long dzongkhagId; 
	private Long gewogId; 
	private Long villageId;
	private String dzongkhagName;
	private String gewogName;
	private String villageName;
	
	private CourtHearingResponse courtHearing; 
	
}
