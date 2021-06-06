package bt.gov.oag.elms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.Agency;
import bt.gov.oag.elms.entity.CaseType;
import bt.gov.oag.elms.entity.Dzongkhag;
import bt.gov.oag.elms.entity.FileCategory;
import bt.gov.oag.elms.entity.Gewog;
import bt.gov.oag.elms.entity.Jurisdiction;
import bt.gov.oag.elms.entity.PoliceStation;
import bt.gov.oag.elms.entity.Village;

public interface MasterService {

	ResponseEntity<List<Agency>> getAgencyList(); 
	ResponseEntity<List<FileCategory>> getFileCategoryList();
	ResponseEntity<List<Dzongkhag>> getDzongkhagList();
	ResponseEntity<List<Gewog>> getGewogListBaseOnDzongkhag(int dzongkhagId);
	ResponseEntity<List<Village>> getVillageListBasedOnGewogId(int gewogId);
	ResponseEntity<List<CaseType>> getCaseTypeList();
	ResponseEntity<List<PoliceStation>> getPoliceStationListBasedOnDzongkhag(int dzongkhag);
	ResponseEntity<List<Jurisdiction>> getJurisdictionList();

}
