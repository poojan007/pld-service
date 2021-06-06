package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.RemandExtension;
 

@Repository
public interface RemandExtensionRepository extends JpaRepository<RemandExtension, Long> {

	List<RemandExtension> findByincomingLetterId(Long incoming_letter);
	

}
