package bt.gov.oag.elms.entity; 

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CaseBrief {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  
	private Long incomingLetterId;
	private String caseCategory;
	private String fact;
	private String issue;
	private String rule;
	private String application;
	private String conclusion; 
	private Timestamp updatedOn;
	private int updatedBy;
	private String updatedByName;   
	
}
