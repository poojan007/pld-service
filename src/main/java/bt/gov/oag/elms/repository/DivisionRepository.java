package bt.gov.oag.elms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.Division; 

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long>{ 

}
