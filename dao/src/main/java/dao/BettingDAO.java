package dao;

import java.util.List;

import model.Betting;

public interface BettingDAO {

	public boolean addBetting(Betting b);
	
	public List<Betting> getAllBettings();
	
	public Betting getBettingById(int id);
	
	public Betting getBettingByName(String name);
	
	public List<Betting> getBettingForTip(int id);
}
