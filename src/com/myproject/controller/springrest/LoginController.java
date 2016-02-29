package com.myproject.controller.springrest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.model.*;

@RestController
public class LoginController {
	
	LoginDetails loginDetails = new LoginDetails();
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public @ResponseBody LoginDetails home() {
		System.out.println("I am here");
		loginDetails.setUsername("admin");
		loginDetails.setPassword("admin");
		return loginDetails;		
	}
}
