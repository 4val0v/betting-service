package controllers;

import java.io.IOException;
import java.util.ArrayList;

import model.Offer;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ServiceV1;
import config.AppConfig;
import dao.BettingDAO;
import dao.BettingDAOImpl;
import dao.MatchDAO;
import dao.MatchDAOImpl;
import dao.TipDAO;
import dao.TipDAOImpl;

@Controller
@RequestMapping("/search")
public class ServiceController {

	@Autowired
	ServiceV1 service;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String find(@RequestParam(value = "stake", required = true)float stake,
										@RequestParam(value = "profit", required = true)float profit)
	{	
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<Offer> offers = service.findPotentialMatches(stake, profit);
		
		String response = "";
		
		try {
			response = mapper.writeValueAsString(offers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		
		return response;
	}
	
}
