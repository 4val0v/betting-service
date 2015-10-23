package service;

import java.util.ArrayList;
import java.util.List;

import model.Offer;
import model.Ticket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.TicketDAO;

public class MainTEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	     /* ApplicationContext context = 
	              new ClassPathXmlApplicationContext("Beans.xml");
	      
	      TicketDAO t = (TicketDAO) context.getBean("TicketDAO");
	      
	      ArrayList<Ticket> ticket = (ArrayList<Ticket>) t.getAllTickets(); 
	      
	      ServiceCalculate service = (ServiceCalculate) context.getBean("serviceCalculateImpl");
	      
	      List<Offer> offers = service.findPotentialMatches(200, 10);
	      
	      for(Offer offer : offers)
	      {
	    	  System.out.println("*****************************************************************");
	    	  System.out.println(offer.getStake1());
	    	  System.out.println(offer.getStake2());
	    	  System.out.println(offer.getProfit());
	    	  System.out.println(offer.getMatch1().getTip().getName());
	      }*/
	      
		
	}

}
