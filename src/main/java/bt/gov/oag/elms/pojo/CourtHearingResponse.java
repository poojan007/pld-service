package bt.gov.oag.elms.pojo;

import java.sql.Date;

import lombok.Data;

@Data
public class CourtHearingResponse {

	private Long id;    
	private Long defendantId;
	private String hearingStage;
	private String comment;
	private Date hearingDate;
}
