package service;

import java.util.ArrayList;
import java.util.List;

import model.Betting;
import model.Match;
import model.Offer;
import model.Tip;
import dao.BettingDAO;
import dao.MatchDAO;
import dao.TipDAO;

public class ServiceV1 {

	private BettingDAO bd;
	private MatchDAO md;
	private TipDAO td;
	
	public ServiceV1() {}
	
	public ServiceV1(BettingDAO bd, MatchDAO md, TipDAO td)
	{
		this.bd = bd;
		this.md = md;
		this.td = td;
	}
	
	public Offer findPotentialMatchesForTip(float stake, int tipId)	
	{	
		ArrayList<Betting> bettings = (ArrayList)bd.getBettingForTip(tipId);
		
		if(bettings.isEmpty())
		{
			return null;
		}
		
		Match max1 = new Match(-1, -1, -1, 0, 0, 0);
		Match max2 = new Match(-1, -1, -1, 0, 0, 0);
		
		for(Betting b : bettings)								
		{
			Match m = md.getMatchForBetting(tipId, b.getId());
			if(m.getOddsHome() > max1.getOddsHome())
			{
				max1 = m;
			}
			if(m.getOddsAway() > max2.getOddsAway())
			{
				max2 = m;
			}
		}
		
		//**************************************************//
		//provera maximalnog uloga u kladionici
		if(max1.getMaxBet() < stake)
		{
			stake = max1.getMaxBet();
		}
		if(max2.getMaxBet() < stake)
		{
			stake = max2.getMaxBet();
		}
		
		float arb1 = (1/max1.getOddsHome())*100;
		float arb2 = (1/max2.getOddsAway())*100;
		float arb = arb1 + arb2;
		
		//if arb > 100 return
		
		float profit = stake/(arb/100) - stake;
		
		//racunanje pojedinacnih uloga
		float stake1 = (stake*(arb1/100))/(arb/100);
		float stake2 = (stake*(arb2/100))/(arb/100);
		
		//zaokruzivanje
		stake1 = (int)(stake1 + 0.5);
		stake2 = (int)(stake2 + 0.5);
		
		return new Offer(stake1, stake2, profit, max1, max2);
	}
	
	public ArrayList<Offer> findPotentialMatches(float stake, float profit)
	{
		List<Tip> tips = td.getAllTips();
		ArrayList<Offer> offers = new ArrayList<Offer>();
		Offer offer = null;
		
		for(Tip t : tips)
		{
			offer = findPotentialMatchesForTip(stake, t.getId());
			if(offer != null)
			{
				if(offer.getProfit() >= profit)
				{
					offers.add(offer);
				}
			}
		}
		
		if(offers == null || offers.size() == 0)
		{
			return offers;
		}
		
		sortPotentialMatches(offers, 0, offers.size() - 1);
		
		return offers;
	}
	
	public void sortPotentialMatches(ArrayList<Offer> offers, int lower, int higher)
	{
		int i = lower;
		int j = higher;
		float pivot = offers.get(lower + (higher - lower)/2).getProfit();
		
		
		while(i <= j)
		{
			while(offers.get(i).getProfit() > pivot)
			{
				i++;
			}
			while(offers.get(j).getProfit() < pivot)
			{
				j--;
			}
			if(i <= j)
			{
				Offer tmp1 = offers.get(i);
				Offer tmp2 = offers.get(j);
				
				offers.set(j, tmp1);
				offers.set(i, tmp2);
				
				i++;
				j--;
			}
			if(lower < j)
			{
				sortPotentialMatches(offers, lower, j);
			}
			if(i < higher)
			{
				sortPotentialMatches(offers, i, higher);
			}
			
		}
		
	}
	
	
	
	
	
}
