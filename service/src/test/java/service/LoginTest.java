package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dao.UserDAO;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
	
	ServiceLogin service;
	
	@Mock
	UserDAO dao;
	
	@Before
	public void setUp()
	{
		service = new ServiceLogin(dao);
	}
	
	@Test
	public void testLogin()
	{
		when(dao.getUserByUsername(anyString())).thenReturn(new User("milan", "milan", "admin"));
		
		User u = service.login("milan", "milan");
		
		assertEquals("milan", u.getUsername());
		assertEquals("milan", u.getPassword());
		assertEquals("admin", u.getType());
	}
	
	@Test
	public void testFailLogin()
	{
		when(dao.getUserByUsername(anyString())).thenReturn(new User("milan", "milan", "admin"));
		
		User u = service.login("dsad", "dsasd");
		
		assertEquals(null, u);
	}

}
