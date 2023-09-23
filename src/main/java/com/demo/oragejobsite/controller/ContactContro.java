package com.demo.oragejobsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.Contactfrontdao;
import com.demo.oragejobsite.entity.DirectConntact;



@CrossOrigin(origins="http://159.203.168.51")
@RestController
public class ContactContro {
	@Autowired
	private Contactfrontdao cfd;
	@CrossOrigin(origins="http://159.203.168.51")
	@PostMapping("/contactform")
	public DirectConntact contactform(@RequestBody DirectConntact frontcon)
	{
			return cfd.save(frontcon);
		
	}
}
