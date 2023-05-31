package org.zerock.domain.caregiver.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zerock.domain.caregiver.entity.Caregiver;

@Repository
public interface CaregiverRepo extends CrudRepository<Caregiver, Long> {
	
	
	@Query(value = "SELECT * FROM seoro.caregiver c WHERE c.reg_check = 0", nativeQuery = true)
	public List<Caregiver> getWaitCare();
	
	@Modifying
	@Transactional
	@Query("UPDATE FROM Caregiver c set c.regCheck = :regCheck WHERE c.careno = :careno")
	public int changeCare(Long careno, int regCheck);
	
}
