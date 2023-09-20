package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.ConatctDao;
import com.demo.oragejobsite.entity.Contact;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ContactController {
	@Autowired
	private ConatctDao cd;
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/insertcontact")
	public Contact insertcontact(@RequestBody Contact contact)
	{
			return cd.save(contact);
		
	}
	
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/fetchcontact")
	public List<Contact> fetchcontact(){
		return cd.findAll();
	}
}
