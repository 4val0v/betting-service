package dao;

import java.util.List;

import model.MatchTicket;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("MatchTicketDAO")
public class MatchTicketDAOImpl implements MatchTicketDAO{

	@Autowired
	private SessionFactory factory;
	
	//public MatchTicketDAOImpl(SessionFactory factory) { this.factory = factory; }
	public MatchTicketDAOImpl() {}
	
	@Override
	public List<MatchTicket> getAll() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		List<MatchTicket> tmp = null;
		
		try{
			t = session.beginTransaction();
			tmp = session.createQuery("from MatchTicket").list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		
		
		return tmp;
	}

	@Override
	public int addMatchTicket(MatchTicket mt) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		int id = 0;
		
		try{
			t = session.beginTransaction();
			
			id = (Integer) session.save(mt);
			
			t.commit();
			session.close();
			
		} catch(HibernateException e) {
			t.rollback();
			session.close();
			e.printStackTrace();
			return 0;
		}
		
		return id;
	}

}
