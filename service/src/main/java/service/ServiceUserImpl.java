package service;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDAO;

@Service
public class ServiceUserImpl implements ServiceUser{

	@Autowired
	UserDAO dao;
	
	@Override
	public User getUserByUsername(String user) {
		// TODO Auto-generated method stub
		return dao.getUserByUsername(user);
	}

}
