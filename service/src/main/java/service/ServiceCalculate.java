package service;

import java.util.ArrayList;

import model.Offer;

public interface ServiceCalculate {
	
	public ArrayList<Offer> findPotentialMatches(float stake, float profit);

}
