package com.m2i.poec.twittergreen.bean;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.mindrot.jbcrypt.BCrypt;

import com.m2i.poec.twittergreen.service.TweeterService;

@RequestScoped
@Named
public class TweetCreateBean {

	private static final Logger LOGGER = Logger.getLogger(TweetCreateBean.class.getName());

	@Inject
	private TweeterService tweetService;

	private Long author_id;
	private String content;
	private Date creation_Date;

	public Long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreation_Date() {
		return creation_Date;
	}

	public void setCreation_Date(Date creation_Date) {
		this.creation_Date = creation_Date;
	}

	public String createTweet() {

		try {
			tweetService.createTweet(author_id, content, creation_Date);
			return ""; //TODO la redirection
		} catch(EJBException ex) {

			// TODO décortiquer l'exception, voir ou est l'erreur, préparer un message d'erreur adéquat
			LOGGER.log(Level.INFO, "oups...", ex);
			return null;
		}
	}

}
