package com.demo.oragejobsite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.EmployerDao;
import com.demo.oragejobsite.entity.Employer;
import com.demo.oragejobsite.entity.User;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class EmployerController {
	@Autowired
	private EmployerDao ed;
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/insertEmployer")
	public ResponseEntity<Object> insertEmployer(@RequestBody Employer emp) {
	    try {
	        // Generate a random alphanumeric 10-character employer ID
	        // Generate a random UUID as a string
	        String randomString = UUID.randomUUID().toString();

	        // Remove hyphens and special symbols
	        randomString = randomString.replaceAll("-", "");
	        // Set the generated ID as the employer ID
	        emp.setEmpid(randomString);

	        // Check if the employer name already exists in the database
	        Employer existingEmployer = ed.findByEmpmailid(emp.getEmpmailid());

	        if (existingEmployer != null) {
	            // Employer with the same name already exists, return a conflict response
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("Employer with this name already exists");
	        } else {
	            // Employer with the name doesn't exist, save the data
	            ed.save(emp);
	            System.out.println("Employer Created Successfully");
	            
	            // Return the created employer object in the response body
	            return ResponseEntity.status(HttpStatus.CREATED).body(emp);
	        }
	    } catch (DataAccessException e) {
	        // Handle database-related exceptions (e.g., unique constraint violation)
	        // Log the exception message for debugging
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred");
	    } catch (Exception e) {
	        // Handle any other exceptions that may occur
	        // Log the exception message for debugging
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request");
	    }
	}

	
	
	//Fetch Employer Details
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/fetchemployer")
	public ResponseEntity<List<Employer>> fetchemployer() {
	    try {
	        List<Employer> users = ed.findAll();
	        if (users.isEmpty()) {
	            // Return a NOT FOUND response if no users are found
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        } else {
	            // Return a OK response with the list of users
	            return ResponseEntity.ok(users);
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur, and return a INTERNAL SERVER ERROR response
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	
	
	
	//update employer
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/updateEmployee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employer updatedEmployer) {
	    try {
	    	 String empid = updatedEmployer.getEmpid();
             // Log the received UID for debugging
             System.out.println("Received UID: " + empid);
	        // Check if an employer with the provided empid exists
	        Optional<Employer> existingEmployerOptional = ed.findById(updatedEmployer.getEmpid());

	        if (existingEmployerOptional.isPresent()) {
	            // If it exists, update the existing record
	            Employer existingEmployer = existingEmployerOptional.get();

	            // Update the fields you want to change, but only if they are not null in the request
	            if (updatedEmployer.getEmpfname() != null) {
	                existingEmployer.setEmpfname(updatedEmployer.getEmpfname());
	            }
	            if (updatedEmployer.getEmplname() != null) {
	                existingEmployer.setEmplname(updatedEmployer.getEmplname());
	            }
	            if (updatedEmployer.getEmpcompany() != null) {
	                existingEmployer.setEmpcompany(updatedEmployer.getEmpcompany());
	            }
	            if (updatedEmployer.getEmpphone() != null) {
	                existingEmployer.setEmpphone(updatedEmployer.getEmpphone());
	            }
	            if (updatedEmployer.getEmpcountry() != null) {
	                existingEmployer.setEmpcountry(updatedEmployer.getEmpcountry());
	            }
	            if (updatedEmployer.getEmpstate() != null) {
	                existingEmployer.setEmpstate(updatedEmployer.getEmpstate());
	            }
	            if (updatedEmployer.getEmpcity() != null) {
	                existingEmployer.setEmpcity(updatedEmployer.getEmpcity());
	            }
	            if (updatedEmployer.getDescriptionemp() != null) {
	                existingEmployer.setDescriptionemp(updatedEmployer.getDescriptionemp());
	            }
	            
	            // Update the 'verified' field if it's not null in the request
                if (updatedEmployer.isVerifiedemp() != false) {
                	existingEmployer.setVerifiedemp(updatedEmployer.isVerifiedemp());
                }

	            // Save the updated record
	            Employer updatedRecord = ed.save(existingEmployer);

	            // Log the updated data for verification
	            System.out.println("Updated Record: " + updatedRecord.toString());

	            return ResponseEntity.ok(updatedRecord);
	        } else {
	            // If it doesn't exist, create a new record
	            Employer newEmployer = ed.save(updatedEmployer);
	            return ResponseEntity.status(HttpStatus.CREATED).body(newEmployer);
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur, and return an INTERNAL SERVER ERROR response
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}

	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/logincheckemp")
	public ResponseEntity<?> logincheckemp(@RequestBody Employer e12, HttpServletResponse response) {
	    try {
	        String checkemail = e12.getEmpmailid();
	        String checkpass = e12.getEmppass();
	        System.out.println(checkemail + " " + checkpass);

	        Employer checkmail = checkMailUser(checkemail, checkpass);
	        if (checkmail != null) {
	            // Create and set cookies here
	            Cookie employerCookie = new Cookie("emp", checkmail.toString());
	            // Set the domain to match your frontend (e.g., localhost)
	            employerCookie.setDomain("localhost");
	            employerCookie.setMaxAge(3600); // Cookie expires in 1 hour (adjust as needed)
	            response.addCookie(employerCookie);

	            return ResponseEntity.ok(checkmail);
	        }

	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
	    } catch (Exception e) {
	        // Handle any exceptions that may occur
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // You can customize the error response as needed
	    }
	}

	private Employer checkMailUser(String checkemail, String checkpass) {
	    System.out.println("hello");
	    List<Employer> allMails = ed.findAll();
	    for (Employer u1 : allMails) {
	        System.out.println(checkemail);
	        if (u1.getEmpmailid() != null && u1.getEmpmailid().equals(checkemail) && u1.getEmppass() != null && u1.getEmppass().equals(checkpass) && u1.isVerifiedemp()) {
	            System.out.println("inside");
	            return u1; // Email and password match
	        }
	    }
	    return null; // Email and password do not match
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/verifyEmployer")
	public ResponseEntity<Object> verifyEmployer(@RequestBody Map<String, String> request) {
	    try {
	        String empmailid = request.get("empmailid");

	        // Find the employer by empmailid
	        Employer employer = ed.findByEmpmailid(empmailid);

	        if (employer != null) {
	            // Set the 'verifiedemp' field to true
	            employer.setVerifiedemp(true);

	            // Save the updated employer record
	            ed.save(employer);

	            // Create a response JSON object
	            Map<String, Object> response = new HashMap<>();
	            response.put("status", "Employer verified successfully");
	            response.put("employer", employer);

	            return ResponseEntity.ok(response);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employer with empmailid " + empmailid + " not found.");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/deleteEmployer/{empid}")
	public ResponseEntity<?> deleteEmployer(@PathVariable String empid) {
	    try {
	        // Check if an employer with the provided empid exists
	        Optional<Employer> existingEmployerOptional = ed.findById(empid);

	        if (existingEmployerOptional.isPresent()) {
	            // If it exists, delete the employer
	            ed.delete(existingEmployerOptional.get());

	            return ResponseEntity.status(HttpStatus.OK).body(true);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employer with empid " + empid + " not found.");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that may occur
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}

	

	

	




}
