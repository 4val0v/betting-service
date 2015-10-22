package dao;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import model.Betting;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("BettingDAO")
public class BettingDAOImpl implements BettingDAO{

	@Autowired
	private SessionFactory factory;
	
	//public BettingDAOImpl(SessionFactory factory) { this.factory = factory; }
	public BettingDAOImpl() {}
	
	@Override
	public boolean addBetting(Betting b) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			session.save(b);
			
			t.commit();
			session.close();
			
		}catch(HibernateException e){
			t.rollback();
			session.close();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<Betting> getAllBettings() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		List<Betting> bettings = null;
		
		try{
			t = session.beginTransaction();
			
			bettings = session.createQuery("from Betting").list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		return bettings;
	}

	@Override
	public Betting getBettingById(int id) {					//srediti iteratore
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		Betting b = null;
		
		try{
		
			t = session.beginTransaction();
			
			String q = "select * from betting where id = :id";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Betting.class);
			query.setParameter("id", id);
			
			List result = query.list();
			Iterator i = result.iterator();
			
			b = (Betting) i.next();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e){
			
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
	
		}
		
		return b;
	}

	@Override
	public Betting getBettingByName(String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		Betting b = null;
		
		try{
			t = session.beginTransaction();
			
			String q = "select * from betting where name = :name";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Betting.class);
			query.setParameter("name", name);
			
			List result = query.list();
			Iterator i = result.iterator();
			
			b = (Betting) i.next();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e){
			
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
	
		}catch(NoSuchElementException e)	//exception za listu
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		return b;
	}

	@Override
	public List<Betting> getBettingForTip(int id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		List<Betting> bettings = null;
		
		try{
			
			t = session.beginTransaction();
			String q = "select * from betting where id in (select bet_id from matches where tip_id = :id)";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Betting.class);
			query.setParameter("id", id);
			
			bettings = query.list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e){
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
			
		return bettings;
	}
	
	
	
	
	
}
