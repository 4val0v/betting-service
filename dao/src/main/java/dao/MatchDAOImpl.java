package dao;

import java.util.Iterator;
import java.util.List;

import model.Match;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("MatchDAO")
public class MatchDAOImpl implements MatchDAO{

	@Autowired
	private SessionFactory factory;
	
	//public MatchDAOImpl(SessionFactory factory) { this.factory = factory; }
	public MatchDAOImpl(){}
	
	@Override
	public Match getMatchById(int id) {
		// TODO Auto-generated method stub
	
		Match match = null;
		
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			match = (Match) session.get(Match.class, id);
			
			t.commit();
			session.close();
			
		}catch(HibernateException e){
			
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
			
		}	
		return match;
	}

	@Override
	public List<Match> getAllMatches() {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		List<Match> matches = null;
		
		try{
			t = session.beginTransaction();
			
			matches = session.createQuery("from Match").list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		return matches;
	}

	@Override
	public List<Match> getMatchesForBetting(int idBet) {
		// TODO Auto-generated method stub
		List<Match> matches = null;
		
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			String q = "select * from matches where bet_id = :id";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Match.class);
			query.setParameter("id", idBet);
			
			matches = query.list();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e){
			
			t.rollback();
			session.clear();
			e.printStackTrace();
			return null;
			
		}
		
		return matches;
	}

	@Override
	public boolean addMatch(Match m) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			session.save(m);
			
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
	public boolean deleteMatch(int id) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		try{
			t = session.beginTransaction();
			
			Match m = (Match) session.get(Match.class, id);
			session.delete(m);
			
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
	public Match getMatchForBetting(int tipId, int betId) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = null;
		
		Match m = null;
		
		try{
			t = session.beginTransaction();
			
			String q = "select * from matches where tip_id = :tipId and bet_id = :betId";
			SQLQuery query = session.createSQLQuery(q);
			query.addEntity(Match.class);
			query.setParameter("tipId", tipId);
			query.setParameter("betId", betId);
			
			List result = query.list();
			Iterator i = result.iterator();
			
			m = (Match) i.next();
			
			t.commit();
			session.close();
			
		}catch(HibernateException e){
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		return m;
	}

}
