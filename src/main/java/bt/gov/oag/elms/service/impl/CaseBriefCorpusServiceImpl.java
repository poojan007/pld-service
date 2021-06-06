package bt.gov.oag.elms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bt.gov.oag.elms.client.WorkflowProcess;
import bt.gov.oag.elms.entity.CaseBrief;
import bt.gov.oag.elms.entity.ConflictOfInterest;
import bt.gov.oag.elms.pojo.CaseBriefResponse;
import bt.gov.oag.elms.repository.CaseBriefRepository;
import bt.gov.oag.elms.repository.ConflictOfInterestRepository;
import bt.gov.oag.elms.service.CaseBriefCorpusService;

@Service
public class CaseBriefCorpusServiceImpl implements CaseBriefCorpusService {

	@Autowired
	WorkflowProcess workflowprocess;

	@Autowired
	private CaseBriefRepository caseBriefRepository;

	@Autowired
	private ConflictOfInterestRepository conflictOfInterestRepository;

	@Override
	public ResponseEntity<CaseBriefResponse> saveCaseBriefDetails(CaseBrief entity, String taskInstanceId,
			String decision_key, String category) {

		Map<String, Object> body = new HashMap<String, Object>();

		CaseBriefResponse caseApiResponse = new CaseBriefResponse();

		try {
			String assignee = String.valueOf(entity.getUpdatedBy());

			body.put(decision_key, category);
			body.put("assigneeProsecutor", assignee);
			workflowprocess.completeTask(taskInstanceId, body);
			caseBriefRepository.save(entity);

			caseApiResponse.setMessage("Insertion Successfull");

			return new ResponseEntity<CaseBriefResponse>(caseApiResponse, HttpStatus.OK);
		} catch (Exception e) {

			caseApiResponse.setMessage("Insertion Failed");
			caseApiResponse.setException(e.getMessage());

			return new ResponseEntity<CaseBriefResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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

}
