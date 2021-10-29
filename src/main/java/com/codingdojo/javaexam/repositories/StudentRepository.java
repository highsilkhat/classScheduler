package com.codingdojo.javaexam.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.javaexam.models.Instructor;
import com.codingdojo.javaexam.models.Student;


@Repository

public interface StudentRepository extends CrudRepository<Student, Long>{
 
		List<Student> findAll();
		
		 Optional<Instructor> findByEmail(String email);


}
