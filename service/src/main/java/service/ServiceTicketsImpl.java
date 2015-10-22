package service;

import java.util.List;

import model.Ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TicketDAO;

@Service
public class ServiceTicketsImpl implements ServiceTickets{

	@Autowired
	TicketDAO td;
	
	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		return td.getAllTickets();
	}

	@Override
	public List<Ticket> getAllTicketsForBetting(int id) {
		// TODO Auto-generated method stub
		return td.getAllTicketsForBetting(id);
	}

	@Override
	public List<Ticket> getAllTicketsForUser(String user) {
		// TODO Auto-generated method stub
		return td.getAllTicketsForUser(user);
	}

	@Override
	public List<Ticket> getAllTicketsForUserAndBetting(String user, int id) {
		// TODO Auto-generated method stub
		return td.getAllTicketsForUserAndBetting(user, id);
	}

}
