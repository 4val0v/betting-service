package service;

import java.util.List;

import model.Betting;

public interface ServiceBetting {

	public List<Betting> getAllBettings();
	public Betting getBettingByName(String name);

}
