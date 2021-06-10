package bt.gov.oag.elms.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bt.gov.oag.elms.entity.IncomingAttachmentMapping;
import bt.gov.oag.elms.entity.IncomingLetter;
import bt.gov.oag.elms.pojo.CaseApiResponse;
import bt.gov.oag.elms.service.CaseService; 

@RestController
@RequestMapping("/case")
public class CaseController {

	@Value("${spring.servlet.multipart.location}")
	String FILE_DIRECTORY;
	
	@Autowired
	private CaseService caseService;

	@GetMapping("/")
	private String Greetings() {
		return "Hello";
	}

	// Incoming Letter
	@PostMapping("/incoming-letter")
	public CaseApiResponse saveIncomingLetter(@RequestBody IncomingLetter entity) {
		return caseService.saveIncomingLetter(entity);
	}

	// Upload incoming document
	@PostMapping("/uploadDocFile")
	public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file) throws IOException {
		File myFile = new File(this.FILE_DIRECTORY + file.getOriginalFilename());
		myFile.createNewFile();
		java.io.FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping("/attachment-details")
	public ResponseEntity<IncomingAttachmentMapping> saveIncomingAttachmentMapping(@RequestBody IncomingAttachmentMapping entity) {
		return caseService.saveIncomingAttachmentMapping(entity);
	}

	@GetMapping("/incoming-letter")
	public List<CaseApiResponse> getIncomingLetterList() {
		return caseService.getIncomingLetterList();
	}

	@GetMapping("/incoming-letter/{id}")
	public CaseApiResponse getIncomingLetterById(@PathVariable Long id) {
		return caseService.getIncomingLetterById(id);
	}

	@PutMapping("/incoming-letter/{id}")
	public CaseApiResponse updateIncomingLetter(@RequestBody IncomingLetter entity, @PathVariable Long id) {
		return caseService.updateIncomingLetter(entity, id);
	}

	@PutMapping("/delete-incoming-letter/{id}")
	public CaseApiResponse logicalDeleteIncomingLetter(@PathVariable Long id) {
		return caseService.logicalDeleteIncomingLetter(id);
	}

	// remove from database
	@PostMapping("/remove-incoming-letter/{id}")
	public CaseApiResponse removeDeleteIncomingLetter(@PathVariable Long id) {
		return caseService.removeDeleteIncomingLetter(id);
	}

	// Incoming Letter
	@PostMapping("/start-case-process/{id}")
	public CaseApiResponse startCaseProcess(@PathVariable Long id) {
		return caseService.startCaseProcess(id);
	}

	@GetMapping("/user-pending-task/{user_id}")
	public ResponseEntity<List<CaseApiResponse>> getUserPendingTask(@PathVariable int user_id) {
		return caseService.getUserPendingTask(user_id);
	}

	@PostMapping("/assign-incoming-letter/{taskInstanceId}")
	public ResponseEntity<CaseApiResponse> updateUserTask(@RequestBody IncomingLetter entity,@PathVariable("taskInstanceId") String taskInstanceId) {
		return caseService.updateUserTask(entity, taskInstanceId);
	}
	
	/* Chief Assigning incoming letter to Case Register */
	@PostMapping("/case-register/{taskInstanceId}")
	public ResponseEntity<CaseApiResponse> assignCaseToRegister(@RequestBody CaseApiResponse entity, @PathVariable("taskInstanceId") String taskInstanceId) {
		return caseService.assignCaseToRegister(entity, taskInstanceId);
	}
	
	@GetMapping("/case-under-review/{user_id}")
	public ResponseEntity<List<CaseApiResponse>> getCaseUnderReviewDetails(@PathVariable int user_id) {
		return caseService.getCaseUnderReviewDetails(user_id);
	}   
	
	/* Update Incoming Letter Status */
	@PostMapping("/update-case-status/{id}/{letterStatus}")
	public CaseApiResponse updateCaseStatus(@PathVariable("id") Long id,@PathVariable("letterStatus") String letterStatus) {
		return caseService.updateCaseStatus(id, letterStatus);
	}
}
