package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.PoliceStation;
 

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Long>{

	@Query(value = "SELECT * FROM police_station WHERE dzongkhag_id=?1",nativeQuery = true)
	List<PoliceStation> findByDzongkhagId(int dzongkhag);

}
