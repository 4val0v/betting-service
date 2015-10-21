package testdao;

import static org.junit.Assert.assertEquals;
import model.Betting;
import model.Match;
import model.MatchTicket;
import model.Ticket;
import model.Tip;
import model.User;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import dao.UserDAO;
import dao.UserDAOImpl;

public class UserDaoTest {

	/*SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class)
			.addAnnotatedClass(Tip.class).addAnnotatedClass(MatchTicket.class).
			addAnnotatedClass(Betting.class).addAnnotatedClass(Match.class).addAnnotatedClass(Ticket.class).
			addAnnotatedClass(Ticket.class).buildSessionFactory();
	UserDAO dao = new UserDAOImpl(factory);
	
	User u = dao.getUserByUsername("milan");
	
	String password = "milan";
	String type = "admin";
	String fpassword = "milan";
	String ftype = "user";
	
	
	@Test
	public void getUserByUsername()
	{
		System.out.println("@password " + password + "=" + u.getPassword());
		assertEquals(password, u.getPassword());
		System.out.println("@type " + type + "=" + u.getType());
		assertEquals(type, u.getType());
	}
	
	@Test
	public void failGetUserByUsername()
	{
		System.out.println("@password " + fpassword + "=" + u.getPassword());
		assertEquals(fpassword, u.getPassword());
		System.out.println("@type " + ftype + "=" + u.getType());
		assertEquals(ftype, u.getType());
	}*/
	
	
}
