package org.zerock.domain.betSeniorCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.domain.betSeniorCare.entity.BetSeniorCare;

@Repository
public interface BetRepo extends CrudRepository<BetSeniorCare, Long>{
	@Query(value="select conno from betseniorcare where stateck=0 AND careno = (:careno) ORDER BY regdate desc", nativeQuery = true)
	public List<Long> findByCarenoStateckRsDESC(@Param("careno") long careno);
	
	@Query(value="select conno from betseniorcare where stateck=1 AND careno = (:careno) ORDER BY regdate desc", nativeQuery = true)
	public List<Long> findByCarenoStateckCfDESC(@Param("careno") long careno);
	
	/*
	 * @Query(value
	 * ="select conno from betseniorcare where year=(:year) AND month=(:month) AND stateck !=ck"
	 * ) public List<Long> findByMonthYearDESC(@Param)
	 */
}
