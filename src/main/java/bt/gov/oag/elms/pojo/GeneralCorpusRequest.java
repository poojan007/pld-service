package bt.gov.oag.elms.pojo;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class GeneralCorpusRequest {
	private int incomingLetterId;
	private Date corpusDate;
	private String briefFact;
	private String issue;
	private String recommendation;
	private String remark;
	private String member;
	private String presidedBy;
	private int createdBy;
	private int updatedBy;
	private List<TaskVariables> taskVariables;
}
