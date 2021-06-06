package bt.gov.oag.elms.entity; 

import java.math.BigInteger;

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
public class EmployeeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  
	private String employeeNumber;
	private BigInteger cid;  
	private String employeeName;
	private String designation;  
	@Column(name = "organization_id")
	private int organizationId; 
	@Column(name = "division_unit_id")
	private int divisionUnitId;
	
	@OneToOne
	@JoinColumn(name = "organization_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Organization getOrganization;
	
	@OneToOne
	@JoinColumn(name = "division_unit_id", referencedColumnName = "id", insertable = false, updatable = false)
	private DivisionUnit getDivisionUnit;

	 
}
