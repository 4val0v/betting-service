package config;

import model.Betting;
import model.Match;
import model.MatchTicket;
import model.Ticket;
import model.Tip;
import model.User;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public ObjectMapper objectMapper()
	{
		return new ObjectMapper();
	}
	
}
