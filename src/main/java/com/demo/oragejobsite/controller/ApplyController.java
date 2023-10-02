package com.demo.oragejobsite.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.ApplyDao;
import com.demo.oragejobsite.entity.ApplyJob;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ApplyController {
	@Autowired
	private ApplyDao apd;

	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/insertapplyjob")
	public ResponseEntity<?> insertapplyjob(@RequestBody ApplyJob applyjob) {
	    try {
	    	
	    	// Generate a random UUID as a string
	        String randomString = UUID.randomUUID().toString();

	        // Remove hyphens and special symbols
	        randomString = randomString.replaceAll("-", "");

        // Set the randomUid as the UID for the user
    	 applyjob.setJuid(randomString);
	        // Check if you need any validation logic here before saving the applyjob

	        // Save the applyjob
	        ApplyJob savedApplyJob = apd.save(applyjob);

	        return ResponseEntity.status(HttpStatus.CREATED).body(savedApplyJob);
	    } catch (DataAccessException e) {
	        // Handle database-related exceptions (e.g., constraint violations)
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle any other exceptions that may occur
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}

	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/fetchapplyform")
	public ResponseEntity<?> fetchapplyform() {
	    try {
	        // Fetch all ApplyJob records
	        List<ApplyJob> applyJobs = apd.findAll();

	        return ResponseEntity.ok(applyJobs);
	    } catch (DataAccessException e) {
	        // Handle database-related exceptions
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred: " + e.getMessage());
	    } catch (Exception e) {
	        // Handle any other exceptions that may occur
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
	    }
	}

}
