package com.placement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.entities.Recruiter;

public interface RecruiterRepository extends JpaRepository<Recruiter, Long>
{
	
	Recruiter findByEmail(String email);

}
