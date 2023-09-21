package com.demo.oragejobsite.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.EmployerDao;
import com.demo.oragejobsite.entity.Employer;

@CrossOrigin(origins="http://159.203.168.51")
@RestController
public class EmployerController {
	@Autowired
	private EmployerDao ed;
	
	
	@CrossOrigin(origins="http://159.203.168.51")
	@PostMapping("/insertemployer")
	public Employer insertemployer(@RequestBody Employer emp)
	{
		
			return ed.save(emp);
		
	}
	
	
	@CrossOrigin(origins = "http://159.203.168.51")
	@PostMapping("/insertemployeremail")
	public Employer insertemployeremail(@RequestBody Map<String, String> requestBody) {
	    String empmailid = requestBody.get("empmailid");
	    
	    if (empmailid != null) {
	        Employer employer = new Employer();
	        employer.setEmpmailid(empmailid);

	        // Log the employer object before saving
	        System.out.println("Employer object to be saved: " + employer.toString());

	        return ed.save(employer);
	    } else {
	        // Handle the case when empmailid is missing or null
	        return null; // or return an error response
	    }
	}

	
	
	
	@CrossOrigin(origins="http://159.203.168.51")
	@PostMapping("/logincheckemp")
	public Employer logincheckemp(@RequestBody Employer e12, HttpServletResponse response)
	{
		String checkemail = e12.getEmpmailid();
		String checkpass = e12.getEmppass();
		System.out.println(checkemail +"this "+ checkpass);
		
		Employer checkmail = checkMailUser(checkemail,checkpass);
		 if (checkmail!=null) {
	            // Create and set cookies here
//	            Cookie userCookie = new Cookie("username", checkemail);
//	            userCookie.setMaxAge(3600); // Cookie expires in 1 hour (adjust as needed)
//	            userCookie.setDomain("http://localhost");
//	            response.addCookie(userCookie);
			 
			 Cookie employerCookie = new Cookie("emp", checkmail.toString());
//			 userCookie.setMaxAge(3600);
//			 userCookie.setDomain(""); // Set the domain to match your frontend
			 response.addCookie(employerCookie);
			 
	            return checkmail;
	        }
	        return new Employer();
		
			
		
	}
	private Employer checkMailUser(String checkemail, String checkpass) {
		

		  
		  System.out.println("hello");
			// TODO Auto-generated method stub
			  List<Employer> allMails = ed.findAll();
			  for (Employer u1 : allMails) {
				  System.out.println(checkemail);
			        if (u1.getEmpmailid().equals(checkemail) && u1.getEmppass().equals(checkpass)) {
			           System.out.println("inside");
			        	return u1; // Email already exists
			        }
			    }
			return null;
		  
//		return new Employer();
	}
	
	
	
	@CrossOrigin(origins="http://159.203.168.51")
	@GetMapping("/fetchemployer")
	public List<Employer> fetchemployer(){
		return ed.findAll();
	}
	
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/updateEmployee")
	public Employer updateEmployee(@RequestBody Employer s1) {
//		 Optional<Employer> existingEmployer = ed.findById(s1.getEmpid());
		Optional<Employer> existingEmployerOptional = ed.findByEmppass(s1.getEmppass());

		if (existingEmployerOptional.isPresent()) {
		        // If it exists, update the existing record
		        Employer employerToUpdate = existingEmployerOptional.get();
		        // Update the fields you want to change
		        employerToUpdate.setEmpfname(s1.getEmpfname());
		        employerToUpdate.setEmplname(s1.getEmplname());
		        employerToUpdate.setEmpcompany(s1.getEmpcompany());
		        
		        employerToUpdate.setEmppass(s1.getEmppass());
		        employerToUpdate.setEmpphone(s1.getEmpphone());
		        employerToUpdate.setEmpcountry(s1.getEmpcountry());
		        employerToUpdate.setEmpstate(s1.getEmpstate());
		        employerToUpdate.setEmpcity(s1.getEmpcity());
		        employerToUpdate.setDescriptionemp(s1.getDescriptionemp());
		        // Update other fields as needed
		        // Save the updated record
		        return ed.save(employerToUpdate);
		    } else {
		        // If it doesn't exist, create a new record
		        return ed.save(s1);
		    }
	}
}
