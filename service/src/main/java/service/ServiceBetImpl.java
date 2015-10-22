package service;

import model.Match;
import model.MatchTicket;
import model.Offer;
import model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MatchTicketDAO;
import dao.TicketDAO;

@Service
public class ServiceBetImpl implements ServiceBet{

	@Autowired
	private TicketDAO td;
	@Autowired
	private MatchTicketDAO mtd;
	
	public ServiceBetImpl() {}
	
	public ServiceBetImpl(TicketDAO td, MatchTicketDAO mtd)
	{
		this.td = td;
		this.mtd = mtd;
	}
	
	@Override
	public String bet(String user, Offer offer)
	{
		Match m1 = offer.getMatch1();
		Match m2 = offer.getMatch2();
		
		Ticket t1 = new Ticket();
		Ticket t2 = new Ticket();
		
		//ko igra tiket
		t1.setIdUser(user);
		t2.setIdUser(user);
		//u kojoj kladionici
		t1.setIdBetting(m1.getIdBetting());
		t2.setIdBetting(m2.getIdBetting());
		//inicijalno, promenice se kad se bude upisalo u bazu
		t1.setId(0);
		t2.setId(0);
		//postavljanje uloga
		t1.setStake(offer.getStake1());
		t2.setStake(offer.getStake2());
		
		float profit1 = offer.getStake1()*m1.getOddsHome();
		float profit2 = offer.getStake2()*m2.getOddsAway();
		//postavljanje potencijalne zarade
		t1.setOutcome(profit1);
		t2.setOutcome(profit2);
		
		int tid1 = td.addTicket(t1);
		int tid2 = td.addTicket(t2);
		
		if(tid1 == 0 || tid2 == 0)
		{
			return "fail";
		}
		
		MatchTicket mt1 = new MatchTicket();
		MatchTicket mt2 = new MatchTicket();
		
		//dodavanje u tabelu meceva na tiketima
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
		
		if(mtid1 == 0 || mtid2 == 0)
		{
			return "fail";
		}
		
		return "success";
	}
	
	
}
