package com.codingdojo.javaexam.controllers;

import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.javaexam.models.Course;
import com.codingdojo.javaexam.models.CourseStudent;
import com.codingdojo.javaexam.models.Instructor;
import com.codingdojo.javaexam.models.Student;
import com.codingdojo.javaexam.services.CourseService;
import com.codingdojo.javaexam.services.CourseStudentService;
import com.codingdojo.javaexam.services.InstructorService;
import com.codingdojo.javaexam.services.StudentService;

@Controller

public class CourseController {
	
	@Autowired
	CourseService courseServ;
	
	@Autowired
	InstructorService instructorServ;
	
	@Autowired
	StudentService studentServ;
	
	@Autowired
	CourseStudentService courseStudentServ;
	
	@RequestMapping("/courses/new")
	public String courseForm(@ModelAttribute Course course,
							HttpSession session,
							Model model)
	{
		if(session.getAttribute("instructor_id") != null) {
			
			Instructor instructor = instructorServ.findInstructor((Long) session.getAttribute("instructor_id"));
			model.addAttribute("instructor", instructor);
			
			return "/courses/form.jsp";
		}
		else 
		{
		return "redirect:/";
		}
		
	}
	
	@PostMapping("/course/post")
	public String postCourse(@Valid
							@ModelAttribute("course")
							Course course,
							BindingResult result)
	{
		if (result.hasErrors()) {
			return "/courses/form.jsp";
		} else {
			
			LocalTime courseTime = new LocalTime (Localtime) course.time
			
			courseServ.createCourse(course);
			return "redirect:/courses";
		}
	}
	
	@RequestMapping("/courses/{id}")
	public String showCourse(@PathVariable("id") Long id,
							@ModelAttribute("student") Student student,
							@ModelAttribute("courseStudent")
							CourseStudent courseStudent,
							Model model,
							HttpSession session)
	{
	
	List<Student> allStudents = studentServ.allStudents();
	
	model.addAttribute("students", allStudents);
		
	model.addAttribute("instructor_id", session.getAttribute("instructor_id"));
	
	Course course = courseServ.findCourse(id);

	model.addAttribute("course", course);

	return "/courses/show.jsp";
	}
	
	@GetMapping("/courses/{id}/edit")
	public String edit(@PathVariable("id") 
						Long id, 
						Model model) 
	{
		Course course = courseServ.findCourse(id);
		
		model.addAttribute("course", course);
		
		return "/courses/edit.jsp";
	}
	
	@PutMapping("/courses/{id}")
	public String update(@Valid
						@ModelAttribute("course") 
						Course course, 
						BindingResult result,
						Model model)
	{	
		
		if (result.hasErrors()) {
			return "/courses/edit.jsp";
		} else {
			courseServ.createCourse(course);
			return "redirect:/courses";
		}
	}
	
	@RequestMapping(value="/courses/{id}", method=RequestMethod.DELETE)
	public String destroy(@PathVariable("id") Long id) {
	courseServ.deleteCourse(id);
	return "redirect:/courses";
	}
	
	@PostMapping("/student/post")
	public String postStudent(@Valid
							@ModelAttribute("student")
							Student student,
							BindingResult result)
				{
						studentServ.createStudent(student);
						
						return "redirect:/courses";
				}
	
	@PostMapping("/join")
	public String join (@ModelAttribute("courseStudent")
						CourseStudent courseStudent)
	{
	courseStudentServ.createCourseStudent(courseStudent);
	
	return "redirect:courses";
	}
	
	
	

}
