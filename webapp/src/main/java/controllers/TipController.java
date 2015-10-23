package controllers;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.ServiceTip;

@Controller
public class TipController {

	@Autowired
	ServiceTip service;
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping(value = "/tips", method = RequestMethod.GET)
	public @ResponseBody String getAllTips()
	{
		
		String resp = "";
		
		try {
			resp = mapper.writeValueAsString(service.getAllTips());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
		
		return resp;
	}
		
}
