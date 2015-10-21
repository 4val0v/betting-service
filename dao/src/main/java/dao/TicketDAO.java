package dao;

import java.util.List;

import model.Ticket;

public interface TicketDAO {

	public int addTicket(Ticket ticket);
	
	public Ticket getTicketById(int id);
	
	public List<Ticket> getAllTicketsForBetting(int id);
	
	public List<Ticket> getAllTickets();
	
	public List<Ticket> getAllTicketsForUser(String user);
	
	public List<Ticket> getAllTicketsForUserAndBetting(String user, int id);
}
