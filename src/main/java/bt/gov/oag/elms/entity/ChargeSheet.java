package bt.gov.oag.elms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class ChargeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private IncomingLetter incomingLetter;
	private String salutation;
	private String jurisdiction;
	private String factsOfCase;
	private String restitution;
	private String propertyAttachment;
	private String prayers;
	private int createdBy;
	@CreationTimestamp
	private Timestamp createdOn;
	@Column(insertable=false)
	@UpdateTimestamp
	private Timestamp updatedOn;
}
