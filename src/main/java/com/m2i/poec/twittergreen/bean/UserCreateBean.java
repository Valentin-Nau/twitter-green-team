package com.m2i.poec.twittergreen.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import com.m2i.poec.twittergreen.check.Validator;
import com.m2i.poec.twittergreen.service.TweeterService;

@RequestScoped
@Named
public class UserCreateBean implements Serializable {
	
	private HashMap<String, String> message;
	private Validator validator = new Validator();
	private static final Logger LOGGER = Logger.getLogger(UserCreateBean.class.getName());

	@Inject
	private TweeterService tweetService;
	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String picture;
	
	public UserCreateBean() {
		username="";
		password="";
		confirmPassword="";
		email="";
		picture="";
	}

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

	public void setMessage(HashMap<String, String> message) {
		this.message = message;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String createUser() {
		try {
			validator.check(username, password, confirmPassword, email, picture);
			tweetService.createUser(username, password, email, picture);
			return "Login.xhtml";
																	
		} catch (Exception e) {
			message.put("username", "username est vide");
			return "User.xhtml";
		}
	}

}
