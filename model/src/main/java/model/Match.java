package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(
		name = "matches",
		uniqueConstraints = @UniqueConstraint(columnNames = { "tip_id", "bet_id" })
		)
public class Match {
	
	@Id @Column(name = "match_id")
	@GeneratedValue
	private int idMatch;
	
	@Column(name = "tip_id")
	private int idTip;
	
	@Column(name = "bet_id")
	private int idBetting;
	
	@Column(name = "odds_home")
	private float oddsHome;
	
	@Column(name = "odds_away")
	private float oddsAway;
	
	@Column(name = "max_bet")
	private float maxBet;
	
	@ManyToOne
	@JoinColumn(name = "tip_id", insertable=false, updatable=false)
	private Tip tip;
	

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public Match(){}
	
	public Match(int idMatch, int idTip, int idBetting, float oddsHome, float oddsAway, float maxBet)
	{
		this.idMatch = idMatch;
		this.idTip = idTip;
		this.idBetting = idBetting;
		this.oddsHome = oddsHome;
		this.oddsAway = oddsAway;
		this.maxBet = maxBet;
	}

	public int getIdMatch() {
		return idMatch;
	}

	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}

	public int getIdTip() {
		return idTip;
	}

	public void setIdTip(int idTip) {
		this.idTip = idTip;
	}

	public int getIdBetting() {
		return idBetting;
	}

	public void setIdBetting(int idBetting) {
		this.idBetting = idBetting;
	}

	public float getOddsHome() {
		return oddsHome;
	}

	public void setOddsHome(float oddsHome) {
		this.oddsHome = oddsHome;
	}

	public float getOddsAway() {
		return oddsAway;
	}

	public void setOddsAway(float oddsAway) {
		this.oddsAway = oddsAway;
	}

	public float getMaxBet() {
		return maxBet;
	}

	public void setMaxBet(float maxBet) {
		this.maxBet = maxBet;
	}
	

}
