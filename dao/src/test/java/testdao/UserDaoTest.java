package testdao;

import static org.junit.Assert.assertEquals;
import model.User;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.UserDAO;

public class UserDaoTest {

	ApplicationContext context = 
            new ClassPathXmlApplicationContext("Beans.xml");
	
	UserDAO dao = (UserDAO) context.getBean("UserDAO");
	
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
	}
	
	
}
