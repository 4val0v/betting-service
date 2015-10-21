package service;

import model.User;
import dao.UserDAO;

public class ServiceLogin {

	private UserDAO dao;
	
	public ServiceLogin(UserDAO dao)
	{
		this.dao = dao;
	}
	
	public User login(String username, String password)
	{
		User u = dao.getUserByUsername(username);
		
		if(u == null)
		{
			return null;
		}
		else
		{
			if(u.getPassword().equals(password))
			{
				return u;
			}
			else
			{
				return null;
			}
		}
	}
	
	
}
