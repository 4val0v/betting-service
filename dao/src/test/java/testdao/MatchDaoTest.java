package testdao;

import static org.junit.Assert.assertEquals;
import model.Betting;
import model.Match;
import model.MatchTicket;
import model.Ticket;
import model.Tip;
import model.User;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

import dao.MatchDAO;
import dao.MatchDAOImpl;

public class MatchDaoTest {
	
	MatchDAO dao = new MatchDAOImpl();
	Match m = dao.getMatchById(7);
	
	int tip_id = 6;
	int bet_id = 2;
	float odds_home = (float)1.8;
	float odds_away = (float)2.2;
	float max_bet = 500;
	
	@Test
	public void getMatchById()
	{
		assertEquals(tip_id, m.getIdTip());
		assertEquals(bet_id, m.getIdBetting());
		assertEquals(odds_home, m.getOddsHome(), 0.0001);
		assertEquals(odds_away, m.getOddsAway(), 0.0001);
		assertEquals(max_bet, m.getMaxBet(), 0.0001);	
	}
	
}
