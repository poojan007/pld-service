package bt.gov.oag.elms.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class JudgementReport { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;   
	private Long caseId;   
	private String convicetedAcquittedDeferred;
	@UpdateTimestamp
	private Timestamp updatedOn;
	private int updatedBy;
	private String updateByName;
}
