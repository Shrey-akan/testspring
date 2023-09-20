package com.demo.oragejobsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.NotificationDao;
import com.demo.oragejobsite.entity.Notification;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class NotificationController {
	@Autowired
	private NotificationDao nd;
	
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/insertnotification")
	public Notification insertnotification(@RequestBody Notification nn)
	{
			return nd.save(nn);
		
	}
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/fetchnotify")
	public List<Notification> fetchnotify(){
		return nd.findAll();
	}
	
}
