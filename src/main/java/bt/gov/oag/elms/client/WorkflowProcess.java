package bt.gov.oag.elms.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import bt.gov.oag.elms.pojo.WorkflowResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WorkflowProcess {

	private final ActivitiClient activitiClient;
//	private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;
	// private final ExecutorService traceableExecutorService;

	public WorkflowResponse startProcess(int assignee, String processId) {

		// circuitBreakerFactory.configureExecutorService(traceableExecutorService);
		// Resilience4JCircuitBreaker circuitBreaker =
		// circuitBreakerFactory.create("workflow");

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("assignee", assignee);

		// java.util.function.Supplier<WorkflowResponse> processResponse = () ->
		// activitiClient.startProcess(processId, body);
		// WorkflowResponse apiResponse = circuitBreaker.run(processResponse, null);
		WorkflowResponse apiResponse = activitiClient.startProcess(processId, body);

		return apiResponse;
	}

	public WorkflowResponse userPendingTask(String assignee) {

		// circuitBreakerFactory.configureExecutorService(traceableExecutorService);
		// Resilience4JCircuitBreaker circuitBreaker =
		// circuitBreakerFactory.create("workflow");

//		Map<String, Object> body = new HashMap<String, Object>();
//		body.put("assignee", assignee);  

		// java.util.function.Supplier<WorkflowResponse> processResponse = () ->
		// activitiClient.startProcess(processId, body);
		// WorkflowResponse apiResponse = circuitBreaker.run(processResponse, null);
		WorkflowResponse apiResponse = activitiClient.getActiveTaskInstances(assignee);

		return apiResponse;
	}
	
	public WorkflowResponse completeTask(String taskInstanceId,Map<String, Object> taskInstanceVariables) {

		// circuitBreakerFactory.configureExecutorService(traceableExecutorService);
		// Resilience4JCircuitBreaker circuitBreaker =
		// circuitBreakerFactory.create("workflow");

//		Map<String, Object> body = new HashMap<String, Object>();
//		body.put("assignee", assignee);  

		// java.util.function.Supplier<WorkflowResponse> processResponse = () ->
		// activitiClient.startProcess(processId, body);
		// WorkflowResponse apiResponse = circuitBreaker.run(processResponse, null);
		WorkflowResponse apiResponse = activitiClient.completeTask(taskInstanceId, taskInstanceVariables);

		return apiResponse;
	}
	
	public WorkflowResponse getCompletedTaskInstances(String assignee) {

		// circuitBreakerFactory.configureExecutorService(traceableExecutorService);
		// Resilience4JCircuitBreaker circuitBreaker =
		// circuitBreakerFactory.create("workflow");

//		Map<String, Object> body = new HashMap<String, Object>();
//		body.put("assignee", assignee);  

		// java.util.function.Supplier<WorkflowResponse> processResponse = () ->
		// activitiClient.startProcess(processId, body);
		// WorkflowResponse apiResponse = circuitBreaker.run(processResponse, null);
		WorkflowResponse apiResponse = activitiClient.getCompletedTaskInstances(assignee);

		return apiResponse;
	}


}
