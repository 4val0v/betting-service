package dao;

import java.util.List;

import model.Tip;

public interface TipDAO {

	public Tip getTipById(int id);
	
	public boolean addTip(Tip t);
	
	public List<Tip> getAllTips();
	
	public Tip getTipByName(String name);
	
	public boolean deleteTip(int id);
	
	
}
