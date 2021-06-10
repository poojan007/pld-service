package bt.gov.oag.elms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.CourtHearing;

@Repository
public interface CourtHearingRepo extends JpaRepository<CourtHearing, Long>{
	
	CourtHearing findByDefendantId(Long defendant_id);

}
