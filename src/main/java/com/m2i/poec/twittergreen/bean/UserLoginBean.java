package com.m2i.poec.twittergreen.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import com.m2i.poec.twittergreen.entity.Users;
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

	private Users user;

	private String username;
	private String password;

	private String errorName;
	private String errorPass;

	private boolean loggedIn = false;

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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void logUserAfterSigIn(String username, String password) {
		this.username = username;
		this.password = password;
		logUser();
		((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("user",
				username);
	}

	public String logUser() {
		try {
			errorName = "";
			errorPass = "";
			user = tweeterService.logUser(username, password);
			setLoggedIn(true);
			((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("user",
					username);
			LOGGER.info("LoggedIn mis à true");
			return "Profil?faces-redirect=true";
		} catch (EJBException e) {
			if (e.getCause().getClass() == NoResultException.class) {
				errorName = "Le nom d'utilisateur n'existe pas";
			} else if (e.getCause().getClass() == IllegalArgumentException.class) {
				errorPass = "Erreur inconnu";
			}
			setLoggedIn(false);
			return "Login";
		} catch (WrongPasswordException e) {
			errorPass = "Le mot de passe est incorrect";
			setLoggedIn(false);
			return "Login";
		}
	}
}
