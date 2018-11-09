package edu.gatech.curator.repository;

import edu.gatech.curator.entity.SourceSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceSystemsRepository extends CrudRepository<SourceSystem, Long> {
}
