package controllers;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import config.MySessionFactoryConfig;
import dao.TipDAO;
import dao.TipDAOImpl;

@Controller
public class TipController {

	@Autowired
	TipDAO dao;
	
	@RequestMapping(value = "/tips", method = RequestMethod.GET)
	public @ResponseBody String getAllTips()
	{
		
		ObjectMapper mapper = new ObjectMapper();
		
		String resp = "";
		
		try {
			resp = mapper.writeValueAsString(dao.getAllTips());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
		
		return resp;
	}
		
}
