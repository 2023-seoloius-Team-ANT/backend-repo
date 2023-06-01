package org.zerock.domain.senior.repository;

import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.senior.entity.Senior;


public interface SeniorRepo extends CrudRepository<Senior, Long> {
	
	boolean existsBySid(String cid);
	
	Senior findBySeniorno(long sno);
}
