package bt.gov.oag.elms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.CaseJudgement;

@Repository
public interface CaseJudgementRepo extends JpaRepository<CaseJudgement, Long>{

	@Query(value = "SELECT * FROM case_judgement WHERE case_id=?1",nativeQuery = true)
	CaseJudgement findByCaseId(Long caseId);

}
