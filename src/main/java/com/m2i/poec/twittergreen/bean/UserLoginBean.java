package com.m2i.poec.twittergreen.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.m2i.poec.twittergreen.entity.User;
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

	private User user;
	
	private String username;
	private String password;
	
	private String errorName;
	private String errorPass;
		
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
			errorName="";
			errorPass="";
			user = tweeterService.logUser(username, password);
			return "Profil?faces-redirect=true";
		}
		catch (EJBException e) {
			errorName = "Le nom d'utilisateur n'existe pas";
			return "Login";
		}
		catch (WrongPasswordException e) {
			errorPass = "Le mot de passe est incorrect";
			return "Login";
		}
	}
	
	public String test(){
		LOGGER.log(Level.INFO, "================== DEBUT DE TEST ==================");
		List<User> users= tweeterService.findAllUsers();
		LOGGER.info(users.toString());
		tweeterService.createUser("test", "test", "test", "test");
		LOGGER.info(users.toString());
		return "NewFile";
	}
}
