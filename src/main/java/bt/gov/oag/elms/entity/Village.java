package bt.gov.oag.elms.entity; 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Village {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  
	private int gewogId;  
	private String villageName; 
}
