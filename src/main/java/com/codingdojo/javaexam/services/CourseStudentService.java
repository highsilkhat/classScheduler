package com.codingdojo.javaexam.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.javaexam.models.CourseStudent;
import com.codingdojo.javaexam.repositories.CourseStudentRepository;

@Service

public class CourseStudentService {
	
	@Autowired
	private CourseStudentRepository courseCourseStudentRepo;
	
	
	public List<CourseStudent> allCourseStudents()
	{
		return courseCourseStudentRepo.findAll();
	}

	public CourseStudent createCourseStudent(CourseStudent courseCourseStudent)
	{
		return courseCourseStudentRepo.save(courseCourseStudent);
	}
	
	public CourseStudent findCourseStudent(Long id)
	{
		Optional<CourseStudent> optionalCourseStudent = courseCourseStudentRepo.findById(id);
		
		if (optionalCourseStudent.isPresent())
		{
			return optionalCourseStudent.get();	
		}	
		else
		{
		return null;
		}
	}
	
	public CourseStudent updateCourseStudent(CourseStudent courseCourseStudent)
	{
		return courseCourseStudentRepo.save(courseCourseStudent);
	}
	
	public void deleteCourseStudent(Long id)
	{
		courseCourseStudentRepo.deleteById(id);
	}
	
	
}

