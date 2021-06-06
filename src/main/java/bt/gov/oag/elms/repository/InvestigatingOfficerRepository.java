package bt.gov.oag.elms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.InvestigatingOfficer;
 

@Repository
public interface InvestigatingOfficerRepository extends JpaRepository<InvestigatingOfficer, Long>{

	InvestigatingOfficer findByIncomingLetterId(Long incoming_letter_id); 

}

