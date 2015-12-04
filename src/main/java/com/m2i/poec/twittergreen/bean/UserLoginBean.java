package com.m2i.poec.twittergreen.bean;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import com.m2i.poec.twittergreen.entity.Users;
import com.m2i.poec.twittergreen.exception.WrongPasswordException;
import com.m2i.poec.twittergreen.service.TweeterService;
import java.io.Serializable;

@RequestScoped
@Named
public class UserLoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UserCreateBean.class.getName());
	
	private static final String ERROR_NAME = "Le nom d'utilisateur n'existe pas";
	private static final String ERROR_PASS = "Le mot de passe est incorrect";

	@Inject
    private SessionBean sessionBean;
	
	@Inject
	private TweeterService tweeterService;

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

	public void logUserAfterSigIn(String username, String password) {
		LOGGER.info("On passe dans logUserAfterSignIn");
		this.username = username;
		this.password = password;
		logUser();
	}

	public String logUser() {
		try {
			errorName = "";
			errorPass = "";
			
			Users user = tweeterService.logUser(username, password);
			sessionBean.setUser(user);
			LOGGER.info(sessionBean.getUser().toString());
			((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("user", user);
			
			return "Profil?faces-redirect=true";
			
		} catch (EJBException e) {
			
			if (e.getCause().getClass() == NoResultException.class) {
				errorName = ERROR_NAME;
			} 
			else if (e.getCause().getClass() == IllegalArgumentException.class) {
				errorPass = "Erreur inconnu";
			}
			
			return "Login";
			
		} catch (WrongPasswordException e) {
			
			errorPass = ERROR_PASS;
			return "Login";
		}
	}

	public void setTwitterService(TweeterService tweetService) {
		this.tweeterService = tweetService;
		
	}
}
