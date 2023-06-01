package org.zerock.domain.caregiver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.domain.caregiver.entity.Caregiver;

@Repository
public interface CaregiverRepo extends CrudRepository<Caregiver, Long> {
	boolean existsByCid(String cid);
	
	Caregiver findByCareno(long cno);
	
	@Query(value = "select careno from (select caregiver.careno, caregiver.name, caregiver.char1, caregiver.char2, caregiver.char3, caregiver.gender, caregiver.work_time, caregiver.workday, caregiver.birth, caregiver.profile, betseniorcare.month, betseniorcare.year, betseniorcare.stateck from caregiver LEFT JOIN betseniorcare ON caregiver.careno = betseniorcare.careno) hap where (year IS NULL OR year<> (:year)) AND (month <> (:month) OR month IS NULL) AND (stateck <>1 OR stateck IS NULL)", nativeQuery = true)
	public List<Long> findByMonthYearDESC(@Param("year") int year, @Param("month") int month);
}
