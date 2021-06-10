package bt.gov.oag.elms.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CaseJudgement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;    
	private Long caseId; 
	private String comment;
	private Date judgementDate;
	private String fileName;
}
