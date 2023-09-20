package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.PostjobDao;
import com.demo.oragejobsite.entity.PostJob;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class PostjobController {
	@Autowired
	private PostjobDao pjd;
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/jobpostinsert")
	public PostJob jobpostinsert(@RequestBody PostJob pj)
	{
			return pjd.save(pj);
		
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/fetchjobpost")
	public List<PostJob> fetchjobpost(){
		return pjd.findAll();
	}
	
	
}

