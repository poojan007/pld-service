package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.PoliceStation;
 

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Long>{

	List<PoliceStation> findByDzongkhagId(int dzongkhag);

}
