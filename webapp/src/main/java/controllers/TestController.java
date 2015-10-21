package controllers;

import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test()
	{
		return "test";
	}
	
	@RequestMapping(value = "/testlogin", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded", produces = "application/x-www-form-urlencoded")
	public @ResponseBody String login(@RequestBody User user)
	{
		System.out.println("we are here");
//		System.out.println(username + password);
		
		return "message";
	}
	
}
