package bt.gov.oag.elms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.CaseBrief;
import bt.gov.oag.elms.entity.ConflictOfInterest;
import bt.gov.oag.elms.pojo.CaseBriefResponse;

public interface CaseBriefCorpusService {

	ResponseEntity<CaseBriefResponse> saveCaseBriefDetails(CaseBrief entity, String taskInstanceId, String decision_key,
			String category); 
	ResponseEntity<List<CaseBrief>> getCaseBriefDetails(); 
	ResponseEntity<ConflictOfInterest> saveConflictOfInterestDetails(ConflictOfInterest entity); 
	ResponseEntity<List<ConflictOfInterest>> getConflictOfInterestDetails();

}
