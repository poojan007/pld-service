package bt.gov.oag.elms.service;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.CaseInformation;
import bt.gov.oag.elms.entity.DefendantInformation;
import bt.gov.oag.elms.entity.IncomingLetter;
import bt.gov.oag.elms.entity.InvestigatingOfficer;
import bt.gov.oag.elms.entity.VictimInformation;
import bt.gov.oag.elms.pojo.CaseApiResponse;

public interface CaseInformationService {

	ResponseEntity<CaseInformation> saveCaseInformation(CaseInformation entity); 
	ResponseEntity<CaseInformation> getCaseInformationByCaseId(Long incoming_letter_id);
	ResponseEntity<CaseInformation> getCaseInformationById(Long id);
	ResponseEntity<DefendantInformation> saveDefendantInformation(DefendantInformation entity);
	ResponseEntity<DefendantInformation> getDefendantInformationById(Long id);
	ResponseEntity<DefendantInformation> getDefendantInformationByCaseId(Long incoming_letter_id);
	ResponseEntity<CaseApiResponse> saveInvestigatingInformation(InvestigatingOfficer entity,String taskInstanceId);
	ResponseEntity<InvestigatingOfficer> getInvestigatingOfficerInformationById(Long id);
	ResponseEntity<InvestigatingOfficer> getInvestigatingInformationByCaseId(Long incoming_letter_id);
	ResponseEntity<VictimInformation> saveVictimInformation(VictimInformation entity);
	ResponseEntity<VictimInformation> getVictimInformationById(Long id);
	ResponseEntity<VictimInformation> getVictimInformationByCaseId(Long incoming_letter_id);
	ResponseEntity<CaseApiResponse> saveWorkloadDetails(IncomingLetter entity, String taskInstanceId,
			String decision_key, Boolean examine_fact); 

	 

}
