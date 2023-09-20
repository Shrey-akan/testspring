package com.demo.oragejobsite.controller;

import java.util.List;
import java.util.Map;

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

@CrossOrigin(origins="https://oragetechui.vercel.app")
@RestController
public class EmployerController {
	@Autowired
	private EmployerDao ed;
	
	
	@CrossOrigin(origins="https://oragetechui.vercel.app")
	@PostMapping("/insertemployer")
	public Employer insertemployer(@RequestBody Employer emp)
	{
		
			return ed.save(emp);
		
	}
	
	
	@CrossOrigin(origins = "https://oragetechui.vercel.app")
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

	
	
	
	@CrossOrigin(origins="https://oragetechui.vercel.app")
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
	
	
	
	@CrossOrigin(origins="https://oragetechui.vercel.app")
	@GetMapping("/fetchemployer")
	public List<Employer> fetchemployer(){
		return ed.findAll();
	}
}
