package bt.gov.oag.elms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bt.gov.oag.elms.entity.Agency;
import bt.gov.oag.elms.entity.CaseType;
import bt.gov.oag.elms.entity.Dzongkhag;
import bt.gov.oag.elms.entity.FileCategory;
import bt.gov.oag.elms.entity.Gewog;
import bt.gov.oag.elms.entity.Jurisdiction;
import bt.gov.oag.elms.entity.Ministry;
import bt.gov.oag.elms.entity.Offence;
import bt.gov.oag.elms.entity.PoliceStation;
import bt.gov.oag.elms.entity.Village;
import bt.gov.oag.elms.service.MasterService;

@RestController
@RequestMapping("/pld-master")
public class MasterController {
	
	@Autowired
	MasterService masterService;
	
	@GetMapping("/agency")
	public ResponseEntity<List<Agency>> getAgencyList() throws Exception{
		return masterService.getAgencyList();
	}
	
	@GetMapping("/file-category")
	public ResponseEntity<List<FileCategory>> getFileCategoryList() {
		return masterService.getFileCategoryList();
	}
	
	@GetMapping("/dzongkhag")
	public ResponseEntity<List<Dzongkhag>> getDzongkhagList() {
		return masterService.getDzongkhagList();
	}
	
	@GetMapping("/gewog-by-dzongkhag-id/{dzongkhagId}")
	public ResponseEntity<List<Gewog>> getGewogListBaseOnDzongkhag(@PathVariable int dzongkhagId) {
		return masterService.getGewogListBaseOnDzongkhag(dzongkhagId);
	}
	
	@GetMapping("/village-by-gewog-id/{gewogId}")
	public ResponseEntity<List<Village>> getVillageListBasedOnGewogId(@PathVariable int gewogId) {
		return masterService.getVillageListBasedOnGewogId(gewogId);
	} 
	
	@GetMapping("/case-type")
	public ResponseEntity<List<CaseType>> getCaseTypeList() {
		return masterService.getCaseTypeList();
	}
	
	@GetMapping("/police-station/{dzongkhag}")
	public ResponseEntity<List<PoliceStation>> getPoliceStationListBasedOnDzongkhag(@PathVariable int dzongkhag) {
		return masterService.getPoliceStationListBasedOnDzongkhag(dzongkhag);
	}
	
	@GetMapping("/jurisdiction")
	public ResponseEntity<List<Jurisdiction>> getJurisdictionList() {
		return masterService.getJurisdictionList();
	}

	@GetMapping("/offence-list")
	public ResponseEntity<List<Offence>> getOffenceList() {
		return masterService.getOffenceList();
	} 
	

	@GetMapping("/ministry-list")
	public ResponseEntity<List<Ministry>> getMinistryList() {
		return masterService.getMinistryList();
	} 
	
}
