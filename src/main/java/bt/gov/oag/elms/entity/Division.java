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
public class Division {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  
	@Column(name = "department_id")
	private Long departmentId; 
	private String divisionName; 
	
	@OneToOne
	@JoinColumn(name = "department_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Department getDepartment; 

	 
}
