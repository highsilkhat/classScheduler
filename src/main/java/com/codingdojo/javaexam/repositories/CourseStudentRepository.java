package com.codingdojo.javaexam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.javaexam.models.CourseStudent;

public interface CourseStudentRepository extends CrudRepository<CourseStudent, Long>{
	
	List<CourseStudent> findAll();

}
