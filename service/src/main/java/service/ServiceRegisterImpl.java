package service;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDAO;

@Service
public class ServiceRegisterImpl implements ServiceRegister{

	@Autowired
	private UserDAO dao;
	
	public ServiceRegisterImpl() {}
	
	public ServiceRegisterImpl(UserDAO dao)
	{
		this.dao = dao;
	}
	
	@Override
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
