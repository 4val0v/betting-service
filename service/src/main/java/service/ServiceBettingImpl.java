package service;

import java.util.List;

import model.Betting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BettingDAO;

@Service
public class ServiceBettingImpl implements ServiceBetting{

	@Autowired
	BettingDAO dao;
	
	public ServiceBettingImpl() {}

	@Override
	public List<Betting> getAllBettings() {
		// TODO Auto-generated method stub
		
		return dao.getAllBettings();
	}

	@Override
	public Betting getBettingByName(String name) {
		// TODO Auto-generated method stub
		return dao.getBettingByName(name);
	}	
	
}
