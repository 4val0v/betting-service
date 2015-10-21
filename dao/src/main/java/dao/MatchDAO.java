package dao;

import java.util.List;

import model.Match;

public interface MatchDAO {
	
	public Match getMatchById(int id);
	
	public List<Match> getAllMatches();
	
	public List<Match> getMatchesForBetting(int idBet);
	
	public boolean addMatch(Match m);
	
	public boolean deleteMatch(int id);
	
	public Match getMatchForBetting(int tipId, int betId);
}
