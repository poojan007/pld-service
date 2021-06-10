package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.DefendantInformation;
 

@Repository
public interface DefendantInformationRepository extends JpaRepository<DefendantInformation, Long> {

	DefendantInformation findByIncomingLetterId(Long incoming_letter_id);

	@Query(value = "SELECT * FROM defendant_information WHERE incoming_letter_id=?1",nativeQuery = true)
	List<DefendantInformation> findByIncomingLetterIdList(Long incoming_letter_id);  
}
