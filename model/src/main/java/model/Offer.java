package model;

public class Offer {
	
	private float stake1;			//ulog na 1
	private float stake2;			//ulog na 2
	private float profit;			//profit
	private Match match1;			//mec za 1	
	private Match match2;			//mec za 2
	
	public Offer() {}
	public Offer(float stake1, float stake2, float profit, Match match1, Match match2)
	{
		this.stake1 = stake1;
		this.stake2 = stake2;
		this.profit = profit;
		this.match1 = match1;
		this.match2 = match2;
	}
	
	
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public float getStake1() {
		return stake1;
	}
	public void setStake1(float stake1) {
		this.stake1 = stake1;
	}
	public float getStake2() {
		return stake2;
	}
	public void setStake2(float stake2) {
		this.stake2 = stake2;
	}
	public Match getMatch1() {
		return match1;
	}
	public void setMatch1(Match match1) {
		this.match1 = match1;
	}
	public Match getMatch2() {
		return match2;
	}
	public void setMatch2(Match match2) {
		this.match2 = match2;
	}
	

}
