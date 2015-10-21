package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ServiceLogin;
import dao.UserDAO;

@Controller
@RequestMapping(value = { "/login", "/login/" })
public class LoginController {
	
	@Autowired
	UserDAO dao;
	@Autowired
	ServiceLogin service;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String login(@RequestParam (value = "username", required = true)String username,
										@RequestParam (value = "password", required = true)String password,
										ModelMap map, HttpServletRequest request)
	{	
		User u = service.login(username, password);
		
		if(u == null)
		{
			return "fail";
		}
		else
		{
			HttpSession s = request.getSession();
			s.setAttribute("user", u);
			
			return "success";
		}
		
		/*User u = dao.getUserByUsername(username);
		if(u != null)
		{
			if(password.equals(u.getPassword()))
			{
				HttpSession s = request.getSession();
				s.setAttribute("user", u);
				
				return "success";
			}
			else
			{
				return "passwordfail";
			}
		}
		else
		{
			return "usernamefail";
		}*/
	}
	
}
