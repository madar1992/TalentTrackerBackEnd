package com.example.demo.service;

import java.util.List;
import java.util.Random;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.modal.*;
import com.example.demo.emailservice.EmailSenderService;
import com.example.demo.modal.OtpAndEmail;
import com.example.demo.modal.User;
import com.example.demo.repository.ApplicantProfileRepository;
import com.example.demo.repository.OtpEmailRepository;
import com.example.demo.repository.UserRepository;
import org.apache.pdfbox.text.PDFTextStripper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final OtpEmailRepository otpRepository;
    private final EmailSenderService emailService;
    private final ApplicantProfileRepository applicantProfileRepo;
   

    @Autowired
    public UserService(UserRepository userRepository,EmailSenderService emailService,OtpEmailRepository otpRepository,ApplicantProfileRepository applicantProfileRepo) {
        this.userRepository = userRepository;
        this.otpRepository=otpRepository;
        this.emailService=emailService;
        this.applicantProfileRepo=applicantProfileRepo;
	
    }
    
    public ApplicantProfile UpdateApplicantProfile(ApplicantProfile profileUpdate) {
    	return applicantProfileRepo.save(profileUpdate);
    }
    
    public String resetPassword(User user) {
    	User user1=userRepository.save(user);
    	
    	if(user1!=null) {
    		return "Password reset was done successfully";
    	}
    	return "Password reset was failed";
    }
    public User registerUser(User user) throws MessagingException {
    	
    	emailService.sendEmail(user.getEmail(), "Account Register",
                "<h3>ThankYou for Choosing us</h3>"
                        + "<b><i>Your registration is Completed</i></b><br/><br/>UserName is : <b>"
                        + user.getEmail() + "</b><br/>Password : <b>" + user.getPassword() + "</b>");
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean adminLogin(User user) {
        // Your admin login logic here
        return true;
    }

    public boolean verifyOTP(String email, String enteredOTP) {
    	List<OtpAndEmail> users = otpRepository.findByEmail(email);

        if (users.isEmpty()) {
            return false; // No OTP entries for this email
        }

        for (OtpAndEmail user : users) {
            if (user.getOtp() != null && user.getOtp().equals(enteredOTP)) {
                return true; // Found a matching OTP entry
            }
        }

        return false; // No matching OTP entry found
    }
    public void sendOTP(String email) throws MessagingException {
    	String otp = generateOTP();

        OtpAndEmail userOtp=new OtpAndEmail();
        userOtp.setEmail(email);
        userOtp.setOtp(otp);

        otpRepository.save(userOtp); // Save the new user entity to the database
        String to=email;
        String subject = "OTP for your verification";
        String body = "Please enter this OTP to verify: " + otp;
        emailService.sendEmail(to, subject, body); 
    }

    private String generateOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }

   

    public String convertPdfToJson(MultipartFile pdfFile) {
        try {
            // Use Apache PDFBox to extract text content and convert to JSON
            PDDocument document = PDDocument.load(pdfFile.getInputStream());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();

            // Convert the text content to JSON format as needed

            return text;
        } catch (Exception e) {
            throw new RuntimeException("Error converting PDF to JSON.");
        }
    }

   
}


