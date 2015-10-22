package dao;

import java.util.List;

import model.Ticket;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("TicketDAO")
public class TicketDAOImpl implements TicketDAO{

	@Autowired
	private SessionFactory factory;
	
	//public TicketDAOImpl(SessionFactory factory) { this.factory = factory; }
	public TicketDAOImpl(){}
	
	@Override
	public int addTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null; 	
		int id = 0;
		
		try{
			t = session.beginTransaction();
			id = (Integer) session.save(ticket);
			t.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return 0;
		}
		
		return id;
	}

	@Override
	public Ticket getTicketById(int id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		Ticket ticket = null;
		
		try{
			t = session.beginTransaction();
			ticket = (Ticket) session.get(Ticket.class, id);
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		return ticket;
	}

	@Override
	public List<Ticket> getAllTicketsForBetting(int id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		List<Ticket> tickets = null;
		
		try{
			t = session.beginTransaction();
			
			String q = "select * from ticket where bet_id = :id";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Ticket.class);
			query.setParameter("id", id);
			
			tickets = query.list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		
		return tickets;
	}

	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		List<Ticket> tickets = null;
		
		try{
			t = session.beginTransaction();
			
			tickets = session.createQuery("from Ticket").list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		return tickets;
		
	}

	@Override
	public List<Ticket> getAllTicketsForUser(String user) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		List<Ticket> tickets = null;
		
		try{
			t = session.beginTransaction();
			
			String q = "select * from ticket where user = :user";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Ticket.class);
			query.setParameter("user", user);
			
			tickets = query.list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}	
		
		return tickets;
	}

	@Override
	public List<Ticket> getAllTicketsForUserAndBetting(String user, int id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		List<Ticket> tickets = null;
		
		try{
			t = session.beginTransaction();
			String q = "select * from ticket where user = :user and bet_id = :id";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Ticket.class);
			query.setParameter("user", user);
			query.setParameter("id", id);
			
			tickets = query.list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		return tickets;
	}

}
