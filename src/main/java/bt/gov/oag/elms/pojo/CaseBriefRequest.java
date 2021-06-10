package bt.gov.oag.elms.pojo;

import java.util.List;

import lombok.Data;

@Data
public class CaseBriefRequest {
	private String fact;
	private String issue;
	private String rule;
	private String application;
	private String conclusion;
	private int createdBy;
	private Long incomingLetterId;
	private String caseCategory;
	private List<TaskVariables> taskVariables;
}
