package com.codingdojo.javaexam.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.javaexam.models.Instructor;
import com.codingdojo.javaexam.models.LoginInstructor;
import com.codingdojo.javaexam.repositories.InstructorRepository;

@Service

public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepo;

    public Instructor register(Instructor newInstructor, BindingResult result)
    {
        if(instructorRepo.findByEmail(newInstructor.getEmail()).isPresent())
        {
            result.rejectValue("email", "Unique", "This email is already in use.");
        }
        if (!newInstructor.getPassword().equals(newInstructor.getConfirm()))
        {
            result.rejectValue("confirm", "Matches", "The confirm password must match Password.");
        }
        if(result.hasErrors()) 
        {
            return null;
        }
        else 
        {
            String hashed = BCrypt.hashpw(newInstructor.getPassword(), BCrypt.gensalt());
            newInstructor.setPassword(hashed);
            return instructorRepo.save(newInstructor);
        }
    }

    public Instructor login(LoginInstructor newLogin, BindingResult result)
    {
        if(result.hasErrors()) 
        {
            return null;
        }
    

    Optional<Instructor> potentialInstructor = instructorRepo.findByEmail(newLogin.getEmail());
    if(!potentialInstructor.isPresent())
    
    {
        result.rejectValue("email", "Unique", "Unknown email.");
        return null;
    }

    Instructor instructor = potentialInstructor.get();
    if(!BCrypt.checkpw(newLogin.getPassword(), instructor.getPassword()))
    {
        result.rejectValue("password", "Matches", "Invalid Password");
    }
    if (result.hasErrors())
    {
        return null;
    }
    else
    {
        return instructor;
    }
    }

    public Instructor findInstructor(Long id) 
	{
		Optional<Instructor> optionalInstructor = instructorRepo.findById(id);
		
		if (optionalInstructor.isPresent()) 
		{
			return optionalInstructor.get();
		}	else {
			return null;
		}
	}
    

}
