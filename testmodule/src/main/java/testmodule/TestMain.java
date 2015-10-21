package testmodule;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import model.User;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Hi");
		
		User u = new User("milan", "milan", "user");
		
		System.out.println(u.getUsername() + u.getPassword() + u.getType());
		
		Session s;
		
		SessionFactory factory = new AnnotationConfiguration().configure().addAnnotatedClass(User.class).buildSessionFactory();
		
		Transaction t;
		
		s = factory.openSession();
		t = s.beginTransaction();
		
		User u1 = new User("nikola", "nikola", "user");
		
		s.save(u1);
		t.commit();
		
		s.close();
		
	}

}
