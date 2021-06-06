package bt.gov.oag.elms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bt.gov.oag.elms.entity.RemandExtension;
import bt.gov.oag.elms.repository.RemandExtensionRepository;
import bt.gov.oag.elms.service.RemandExtensionService;

@Service
public class RemandExtensionServiceImpl implements RemandExtensionService{
	
	@Autowired
	private RemandExtensionRepository remandExtensionRepository;

	@Override
	public ResponseEntity<RemandExtension> saveRemandExtensionDetails(RemandExtension data) { 
		return new ResponseEntity<RemandExtension>(remandExtensionRepository.save(data),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RemandExtension>> getRemandExtensionDeatails() {
		return new ResponseEntity<List<RemandExtension>>(remandExtensionRepository.findAll(),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<RemandExtension>> getRemandExtensionDeatails(Long incoming_letter) {
		return new ResponseEntity<List<RemandExtension>>(remandExtensionRepository.findByincomingLetterId(incoming_letter),HttpStatus.OK);
	}

}
