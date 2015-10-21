package config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import service.ServiceLogin;
import service.ServiceRegister;
import service.ServiceV1;
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
public class MySessionFactoryConfig {

	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public MySessionFactory mySessionFactory()
	{
		return new MySessionFactory();
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public UserDAO userDao()
	{
		return new UserDAOImpl(mySessionFactory().getFactory());
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public TipDAO tipDao()
	{
		return new TipDAOImpl(mySessionFactory().getFactory());
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public MatchDAO matchDao()
	{
		return new MatchDAOImpl(mySessionFactory().getFactory());
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public BettingDAO bettingDao()
	{
		return new BettingDAOImpl(mySessionFactory().getFactory());
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public TicketDAO ticketDao()
	{
		return new TicketDAOImpl(mySessionFactory().getFactory());
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public ServiceV1 serviceV1()
	{
		return new ServiceV1(bettingDao(), matchDao(), tipDao());
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public MatchTicketDAO matchTicketDao()
	{
		return new MatchTicketDAOImpl(mySessionFactory().getFactory());
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public ServiceLogin serviceLogin()
	{
		return new ServiceLogin(userDao());
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_SINGLETON)
	public ServiceRegister serviceRegister()
	{
		return new ServiceRegister(userDao());
	}
}
