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

import bt.gov.oag.elms.entity.ChargeSheet;
import bt.gov.oag.elms.pojo.ChargeSheetRequest;
import bt.gov.oag.elms.service.ChargeSheetService;

@RestController
@RequestMapping("/charge-sheet")
public class ChargeSheetController {

	@Autowired
	private ChargeSheetService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ChargeSheet> getChargeSheet(@PathVariable int id){
		return service.getOneChargeSheet(id);
	}
	
	@PostMapping("/{taskInstanceId}")
	public ResponseEntity<ChargeSheet> createNewChargeSheet(@RequestBody ChargeSheetRequest chargeSheet, @PathVariable int taskInstanceId, HttpServletRequest req){
		return service.createNewChargeSheet(chargeSheet, taskInstanceId, req);
	}
}
