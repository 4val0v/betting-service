package controllers;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ServiceMatches;
import dao.MatchDAO;

@Controller
public class MatchesController {

	@Autowired
	ServiceMatches service;
	
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping(value = "/matchesforbetting", method = RequestMethod.GET)
	public @ResponseBody String getMatchesFor(@RequestParam(value = "id", required = true)int idBet)
	{
		//ObjectMapper mapper = new ObjectMapper();
		
		String matches = "";
		
		try {
			matches = mapper.writeValueAsString(service.getMatchesForBetting(idBet));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		
		return matches;
	}
	
}
