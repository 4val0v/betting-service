package dao;

import java.util.List;

import model.MatchTicket;

public interface MatchTicketDAO {

	public List<MatchTicket> getAll();
	
	public int addMatchTicket(MatchTicket mt);
	
}
