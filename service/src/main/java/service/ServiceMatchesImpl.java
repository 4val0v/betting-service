package service;

import java.util.List;

import model.Match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.MatchDAO;

@Service
public class ServiceMatchesImpl implements ServiceMatches{

	@Autowired
	MatchDAO dao;
	
	@Override
	public List<Match> getMatchesForBetting(int idBet) {
		// TODO Auto-generated method stub
		return dao.getMatchesForBetting(idBet);
	}

	
	
	
}
