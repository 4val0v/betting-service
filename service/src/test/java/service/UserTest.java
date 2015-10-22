package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dao.UserDAO;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	@InjectMocks
	ServiceUser service = new ServiceUserImpl();
	
	@Mock
	UserDAO dao;
	
	@Test
	public void getUser()
	{
		when(dao.getUserByUsername(anyString())).thenReturn(new User("milan", "milan", "admin"));
		
		User u = service.getUserByUsername("milan");
		
		assertEquals("milan", u.getUsername());
		assertEquals("milan", u.getPassword());
		assertEquals("admin", u.getType());
	}
	
	@Test
	public void getUserFail()
	{
		when(dao.getUserByUsername(anyString())).thenReturn(null);
		
		User u = service.getUserByUsername("sda");
		
		assertNotNull(u);
	}
	
	
}
