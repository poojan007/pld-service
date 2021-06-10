package bt.gov.oag.elms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import bt.gov.oag.elms.entity.CourtHearing;
import bt.gov.oag.elms.entity.HearingStage;
import bt.gov.oag.elms.entity.JudgementReport;
import bt.gov.oag.elms.pojo.CaseInformationRequest;
import bt.gov.oag.elms.pojo.CaseInformationResponse;
import bt.gov.oag.elms.pojo.CaseJudgementRequest;
import bt.gov.oag.elms.pojo.CaseJudgementResponse;

public interface FileCaseService { 
	
	ResponseEntity<Object> uploadFile(MultipartFile file);  
	CaseInformationResponse updateCaseInformationDetail(CaseInformationRequest caseRequest, String taskInstanceId); 
	ResponseEntity<List<HearingStage>> getAllHearingStage();
	ResponseEntity<CourtHearing> saveCourtHearing(CourtHearing hearingRequest);
	ResponseEntity<CourtHearing> getHearingByDefendant(Long defendant_id);
	ResponseEntity<CaseJudgementResponse> saveCaseJudgement(CaseJudgementRequest caseJudgementRequest);
	ResponseEntity<CaseJudgementResponse> saveJudgementReport(CaseJudgementRequest judgementReport,String taskInstanceId);
	CaseJudgementResponse sendToAgency(CaseJudgementRequest judgementReport,String taskInstanceId); 

}
