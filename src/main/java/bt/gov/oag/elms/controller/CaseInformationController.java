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

import bt.gov.oag.elms.entity.CaseInformation;
import bt.gov.oag.elms.entity.DefendantInformation;
import bt.gov.oag.elms.entity.IncomingLetter;
import bt.gov.oag.elms.entity.InvestigatingOfficer;
import bt.gov.oag.elms.entity.VictimInformation;
import bt.gov.oag.elms.pojo.CaseApiResponse;
import bt.gov.oag.elms.pojo.DefendantResponse;
import bt.gov.oag.elms.service.CaseInformationService; 

@RestController
@RequestMapping("/case-register")
public class CaseInformationController {

	 
	@Autowired
	private CaseInformationService caseInformationService;

	/* ********* Case Information Table ********* */
	@PostMapping("/case-information")
	public ResponseEntity<CaseInformation> saveCaseInformation(@RequestBody CaseInformation entity) {
		return caseInformationService.saveCaseInformation(entity);
	}

	@GetMapping("/case-information-id/{id}")
	public ResponseEntity<CaseInformation> getCaseInformationById(@PathVariable Long id) {
		return caseInformationService.getCaseInformationById(id);
	}

	@GetMapping("/case-information-by-caseId/{incoming_letter_id}")
	public ResponseEntity<CaseInformation> getCaseInformationByCaseId(@PathVariable Long incoming_letter_id) {
		return caseInformationService.getCaseInformationByCaseId(incoming_letter_id);
	}
	
	/* ********* Victim Information Table ********* */ 
	@PostMapping("/victim-information")
	public ResponseEntity<VictimInformation> saveVictimInformation(@RequestBody VictimInformation entity) {
		return caseInformationService.saveVictimInformation(entity);
	}

	@GetMapping("/victim-information-id/{id}")
	public ResponseEntity<VictimInformation> getVictimInformationById(@PathVariable Long id) {
		return caseInformationService.getVictimInformationById(id);
	}

	@GetMapping("/victim-information-by-caseId/{incoming_letter_id}")
	public ResponseEntity <List<VictimInformation>> getVictimInformationByCaseId(@PathVariable Long incoming_letter_id) {
		return caseInformationService.getVictimInformationByCaseId(incoming_letter_id);
	}
	
	/* ********* DefendantInformation Information Table ********* */ 
	@PostMapping("/defendant-information")
	public ResponseEntity<DefendantInformation> saveDefendantInformation(@RequestBody DefendantInformation entity) {
		return caseInformationService.saveDefendantInformation(entity);
	}

	@GetMapping("/defendant-information-id/{id}")
	public ResponseEntity<DefendantInformation> getDefendantInformationById(@PathVariable Long id) {
		return caseInformationService.getDefendantInformationById(id);
	}

	@GetMapping("/defendant-information-by-caseId/{incoming_letter_id}")
	public ResponseEntity <List<DefendantInformation>> getDefendantInformationByCaseId(@PathVariable Long incoming_letter_id) {
		return caseInformationService.getDefendantInformationByCaseId(incoming_letter_id);
	}

	/* ********* investigating Information Table ********* */ 
	@PostMapping("/investigating-information/{taskInstanceId}")
	public ResponseEntity<CaseApiResponse> saveInvestigatingInformation(@RequestBody InvestigatingOfficer entity,@PathVariable("taskInstanceId") String taskInstanceId) {
		return caseInformationService.saveInvestigatingInformation(entity,taskInstanceId);
	}

	@GetMapping("/investigating-information-id/{id}")
	public ResponseEntity<InvestigatingOfficer> getInvestigatingInformationById(@PathVariable Long id) {
		return caseInformationService.getInvestigatingOfficerInformationById(id);
	}

	@GetMapping("/investigating-information-by-caseId/{incoming_letter_id}")
	public ResponseEntity<InvestigatingOfficer> getInvestigatingInformationByCaseId(@PathVariable Long incoming_letter_id) {
		return caseInformationService.getInvestigatingInformationByCaseId(incoming_letter_id);
	} 
	
	/* ********* Prosecution Workload ********* */ 
	
	@PostMapping("/workload-details/{taskInstanceId}/{decision_key}/{examine_fact}")
	public ResponseEntity<CaseApiResponse> saveWorkloadDetails(@RequestBody IncomingLetter entity,@PathVariable("taskInstanceId") String taskInstanceId,@PathVariable ("decision_key") String decision_key,@PathVariable Boolean examine_fact) {
		return caseInformationService.saveWorkloadDetails(entity,taskInstanceId, decision_key,examine_fact);
	} 
	
}
