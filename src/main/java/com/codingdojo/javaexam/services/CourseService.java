package com.codingdojo.javaexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.javaexam.models.Course;
import com.codingdojo.javaexam.repositories.CourseRepository;

@Service

public class CourseService {
	
	@Autowired
	private CourseRepository courseRepo;
	
	public List<Course> allCourses()
	{
		return courseRepo.findAll();
	}

	public Course createCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public Course findCourse(Long id)
	{
		Optional<Course> optionalCourse = courseRepo.findById(id);
		
		if (optionalCourse.isPresent())
		{
			return optionalCourse.get();	
		}	
		else
		{
		return null;
		}
	}
	
	public Course updateCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public void deleteCourse(Long id)
	{
		courseRepo.deleteById(id);
	}
	
	
}

