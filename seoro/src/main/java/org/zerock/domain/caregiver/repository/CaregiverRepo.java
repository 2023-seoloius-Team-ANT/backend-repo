package org.zerock.domain.caregiver.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.domain.admin.dto.response.YearMonth;
import org.zerock.domain.admin.dto.response.YearMonthDTO;
import org.zerock.domain.caregiver.entity.Caregiver;

@Repository
public interface CaregiverRepo extends CrudRepository<Caregiver, Long> {
	boolean existsByCid(String cid);

	Caregiver findByCareno(long cno);
	
	@Query(value = "SELECT * FROM seoro.caregiver c WHERE c.reg_check = 0", nativeQuery = true)
	public List<Caregiver> getWaitCare();
	
	@Modifying
	@Transactional
	@Query("UPDATE FROM Caregiver c set c.regCheck = :regCheck WHERE c.careno = :careno")
	public int changeCare(Long careno, int regCheck);
	
	
	@Query(value = "select distinct careno from (select c.careno, c.reg_check, b.month, b.year, b.stateck from caregiver as c LEFT JOIN betseniorcare as b ON c.careno = b.careno UNION select c.careno, c.reg_check, b.month, b.year, b.stateck from caregiver as c RIGHT JOIN betseniorcare as b ON c.careno = b.careno) hap where ((stateck !=1 OR stateck IS NULL) OR (stateck = 1 AND (year != 2022 OR year IS NULL)) OR ((year = 2022 OR year IS NULL) OR (month != 7 OR month IS NULL) OR stateck=1)) AND (reg_check =1)", nativeQuery = true)
	public List<Long> findByMonthYearDESC(@Param("year") int year, @Param("month") int month);
	
	@Query(value = "SELECT count(*) FROM seoro.caregiver c WHERE c.cid = ?1 and c.pwd = ?2", nativeQuery = true)
	public int findByCid(String cid, String pwd);
	
	@Query(value = "SELECT * FROM seoro.caregiver c WHERE c.cid = ?1 and c.pwd = ?2", nativeQuery = true)
	public Caregiver findByCaregiver(String cid, String pwd);
	
//	@Query(value ="SELECT date_format(regdate, '%m') dateMonth, count(*) cnt from seoro.caregiver where date_format(regdate, '%Y') = :year group by dateMonth order by date_format(regdate, '%m') asc;", nativeQuery = true)
//	public List<YearMonthDTO> findStaticSenior(@Param("year") int year);
	
	@Query(value ="SELECT date_format(regdate, '%m') dateMonth, count(*) cnt from seoro.caregiver where date_format(regdate, '%Y') = :year group by dateMonth order by date_format(regdate, '%m') asc;", nativeQuery = true)
	public List<YearMonth> findStaticSenior(@Param("year") int year);
	
	@Query(value="select count(*) whole from seoro.caregiver", nativeQuery = true)
	public int findSeniorWhole();
	
}

/*

SELECT DISTINCT careno FROM // 아래 조건에 해당하는 여러개의 pk가 뽑히기 때문에 distinct로 겹치는 걸 없앤다.
(SELECT
c.careno, c.name, c.char1, c.char2, c.char3, c.gender, c.work_time, 
c.workday, c.birth, c.reg_check, c.profile, b.month, b.year, b.stateck // 필요한 정보들을 가져오기
FROM caregiver AS c LEFT JOIN betseniorcare AS b
ON c.careno = b.careno
UNION // mysql에는 outer join이 없다. 그래서 LEFT JOIN, OUTER JOIN을 UNION하는 과정이 필요
SELECT
c.careno, c.name, c.char1, c.char2, c.char3, c.gender, c.work_time, 
c.workday, c.birth, c.reg_check, c.profile, b.month, b.year, b.stateck
FROM caregiver AS c RIGHT JOIN betseniorcare AS b
ON c.careno = b.careno) hap
WHERE // stateck: 1이면 예약이 승인된 상태 (1) statekck가 1이 아니거나 null인 상태 (예약이 대기상태거나 거절상태 혹은 예약이 아직 없는 경우) / (2) 년도가 검색한 년도와 다르고 stateck가 1인 경우 (예약 테이블의 년도와 검색 년도가 다르다면 stateck가 뭐든 상관 없기 때문) / (3) 달이 달라도 stateck가 뭐든 상관없다.
((stateck !=1 OR stateck IS NULL) OR (stateck = 1 AND (year != 2022 OR year IS NULL)) OR ((year = 2022 OR year IS NULL) OR (month != 7 OR month IS NULL) OR stateck=1))
AND (reg_check =1);

*/
