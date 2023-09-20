package com.demo.oragejobsite.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.UserDao;
import com.demo.oragejobsite.entity.User;

@CrossOrigin(origins="http://159.203.168.51")
@RestController
public class UserController {

	@Autowired
	private UserDao ud;
	
	
	@CrossOrigin(origins="http://159.203.168.51")
	@PostMapping("/insertusermail")
	public User insertusermail(@RequestBody User c1)
	{
			return ud.save(c1);
		
	}
	
	@CrossOrigin(origins="http://159.203.168.51")
	@PostMapping("/logincheck")
	public User logincheck(@RequestBody User c12, HttpServletResponse response)
	{
		String checkemail = c12.getUserName();
		System.out.println(checkemail+"hello3");
		String checkpass = c12.getUserPassword();
		
		System.out.println(checkpass+"hello3");
		User checkmail = checkMailUser(checkemail,checkpass);
		 if (checkmail!=null) {
	            // Create and set cookies here
//	            Cookie userCookie = new Cookie("username", checkemail);
//	            userCookie.setMaxAge(3600); // Cookie expires in 1 hour (adjust as needed)
//	            userCookie.setDomain("http://localhost");
//	            response.addCookie(userCookie);
			 
			 Cookie userCookie = new Cookie("user", checkemail);
//			 userCookie.setMaxAge(3600);
//			 userCookie.setDomain(""); // Set the domain to match your frontend
			 response.addCookie(userCookie);

	            return checkmail;
	        }
	        return null;

	}
	private User checkMailUser(String checkemail,String checkpass) {
		System.out.println("hello");
		// TODO Auto-generated method stub
		  List<User> allMails = ud.findAll();
		  for (User u1 : allMails) {
			  System.out.println(checkemail);
		        if (u1.getUserName().equals(checkemail) && u1.getUserPassword().equals(checkpass)) {
		           System.out.println("inside");
		        	return u1; // Email already exists
		        }
		    }
		return null;
	}
	
	
	
	@CrossOrigin(origins="http://159.203.168.51")
	@GetMapping("/fetchuser")
	public List<User> fetchuser(){
		return ud.findAll();
	}
	

	
}
