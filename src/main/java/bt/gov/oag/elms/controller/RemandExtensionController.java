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

import bt.gov.oag.elms.entity.RemandExtension;
import bt.gov.oag.elms.service.RemandExtensionService;

@RestController
@RequestMapping("/remand-extension")
public class RemandExtensionController {

	@Autowired 
	private RemandExtensionService remandExtensionService;
	
	@PostMapping("/details/{taskInstanceId}")
	public ResponseEntity<RemandExtension> saveRemandExtensionDeatails(@RequestBody RemandExtension data,@PathVariable String taskInstanceId){
		return remandExtensionService.saveRemandExtensionDetails(data,taskInstanceId);
	}
	
	@GetMapping("/detailList")
	public ResponseEntity<List<RemandExtension>> getRemandExtensionDeatails(){
		return remandExtensionService.getRemandExtensionDeatails();
	}
	
	@GetMapping("/detailByIncomingLetter/{incoming_letter}")
	public ResponseEntity<List<RemandExtension>> getRemandExtensionByLetterId(@PathVariable Long incoming_letter){
		return remandExtensionService.getRemandExtensionDeatails(incoming_letter);
	}
}
