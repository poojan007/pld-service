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
import bt.gov.oag.elms.entity.CaseBrief;
import bt.gov.oag.elms.entity.ConflictOfInterest;
import bt.gov.oag.elms.exception.RecordNotFoundException;
import bt.gov.oag.elms.pojo.CaseBriefRequest;
import bt.gov.oag.elms.repository.CaseBriefRepository;
import bt.gov.oag.elms.repository.ConflictOfInterestRepository;
import bt.gov.oag.elms.service.CaseBriefCorpusService;

@Service
public class CaseBriefCorpusServiceImpl implements CaseBriefCorpusService {

	@Autowired
	WorkflowProcess workflowService;

	@Autowired
	private CaseBriefRepository caseBriefRepository;

	@Autowired
	private ConflictOfInterestRepository conflictOfInterestRepository;
	
	@Autowired
	private TaskUtil taskUtilService;

	@Override
	public ResponseEntity<CaseBrief> saveCaseBriefDetails(CaseBriefRequest request, String taskInstanceId, HttpServletRequest req) {
		HttpHeaders responseHeaders = new HttpHeaders();
		
		CaseBrief caseBrief = new CaseBrief();
		BeanUtils.copyProperties(request, caseBrief);
		
		CaseBrief newCaseBrief = caseBriefRepository.saveAndFlush(caseBrief);
		responseHeaders.set("Location", urlHelper(newCaseBrief, req));
		
		// Complete the task
		workflowService.completeTask(taskInstanceId, taskUtilService.processTaskVariables(request.getTaskVariables()));
		
		return new ResponseEntity<CaseBrief>(newCaseBrief, responseHeaders, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<CaseBrief>> getCaseBriefDetails() {
		return new ResponseEntity<List<CaseBrief>>(caseBriefRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ConflictOfInterest> saveConflictOfInterestDetails(ConflictOfInterest entity) {
		return new ResponseEntity<ConflictOfInterest>(conflictOfInterestRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<ConflictOfInterest>> getConflictOfInterestDetails() {
		return new ResponseEntity<List<ConflictOfInterest>>(conflictOfInterestRepository.findAll(), HttpStatus.OK);
	}

	private String urlHelper(CaseBrief caseBrief, HttpServletRequest request) {
		StringBuilder resourcePath = new StringBuilder();

		resourcePath.append(request.getRequestURL());
		resourcePath.append("/");
		resourcePath.append(caseBrief.getId());

		return resourcePath.toString();
	}
	
	private CaseBrief findIfExists(Long id) {
		Optional<CaseBrief> existingCaseBrief = caseBriefRepository.findById(id);

		if (existingCaseBrief.isPresent()) {
			return existingCaseBrief.get();
		} else {
			throw new RecordNotFoundException("Case brief with id: " + id + " doesnot exist");
		}
	}
}
