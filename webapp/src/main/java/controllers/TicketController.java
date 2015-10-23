package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Betting;
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
import service.ServiceBetting;
import service.ServiceTickets;
import service.ServiceUser;
import dao.MatchTicketDAO;
import dao.UserDAO;

@Controller
public class TicketController {

	@Autowired
	ServiceBet serviceBet;
	@Autowired
	ServiceTickets serviceTicket;
	@Autowired
	ServiceBetting serviceBetting;
	@Autowired
	ServiceUser serviceUser;
	
	@Autowired
	ObjectMapper mapper;
	

	
	@RequestMapping(value = "/tickets/all", method = RequestMethod.GET)
	public @ResponseBody String getAllTickets(HttpServletRequest request)
	{	
		User u = (User) request.getSession().getAttribute("user");
		
		if(u == null)
		{
			return "{}";
		}
		if(!u.getType().equals("admin"))
		{
			return "{}";
		}
		
		String resp = "";
		
		try {
			resp = mapper.writeValueAsString(serviceTicket.getAllTickets());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		}
		
		return resp;
	}
	
	@RequestMapping(value = "/tickets/betting", method = RequestMethod.GET)
	public @ResponseBody String getTicketsForBetting(@RequestParam(value = "name", required = true)String name, HttpServletRequest request)
	{
		User u = (User) request.getSession().getAttribute("user");
		
		if(u == null)
		{
			return "{}";
		}
		if(!u.getType().equals("admin"))
		{
			return "{}";
		}
		
		String resp = "";

		try {
			Betting b = serviceBetting.getBettingByName(name);
			if(b == null)
			{
				return "{\"message\":\"Betting doesn't exist\"}";
			}
			int id = b.getId();
			resp = mapper.writeValueAsString(serviceTicket.getAllTicketsForBetting(id));
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
	public @ResponseBody String getTicketsForUser(@RequestParam(value = "user", required = true)String user, HttpServletRequest request)
	{
		if(request.getSession().getAttribute("user") == null)
		{
			return "{}";
		}
		
		String resp = "";
		
		try{
			if(serviceUser.getUserByUsername(user) == null)
			{
				return "{\"message\":\"User doesn't exist\"}";
			}
			resp = mapper.writeValueAsString(serviceTicket.getAllTicketsForUser(user));
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
		HttpSession s = request.getSession();
		
		User u = (User) s.getAttribute("user");
		
		if(u == null)
		{
			return "oops";
		}
		if(!u.getType().equals("user"))
		{
			return "oops";
		}
		
		try {
			
			String user = u.getUsername();
			Offer offer = mapper.readValue(data, Offer.class);
			
			String resp = serviceBet.bet(user, offer);
			
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
		String resp = "";

		try {
			Betting b = serviceBetting.getBettingByName(betting);
			
			if(serviceUser.getUserByUsername(user) == null)
			{
				return "{\"status\":\"error\",\"message\":\"User doesn't exist\"}";
			}
			if(b == null)
			{
				return "{\"status\":\"error\",\"message\":\"Betting doesn't exist\"}";
			}
		
			int id = b.getId();
			resp = mapper.writeValueAsString(serviceTicket.getAllTicketsForUserAndBetting(user, id));
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
