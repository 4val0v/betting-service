package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	@RequestMapping(method = RequestMethod.GET)
	public String redirect(HttpServletRequest request)
	{	
		HttpSession s = request.getSession();
		
		User user = (User) s.getAttribute("user");
		
		if(user == null)
		{
			return "index";
		}
		else
		{
			return user.getType();
		}
	}
	
	
}
