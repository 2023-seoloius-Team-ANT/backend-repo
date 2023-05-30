package org.zerock.domain.searched.repository;

import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.searched.entity.Searched;

public interface SearchRepo extends CrudRepository<Searched, Long> {

}
