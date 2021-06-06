package bt.gov.oag.elms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bt.gov.oag.elms.entity.GeneralCorpus;
import bt.gov.oag.elms.pojo.GeneralCorpusRequest;
import bt.gov.oag.elms.service.GeneralCorpusService;

@RestController
@RequestMapping("/corpus")
public class GeneralCorpusController {

	@Autowired
	private GeneralCorpusService corpusService;
	
	@GetMapping("/{id}")
	public ResponseEntity<GeneralCorpus> getGeneralCorpusDetailsById(@PathVariable Long id){
		return corpusService.getGeneralCorpusDetailsById(id);
	}
	
	@PostMapping("/{taskInstanceId}")
	public ResponseEntity<GeneralCorpus> createNewGeneralCorpus(@RequestBody GeneralCorpusRequest generalCorpus, @PathVariable String taskInstanceId, HttpServletRequest req){
		return corpusService.createNewGeneralCorpus(generalCorpus, taskInstanceId, req);
	}
}
