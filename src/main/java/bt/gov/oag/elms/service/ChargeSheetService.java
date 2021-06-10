package bt.gov.oag.elms.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import bt.gov.oag.elms.entity.ChargeSheet;
import bt.gov.oag.elms.pojo.ChargeSheetRequest;

public interface ChargeSheetService {

	public ResponseEntity<ChargeSheet> getOneChargeSheet(int id);
	public ResponseEntity<ChargeSheet> createNewChargeSheet(ChargeSheetRequest chargeSheet, int taskInstanceId, HttpServletRequest request);
}
