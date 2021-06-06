package bt.gov.oag.elms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bt.gov.oag.elms.client.WorkflowProcess;
import bt.gov.oag.elms.entity.CaseInformation;
import bt.gov.oag.elms.entity.DefendantInformation;
import bt.gov.oag.elms.entity.IncomingLetter;
import bt.gov.oag.elms.entity.InvestigatingOfficer;
import bt.gov.oag.elms.entity.VictimInformation;
import bt.gov.oag.elms.pojo.CaseApiResponse;
import bt.gov.oag.elms.repository.CaseInformationRepository;
import bt.gov.oag.elms.repository.DefendantInformationRepository;
import bt.gov.oag.elms.repository.IncomingLetterRepository;
import bt.gov.oag.elms.repository.InvestigatingOfficerRepository;
import bt.gov.oag.elms.repository.VictimInformationRepository;
import bt.gov.oag.elms.service.CaseInformationService;

@Service
public class CaseInformationImpl implements CaseInformationService {

	Map<String, Object> body = new HashMap<String, Object>();

	@Autowired
	WorkflowProcess workflowprocess;

	@Autowired
	private IncomingLetterRepository incomingLetterRepository;

	@Autowired
	private CaseInformationRepository caseInformationRepository;

	@Autowired
	private DefendantInformationRepository defendantInformationRepository;

	@Autowired
	private InvestigatingOfficerRepository investigatingOfficerRepository;

	@Autowired
	private VictimInformationRepository victimInformationRepository;

	@Override
	public ResponseEntity<CaseInformation> saveCaseInformation(CaseInformation entity) {
		return new ResponseEntity<CaseInformation>(caseInformationRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CaseInformation> getCaseInformationByCaseId(Long incoming_letter_id) {
		return new ResponseEntity<CaseInformation>(caseInformationRepository.findByIncomingLetterId(incoming_letter_id),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CaseInformation> getCaseInformationById(Long id) {
		return new ResponseEntity<CaseInformation>(caseInformationRepository.findById(id).orElse(null), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DefendantInformation> saveDefendantInformation(DefendantInformation entity) {
		return new ResponseEntity<DefendantInformation>(defendantInformationRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DefendantInformation> getDefendantInformationById(Long id) {
		return new ResponseEntity<DefendantInformation>(defendantInformationRepository.findById(id).orElse(null),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<DefendantInformation> getDefendantInformationByCaseId(Long incoming_letter_id) {
		return new ResponseEntity<DefendantInformation>(
				defendantInformationRepository.findByIncomingLetterId(incoming_letter_id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CaseApiResponse> saveInvestigatingInformation(InvestigatingOfficer entity,
			String taskInstanceId) {

		IncomingLetter incomingLetter = new IncomingLetter();
		CaseApiResponse caseApiResponse = new CaseApiResponse();

		try {
			incomingLetter = incomingLetterRepository.findById(entity.getIncomingLetterId()).orElse(null);
			String assignee = String.valueOf(incomingLetter.getForwardedTo());

			body.put("assigneeProsecutor", assignee);

			workflowprocess.completeTask(taskInstanceId, body);

			investigatingOfficerRepository.save(entity);
			caseApiResponse.setMessage("Successfully updated");

			return new ResponseEntity<CaseApiResponse>(caseApiResponse, HttpStatus.OK);
		} catch (Exception e) {
			caseApiResponse.setMessage("Task updated failed");
			caseApiResponse.setException(e.getMessage());
			return new ResponseEntity<CaseApiResponse>(caseApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<InvestigatingOfficer> getInvestigatingOfficerInformationById(Long id) {
		return new ResponseEntity<InvestigatingOfficer>(investigatingOfficerRepository.findById(id).orElse(null),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InvestigatingOfficer> getInvestigatingInformationByCaseId(Long incoming_letter_id) {
		return new ResponseEntity<InvestigatingOfficer>(
				investigatingOfficerRepository.findByIncomingLetterId(incoming_letter_id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VictimInformation> saveVictimInformation(VictimInformation entity) {
		return new ResponseEntity<VictimInformation>(victimInformationRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VictimInformation> getVictimInformationById(Long id) {
		return new ResponseEntity<VictimInformation>(victimInformationRepository.findById(id).orElse(null),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VictimInformation> getVictimInformationByCaseId(Long incoming_letter_id) {
		return new ResponseEntity<VictimInformation>(
				victimInformationRepository.findByIncomingLetterId(incoming_letter_id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CaseApiResponse> saveWorkloadDetails(IncomingLetter entity, String taskInstanceId,
			String decision_key, Boolean examine_fact) {

			CaseApiResponse caseApiResponse = new CaseApiResponse();

		try {

			body.put(decision_key, examine_fact);
			body.put("assigneeProsecutor", entity.getForwardedTo());

			workflowprocess.completeTask(taskInstanceId, body);

			IncomingLetter savedEntity = incomingLetterRepository.save(entity);
			BeanUtils.copyProperties(savedEntity, caseApiResponse);
			
			caseApiResponse.setTaskIntanceId(taskInstanceId); 
			return new ResponseEntity<CaseApiResponse>(caseApiResponse, HttpStatus.OK); 
			
		} catch (Exception e) {
			
			caseApiResponse.setMessage("Task updated failed");
			caseApiResponse.setException(e.getMessage());
			return new ResponseEntity<CaseApiResponse>(caseApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}

}