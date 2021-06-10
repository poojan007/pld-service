package bt.gov.oag.elms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import bt.gov.oag.elms.pojo.CaseBriefRequest;
import bt.gov.oag.elms.service.CaseBriefCorpusService;

@RestController
@RequestMapping("/case-brief")
public class CaseBriefCorpusController {

	@Autowired
	private CaseBriefCorpusService caseBriefCorpusService;

	/* Case Brief */
	@PostMapping("/details/{taskInstanceId}")
	public ResponseEntity<CaseBrief> saveCaseBriefDetails(@RequestBody CaseBriefRequest request,
			@PathVariable("taskInstanceId") String taskInstanceId, HttpServletRequest req) {
		return caseBriefCorpusService.saveCaseBriefDetails(request, taskInstanceId, req);
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
