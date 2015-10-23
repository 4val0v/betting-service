package service;

import java.util.ArrayList;
import java.util.List;

import model.Betting;
import model.Match;
import model.Offer;
import model.Tip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.BettingDAO;
import dao.MatchDAO;
import dao.TipDAO;

@Service
public class ServiceCalculateImpl implements ServiceCalculate{

	@Autowired
	private BettingDAO bd;
	@Autowired
	private MatchDAO md;
	@Autowired
	private TipDAO td;
	
	public ServiceCalculateImpl() {}
	
	public ServiceCalculateImpl(BettingDAO bd, MatchDAO md, TipDAO td)
	{
		this.bd = bd;
		this.md = md;
		this.td = td;
	}
	
	@Override
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
	
	private Offer findPotentialMatchesForTip(float stake, int tipId)	
	{	
		ArrayList<Betting> bettings = (ArrayList)bd.getBettingForTip(tipId);
		
		if(bettings.isEmpty())
		{
			return null;
		}
		
		Match max1 = md.getMatchForBetting(tipId, bettings.get(0).getId());  //match for first betting
		Match max2 = md.getMatchForBetting(tipId, bettings.get(0).getId());
		
		for(int i = 1; i < bettings.size(); i++)	//from second to end
		{
			Match m = md.getMatchForBetting(tipId, bettings.get(i).getId());
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
	
	private void sortPotentialMatches(ArrayList<Offer> offers, int lower, int higher)
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
