package org.zerock.domain.caregiver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zerock.domain.caregiver.entity.Caregiver;

@Repository
public interface CaregiverRepo extends CrudRepository<Caregiver, Long> {
	
	
	@Query(value="SELECT regCheck FROM caregiver as c WHERE c.regCheck = ?1", nativeQuery=true)
	public List<Caregiver> getWaitCare(int regCheck);
	
}
