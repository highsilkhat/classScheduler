package com.codingdojo.javaexam.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.javaexam.models.Student;
import com.codingdojo.javaexam.repositories.StudentRepository;

@Service

public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	public Student addStudent(Student newStudent, BindingResult result)
    {
        if(studentRepo.findByEmail(newStudent.getEmail()).isPresent())
        {
            result.rejectValue("email", "Unique", "Student already in use, please select from drop-down menu");
        }
     
        if(result.hasErrors()) 
        {
            return null;
            
        }
        else {
        	return studentRepo.save(newStudent);
        }
    }
	
	public List<Student> allStudents()
	{
		return studentRepo.findAll();
	}

	public Student createStudent(Student student)
	{
		return studentRepo.save(student);
	}
	
	public Student findStudent(Long id)
	{
		Optional<Student> optionalStudent = studentRepo.findById(id);
		
		if (optionalStudent.isPresent())
		{
			return optionalStudent.get();	
		}	
		else
		{
		return null;
		}
	}
	
	public Student updateStudent(Student student)
	{
		return studentRepo.save(student);
	}
	
	public void deleteStudent(Long id)
	{
		studentRepo.deleteById(id);
	}
	
	
}

