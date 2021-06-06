package bt.gov.oag.elms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bt.gov.oag.elms.entity.IncomingLetter;
 

@Repository
public interface IncomingLetterRepository extends JpaRepository<IncomingLetter, Long>{

	@Query(value = "SELECT * FROM incoming_letter a WHERE a.letter_status = ?1",nativeQuery = true)
	List<IncomingLetter> findByLetterStatus(String letter_status);

	IncomingLetter findByProccessId(String processInstanceId);

	@Query(value = "SELECT * FROM incoming_letter a WHERE a.forwarded_to = ?1",nativeQuery = true)
	IncomingLetter findByForwardedTo(int user_id);  
}
