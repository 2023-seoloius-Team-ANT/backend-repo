package org.zerock.domain.betSeniorCare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.domain.admin.dto.response.WorkStatic;
import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;

@Repository
public interface BetRepo extends CrudRepository<BetSeniorCare, Long>{
	@Query(value="select conno from betseniorcare where stateck=0 AND careno = (:careno) ORDER BY regdate desc", nativeQuery = true)
	public List<Long> findByCarenoStateckRsDESC(@Param("careno") long careno);
	
	@Query(value="select conno from betseniorcare where stateck=1 AND careno = (:careno) AND year = (:year) ORDER BY regdate desc", nativeQuery = true)
	public List<Long> findByCarenoStateckCfDESC(@Param("careno") long careno, @Param("year") int year);
	
	// 예약 시 이미 같은 seniorno과 careno이 있는지, 그리고 그 stateck가 대기상태 (0)인지 확인해야한다.
	@Query(value="SELECT * from BetSeniorCare where stateck = 0 AND year = (:year) AND month =(:month) AND careno=(:careno) AND seniorno=(:seniorno)" , nativeQuery = true)
	public Optional<BetSeniorCare> duplicateCk(@Param("year") int year, @Param("month") int month, @Param("careno") long careno, @Param("seniorno") long seniorno);
	
	@Query(value = "select date_format(regdate, '%Y-%m-%d') AS date, seniorno, careno, stateck, reason from seoro.betseniorcare where date_format(regdate, '%Y') = (:year) and (stateck = 1 or stateck = 2) order by date desc;", nativeQuery = true)
	public List<WorkStatic> findAdminWork(@Param("year") int year);
}
