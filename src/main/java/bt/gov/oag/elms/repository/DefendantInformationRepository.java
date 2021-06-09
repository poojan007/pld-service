package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.DefendantInformation;
 

@Repository
public interface DefendantInformationRepository extends JpaRepository<DefendantInformation, Long> {

	List<DefendantInformation> findByIncomingLetterId(Long incoming_letter_id);
	
}
