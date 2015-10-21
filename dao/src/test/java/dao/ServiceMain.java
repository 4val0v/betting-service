package dao;

import java.util.ArrayList;

import model.Betting;
import model.Match;
import model.MatchTicket;
import model.Ticket;
import model.Tip;
import model.User;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class ServiceMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class)
				.addAnnotatedClass(Tip.class).addAnnotatedClass(MatchTicket.class).
				addAnnotatedClass(Betting.class).addAnnotatedClass(Match.class).addAnnotatedClass(Ticket.class).
				addAnnotatedClass(Ticket.class).buildSessionFactory();	
		
		UserDAO ud = new UserDAOImpl(factory);
		TipDAO td = new TipDAOImpl(factory);
		BettingDAO bd = new BettingDAOImpl(factory);
		MatchDAO md = new MatchDAOImpl(factory);
		TicketDAO tc = new TicketDAOImpl(factory);
		MatchTicketDAO mtd = new MatchTicketDAOImpl(factory);
		
		
		ArrayList<Betting> bettings = (ArrayList) bd.getBettingForTip(6);
		
		Match max1 = new Match(0, 0, 0, 0, 0, 0);
		Match max2 = new Match(0, 0, 0, 0, 0, 0);
		
		
		for(Betting b : bettings)
		{
			Match m1 = md.getMatchForBetting(6, b.getId());
			if(m1.getOddsHome() > max1.getOddsHome())
			{
				max1 = m1;
			}
			Match m2 = md.getMatchForBetting(6, b.getId());
			if(m2.getOddsAway() > max2.getOddsAway())
			{
				max2 = m2;
			}
		}
		
		System.out.println(max1.getIdMatch());
		System.out.println(max2.getIdMatch());
		
		float arb1;
		float arb2;
		float arb;
		
		arb1 = (1/max1.getOddsHome())*100;
		arb2  =(1/max2.getOddsAway())*100;
		arb = arb1 + arb2;
		
		System.out.println(arb1);
		System.out.println(arb2);
		System.out.println(arb);
		
		float outcome = 1000/(arb/100) - 1000;			//?
		
		System.out.println(outcome);
		
		float val1 = (1000*(arb1/100))/(arb/100);
		float val2 = (1000*(arb2/100))/(arb/100);
		
		System.out.println(val1);
		System.out.println(val2);
		
	}

}
