package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ServiceRegister;
import dao.UserDAO;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	ServiceRegister service;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String register(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		User user = service.register(username, password, type);
		
		if(user != null)
		{
			HttpSession s = request.getSession();
			s.setAttribute("user", user);
			return "success";
		}
		else
		{
			return "fail";
		}
	}
	
	

}
