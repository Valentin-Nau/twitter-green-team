package com.m2i.poec.twittergreen.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.m2i.poec.twittergreen.service.TweeterService;

@RequestScoped
@Named
public class TweetCreateBean {

	private static final Logger LOGGER = Logger.getLogger(TweetCreateBean.class.getName());

	@Inject
	private TweeterService tweetService;

	@Inject
	private UserLoginBean userLoginBean;
	
	private String content ="";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String createTweet() {

		try {
			tweetService.createTweet(userLoginBean.getUser(), content);
			content = "";
			return "Profil";
		} catch(EJBException ex) {

			// TODO décortiquer l'exception, voir ou est l'erreur, préparer un message d'erreur adéquat
			LOGGER.log(Level.INFO, "oups...", ex);
			return null;
		}
	}	
}
