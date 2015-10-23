package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import model.Betting;
import model.Match;
import model.Offer;
import model.Tip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import dao.BettingDAO;
import dao.MatchDAO;
import dao.TipDAO;

@RunWith(MockitoJUnitRunner.class)
public class FindPotentialMatches {
	
	@InjectMocks
	ServiceCalculate service = new ServiceCalculateImpl();
	
	@Mock
	BettingDAO bd;
	@Mock
	TipDAO td;
	@Mock
	MatchDAO md;
	
	@Test
	public void getOffers()
	{
		when(td.getAllTips()).thenReturn(createTips());					//pravim tipove 1(real-manutd) i 2(bar-mil)
		
		when(bd.getBettingForTip(1)).thenReturn(createBettings1());		//kladionice koje imaju real-manutd  tip_id = 1
		when(bd.getBettingForTip(2)).thenReturn(createBettings2());		//kladionice koje imaju bar-mil tip_id = 2;

		when(md.getMatchForBetting(1, 1)).thenReturn(new Match(1, 1, 1, 2.3f, 1.5f, 1000));		//uzimanje ponuda iz kladionica za prvi mec(real-manutd)
		when(md.getMatchForBetting(1, 2)).thenReturn(new Match(5, 1, 2, 2.4f, 1.6f, 1000));
		when(md.getMatchForBetting(1, 3)).thenReturn(new Match(19, 1, 3, 1.8f, 2.1f, 500));
		
		when(md.getMatchForBetting(2, 1)).thenReturn(new Match(12, 2, 1, 1.2f, 4.9f, 500));
		when(md.getMatchForBetting(2, 3)).thenReturn(new Match(9, 2, 3, 1.36f, 3.17f, 500));
		when(md.getMatchForBetting(2, 4)).thenReturn(new Match(10, 2, 4, 1.189f, 5.5f, 500));
		
		ArrayList<Offer> offers = service.findPotentialMatches(200, 10);
		
//		assertEquals(0, offers.size());
		
		Offer offer1 = offers.get(0);
		Offer offer2 = offers.get(1);
		
		assertNotNull(offer1);
		assertNotNull(offer2);
		
		assertEquals(93, offer1.getStake1(), 0.0001);
		assertEquals(107, offer1.getStake2(), 0.0001);
		assertEquals(24, offer1.getProfit(), 0.0001);
		
		assertEquals(160, offer2.getStake1(), 0.0001);
		assertEquals(40, offer2.getStake2(), 0.0001);
		assertEquals(18.0758, offer2.getProfit(), 0.0001);
				
	}
	
	@Test
	public void getOffersFail()
	{
		when(td.getAllTips()).thenReturn(createTips());					//pravim tipove 1(real-manutd) i 2(bar-mil)
		
		when(bd.getBettingForTip(1)).thenReturn(createBettings1());		//kladionice koje imaju real-manutd  tip_id = 1
		when(bd.getBettingForTip(2)).thenReturn(createBettings2());		//kladionice koje imaju bar-mil tip_id = 2;

		when(md.getMatchForBetting(1, 1)).thenReturn(new Match(1, 1, 1, 2.3f, 1.5f, 1000));		//uzimanje ponuda iz kladionica za prvi mec(real-manutd)
		when(md.getMatchForBetting(1, 2)).thenReturn(new Match(5, 1, 2, 2.4f, 1.6f, 1000));
		when(md.getMatchForBetting(1, 3)).thenReturn(new Match(19, 1, 3, 1.8f, 2.1f, 500));
		
		when(md.getMatchForBetting(2, 1)).thenReturn(new Match(12, 2, 1, 1.2f, 4.9f, 500));
		when(md.getMatchForBetting(2, 3)).thenReturn(new Match(9, 2, 3, 1.36f, 3.17f, 500));
		when(md.getMatchForBetting(2, 4)).thenReturn(new Match(10, 2, 4, 1.189f, 5.5f, 500));
		
		ArrayList<Offer> offers = service.findPotentialMatches(200, 50);
		
		assertEquals(2, offers.size());
	}
	
	
	private List<Betting> createBettings1()
	{
		ArrayList<Betting> bettings = new ArrayList<Betting>();
		
		Betting betting1 = new Betting(1, "Maxbet");
		Betting betting2 = new Betting(2, "Mozzart");
		Betting betting3 = new Betting(3, "Pinbet");	
		
		bettings.add(betting1);
		bettings.add(betting2);
		bettings.add(betting3);
		
		return bettings;
	}
	
	private List<Betting> createBettings2()
	{
		ArrayList<Betting> bettings = new ArrayList<Betting>();
		
		Betting betting1 = new Betting(1, "Maxbet");
		Betting betting3 = new Betting(3, "Pinbet");
		Betting betting4 = new Betting(4, "Meridian");
		
		bettings.add(betting1);
		bettings.add(betting3);
		bettings.add(betting4);
		
		return bettings;
	}
	
	private List<Tip> createTips()
	{
		ArrayList<Tip> tips = new ArrayList<Tip>();
		tips.add(new Tip(1, "real-manutd"));
		tips.add(new Tip(2, "bar-mil"));
		
		return tips;
	}
	

}
