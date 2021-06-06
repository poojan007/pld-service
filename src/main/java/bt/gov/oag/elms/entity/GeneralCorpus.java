package bt.gov.oag.elms.entity; 

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class GeneralCorpus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "incoming_letter_id")
	private int incomingLetterId;
	private Date corpusDate;
	private String briefFact;
	private String issue;
	private String recommendation;
	private String remark;
	private String member;
	private String presidedBy;
	private int updatedBy;
	private String updatedByName; 
	@CreationTimestamp
	private Timestamp createdOn;
	@Column(insertable=false)
	@UpdateTimestamp
	private Timestamp updatedOn;
}
