package service;

import java.util.ArrayList;
import java.util.List;

import model.Betting;
import model.Match;
import model.MatchTicket;
import model.Offer;
import model.Ticket;
import model.Tip;
import model.User;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import dao.BettingDAO;
import dao.BettingDAOImpl;
import dao.MatchDAO;
import dao.MatchDAOImpl;
import dao.MatchTicketDAO;
import dao.MatchTicketDAOImpl;
import dao.TicketDAO;
import dao.TicketDAOImpl;
import dao.TipDAO;
import dao.TipDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;

public class ServiceMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class)
				.addAnnotatedClass(Tip.class).addAnnotatedClass(MatchTicket.class).
				addAnnotatedClass(Betting.class).addAnnotatedClass(Match.class).addAnnotatedClass(Ticket.class).
				addAnnotatedClass(Ticket.class).buildSessionFactory();	
		
		//UserDAO ud = new UserDAOImpl(factory);
		//TipDAO td = new TipDAOImpl(factory);
		BettingDAO bd = new BettingDAOImpl(factory);
		MatchDAO md = new MatchDAOImpl(factory);
		//TicketDAO tc = new TicketDAOImpl(factory);
		MatchTicketDAO mtd = new MatchTicketDAOImpl(factory);
		
		

		ServiceV1 service = new ServiceV1(bd, md, td);
		
		/*Offer offer = service.findPotentialMatches(1000, 100, 4);
		
		System.out.println(offer.getStake1());
		System.out.println(offer.getStake2());
		System.out.println(offer.getMatch1().getIdMatch());
		System.out.println(offer.getMatch2().getIdMatch());
		System.out.println(offer.getProfit());
		
		List<Tip> tips = td.getAllTips();
		Offer offer = null;
		
		offer = service.findPotentialMatchesForTip(500, 4);
		System.out.println("***************************");
		System.out.println(offer.getStake1());
		System.out.println(offer.getStake2());
		System.out.println(offer.getMatch1().getIdMatch());
		System.out.println(offer.getMatch2().getIdMatch());
		System.out.println(offer.getProfit());
		
		for(Tip t : tips)
		{
			offer = service.findPotentialMatchesForTip(1000, t.getId());
			System.out.println("***************************");
			System.out.println(offer.getStake1());
			System.out.println(offer.getStake2());
			System.out.println(offer.getMatch1().getIdMatch());
			System.out.println(offer.getMatch2().getIdMatch());
			System.out.println(offer.getProfit());
		}
		
		service.findPotentialMatches(500, 10);
		
		ArrayList<Offer> offers = service.findPotentialMatches(1000, 10);
		for(Offer offer : offers)
		{
			System.out.println("***************************");
			System.out.println(offer.getStake1());
			System.out.println(offer.getStake2());
			System.out.println(offer.getMatch1().getIdMatch());
			System.out.println(offer.getMatch2().getIdMatch());
			System.out.println(offer.getProfit());
		}
		
		//Offer offer = service.findPotentialMatchesForTip(500, tipId)*/
	}

}
