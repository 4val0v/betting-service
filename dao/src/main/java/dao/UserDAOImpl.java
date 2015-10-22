package dao;

import java.util.List;

import model.User;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private SessionFactory factory;
	
	//public UserDAOImpl(SessionFactory factory){ this.factory = factory; }
	public UserDAOImpl(){}
	
	@Override
	public boolean addUser(User u)
	{
		Session session = factory.openSession();
		Transaction t = null; 
		
		try{
			
			t = session.beginTransaction();
			session.save(u);
			
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
	
	@Override
	public boolean deleteUser(String username)
	{
		Session session = factory.openSession();
		Transaction t = null;
		t = session.beginTransaction();
		
		try
		{
			User u = (User) session.get(User.class, username);
			session.delete(u);
			
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
	
	@Override
	public List<User> getAllUsers()
	{
		Session session = factory.openSession();
		Transaction t = null;
		t = session.beginTransaction();
		
		List<User> users = null;
		
		try{
			users = session.createQuery("from User").list();
			
			t.commit();
			session.close();	
		}
		catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
	
		return users;
	}
	
	@Override
	public User getUserByUsername(String username)
	{
		Session session = factory.openSession();
		Transaction t = null;
		t = session.beginTransaction();
		
		User u = null;
		
		try{
			u = (User) session.get(User.class, username);
			
			t.commit();
			session.close();
		}
		catch(HibernateException e)
		{
			t.rollback();
			session.close();
			e.printStackTrace();
			return null;
		}
		
		return u;
	}
	
}
