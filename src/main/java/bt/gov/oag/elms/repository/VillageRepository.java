package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.Village;
 

@Repository
public interface VillageRepository extends JpaRepository<Village, Integer>{

	List<Village> findByGewogId(int gewogId); 

}
