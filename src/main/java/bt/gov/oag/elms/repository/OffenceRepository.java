package bt.gov.oag.elms.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.Offence;

@Repository
public interface OffenceRepository extends JpaRepositoryImplementation<Offence, Long>{

}
