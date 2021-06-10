package bt.gov.oag.elms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bt.gov.oag.elms.client.WorkflowProcess;
import bt.gov.oag.elms.entity.CaseInformation;
import bt.gov.oag.elms.entity.CourtHearing;
import bt.gov.oag.elms.entity.DefendantInformation;
import bt.gov.oag.elms.entity.IncomingLetter;
import bt.gov.oag.elms.entity.InvestigatingOfficer;
import bt.gov.oag.elms.entity.PoliceStation;
import bt.gov.oag.elms.entity.VictimInformation;
import bt.gov.oag.elms.pojo.CaseApiResponse;
import bt.gov.oag.elms.pojo.InvestigatingOfficerResponse;
import bt.gov.oag.elms.pojo.CourtHearingResponse;
import bt.gov.oag.elms.pojo.DefendantResponse;
import bt.gov.oag.elms.pojo.TaskInstanceDetail;
import bt.gov.oag.elms.repository.CaseInformationRepository;
import bt.gov.oag.elms.repository.CourtHearingRepo;
import bt.gov.oag.elms.repository.DefendantInformationRepository;
import bt.gov.oag.elms.repository.IncomingLetterRepository;
import bt.gov.oag.elms.repository.InvestigatingOfficerRepository;
import bt.gov.oag.elms.repository.PoliceStationRepository;
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
	
	@Autowired
	private PoliceStationRepository policeStationRepo;

	@Autowired
	private CourtHearingRepo courtHearingRepo;

	@Override
	public ResponseEntity<CaseInformation> saveCaseInformation(CaseInformation entity) {
	
		return new ResponseEntity<CaseInformation>(caseInformationRepository.save(entity), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CaseInformation> getCaseInformationByCaseId(Long incoming_letter_id) {
		CaseInformation caseInformation = caseInformationRepository.findByIncomingLetterId(incoming_letter_id);
		return new ResponseEntity<CaseInformation>(caseInformation, HttpStatus.OK);
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
	public List<DefendantResponse> getDefendantInformationByCaseId(Long incoming_letter_id) {
		
		CourtHearingResponse coutCourtHearingResponses = new CourtHearingResponse();
		List<DefendantResponse> defendantResponseList = new ArrayList<>();
		List<DefendantInformation> defendantInformations = defendantInformationRepository
				.findByIncomingLetterIdList(incoming_letter_id);

		try {
			
			for (DefendantInformation defendant : defendantInformations) {

				defendant = defendantInformationRepository.findByIncomingLetterId(incoming_letter_id);
				DefendantResponse defendantResponse = new DefendantResponse();
				BeanUtils.copyProperties(defendant, defendantResponse);

//				CourtHearing courtHearing = courtHearingRepo.findByDefendantId(defendant.getId());
//				
//				if(courtHearing != null) {
//					BeanUtils.copyProperties(courtHearing, coutCourtHearingResponses); 
//					defendantResponse.setCourtHearing(coutCourtHearingResponses); 
//				}
				
				defendantResponseList.add(defendantResponse);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return defendantResponseList;
	}

//	public ResponseEntity<List<DefendantInformation>> getDefendantInformationByCaseId(Long incoming_letter_id) {
//		return new ResponseEntity<List<DefendantInformation>>(
//				defendantInformationRepository.findByIncomingLetterId(incoming_letter_id), HttpStatus.OK);
//	}

	@Override
	public ResponseEntity<CaseApiResponse> saveInvestigatingInformation(InvestigatingOfficer entity,
			String taskInstanceId) {

		IncomingLetter incomingLetter = new IncomingLetter();
		CaseApiResponse caseApiResponse = new CaseApiResponse();

		try {
			incomingLetter = incomingLetterRepository.findById(entity.getIncomingLetterId()).orElse(null);
			incomingLetter.setCaseDataExist(Long.parseLong("1"));
	
			String assignee = String.valueOf(incomingLetter.getForwardedTo());

			body.put("assigneeProsecutor", assignee);

			workflowprocess.completeTask(taskInstanceId, body);

			investigatingOfficerRepository.save(entity);
			incomingLetterRepository.save(incomingLetter);
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
	public ResponseEntity<InvestigatingOfficerResponse> getInvestigatingInformationByCaseId(Long incoming_letter_id) {
		InvestigatingOfficer investigatingOfficer = investigatingOfficerRepository.findByIncomingLetterId(incoming_letter_id);
		PoliceStation policeStation = policeStationRepo.findById(investigatingOfficer.getPoliceStation()).get();
		
		InvestigatingOfficerResponse response = new InvestigatingOfficerResponse();
		BeanUtils.copyProperties(investigatingOfficer, response);
		response.setDzongkhagId(policeStation.getDzongkhag().getId());
		response.setPoliceStationId(investigatingOfficer.getPoliceStation());
		response.setPoliceStationName(policeStation.getPoliceStationName());
		
		return new ResponseEntity<InvestigatingOfficerResponse>(response, HttpStatus.OK);
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
	public ResponseEntity<List<VictimInformation>> getVictimInformationByCaseId(Long incoming_letter_id) {
		return new ResponseEntity <List<VictimInformation>>(
				victimInformationRepository.findByIncomingLetterId(incoming_letter_id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CaseApiResponse> saveWorkloadDetails(CaseApiResponse caseRequest, String taskInstanceId,
			String decision_key, Boolean examine_fact) {

		CaseApiResponse caseResponse = new CaseApiResponse();
		IncomingLetter updateIncomingLetter = new IncomingLetter();

		try {  
			
			body.put(decision_key, examine_fact);
			body.put(caseRequest.getTaskVariables().get(0).getKey(), caseRequest.getTaskVariables().get(0).getValue());
			body.put(caseRequest.getTaskVariables().get(1).getKey(), caseRequest.getTaskVariables().get(1).getValue());
			
			workflowprocess.completeTask(taskInstanceId, body);
			
			BeanUtils.copyProperties(caseRequest, updateIncomingLetter);
			incomingLetterRepository.save(updateIncomingLetter); 
			
			caseResponse.setTaskIntanceId(taskInstanceId);
			return new ResponseEntity<CaseApiResponse>(caseResponse, HttpStatus.OK);

		} catch (Exception e) {

			caseResponse.setMessage("Task updated failed");
			caseResponse.setException(e.getMessage());
			return new ResponseEntity<CaseApiResponse>(caseResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
