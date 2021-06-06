package bt.gov.oag.elms.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.GeneralCorpus;
import bt.gov.oag.elms.pojo.GeneralCorpusRequest;

public interface GeneralCorpusService {

	public ResponseEntity<GeneralCorpus> getGeneralCorpusDetailsById(Long id);
	public ResponseEntity<GeneralCorpus> createNewGeneralCorpus(GeneralCorpusRequest generalCorpus, String taskInstanceId, HttpServletRequest request);
}
