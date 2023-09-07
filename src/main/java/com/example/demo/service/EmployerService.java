package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.emailservice.EmailSenderService;
import com.example.demo.modal.Employer;
import com.example.demo.modal.User;
import com.example.demo.repository.EmployerRepository;

import jakarta.mail.MessagingException;

@Service
public class EmployerService {

	@Autowired
	private EmployerRepository employerRepository;
	@Autowired
	private EmailSenderService emailService;
	
	
	public Employer getEmployerByEmail(String email) {
		
		return this.employerRepository.findByEmail(email);
		
	}

	public String resetPassword(Employer employer) {
    	Employer user1=employerRepository.save(employer);
    	
    	if(user1!=null) {
    		return "Password reset was done successfully";
    	}
    	return "Password reset was failed";
    }

	 public Employer registerEmployer(Employer employer) throws MessagingException {
    	
    	
		emailService.sendEmail(employer.getEmail(), "Account Register",
                "<h3>ThankYou for Choosing us</h3>"
                        + "<b><i>Your registration is Completed</i></b><br/><br/>UserName is : <b>"
                        + employer.getEmail() + "</b><br/>Password : <b>" + employer.getPassword() + "</b>");
        return employerRepository.save(employer);
    }
	 
	
}
