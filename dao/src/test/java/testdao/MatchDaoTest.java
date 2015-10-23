package testdao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import model.Match;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.MatchDAO;

public class MatchDaoTest {
	
	ApplicationContext context = 
            new ClassPathXmlApplicationContext("Beans.xml");
	
	MatchDAO dao = (MatchDAO) context.getBean("MatchDAO");
	
	
	int tip_id = 6;
	int bet_id = 2;
	float odds_home = (float)1.8;
	float odds_away = (float)2.2;
	float max_bet = 500;
	
	@Test
	public void getMatchById()
	{
		Match m = dao.getMatchById(7);
		
		assertEquals(tip_id, m.getIdTip());
		assertEquals(bet_id, m.getIdBetting());
		assertEquals(odds_home, m.getOddsHome(), 0.0001);
		assertEquals(odds_away, m.getOddsAway(), 0.0001);
		assertEquals(max_bet, m.getMaxBet(), 0.0001);	
	}
	
	@Test
	public void getMatchByIdFail()
	{
		Match m = dao.getMatchById(30);
		
		assertNotNull(m);
	}
	
}
