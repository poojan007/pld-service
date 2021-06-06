package bt.gov.oag.elms.entity; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class DivisionUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;   
	@Column(name = "division_id")
	private Long divisionId;  
	private String divisionUnitName;
	
	@OneToOne
	@JoinColumn(name = "division_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Division division;

	 
}
