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

import com.demo.oragejobsite.dao.UserDao;
import com.demo.oragejobsite.entity.Employer;
import com.demo.oragejobsite.entity.User;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	private UserDao ud;
		
	
	
	//Insert User And Also Check if the user already exist in the database 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/insertusermail")
	public ResponseEntity<Object> insertusermail(@RequestBody User c1) {
	    try {
	    	// Generate a random UUID as a string
	        String randomString = UUID.randomUUID().toString();

	        // Remove hyphens and special symbols
	        randomString = randomString.replaceAll("-", "");

	        // Set the randomString as the Juid for the ApplyJob
	        c1.setUid(randomString);

	   
	        // Set the initial value of 'verify' to false
	        c1.setVerified(false);

	        // Check if the userName already exists in the database
	        User existingUser = ud.findByUserName(c1.getUserName());

	        if (existingUser != null) {
	            // User with the same userName already exists, return a conflict response
	            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this username already exists");
	        } else {
	            // User with the userName doesn't exist, save the data
	            ud.save(c1);
	            System.out.println("User Created Successfully");
	            return ResponseEntity.status(HttpStatus.CREATED).body(c1);
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





	//fetch user data
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/fetchuser")
	public ResponseEntity<List<User>> fetchuser() {
	    try {
	        List<User> users = ud.findAll();
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
	
	//Update User data
	@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser) {
        try {
        	 String uid = updatedUser.getUid();
             // Log the received UID for debugging
             System.out.println("Received UID: " + uid);

             // Check if a user with the provided UID exists
             Optional<User> existingUserOptional = ud.findById(uid);
             System.out.println("Existing User Optional: " + existingUserOptional);
            if (existingUserOptional.isPresent()) {
                // If it exists, update the existing record
                User existingUser = existingUserOptional.get();
                
                // Update the fields you want to change, but only if they are not null in the request
                if (updatedUser.getUserName() != null) {
                    existingUser.setUserName(updatedUser.getUserName());
                }
                if (updatedUser.getUserFirstName() != null) {
                    existingUser.setUserFirstName(updatedUser.getUserFirstName());
                }
                if (updatedUser.getUserLastName() != null) {
                    existingUser.setUserLastName(updatedUser.getUserLastName());
                }
                if (updatedUser.getCompanyuser() != null) {
                    existingUser.setCompanyuser(updatedUser.getCompanyuser());
                }
                if (updatedUser.getUserphone() != null) {
                    existingUser.setUserphone(updatedUser.getUserphone());
                }
                if (updatedUser.getUsercountry() != null) {
                    existingUser.setUsercountry(updatedUser.getUsercountry());
                }
                if (updatedUser.getUserstate() != null) {
                    existingUser.setUserstate(updatedUser.getUserstate());
                }
                if (updatedUser.getUsercity() != null) {
                    existingUser.setUsercity(updatedUser.getUsercity());
                }
                if (updatedUser.getWebsiteuser() != null) {
                    existingUser.setWebsiteuser(updatedUser.getWebsiteuser());
                }
                
                // Update the 'verified' field if it's not null in the request
                if (updatedUser.isVerified() != false) {
                    existingUser.setVerified(updatedUser.isVerified());
                }

                // Save the updated record
                User updatedRecord = ud.save(existingUser);

                // Log the updated data for verification
                System.out.println("Updated Record: " + updatedRecord.toString());

                return ResponseEntity.ok(updatedRecord);
            } else {
                // If the user with the provided UID doesn't exist, return a NOT FOUND response
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with UID " + updatedUser.getUid() + " not found.");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur, and return an INTERNAL SERVER ERROR response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
        }
    }
	
	
	
	
	
	
	@CrossOrigin(origins="http://localhost:4200")
	 @PostMapping("/logincheck")
    public ResponseEntity<?> logincheck(@RequestBody User c12, HttpServletResponse response) {
        String checkemail = c12.getUserName();
        String checkpass = c12.getUserPassword();

        User checkmail = checkMailUser(checkemail, checkpass);

        if (checkmail != null) {
            // Create and set cookies here
            Cookie userCookie = new Cookie("user", checkemail);
            userCookie.setMaxAge(3600); // Cookie expires in 1 hour (adjust as needed)
            userCookie.setPath("/"); // Set the path to match your frontend
            response.addCookie(userCookie);

            return ResponseEntity.ok(checkmail);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // ... Other methods ...

    private User checkMailUser(String checkemail, String checkpass) {
        // TODO Auto-generated method stub
        List<User> allMails = ud.findAll();
        for (User u1 : allMails) {
        	if (u1.getUserName().equals(checkemail) && u1.getUserPassword().equals(checkpass) && u1.isVerified()) {
                return u1; // User found, return user details
            }
        }
        return null; // User not found
    }
	
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/verifyUser")
    public ResponseEntity<?> verifyUser(@RequestBody Map<String, String> request) {
        try {
            String userName = request.get("userName");

            // Find the user by userName
            User user = ud.findByUserName(userName);

            if (user != null) {
                // Set the 'verified' field to true
                user.setVerified(true);

                // Save the updated user record
                ud.save(user);
                Map<String, Object> response = new HashMap<>();
	            response.put("status", "User verified successfully");
	            response.put("employer", user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with userName " + userName + " not found.");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
        }
    }



	
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteUser/{uid}")
    public ResponseEntity<Object> deleteUserByUid(@PathVariable String uid) {
        try {
            // Check if a user with the provided UID exists
            Optional<User> existingUserOptional = ud.findById(uid);

            if (existingUserOptional.isPresent()) {
                // If it exists, delete the user
                ud.delete(existingUserOptional.get());

                return ResponseEntity.status(HttpStatus.OK).body(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with UID " + uid + " not found.");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request: " + e.getMessage());
        }
    }

	
}
