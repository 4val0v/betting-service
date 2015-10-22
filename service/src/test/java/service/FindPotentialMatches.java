package service;

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
	ServiceCalculate service;
	
	@Mock
	BettingDAO bd;
	@Mock
	TipDAO td;
	@Mock
	MatchDAO md;
	
	@Test
	public void getOffer()
	{
		when(bd.getBettingForTip(4)).thenReturn(createBettings());
		when(td.getAllTips()).thenReturn(createTips());
		when(md.getMatchForBetting(4, 2)).thenReturn(new Match());
		when(md.getMatchForBetting(4, 4)).thenReturn(new Match());
		
		ArrayList<Offer> offers = service.findPotentialMatches(200, 50);
		
		for(Offer offer : offers)
		{
			
		}
		
		
	}
	
	private List<Betting> createBettings()
	{
		Betting betting1 = new Betting(2, "Mozzart");
		Betting betting2 = new Betting(4, "Merridian");
		
		return new ArrayList<Betting>();
	}
	
	private List<Tip> createTips()
	{
		return new ArrayList<Tip>();
	}
	

}
