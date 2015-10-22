package service;

import java.util.List;

import model.Ticket;

public interface ServiceTickets {

	public List<Ticket> getAllTickets();
	
	public List<Ticket> getAllTicketsForBetting(int id);
	
	public List<Ticket> getAllTicketsForUser(String user);
	
	public List<Ticket> getAllTicketsForUserAndBetting(String user, int id);
}
