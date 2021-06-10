package bt.gov.oag.elms.pojo;

import lombok.Data;

@Data
public class InvestigatingOfficerResponse {
	private Long id;
	private Long incomingLetterId;
	private String investigatorName;
	private int phoneNo;
	private int mobileNo;
	private String email;
	private Long policeStationId;
	private Long dzongkhagId;
	private String policeStationName;
}
