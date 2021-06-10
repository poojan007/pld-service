package bt.gov.oag.elms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import bt.gov.oag.elms.entity.CaseJudgement;
import bt.gov.oag.elms.entity.CourtHearing;
import bt.gov.oag.elms.entity.HearingStage;
import bt.gov.oag.elms.entity.JudgementReport;
import bt.gov.oag.elms.pojo.CaseInformationRequest;
import bt.gov.oag.elms.pojo.CaseInformationResponse;
import bt.gov.oag.elms.pojo.CaseJudgementRequest;
import bt.gov.oag.elms.pojo.CaseJudgementResponse;
import bt.gov.oag.elms.pojo.TaskInstanceDetail;
import bt.gov.oag.elms.pojo.TaskVariables;

public interface FileCaseService { 
	
	ResponseEntity<Object> uploadFile(MultipartFile file);  
	CaseInformationResponse updateCaseInformationDetail(CaseInformationRequest caseRequest, String taskInstanceId); 
	ResponseEntity<List<HearingStage>> getAllHearingStage();
	ResponseEntity<CourtHearing> saveCourtHearing(CourtHearing hearingRequest);
	ResponseEntity<CourtHearing> getHearingByDefendant(Long defendant_id);
	ResponseEntity<CaseJudgementResponse> saveCaseJudgement(CaseJudgementRequest caseJudgementRequest,String taskInstanceId);
	ResponseEntity<CaseJudgementResponse> saveJudgementReport(CaseJudgementRequest judgementReport,String taskInstanceId);
	CaseJudgementResponse sendToAgency(CaseJudgementRequest judgementReport,String taskInstanceId);
	CaseJudgementResponse taskCompleted(String assignee, String taskInstanceId);
	CaseJudgement getCaseJudgementByCaseId(Long caseId);
	CaseJudgementResponse decisionForCorpusMeeting(List<TaskVariables> taskTaskVariables, String taskInstanceId); 

}
