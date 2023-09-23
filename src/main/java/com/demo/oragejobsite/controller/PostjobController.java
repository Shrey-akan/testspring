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

@CrossOrigin(origins="http://159.203.168.51")
@RestController
public class PostjobController {
	@Autowired
	private PostjobDao pjd;
	
	
	@CrossOrigin(origins="http://159.203.168.51")
	@PostMapping("/jobpostinsert")
	public PostJob jobpostinsert(@RequestBody PostJob pj)
	{
			return pjd.save(pj);
		
	}
	
	@CrossOrigin(origins="http://159.203.168.51")
	@GetMapping("/fetchjobpost")
	public List<PostJob> fetchjobpost(){
		return pjd.findAll();
	}
	
	
}

