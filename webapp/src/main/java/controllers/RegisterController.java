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
	UserDAO dao;
	@Autowired
	ServiceRegister service;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String register(HttpServletRequest request, ModelMap map)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		String resp = service.register(username, password, type);
		
		if(resp.equals("success"))
		{
			User user = new User(username, password, type);
			HttpSession s = request.getSession();
			s.setAttribute("user", user);			
		}
		
		return resp;
		
	}
	
	

}
