package service;

import model.User;
import dao.UserDAO;

public class ServiceRegister {

	private UserDAO dao;
	
	public ServiceRegister(UserDAO dao)
	{
		this.dao = dao;
	}
	
	public String register(String username, String password, String type)
	{
		if((!type.equals("user") && !type.equals("admin")) || (username == null || password == null || type == null))
		{
			return "fail";
		}
		if(dao.addUser(new User(username, password, type)))
		{
			return "success";
		}
		else
		{
			return "fail";
		}
	}
}
