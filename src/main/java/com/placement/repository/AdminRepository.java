package com.placement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placement.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>  
{

	Admin findByEmailAndPassword(String email,String password);
	Admin findByEmail(String email);
}
