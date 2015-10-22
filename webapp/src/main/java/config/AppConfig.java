package config;

import model.Betting;
import model.Match;
import model.MatchTicket;
import model.Ticket;
import model.Tip;
import model.User;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import service.ServiceBetImpl;
import service.ServiceLoginImpl;
import service.ServiceRegisterImpl;
import service.ServiceCalculateImpl;
import dao.BettingDAO;
import dao.BettingDAOImpl;
import dao.MatchDAO;
import dao.MatchDAOImpl;
import dao.MatchTicketDAO;
import dao.MatchTicketDAOImpl;
import dao.TicketDAO;
import dao.TicketDAOImpl;
import dao.TipDAO;
import dao.TipDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;

@Configuration
public class AppConfig {
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public SessionFactory sessionFactory()
	{
		return new AnnotationConfiguration().configure().addAnnotatedClass(User.class)
				.addAnnotatedClass(Tip.class).addAnnotatedClass(MatchTicket.class).
				addAnnotatedClass(Betting.class).addAnnotatedClass(Match.class).addAnnotatedClass(Ticket.class).
				addAnnotatedClass(Ticket.class).buildSessionFactory();
	}
	
}
