package service;

import java.util.List;

import model.Tip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TipDAO;

@Service
public class ServiceTipImpl implements ServiceTip{

	@Autowired
	TipDAO td;
	
	@Override
	public List<Tip> getAllTips() {
		// TODO Auto-generated method stub
		return td.getAllTips();
	}

}
