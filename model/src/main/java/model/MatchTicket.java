package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
		name = "match_on_ticket",
		uniqueConstraints = @UniqueConstraint(columnNames = { "match_id", "ticket_id" })
		)

public class MatchTicket {

	@Id @Column(name = "id")
	@GeneratedValue
	private int id;
	@Column(name = "match_id")
	private int match_id;
	@Column(name = "ticket_id")
	private int ticket_id;
	@Column(name = "guess")
	private int guess;
	
	@ManyToOne
	@JoinColumn(name = "match_id", insertable=false, updatable=false)
	private Match match;
	
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public MatchTicket(){}

	public MatchTicket(int id, int match_id, int ticket_id, int guess) {
		this.id = id;
		this.match_id = match_id;
		this.ticket_id = ticket_id;
		this.guess = guess;
	}
	
	public int getGuess()
	{
		return guess;
	}
	
	public void setGuess(int guess)
	{
		this.guess = guess;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMatch_id() {
		return match_id;
	}
	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}
	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	
	
}
