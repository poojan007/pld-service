package bt.gov.oag.elms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.CaseBrief;
import bt.gov.oag.elms.entity.ConflictOfInterest;
import bt.gov.oag.elms.pojo.CaseBriefRequest;

public interface CaseBriefCorpusService {

	ResponseEntity<CaseBrief> saveCaseBriefDetails(CaseBriefRequest request, String taskInstanceId, HttpServletRequest req); 
	ResponseEntity<CaseBrief> getCaseBriefDetails(Long incomingLetterId); 
	ResponseEntity<ConflictOfInterest> saveConflictOfInterestDetails(ConflictOfInterest entity); 
	ResponseEntity<List<ConflictOfInterest>> getConflictOfInterestDetails();

}
