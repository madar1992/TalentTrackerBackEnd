package com.example.demo.controller;


import java.util.List;
import com.example.demo.modal.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.modal.Employer;
import com.example.demo.modal.OtpAndEmail;
import com.example.demo.modal.User;
import com.example.demo.service.EmployerService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.MessagingException;

@CrossOrigin("*")
@RestController
public class UserController {

	
	private final UserService userService;
	
	private final EmployerService employerService;
	
	
	@Autowired
	public UserController(UserService userService, EmployerService employerService) {
		super();
		this.userService = userService;
		this.employerService = employerService;
	}

    @PostMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestBody Map<String, String> requestData) throws MessagingException{
    	
        String email = requestData.get("email");
        User user=userService.getUserByEmail(email);
        if(user!=null) {
        	userService.sendOTP(email);
        	  
            return ResponseEntity.ok("OTP sent successfully");
        }
        return ResponseEntity.ok("User is not found with this Email Id");
    }
	@PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestData) throws MessagingException{
    	
        String email = requestData.get("email");
        String password=requestData.get("password");
        
        User user=userService.getUserByEmail(email);
        user.setId(user.getId());
        user.setPassword(password);
        String str="";
        if(user!=null) {
        	  str=userService.resetPassword(user);
        }
        if(str.equals("Password reset was done successfully")) {
  		  return ResponseEntity.ok("Password reset was done successfully");
  	  }else {
  		  return ResponseEntity.ok("Password reset was failed");
  	  }
    }
	
	
	@PostMapping("/updateProfile")
    public ResponseEntity<ApplicantProfile> createOrUpdateApplicantProfile(@RequestBody ApplicantProfile applicantProfile) {
		
		System.out.println(applicantProfile.getGraduationDetails());
        ApplicantProfile savedProfile = userService.UpdateApplicantProfile(applicantProfile);
        return ResponseEntity.ok(savedProfile);
    }
	

//	@PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> requestData) throws MessagingException {
//        String email = requestData.get("email");
//
//        userService.sendOTP(email);
//        return ResponseEntity.ok("OTP sent successfully");
//    }
    
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestBody Map<String, String> requestData) throws MessagingException {
        String email = requestData.get("email");
        
        userService.sendOTP(email);
  
        return ResponseEntity.ok("OTP sent successfully");
    }
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody OtpAndEmail requestData) {
        String email = requestData.getEmail();
        String otp = requestData.getOtp();

        boolean isOTPVerified = userService.verifyOTP(email, otp);

        if (isOTPVerified) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) throws MessagingException {
    	System.out.println(user.getEmail());
    	User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser == null) {
        	User registeredUser = userService.registerUser(user);
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
    

    	@PostMapping("/login")
    	public ResponseEntity<User> loginUser(@RequestBody User user) {
    		
            User existingUser = userService.getUserByEmail(user.getEmail());
            
         // Check if the existingUser is null
            if (existingUser == null) {
                
                return ResponseEntity.badRequest().body(existingUser);
            }
            
            // Check if the password matches
            if (!user.getPassword().equals(existingUser.getPassword())) {
               
                return ResponseEntity.badRequest().body(existingUser);
            }

            // Login successful
            return ResponseEntity.ok(existingUser);
        }
    	@PostMapping("/adminlogin")
        public ResponseEntity<String> adminlogin(@RequestBody User user) {


            boolean b=userService.adminLogin(user);

            if(b) {
                return new ResponseEntity<>("success",HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
            }
        }

    }



