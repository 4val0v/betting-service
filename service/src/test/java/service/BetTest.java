package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import model.Match;
import model.MatchTicket;
import model.Offer;
import model.Ticket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dao.MatchTicketDAO;
import dao.TicketDAO;

@RunWith(MockitoJUnitRunner.class)
public class BetTest {

	ServiceBet service;
	
	@Mock
	TicketDAO td;
	
	@Mock
	MatchTicketDAO mtd;
	
	@Before
	public void setUp()
	{
		service = new ServiceBet(td, mtd);
	}
	
	@Test
	public void testBet()
	{
		when(td.addTicket(any(Ticket.class))).thenReturn(1);
		when(mtd.addMatchTicket(any(MatchTicket.class))).thenReturn(1);
		
		String resp = service.bet("whoever", returnOffer());
		
		assertEquals("success", resp);
		
	}
	
	@Test
	public void testFailBet()
	{
		when(td.addTicket(any(Ticket.class))).thenReturn(0);
		when(mtd.addMatchTicket(any(MatchTicket.class))).thenReturn(1);
		
		String resp = service.bet("whoever", returnOffer());
		
		assertEquals("success", resp);
	}
	
	private Offer returnOffer()
	{
		Offer offer = new Offer();
		
		offer.setStake1(100);
		offer.setStake2(100);
		offer.setProfit(50);
		
		Match m1 = new Match(1, 1, 1, 1.5f, 2.4f, 500);
		Match m2 = new Match(2, 1, 2, 1.6f, 2.3f, 500);
		
		offer.setMatch1(m1);
		offer.setMatch2(m2);
		
		return offer;
	}
	
}
