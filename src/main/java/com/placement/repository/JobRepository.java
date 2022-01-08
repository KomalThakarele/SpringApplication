package com.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.placement.entities.Jobs;

public interface JobRepository extends JpaRepository<Jobs, Long> 
{
	
	@Query(value = "SELECT * FROM jobs j WHERE j.rid = :rid",nativeQuery = true)
	public List<Jobs> getJobsById( @Param("rid")long rid);
	
}
