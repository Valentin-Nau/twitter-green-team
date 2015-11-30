package com.m2i.poec.twittergreen.bean;

import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.m2i.poec.twittergreen.service.TweeterService;

@RequestScoped
@Named
public class UserCreateBean {

	private static final Logger LOGGER = Logger.getLogger(UserCreateBean.class.getName());

	@Inject
	private TweeterService tweetService;

	private String username;

	private String password;

	private String email;

	private String picture;

	public TweeterService getTweetService() {
		return tweetService;
	}

	public void setTweetService(TweeterService tweetService) {
		this.tweetService = tweetService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public String createUser() {
		try {
			tweetService.createUser(username, password, email, picture);
		} catch (EJBException ex) {
			
			
			return"";
			
		}

		return "";
	}
}
