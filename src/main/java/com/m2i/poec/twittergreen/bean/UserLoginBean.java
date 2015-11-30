package com.m2i.poec.twittergreen.bean;

import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import com.m2i.poec.twittergreen.exception.WrongPasswordException;
import com.m2i.poec.twittergreen.service.TweeterService;
import java.io.Serializable;

@SessionScoped
@Named
public class UserLoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UserCreateBean.class.getName());

	@Inject
	private TweeterService tweeterService;

	private Long id;	
	private String username;	
	private String password;
	
	private String errorName;
	private String errorPass;
	

	public TweeterService getTweetService() {
		return tweeterService;
	}

	public void setTweetService(TweeterService tweetService) {
		this.tweeterService = tweetService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public static Logger getLogger() {
		return LOGGER;
	}
	
	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
	
	public void setErrorPass(String errorPass) {
		this.errorPass = errorPass;
	}
	
	public String getErrorPass() {
		return errorPass;
	}
	
	public String logUser() {
		try {
			tweeterService.logUser(username, password);
			return "Profil?faces-redirect=true";
		}
		catch (NoResultException e) {
			errorName = "Le nom d'utilisateur n'existe pas";
			return "Login";
		}
		catch (WrongPasswordException e) {
			errorPass = "Le mot de passe est incorrect";
			return "Login";
		}
	}
}
