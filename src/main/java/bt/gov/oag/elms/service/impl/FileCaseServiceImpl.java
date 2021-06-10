package bt.gov.oag.elms.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bt.gov.oag.elms.TaskUtil;
import bt.gov.oag.elms.client.WorkflowProcess;
import bt.gov.oag.elms.entity.CaseInformation;
import bt.gov.oag.elms.entity.CaseJudgement;
import bt.gov.oag.elms.entity.CourtHearing;
import bt.gov.oag.elms.entity.HearingStage;
import bt.gov.oag.elms.entity.JudgementReport;
import bt.gov.oag.elms.pojo.CaseBriefResponse;
import bt.gov.oag.elms.pojo.CaseInformationRequest;
import bt.gov.oag.elms.pojo.CaseInformationResponse;
import bt.gov.oag.elms.pojo.CaseJudgementRequest;
import bt.gov.oag.elms.pojo.CaseJudgementResponse;
import bt.gov.oag.elms.repository.CaseInformationRepository;
import bt.gov.oag.elms.repository.CaseJudgementRepo;
import bt.gov.oag.elms.repository.CourtHearingRepo;
import bt.gov.oag.elms.repository.HearingStageRepo;
import bt.gov.oag.elms.repository.JudgementReportRepo;
import bt.gov.oag.elms.service.FileCaseService;

@Service
public class FileCaseServiceImpl implements FileCaseService {

	@Value("${spring.servlet.multipart.location}")
	String FILE_DIRECTORY;

	@Autowired
	CaseInformationRepository caseInformationRepository;

	@Autowired
	private WorkflowProcess workflowService;

	@Autowired
	private TaskUtil taskUtilService;

	@Override
	public ResponseEntity<Object> uploadFile(MultipartFile file) {

		HttpStatus httpResponse = null; 
		try { 
			File myFile = new File(this.FILE_DIRECTORY + file.getOriginalFilename());
			myFile.createNewFile();
			java.io.FileOutputStream fos = new FileOutputStream(myFile);
			fos.write(file.getBytes());
			fos.close(); 
			httpResponse = HttpStatus.OK; 
		} catch (Exception e) {
			httpResponse = HttpStatus.INTERNAL_SERVER_ERROR;
		} 
		return new ResponseEntity<Object>(httpResponse);
	}

	@Override
	public CaseInformationResponse updateCaseInformationDetail(CaseInformationRequest caseRequest,
			String taskInstanceId) {
		
		CaseInformationResponse caseInformationResponse = new CaseInformationResponse();
		CaseInformation updateCaseInformation = new CaseInformation();

		BeanUtils.copyProperties(caseRequest, updateCaseInformation);

		try {  
			 
			caseInformationRepository.save(updateCaseInformation);

			caseInformationResponse.setMessage("Success");
			caseInformationResponse.setStatus(HttpStatus.OK);
			
			//workflowService.completeTask(taskInstanceId,taskUtilService.processTaskVariables(caseRequest.getTaskVariables()));

		} catch (Exception e) { 
			caseInformationResponse.setMessage("Error");
			caseInformationResponse.setStatus(HttpStatus.OK);
		
		}

		return caseInformationResponse;
	}

	@Autowired
	HearingStageRepo hearingStageRepo;
	
	@Autowired
	CourtHearingRepo courtHearingRepo;
	
	@Override
	public ResponseEntity<List<HearingStage>> getAllHearingStage() { 
		return new ResponseEntity<List<HearingStage>>(hearingStageRepo.findAll(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CourtHearing> saveCourtHearing(CourtHearing hearingRequest) {
		 return new ResponseEntity<CourtHearing>(courtHearingRepo.save(hearingRequest),HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CourtHearing> getHearingByDefendant(Long defendant_id) {
		return new ResponseEntity<CourtHearing>(courtHearingRepo.findByDefendantId(defendant_id),HttpStatus.OK);
	}

	@Autowired
	CaseJudgementRepo caseJudgementRepo;
	
	@Autowired
	JudgementReportRepo judgementReportRepo;
	 
	@Override
	public ResponseEntity<CaseJudgementResponse> saveCaseJudgement(CaseJudgementRequest caseJudgementRequest) {
		
		CaseJudgementResponse caseJudgementResponse = new CaseJudgementResponse(); 
		CaseJudgement saveJudgement = new CaseJudgement();
		
		try {
			BeanUtils.copyProperties(caseJudgementRequest, saveJudgement);
			caseJudgementRepo.save(saveJudgement);
			caseJudgementResponse.setMessage("Successfully Added"); 
			return new ResponseEntity<CaseJudgementResponse>(caseJudgementResponse,HttpStatus.OK);
		} catch (Exception e) {
			caseJudgementResponse.setMessage("Error Occured");
			return new ResponseEntity<CaseJudgementResponse>(caseJudgementResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<CaseJudgementResponse> saveJudgementReport(CaseJudgementRequest judgementReport,String taskInstanceId) {
		
		CaseJudgementResponse caseJudgementResponse = new CaseJudgementResponse(); 
		JudgementReport saveJudgementReport = new JudgementReport();
		
		try {
			BeanUtils.copyProperties(judgementReport, saveJudgementReport);
			judgementReportRepo.save(saveJudgementReport);
			
			caseJudgementResponse.setMessage("Successfully Added"); 
			
			//      workflowService.completeTask(taskInstanceId, taskUtilService.processTaskVariables(judgementReport.getTaskVariables()));

			
			return new ResponseEntity<CaseJudgementResponse>(caseJudgementResponse,HttpStatus.OK);
		} catch (Exception e) {
			
			caseJudgementResponse.setMessage("Error Occured");
			return new ResponseEntity<CaseJudgementResponse>(caseJudgementResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public CaseJudgementResponse sendToAgency(CaseJudgementRequest sendAgency,String taskInstanceId) {

		CaseJudgementResponse caseJudgementResponse = new CaseJudgementResponse();
		
		try {
			
			
			workflowService.completeTask(taskInstanceId, taskUtilService.processTaskVariables(sendAgency.getTaskVariables()));

			caseJudgementResponse.setMessage("Success");
			caseJudgementResponse.setStatus(HttpStatus.OK);
		} catch (Exception e) {

			caseJudgementResponse.setMessage("Failed");
			caseJudgementResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); 
			 
		}
		return caseJudgementResponse;
	} 

}
