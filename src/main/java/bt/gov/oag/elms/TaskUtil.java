package bt.gov.oag.elms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bt.gov.oag.elms.pojo.TaskVariables;

@Service
public class TaskUtil {

	public Map<String, Object> processTaskVariables(List<TaskVariables> taskVariableList){
		Map<String, Object> taskInstanceVariables = new HashMap<String, Object>();
		for(TaskVariables taskVars : taskVariableList) {
			taskInstanceVariables.put(taskVars.getKey(), taskVars.getValue());
		}
		
		return taskInstanceVariables;
	}
}
