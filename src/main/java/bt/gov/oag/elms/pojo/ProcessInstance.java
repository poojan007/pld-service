package bt.gov.oag.elms.pojo;

import lombok.Data;

@Data
public class ProcessInstance {

	private String id;
	private String name;
	private String processDefinitionId;
	private String processDefinitionName;

}
