package org.zerock.domain.admin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zerock.domain.admin.entity.Sadmin;

@Repository
public interface AdminRepo extends CrudRepository<Sadmin, Long> {
	
	@Query(value = "SELECT count(*) FROM seoro.admin ad WHERE ad.aid = ?1 and ad.pwd = ?2", nativeQuery = true)
	public int findByAid(String aid, String pwd);
	
	@Query(value = "SELECT * FROM seoro.admin ad WHERE ad.aid = ?1 and ad.pwd = ?2", nativeQuery = true)
	public Sadmin findByAdmin(String aid, String pwd);	
}
