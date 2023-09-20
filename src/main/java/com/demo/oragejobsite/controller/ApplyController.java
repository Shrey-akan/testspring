package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.ApplyDao;
import com.demo.oragejobsite.entity.ApplyJob;

@CrossOrigin(origins="http://159.203.168.51")
@RestController
public class ApplyController {
	@Autowired
	private ApplyDao apd;
	@CrossOrigin(origins="http://159.203.168.51")
	@PostMapping("/insertapplyjob")
	public ApplyJob insertapplyjob(@RequestBody ApplyJob applyjob)
	{
			return apd.save(applyjob);
		
	}
	
	
	@CrossOrigin(origins="http://159.203.168.51")
	@GetMapping("/fetchapplyform")
	public List<ApplyJob> fetchapplyform(){
		return apd.findAll();
	}
}
