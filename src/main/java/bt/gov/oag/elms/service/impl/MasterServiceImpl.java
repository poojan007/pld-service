package bt.gov.oag.elms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bt.gov.oag.elms.entity.Agency;
import bt.gov.oag.elms.entity.CaseType;
import bt.gov.oag.elms.entity.Dzongkhag;
import bt.gov.oag.elms.entity.FileCategory;
import bt.gov.oag.elms.entity.Gewog;
import bt.gov.oag.elms.entity.Jurisdiction;
import bt.gov.oag.elms.entity.PoliceStation;
import bt.gov.oag.elms.entity.Village;
import bt.gov.oag.elms.repository.AgencyRepository;
import bt.gov.oag.elms.repository.CaseStageRepository;
import bt.gov.oag.elms.repository.CaseTypeRepository;
import bt.gov.oag.elms.repository.DzongkhagRepository;
import bt.gov.oag.elms.repository.FileCategoryRepository;
import bt.gov.oag.elms.repository.GewogRepository;
import bt.gov.oag.elms.repository.JurisdictionRepository;
import bt.gov.oag.elms.repository.PoliceStationRepository;
import bt.gov.oag.elms.repository.VillageRepository;
import bt.gov.oag.elms.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	AgencyRepository agencyRepository;

	@Autowired
	FileCategoryRepository fileCategoryRepository;

	@Autowired
	DzongkhagRepository dzongkhagRepository;

	@Autowired
	GewogRepository gewogRepository;

	@Autowired
	VillageRepository villageRepository;

	@Autowired
	CaseStageRepository caseStageRepository;

	@Autowired
	CaseTypeRepository caseTypeRepository;

	@Autowired
	PoliceStationRepository policeStationRepository;

	@Autowired
	JurisdictionRepository jurisdictionRepository;

	@Override
	public ResponseEntity<List<Agency>> getAgencyList() { 
		return new ResponseEntity<List<Agency>>(agencyRepository.findAll(),HttpStatus.OK); 
	}

	@Override
	public ResponseEntity<List<FileCategory>> getFileCategoryList() {
		return new ResponseEntity<List<FileCategory>>(fileCategoryRepository.findAll(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Dzongkhag>> getDzongkhagList() {
		return new ResponseEntity<List<Dzongkhag>>(dzongkhagRepository.findAll(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Gewog>> getGewogListBaseOnDzongkhag(int dzongkhagId) {
		return new ResponseEntity<List<Gewog>>(gewogRepository.findByDzongkhagId(dzongkhagId),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Village>> getVillageListBasedOnGewogId(int gewogId) {
		return new ResponseEntity<List<Village>>(villageRepository.findByGewogId(gewogId),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CaseType>> getCaseTypeList() {
		return new ResponseEntity<List<CaseType>>(caseTypeRepository.findAll(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PoliceStation>> getPoliceStationListBasedOnDzongkhag(int dzongkhag) {
		List<PoliceStation> response = new ArrayList<>();
		try {
			response = policeStationRepository.findByDzongkhagId(dzongkhag);
			return new ResponseEntity<List<PoliceStation>>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<PoliceStation>>(HttpStatus.NOT_FOUND);
		} 
	}

	@Override
	public ResponseEntity<List<Jurisdiction>> getJurisdictionList() {
		return new ResponseEntity<List<Jurisdiction>>(jurisdictionRepository.findAll(),HttpStatus.OK);
	}

}
