package bt.gov.oag.elms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bt.gov.oag.elms.client.WorkflowProcess;
import bt.gov.oag.elms.entity.CaseInformation;
import bt.gov.oag.elms.entity.DefendantInformation;
import bt.gov.oag.elms.entity.IncomingAttachmentMapping;
import bt.gov.oag.elms.entity.IncomingLetter;
import bt.gov.oag.elms.pojo.CaseApiResponse;
import bt.gov.oag.elms.pojo.TaskInstanceDetail;
import bt.gov.oag.elms.pojo.WorkflowResponse;
import bt.gov.oag.elms.repository.CaseInformationRepository;
import bt.gov.oag.elms.repository.DefendantInformationRepository;
import bt.gov.oag.elms.repository.IncomingAttachmentMappingRepository;
import bt.gov.oag.elms.repository.IncomingLetterRepository;
import bt.gov.oag.elms.service.CaseService;

@Service
public class CaseServiceImpl implements CaseService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	Map<String, Object> body = new HashMap<String, Object>();

	@Autowired
	private IncomingLetterRepository incomingLetterRepository;

	@Autowired
	private CaseInformationRepository caseInformationRepository;

	@Autowired
	private DefendantInformationRepository defendantInformationRepository;

	@Autowired
	private IncomingAttachmentMappingRepository incomingAttachmentMappingRepository;

	@Autowired
	WorkflowProcess workflowprocess;

	@Override
	public CaseApiResponse saveIncomingLetter(IncomingLetter entity) {

		CaseApiResponse apiResponse = new CaseApiResponse();

		try {
			incomingLetterRepository.save(entity);
			apiResponse.setMessage("Success");
			apiResponse.setStatus(HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Post Incoming Letter: ", e);
			apiResponse.setMessage("Insertion failed");
			apiResponse.setException(e.getMessage());
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return apiResponse;
	}

	@Override
	public ResponseEntity<IncomingAttachmentMapping> saveIncomingAttachmentMapping(IncomingAttachmentMapping entity) {
		return new ResponseEntity<IncomingAttachmentMapping>(incomingAttachmentMappingRepository.save(entity),
				HttpStatus.OK);
	}

	@Override
	public List<CaseApiResponse> getIncomingLetterList() {
		String letter_status = "Y";
		List<CaseApiResponse> caseApiResponses = new ArrayList<>();
		List<IncomingLetter> apiResponse = new ArrayList<>();
		try {
			apiResponse = incomingLetterRepository.findByLetterStatus(letter_status);
			
			for (IncomingLetter task : apiResponse) { 
				CaseApiResponse caseResponse = new CaseApiResponse(); 
				BeanUtils.copyProperties(task, caseResponse);
				caseResponse.setAgencyName(task.getGetAgency().getAgencyName());
				caseResponse.setFileCategoryName(task.getGetFileCategory().getFileCategory());  

				caseApiResponses.add(caseResponse);
			}
			
		} catch (Exception e) {
			LOGGER.error("Get Incoming Letter: ", e);
		}
		return caseApiResponses;
	}

	@Override
	public CaseApiResponse updateIncomingLetter(IncomingLetter entity, Long id) {

		CaseApiResponse apiResponse = new CaseApiResponse();

		try {
			entity.setId(id);
			incomingLetterRepository.save(entity);
			apiResponse.setMessage("Updated Successfull");
			apiResponse.setStatus(HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Post Incoming Letter: ", e);
			apiResponse.setMessage("Update failed");
			apiResponse.setException(e.getMessage());
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return apiResponse;
	}

	@Override
	public CaseApiResponse logicalDeleteIncomingLetter(Long id) {

		CaseApiResponse apiResponse = new CaseApiResponse();
		IncomingLetter entity = new IncomingLetter();

		try {
			entity = incomingLetterRepository.getById(id);
			entity.setId(id);
			entity.setLetterStatus("Closed");
			incomingLetterRepository.save(entity);
			apiResponse.setMessage("Deleted Successfully");
			apiResponse.setStatus(HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Delete Incoming Letter: ", e);
			apiResponse.setMessage("Delete failed");
			apiResponse.setException(e.getMessage());
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return apiResponse;
	}

	@Override
	public CaseApiResponse removeDeleteIncomingLetter(Long id) {

		CaseApiResponse apiResponse = new CaseApiResponse();

		try {

			incomingLetterRepository.deleteById(id);
			apiResponse.setMessage("Removed from database");
			apiResponse.setStatus(HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Removing Incoming Letter from database: ", e);
			apiResponse.setMessage("reomved from database failed");
			apiResponse.setException(e.getMessage());
			apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return apiResponse;
	}

	@Override
	public CaseApiResponse startCaseProcess(Long id) {

		CaseApiResponse caseResponse = new CaseApiResponse();
		WorkflowResponse workflowResponse = new WorkflowResponse();
		IncomingLetter incomingLetterData = new IncomingLetter();

		try {
			incomingLetterData = incomingLetterRepository.findById(id).orElse(null);
			workflowResponse = workflowprocess.startProcess(incomingLetterData.getGetFileCategory().getAssignee(),
					incomingLetterData.getGetFileCategory().getProcessKey());

			Object processInstanceId = workflowResponse.getData();
			incomingLetterData.setProccessId(processInstanceId.toString());
			incomingLetterData.setLetterStatus("F");

			IncomingLetter letterAfterUpdate = incomingLetterRepository.save(incomingLetterData);

			BeanUtils.copyProperties(letterAfterUpdate, caseResponse);

			caseResponse.setMessage("Process Started");

		} catch (Exception e) {
			caseResponse.setMessage("Start Process failed");
			caseResponse.setException(e.getMessage());
			caseResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return caseResponse;
	}

	@Override
	public CaseApiResponse getIncomingLetterById(Long id) {

		CaseApiResponse caseApiResponse = new CaseApiResponse();
		IncomingLetter incomingLetter = incomingLetterRepository.findById(id).orElse(null);

		caseApiResponse.setMessage("Successfull");
		caseApiResponse.setStatus(HttpStatus.OK);

		try {
			BeanUtils.copyProperties(incomingLetter, caseApiResponse);
		} catch (Exception e) {
			caseApiResponse.setMessage("Get Incoming Letter By Id Failed");
			caseApiResponse.setException(e.getMessage());
			caseApiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return caseApiResponse;
	}

	@Override
	public ResponseEntity<List<CaseApiResponse>> getUserPendingTask(int user_id) {

		ObjectMapper mapper = new ObjectMapper();
		String assignee = String.valueOf(user_id);

		List<CaseApiResponse> caseApiResponse = new ArrayList<CaseApiResponse>();
		WorkflowResponse workflowResponse = new WorkflowResponse();

		try {

			workflowResponse = workflowprocess.userPendingTask(assignee);

			List<TaskInstanceDetail> tasks = mapper.convertValue(workflowResponse.getData(),
					new TypeReference<List<TaskInstanceDetail>>() {
					});

			for (TaskInstanceDetail task : tasks) {
				IncomingLetter incomingLetter = incomingLetterRepository.findByProccessId(task.getProcessInstanceId());
				CaseApiResponse caseResponse = new CaseApiResponse();

				BeanUtils.copyProperties(incomingLetter, caseResponse);
				caseResponse.setAgencyName(incomingLetter.getGetAgency().getAgencyName());
				caseResponse.setFileCategoryName(incomingLetter.getGetFileCategory().getFileCategory());
				caseResponse.setTaskIntanceId(task.getId());
				caseResponse.setCaseStatus(task.getName());

				caseApiResponse.add(caseResponse);
			}

			return new ResponseEntity<List<CaseApiResponse>>(caseApiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<CaseApiResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CaseApiResponse> updateUserTask(IncomingLetter entity, String taskInstanceId) {

		String assignee = String.valueOf(entity.getForwardedTo());
		CaseApiResponse caseApiResponse = new CaseApiResponse();

		try {

			body.put("assignee", assignee);
			workflowprocess.completeTask(taskInstanceId, body);

			incomingLetterRepository.save(entity);
			caseApiResponse.setMessage("Task updated");

			return new ResponseEntity<CaseApiResponse>(caseApiResponse, HttpStatus.OK);

		} catch (Exception e) {
			caseApiResponse.setMessage("Task updated failed");
			caseApiResponse.setException(e.getMessage());
			return new ResponseEntity<CaseApiResponse>(caseApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CaseApiResponse> assignCaseToRegister(CaseApiResponse caseResponse, String taskInstanceId) {

		IncomingLetter saveEntity = new IncomingLetter();

		try {
			body.put("assignee", caseResponse.getAssignee());
			body.put("assigneeProsecutor", caseResponse.getForwardedTo());
			body.put("hasFollowedDueProcess", true);

			workflowprocess.completeTask(taskInstanceId, body);

			BeanUtils.copyProperties(caseResponse, saveEntity);

			incomingLetterRepository.save(saveEntity);
			caseResponse.setMessage("Task updated");

			return new ResponseEntity<CaseApiResponse>(caseResponse, HttpStatus.OK);
		} catch (Exception e) {

			caseResponse.setMessage("Task updated failed");
			caseResponse.setException(e.getMessage());

			return new ResponseEntity<CaseApiResponse>(caseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<CaseApiResponse>> getCaseUnderReviewDetails(int user_id) {

		List<CaseApiResponse> caseApiResponse = new ArrayList<CaseApiResponse>();
		WorkflowResponse workflowResponse = new WorkflowResponse();
		ObjectMapper mapper = new ObjectMapper();

		String assignee = String.valueOf(user_id);

		try {
			workflowResponse = workflowprocess.userPendingTask(assignee);

			List<TaskInstanceDetail> tasks = mapper.convertValue(workflowResponse.getData(),
					new TypeReference<List<TaskInstanceDetail>>() {
					});

			for (TaskInstanceDetail task : tasks) {

				IncomingLetter incomingLetter = incomingLetterRepository.findByProccessId(task.getProcessInstanceId());
				CaseApiResponse caseResponse = new CaseApiResponse();

				BeanUtils.copyProperties(incomingLetter, caseResponse);
				caseResponse.setAgencyName(incomingLetter.getGetAgency().getAgencyName());
				caseResponse.setFileCategoryName(incomingLetter.getGetFileCategory().getFileCategory());
				caseResponse.setTaskIntanceId(task.getId());
				caseResponse.setCaseStatus(task.getName());

				Long incoming_letter_id = incomingLetter.getId();

				CaseInformation caseInformation = caseInformationRepository.findByIncomingLetterId(incoming_letter_id);

				BeanUtils.copyProperties(caseInformation, caseResponse);
				caseResponse.setJurisdictionName(caseInformation.getGetJurisdiction().getJurisdictionName());

				DefendantInformation defendantInformation = defendantInformationRepository
						.findByIncomingLetterId(incoming_letter_id);
				BeanUtils.copyProperties(defendantInformation, caseResponse);

				caseApiResponse.add(caseResponse);
			}

			return new ResponseEntity<List<CaseApiResponse>>(caseApiResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<CaseApiResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CaseApiResponse updateCaseStatus(Long id, String letterStatus) {
		
		CaseApiResponse caseApiResponse = new CaseApiResponse();
		IncomingLetter incomingLetter = new IncomingLetter();
		
		try {
			incomingLetter = incomingLetterRepository.findById(id).orElse(null);
			incomingLetter.setLetterStatus(letterStatus); 
			incomingLetterRepository.save(incomingLetter);
		} catch (Exception e) {
			caseApiResponse.setMessage("Task updated failed");
			caseApiResponse.setException(e.getMessage());
			 
		}
		
		return caseApiResponse;
	}

}
