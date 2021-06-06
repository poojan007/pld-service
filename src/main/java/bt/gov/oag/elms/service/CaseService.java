package bt.gov.oag.elms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.IncomingAttachmentMapping;
import bt.gov.oag.elms.entity.IncomingLetter;
import bt.gov.oag.elms.pojo.CaseApiResponse;

public interface CaseService {

	CaseApiResponse saveIncomingLetter(IncomingLetter entity); 
	List<CaseApiResponse> getIncomingLetterList();
	CaseApiResponse updateIncomingLetter(IncomingLetter entity, Long id);
	CaseApiResponse logicalDeleteIncomingLetter(Long id);
	CaseApiResponse removeDeleteIncomingLetter(Long id);
	CaseApiResponse startCaseProcess(Long id);
	CaseApiResponse getIncomingLetterById(Long id);
	ResponseEntity<List<CaseApiResponse>> getUserPendingTask(int user_id);
	ResponseEntity<CaseApiResponse> updateUserTask(IncomingLetter entity, String taskInstanceId);
	ResponseEntity<IncomingAttachmentMapping> saveIncomingAttachmentMapping(IncomingAttachmentMapping entity);
	ResponseEntity<CaseApiResponse> assignCaseToRegister(CaseApiResponse entity, String taskInstanceId);
	ResponseEntity<List<CaseApiResponse>> getCaseUnderReviewDetails(int user_id);
	CaseApiResponse updateCaseStatus(Long id, String letterStatus); 

}
