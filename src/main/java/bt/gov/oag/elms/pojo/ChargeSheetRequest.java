package bt.gov.oag.elms.pojo;

import java.util.List;

import lombok.Data;

@Data
public class ChargeSheetRequest {
	private int incomingLetterId;
	private String salutation;
	private String jurisdiction;
	private String factsOfcase;
	private String restitution;
	private String propertyAttachment;
	private List<ChargeSheetMatterRequest> matterRequests;
	private String prayers;
	private int createdBy;
	private List<TaskVariables> taskVariables;
}
