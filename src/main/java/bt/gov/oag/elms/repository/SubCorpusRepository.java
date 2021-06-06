package bt.gov.oag.elms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.SubCorpus;
 

@Repository
public interface SubCorpusRepository extends JpaRepository<SubCorpus, Long>{

}
