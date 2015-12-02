package com.m2i.poec.twittergreen.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.m2i.poec.twittergreen.entity.Users;
import com.m2i.poec.twittergreen.service.TweeterService;

@RequestScoped
@Named
public class testBean {
	
	private static final Logger LOGGER = Logger.getLogger(testBean.class.getName());

	@Inject
	private TweeterService tweeterService;
	
	public String test(){
		LOGGER.log(Level.INFO, "================== DEBUT DE TEST ==================");
		List<Users> users= tweeterService.findAllUsers();
		for (Users user : users) {
			LOGGER.info(user.toString());
		}		
		tweeterService.createUser("test", "test", "test", "test");
		for (Users user : users) {
			LOGGER.info(user.toString());
		}
		return "NewFile";
	}
}