package dao;

import java.util.Iterator;
import java.util.List;

import model.Tip;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("TipDAO")
public class TipDAOImpl implements TipDAO{

	@Autowired
	private SessionFactory factory;
	
	//public TipDAOImpl(SessionFactory factory) { this.factory = factory; }
	public TipDAOImpl() {}
	
	@Override
	public Tip getTipById(int id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		Tip tip = null;
		
		try{
			
			t = session.beginTransaction();
			
			tip = (Tip) session.get(Tip.class, id);
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}

		return tip;
	}

	@Override
	public boolean addTip(Tip tip) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			
			t = session.beginTransaction();
			
			session.save(tip);
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return false;
		}	
		
		
		
		return true;
	}

	@Override
	public List<Tip> getAllTips() {
		Session session = factory.openSession();
		Transaction t = null;
		List<Tip> tips = null;
		try{
			
			t = session.beginTransaction();
			
			tips = session.createQuery("from Tip").list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}	
		
		return tips;
	}

	@Override
	public Tip getTipByName(String name) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		Tip tip = null;
		
		try{
		
			String q = "select * from tip where name = :name";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Tip.class);
			query.setParameter("name", name);
			
			List result = query.list();
			Iterator i = result.iterator();
			
			tip = (Tip) i.next();
			
		}catch(HibernateException e){
			
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
	
		}
		
		return tip;
	}

	@Override
	public boolean deleteTip(int id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			Tip tip = (Tip) session.get(Tip.class, id);
			session.delete(tip);
			t.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	

}
