package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Offer;
import model.User;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ServiceBet;
import dao.BettingDAO;
import dao.MatchTicketDAO;
import dao.TicketDAO;
import dao.UserDAO;

@Controller
public class TicketController {

	@Autowired
	TicketDAO dao;
	@Autowired
	BettingDAO bd;
	@Autowired
	MatchTicketDAO mtd;
	@Autowired
	UserDAO ud;
	
	@RequestMapping(value = "/tickets/all", method = RequestMethod.GET)
	public @ResponseBody String getAllTickets()
	{	
		ObjectMapper mapper = new ObjectMapper();
		String resp = "";
		
		try {
			resp = mapper.writeValueAsString(dao.getAllTickets());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		}
		
		return resp;
	}
	
	@RequestMapping(value = "/tickets/betting", method = RequestMethod.GET)
	public @ResponseBody String getTicketsForBetting(@RequestParam(value = "name", required = true)String name)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		String resp = "";

		try {
			if(bd.getBettingByName(name) == null)
			{
				return "{\"message\":\"Betting doesn't exist\"}";
			}
			int id = bd.getBettingByName(name).getId();
			resp = mapper.writeValueAsString(dao.getAllTicketsForBetting(id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
		
		return resp;
	}
	
	@RequestMapping(value = "/tickets/user", method = RequestMethod.GET)
	public @ResponseBody String getTicketsForUser(@RequestParam(value = "user", required = true)String user)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		String resp = "";
		
		try{
			if(ud.getUserByUsername(user) == null)
			{
				return "{\"message\":\"User doesn't exist\"}";
			}
			resp = mapper.writeValueAsString(dao.getAllTicketsForUser(user));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		}

		return resp;
	}
	
	@RequestMapping(value = "/playticket", method = RequestMethod.POST)
	public @ResponseBody String playTicket(@RequestParam(value = "offer", required = true) String data, HttpServletRequest request)
	{
		ObjectMapper mapper = new ObjectMapper();
		HttpSession s = request.getSession();
		
		User u = (User) s.getAttribute("user");
		
		if(u == null)
		{
			return "";
		}	
		
		ServiceBet service = new ServiceBet(dao, mtd);
		
		try {
			
			String user = u.getUsername();
			Offer offer = mapper.readValue(data, Offer.class);
			
			String resp = service.bet(user, offer);
			
			System.out.println(resp);
			return resp;
			
		} catch(IOException e)
		{
			e.printStackTrace();
			return "oops";
		}
		
	}
	
	@RequestMapping(value = "/tickets/mix")
	public @ResponseBody String getTickets(@RequestParam(value = "user", required = true)String user, @RequestParam(value = "betting", required = true)String betting)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		String resp = "";

		try {
			if(ud.getUserByUsername(user) == null)
			{
				return "{\"status\":\"error\",\"message\":\"User doesn't exist\"}";
			}
			if(bd.getBettingByName(betting) == null)
			{
				return "{\"status\":\"error\",\"message\":\"Betting doesn't exist\"}";
			}
		
			int id = bd.getBettingByName(betting).getId();
			resp = mapper.writeValueAsString(dao.getAllTicketsForUserAndBetting(user, id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		} catch (Exception e)
		{
			e.printStackTrace();
			return "{}";
		}
		
		return resp;
	}
}
