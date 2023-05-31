package org.zerock.domain.senior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.senior.entity.Senior;


public interface SeniorRepo extends CrudRepository<Senior, Long> {
	
	public boolean existsBySid(String sid);
}
