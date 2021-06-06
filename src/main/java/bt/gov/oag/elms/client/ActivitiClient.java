package bt.gov.oag.elms.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import bt.gov.oag.elms.pojo.WorkflowResponse;

@FeignClient(name = "workflow-service")
public interface ActivitiClient {
	
	@PostMapping("/process/start")
	WorkflowResponse startProcess(@RequestHeader("processKey") String processKey, @RequestBody Map<String, Object> processVariables);
	
	@PostMapping("/task/{taskInstanceId}/complete")
	WorkflowResponse completeTask(@PathVariable("taskInstanceId") String taskInstanceId, @RequestBody Map<String, Object> taskInstanceVariables);
	
	@GetMapping("/task/completed")
	WorkflowResponse getCompletedTaskInstances(@RequestParam(required = true) String assignee);
	
	@GetMapping("task/active")
	WorkflowResponse getActiveTaskInstances(@RequestParam(required = true) String assignee);
	
}
