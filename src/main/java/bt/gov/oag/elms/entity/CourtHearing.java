package bt.gov.oag.elms.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CourtHearing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;    
	private Long defendantId;
	private String hearingStage;
	private String comment;
	private Date hearingDate;
}
