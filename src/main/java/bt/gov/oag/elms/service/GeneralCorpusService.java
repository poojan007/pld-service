package bt.gov.oag.elms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.GeneralCorpus;
import bt.gov.oag.elms.pojo.GeneralCorpusRequest;
import bt.gov.oag.elms.pojo.TaskVariables;

public interface GeneralCorpusService {

	public ResponseEntity<GeneralCorpus> getGeneralCorpusDetailsById(Long id);
	public ResponseEntity<GeneralCorpus> createNewGeneralCorpus(GeneralCorpusRequest generalCorpus, String taskInstanceId, HttpServletRequest request);
	public ResponseEntity<?> approveCorpusMeetingRequest(List<TaskVariables> taskVariables, String taskInstanceId);
}
