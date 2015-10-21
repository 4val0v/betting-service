package service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import model.Betting;
import model.Match;
import model.Offer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dao.BettingDAO;
import dao.MatchDAO;
import dao.TipDAO;

@RunWith(MockitoJUnitRunner.class)
public class FindPotentialMatchTest {
	
	public ServiceV1 service;
	
	@Mock
	BettingDAO bd;
	
	@Mock
	TipDAO td;
	
	@Mock
	MatchDAO md;
	
	@Before  
    public void setUp() {  
       service = new ServiceV1(bd, md, td); 
    }  
	
	@Test
	public void testFindingPotentialMatches()
	{
		when(bd.getBettingForTip(4)).thenReturn(returnBettings());
		when(md.getMatchForBetting(4, 2)).thenReturn(new Match(4, 4, 2, 1.6f, 3.0f, 1000.0f));
		when(md.getMatchForBetting(4, 4)).thenReturn(new Match(8, 4, 4, 2.5f, 1.5f, 500));
		
		Offer offer = service.findPotentialMatchesForTip(200, 4);
		
		assertEquals(109.0, offer.getStake1(), 0.001);
		assertEquals(91.0, offer.getStake2(), 0.001);
		assertEquals(72.7272, offer.getProfit(), 0.001);
		
	}

	private List<Betting> returnBettings() {
		// TODO Auto-generated method stub
		List<Betting> bettings = new ArrayList<Betting>();
		
		bettings.add(new Betting(2, "Mozzart"));
		bettings.add(new Betting(4, "Meridian"));
		
		return bettings;
	}
	
	

}
