package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dao.UserDAO;

@RunWith(MockitoJUnitRunner.class)
public class RegisterTest {

	@InjectMocks
	ServiceRegister service = new ServiceRegisterImpl();
	
	@Mock
	UserDAO dao;
	
	@Test
	public void testRegister()
	{	
		when(dao.addUser(any(User.class))).thenReturn(true);
		
		String resp = service.register("dsad", "dsads", "user");
		
		assertEquals("success", resp);
		
	}
	
	@Test
	public void testFailRegister()
	{
		when(dao.addUser(any(User.class))).thenReturn(false);
		
		String resp = service.register("dsad", "dsad", "dsadsa");
		
		assertEquals("success", resp);
	}
	
	@Test
	public void testFailParamsRegister()
	{
		when(dao.addUser(any(User.class))).thenReturn(true);
		
		String resp = service.register(null, "dsad", "dsadsa");
		
		assertEquals("success", resp);	
	}
	
	
}
