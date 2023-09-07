package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.Employer;
import com.example.demo.modal.User;
import com.example.demo.service.EmployerService;
import com.example.demo.service.UserService;

import jakarta.mail.MessagingException;

@CrossOrigin("*")
@RestController
public class EmployerController {

	@Autowired
	private EmployerService employerService;
	@Autowired
	private UserService userService;

	@PostMapping("/EmpRegister")
    public ResponseEntity<String> registerEmployee(@RequestBody Employer employer) throws MessagingException {
    	System.out.println(employer.getEmail());
    	Employer existingEmployer = employerService.getEmployerByEmail(employer.getEmail());
        if (existingEmployer == null) {
        	Employer registeredUser = employerService.registerEmployer(employer);
            if(registeredUser!=null) {
            	System.out.println("registerd successfully");
            	return ResponseEntity.ok("registerd successfully");
            } 
        }
        else {
        	System.out.println("Email already exit");
            return ResponseEntity.status(401).body("Email already exit");
        }
    	
    	
        
        System.out.println("unable to register");
        return ResponseEntity.status(401).body("unable to register");
    }
	@PostMapping("/empLogin")
	public ResponseEntity<String> employerLogin(@RequestBody Employer employer){
		System.out.println(employer.getEmail());
        Employer existingUser = employerService.getEmployerByEmail(employer.getEmail());
        
     // Check if the existingUser is null
        if (existingUser == null) {
            System.out.println("User with email " + employer.getEmail() + " not found");
            return ResponseEntity.badRequest().body("User with email " + employer.getEmail() + " not found");
        }
        System.out.println(employer.getPassword());
        // Check if the password matches
        if (!employer.getPassword().equals(existingUser.getPassword())) {
            System.out.println("Invalid email or password for user: " + existingUser.getEmail());
            return ResponseEntity.badRequest().body("Invalid email or password");
        }

        // Login successful
        return ResponseEntity.ok("Employer Login successful");
	}
	
	 @PostMapping("/verifyEmployerEmail")
	    public ResponseEntity<String> verifyEmail(@RequestBody Map<String, String> requestData) throws MessagingException{
	    	
	        String email = requestData.get("email");
	        Employer user=employerService.getEmployerByEmail(email);
	        if(user!=null) {
	        	userService.sendOTP(email);
	        	  
	            return ResponseEntity.ok("OTP sent successfully");
	        }
	        return ResponseEntity.ok("User is not found with this Email Id");
	    }
		@PostMapping("/reset-Employer-password")
	    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestData) throws MessagingException{
	    	
	        String email = requestData.get("email");
	        String password=requestData.get("password");
	        
	        Employer user=employerService.getEmployerByEmail(email);
	        user.setRecuterid(user.getRecuterid());
	        user.setPassword(password);
	        String str="";
	        if(user!=null) {
	        	  str=employerService.resetPassword(user);
	        }
	        if(str.equals("Password reset was done successfully")) {
	  		  return ResponseEntity.ok("Password reset was done successfully");
	  	  }else {
	  		  return ResponseEntity.ok("Password reset was failed");
	  	  }
	    }
}
