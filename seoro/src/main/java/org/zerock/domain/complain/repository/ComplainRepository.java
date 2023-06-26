package org.zerock.domain.complain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zerock.domain.complain.entity.Complain;

@Repository
public interface ComplainRepository extends CrudRepository<Complain, Long>{
	@Query(value="SELECT * FROM seoro.complain WHERE stateck=0 order by regdate desc", nativeQuery = true)
	public List<Complain> getWaitComplainList();
	
	@Query(value="SELECT COUNT(*) FROM seoro.complain WHERE stateck=0", nativeQuery = true)
	public Integer getWaitComplainCnt();

	
}
