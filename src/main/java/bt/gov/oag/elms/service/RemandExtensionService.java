package bt.gov.oag.elms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.RemandExtension;

public interface RemandExtensionService {
	
	public ResponseEntity<RemandExtension> saveRemandExtensionDetails(RemandExtension data,String taskInstanceId); 
	public ResponseEntity<List<RemandExtension>> getRemandExtensionDeatails();
	public ResponseEntity<List<RemandExtension>> getRemandExtensionDeatails(Long incoming_letter);
}
