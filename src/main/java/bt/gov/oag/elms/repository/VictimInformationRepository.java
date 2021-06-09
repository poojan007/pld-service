package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.VictimInformation;
 

@Repository
public interface VictimInformationRepository extends JpaRepository<VictimInformation, Long>{

	List<VictimInformation> findByIncomingLetterId(Long incoming_letter_id); 

}
