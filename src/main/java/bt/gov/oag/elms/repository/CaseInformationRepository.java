package bt.gov.oag.elms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.CaseInformation;
 

@Repository
public interface CaseInformationRepository extends JpaRepository<CaseInformation, Long>{

	CaseInformation findByIncomingLetterId(Long incoming_letter_id);
 

}
