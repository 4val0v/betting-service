package controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import config.MySessionFactory;
import config.AppConfig;
import dao.BettingDAO;

@Controller
public class BettingController {

	@Autowired
	BettingDAO dao;
	
	@RequestMapping(value = "/bettings", method = RequestMethod.GET)
	public @ResponseBody String getAllBetings()
	{		
		ObjectMapper mapper = new ObjectMapper();
		
		String response = "";
		try {
			 response = mapper.writeValueAsString(dao.getAllBettings());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		}
		
		return response;
	}
	
	
	
}
