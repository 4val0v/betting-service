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

public class MainTest {

	private static SessionFactory factory;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		factory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class)
				.addAnnotatedClass(Tip.class).addAnnotatedClass(MatchTicket.class).
				addAnnotatedClass(Betting.class).addAnnotatedClass(Match.class).addAnnotatedClass(Ticket.class).
				addAnnotatedClass(Ticket.class).buildSessionFactory();
		
		
		UserDAO ud = new UserDAOImpl(factory);
		TipDAO td = new TipDAOImpl(factory);
		BettingDAO bd = new BettingDAOImpl(factory);
		MatchDAO md = new MatchDAOImpl(factory);
		TicketDAO tc = new TicketDAOImpl(factory);
		MatchTicketDAO mtd = new MatchTicketDAOImpl(factory);
		
		Match m = md.getMatchById(7);
		
	}

}
