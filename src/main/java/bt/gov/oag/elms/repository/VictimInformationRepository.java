package bt.gov.oag.elms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.VictimInformation;
 

@Repository
public interface VictimInformationRepository extends JpaRepository<VictimInformation, Long>{

	VictimInformation findByIncomingLetterId(Long incoming_letter_id); 

}
