package org.zerock.domain.senior.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zerock.domain.admin.dto.response.YearMonth;
import org.zerock.domain.senior.entity.Senior;


public interface SeniorRepo extends CrudRepository<Senior, Long> {
	public boolean existsBySid(String sid);
	
	Senior findBySeniorno(long sno);
		
	@Query(value = "SELECT count(*) FROM seoro.senior s WHERE s.sid = ?1 and s.pwd = ?2", nativeQuery = true)
	public int findBySid(String sid, String pwd);
	
	@Query(value = "SELECT * FROM seoro.senior s WHERE s.sid = ?1 and s.pwd = ?2", nativeQuery = true)
	public Senior findBySenior(String sid, String pwd);
	
	@Query(value ="SELECT date_format(regdate, '%m') dateMonth, count(*) cnt from seoro.senior where date_format(regdate, '%Y') = :year group by dateMonth order by date_format(regdate, '%m') asc;", nativeQuery = true)
	public List<YearMonth> findStaticSenior(@Param("year") int year);
	
	@Query(value="select count(*) whole from seoro.senior", nativeQuery = true)
	public int findSeniorWhole();
	
}
