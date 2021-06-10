package bt.gov.oag.elms.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bt.gov.oag.elms.TaskUtil;
import bt.gov.oag.elms.client.WorkflowProcess;
import bt.gov.oag.elms.entity.GeneralCorpus;
import bt.gov.oag.elms.exception.RecordNotFoundException;
import bt.gov.oag.elms.pojo.GeneralCorpusRequest;
import bt.gov.oag.elms.pojo.TaskVariables;
import bt.gov.oag.elms.repository.GeneralCorpusRepository;
import bt.gov.oag.elms.service.GeneralCorpusService;

@Service
public class GeneralCorpusServiceImpl implements GeneralCorpusService {

	@Autowired
	private GeneralCorpusRepository repo;
	
	@Autowired
	private WorkflowProcess workflowService;
	
	@Autowired
	private TaskUtil taskUtilService;

	@Override
	public ResponseEntity<GeneralCorpus> getGeneralCorpusDetailsById(Long id) {
		GeneralCorpus generalCorpus = findIfExists(id);
		return new ResponseEntity<GeneralCorpus>(generalCorpus, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<GeneralCorpus> createNewGeneralCorpus(GeneralCorpusRequest generalCorpusRequest, String taskInstanceId, HttpServletRequest request) {
		HttpHeaders responseHeaders = new HttpHeaders();
		
		GeneralCorpus generalCorpus = new GeneralCorpus();
		BeanUtils.copyProperties(generalCorpusRequest, generalCorpus);
		
		GeneralCorpus newGeneralCorpus = repo.saveAndFlush(generalCorpus);
		responseHeaders.set("Location", urlHelper(newGeneralCorpus, request));
		
		// Complete the task
		workflowService.completeTask(taskInstanceId, taskUtilService.processTaskVariables(generalCorpusRequest.getTaskVariables()));
		
		return new ResponseEntity<GeneralCorpus>(newGeneralCorpus, responseHeaders, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<?> approveCorpusMeetingRequest(List<TaskVariables> taskVariables, String taskInstanceId) {
		// Complete the task
		workflowService.completeTask(taskInstanceId, taskUtilService.processTaskVariables(taskVariables));
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	private String urlHelper(GeneralCorpus generalCorpus, HttpServletRequest request) {
		StringBuilder resourcePath = new StringBuilder();

		resourcePath.append(request.getRequestURL());
		resourcePath.append("/");
		resourcePath.append(generalCorpus.getId());

		return resourcePath.toString();
	}
	
	private GeneralCorpus findIfExists(Long id) {
		Optional<GeneralCorpus> existingGeneralCorpus = repo.findById(id);

		if (existingGeneralCorpus.isPresent()) {
			return existingGeneralCorpus.get();
		} else {
			throw new RecordNotFoundException("General corpus with id: " + id + " doesnot exist");
		}
	}
}
