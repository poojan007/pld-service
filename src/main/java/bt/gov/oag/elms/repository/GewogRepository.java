package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.Gewog;
 

@Repository
public interface GewogRepository extends JpaRepository<Gewog, Long>{

	List<Gewog> findByDzongkhagId(int dzongkhagId); 
	
}
