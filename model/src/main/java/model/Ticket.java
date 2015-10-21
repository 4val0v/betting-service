package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id @Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "user")
	private String idUser;
	
	@Column(name = "bet_id")
	private int idBetting;
	
	@Column(name = "stake")
	private float stake;
	
	@Column(name = "outcome")
	private float outcome;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "ticket_id")
	private List<MatchTicket> matches = new ArrayList<MatchTicket>();
	
	
	public List<MatchTicket> getMatches() {
		return matches;
	}
	public void setMatches(List<MatchTicket> matches) {
		this.matches = matches;
	}
	public Ticket(){}
	public Ticket(int id, String idUser, int idBetting, float stake, float outcome)
	{
		this.id = id;
		this.idUser = idUser;
		this.idBetting = idBetting;
		this.stake = stake;
		this.outcome = outcome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public int getIdBetting() {
		return idBetting;
	}
	public void setIdBetting(int idBetting) {
		this.idBetting = idBetting;
	}
	public float getStake() {
		return stake;
	}
	public void setStake(float stake) {
		this.stake = stake;
	}
	public float getOutcome() {
		return outcome;
	}
	public void setOutcome(float outcome) {
		this.outcome = outcome;
	}
	
	
}
