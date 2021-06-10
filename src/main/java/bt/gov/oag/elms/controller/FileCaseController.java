package bt.gov.oag.elms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bt.gov.oag.elms.entity.CaseJudgement;
import bt.gov.oag.elms.entity.CourtHearing;
import bt.gov.oag.elms.entity.HearingStage;
import bt.gov.oag.elms.entity.JudgementReport;
import bt.gov.oag.elms.pojo.CaseInformationRequest;
import bt.gov.oag.elms.pojo.CaseInformationResponse;
import bt.gov.oag.elms.pojo.CaseJudgementRequest;
import bt.gov.oag.elms.pojo.CaseJudgementResponse;
import bt.gov.oag.elms.service.FileCaseService;

@RestController
@RequestMapping("/file-case")
public class FileCaseController {
	
	@Autowired
	FileCaseService fileCaseService;
	
	@PostMapping("/uploadDocFile")
	public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file) throws IOException {
		return fileCaseService.uploadFile(file);
	}
	
	@PostMapping("/update/{taskInstanceId}")
	public CaseInformationResponse uploadPowerOfAttorneyFile(@RequestBody CaseInformationRequest caseRequest,@PathVariable String taskInstanceId){
		return fileCaseService.updateCaseInformationDetail(caseRequest,taskInstanceId);
	}
	
	@GetMapping("/hearingStage")
	public ResponseEntity<List<HearingStage>> getAllHearingStage(){
		return fileCaseService.getAllHearingStage();
	}
	
	@PostMapping("/hearing")
	public ResponseEntity<CourtHearing> saveCourtHearing(@RequestBody CourtHearing hearingRequest){
		return fileCaseService.saveCourtHearing(hearingRequest);
	}
	
	@GetMapping("/hearing-by-defendantId/{defendant_id}")
	public ResponseEntity<CourtHearing> getHearingByDefendant(@PathVariable Long defendant_id){
		return fileCaseService.getHearingByDefendant(defendant_id);
	}
	
	@PostMapping("/saveJudgement")
	public ResponseEntity<CaseJudgementResponse> saveCaseJudgement(@RequestBody CaseJudgementRequest caseJudgementRequest){
		return fileCaseService.saveCaseJudgement(caseJudgementRequest);
	} 
	
	@PostMapping("/saveJudgementReport/{taskInstanceId}")
	public ResponseEntity<CaseJudgementResponse> saveJudgementReport(@RequestBody CaseJudgementRequest judgementReport,@PathVariable String taskInstanceId){
		return fileCaseService.saveJudgementReport(judgementReport,taskInstanceId);
	} 
	
	@PostMapping("/sendToAgency/{taskInstanceId}")
	public CaseJudgementResponse sendToAgency(@RequestBody CaseJudgementRequest sendMessage,@PathVariable String taskInstanceId){
		return fileCaseService.sendToAgency(sendMessage,taskInstanceId);
	} 
	
	   
}
