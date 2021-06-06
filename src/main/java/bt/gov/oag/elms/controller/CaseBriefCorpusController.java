package bt.gov.oag.elms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bt.gov.oag.elms.entity.CaseBrief;
import bt.gov.oag.elms.entity.ConflictOfInterest;
import bt.gov.oag.elms.pojo.CaseBriefResponse;
import bt.gov.oag.elms.service.CaseBriefCorpusService;

@RestController
@RequestMapping("/case-brief")
public class CaseBriefCorpusController {

	@Autowired
	private CaseBriefCorpusService caseBriefCorpusService;

	/* Case Brief */
	@PostMapping("/details/{taskInstanceId}/{decision_key}/{category}")
	public ResponseEntity<CaseBriefResponse> saveCaseBriefDetails(@RequestBody CaseBrief entity,
			@PathVariable("taskInstanceId") String taskInstanceId, @PathVariable("decision_key") String decision_key,
			@PathVariable("category") String category) {
		return caseBriefCorpusService.saveCaseBriefDetails(entity, taskInstanceId, decision_key, category);
	}

	@GetMapping("/details")
	public ResponseEntity<List<CaseBrief>> getCaseBriefDetails() {
		return caseBriefCorpusService.getCaseBriefDetails();
	}

	/* Conflict Of Interest */
	@PostMapping("/conflict-of-interest-detail")
	public ResponseEntity<ConflictOfInterest> saveConflictOfInterestDetails(@RequestBody ConflictOfInterest entity) {
		return caseBriefCorpusService.saveConflictOfInterestDetails(entity);
	}

	@GetMapping("/conflict-of-interest-detail")
	public ResponseEntity<List<ConflictOfInterest>> getConflictOfInterestDetails() {
		return caseBriefCorpusService.getConflictOfInterestDetails();
	}
}
