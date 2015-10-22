package service;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDAO;

@Service
public class ServiceLoginImpl implements ServiceLogin{

	@Autowired
	private UserDAO dao;
	
	public ServiceLoginImpl() {}
	
	public ServiceLoginImpl(UserDAO dao)
	{
		this.dao = dao;
	}
	
	@Override
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
