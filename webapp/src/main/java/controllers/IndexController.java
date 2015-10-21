package controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest request)
	{	
		HttpSession s = request.getSession();
		
		User u = (User) s.getAttribute("user");
		
		if(u != null)
		{
			return u.getType();
		}
		
		map.addAttribute("message", "HI");
		
		return "index";
	}
	
	
}
