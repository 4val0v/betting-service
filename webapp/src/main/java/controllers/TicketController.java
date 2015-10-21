package controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Match;
import model.MatchTicket;
import model.Offer;
import model.Ticket;
import model.User;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		
		try {
			String user = u.getUsername();
			
			Offer offer = mapper.readValue(data, Offer.class);
			
			Match m1 = offer.getMatch1();
			Match m2 = offer.getMatch2();
			
			Ticket t1 = new Ticket();
			Ticket t2 = new Ticket();
			
			t1.setIdUser(user);
			t2.setIdUser(user);
			t1.setIdBetting(m1.getIdBetting());
			t2.setIdBetting(m2.getIdBetting());
			t1.setId(0);
			t2.setId(0);
			t1.setStake(offer.getStake1());
			t2.setStake(offer.getStake2());
			
			float profit1 = offer.getStake1()*m1.getOddsHome();
			float profit2 = offer.getStake2()*m2.getOddsAway();
			
			t1.setOutcome(profit1);
			t2.setOutcome(profit2);
			
			int tid1 = dao.addTicket(t1);
			int tid2 = dao.addTicket(t2);
			
			MatchTicket mt1 = new MatchTicket();
			MatchTicket mt2 = new MatchTicket();
			
			mt1.setId(0);
			mt2.setId(0);
			mt1.setGuess(1);
			mt2.setGuess(2);
			mt1.setMatch_id(m1.getIdMatch());
			mt2.setMatch_id(m2.getIdMatch());
			mt1.setTicket_id(tid1);
			mt2.setTicket_id(tid2);
			
			int mtid1 = mtd.addMatchTicket(mt1);
			int mtid2 = mtd.addMatchTicket(mt2);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "oops";
		}
		
		return "added";
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
