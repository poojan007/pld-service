package bt.gov.oag.elms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bt.gov.oag.elms.TaskUtil;
import bt.gov.oag.elms.client.WorkflowProcess;
import bt.gov.oag.elms.entity.ChargeSheet;
import bt.gov.oag.elms.entity.ChargeSheetMatter;
import bt.gov.oag.elms.entity.Offence;
import bt.gov.oag.elms.pojo.ChargeSheetMatterRequest;
import bt.gov.oag.elms.pojo.ChargeSheetRequest;
import bt.gov.oag.elms.pojo.TaskVariables;
import bt.gov.oag.elms.repository.ChargeSheetMatterRepository;
import bt.gov.oag.elms.repository.ChargeSheetRepository;
import bt.gov.oag.elms.repository.OffenceRepository;
import bt.gov.oag.elms.service.ChargeSheetService;

@Service
public class ChargeSheetServiceImpl implements ChargeSheetService {

	@Autowired
	private ChargeSheetRepository repo;
	
	@Autowired
	private ChargeSheetMatterRepository matterRepo;
	
	@Autowired
	private OffenceRepository offenceRepo;
	
	@Autowired
	WorkflowProcess workflowService;
	
	@Autowired
	private TaskUtil taskUtilService;
	
	@Override
	public ResponseEntity<ChargeSheet> getOneChargeSheet(int id) {
		return new ResponseEntity<ChargeSheet>(repo.findById(id).get(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ChargeSheet> createNewChargeSheet(ChargeSheetRequest chargeSheetRequest, int taskInstanceId, HttpServletRequest request){
		// Create a new charge sheet 
		HttpHeaders responseHeaders = new HttpHeaders();
		ChargeSheet chargeSheet = new ChargeSheet();
		BeanUtils.copyProperties(chargeSheetRequest, chargeSheet);
		ChargeSheet newChargeSheet = repo.saveAndFlush(chargeSheet);
		responseHeaders.set("Location", urlHelper(newChargeSheet, request));
		
		// Preparing a new charge sheet matters or offense list
		addChargeSheetMatters(chargeSheetRequest.getMatterRequests(), newChargeSheet);
		
		// Completing the charge sheet task 
		completeChargeSheetTask(taskInstanceId, chargeSheetRequest.getTaskVariables());

		return new ResponseEntity<ChargeSheet>(newChargeSheet, responseHeaders, HttpStatus.CREATED);
	}
	
	private void addChargeSheetMatters(List<ChargeSheetMatterRequest> matterRequests, ChargeSheet chargeSheet) {
		for(ChargeSheetMatterRequest request : matterRequests) {
			ChargeSheetMatter matter = new ChargeSheetMatter();
			Offence offence = offenceRepo.findById(Long.valueOf(request.getOffenceId())).get();
			matter.setOffence(offence);
			matter.setChargeSheet(chargeSheet);
			matter.setChargeCount(request.getChargeCount());
			matter = matterRepo.saveAndFlush(matter);
		}
	}
	
	private void completeChargeSheetTask(int taskInstanceId, List<TaskVariables> taskVariables) {
		workflowService.completeTask(Integer.toString(taskInstanceId), taskUtilService.processTaskVariables(taskVariables));
	}
	
	private String urlHelper(ChargeSheet chargeSheet, HttpServletRequest request) {
		StringBuilder resourcePath = new StringBuilder();
		resourcePath.append(request.getRequestURL());
		resourcePath.append("/");
		resourcePath.append(chargeSheet.getId());
		return resourcePath.toString();
	}
}
