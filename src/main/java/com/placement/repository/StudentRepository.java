package com.placement.repository;

import org.springframework.data.repository.CrudRepository;

import com.placement.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> 
{
	Student findByEmailAndPassword(String email,String password);
	Student findByEmail(String email);
}
