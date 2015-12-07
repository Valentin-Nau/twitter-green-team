package com.m2i.poec.twittergreen.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.m2i.poec.twittergreen.entity.Tweet;
import com.m2i.poec.twittergreen.service.TweeterService;

@Named
@ViewScoped
public class ProfilePageBean implements Serializable{
	private String userName;
	
	private static final Logger LOGGER = Logger.getLogger(ProfilePageBean.class.getName());
	
	@Inject
    private SessionBean sessionBean;
	
	@Inject
	private TweeterService tweeterService;
		
	private String content ="";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		LOGGER.info("setContent : " + content);
		this.content = content;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<Tweet> getTweets() {
		LOGGER.info(userName);
		return tweeterService.getUser(userName).getTweets();
	}
	
	public String createTweet() {
		try {
			LOGGER.info("contenu :" + content);
			tweeterService.createTweet(sessionBean.getUser(), content);
			return "Profil?username=" + sessionBean.getUser().getUsername() + "&faces-redirect=true";
		} catch(EJBException ex) {

			// TODO décortiquer l'exception, voir ou est l'erreur, préparer un message d'erreur adéquat
			LOGGER.log(Level.INFO, "oups...", ex);
			return null;
		}
	}
	
	public String disconnect() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "Login";
	}

}
