package com.codingdojo.javaexam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.javaexam.services.CourseService;
import com.codingdojo.javaexam.services.InstructorService;
import com.codingdojo.javaexam.models.Course;
import com.codingdojo.javaexam.models.Instructor;
import com.codingdojo.javaexam.models.LoginInstructor;

@Controller

public class HomeController {
	
	@Autowired
	private InstructorService instructorServ;

	@Autowired
	private CourseService courseServ;
	
	
	@GetMapping("/")
	public String index(Model model)
	{
		model.addAttribute("newInstructor", new Instructor());
		model.addAttribute("newLogin", new LoginInstructor());
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newInstructor") 
							Instructor newInstructor,
							BindingResult result, 
							Model model, 
							HttpSession session)
	{
		instructorServ.register(newInstructor, result);
		
		if(result.hasErrors())
		{
			model.addAttribute("newLogin", new LoginInstructor());
			return "index.jsp";
		}
		session.setAttribute("instructor_id", newInstructor.getId());
		return "redirect:/courses";
		
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin")
						LoginInstructor newLogin,
						BindingResult result,
						Model model,
						HttpSession session)
	{
		Instructor instructor = instructorServ.login(newLogin, result);
		if(result.hasErrors())
		{
			model.addAttribute("newInstructor", new Instructor());
			return "index.jsp";
		}
		session.setAttribute("instructor_id", instructor.getId());
		return "redirect:/courses";
	}

	@GetMapping("/courses")
	public String courses(HttpSession session,
							Model model) 
	{
		if(session.getAttribute("instructor_id") != null) {
			
			List<Course> allCourses = courseServ.allCourses();
			
			model.addAttribute("courses", allCourses);
			
			Instructor instructor = instructorServ.findInstructor((Long) session.getAttribute("instructor_id"));
			model.addAttribute("instructor", instructor);
			
			return "dashboard.jsp";
		}
		else 
		{
		return "redirect:/";
		}
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("instructor_id");
		return "redirect:/";
	}
}
