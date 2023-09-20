package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.ResumeDao;
import com.demo.oragejobsite.entity.ResumeBuilder;

@CrossOrigin(origins="http://159.203.168.51")
@RestController
public class Resumecontroller {
	
	@Autowired
	private ResumeDao rm;
	
	@CrossOrigin(origins="http://159.203.168.51")
	@PostMapping("/resumeinsert")
	public ResumeBuilder resumeinsert(@RequestBody ResumeBuilder resume)
	{
			return rm.save(resume);
		
	}
	
	@CrossOrigin(origins="http://159.203.168.51")
	@GetMapping("/fetchresumedetails")
	public List<ResumeBuilder> fetchresumedetails(){
		return rm.findAll();
	}
	
	
	
	
	
}
