package com.m2i.poec.twittergreen.bean;

import java.util.Date;
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

	private Long authorId = 1L;
	private String content;
	private Date creationDate;

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String createTweet() {

		try {
			tweetService.createTweet(authorId, content);
			return "Profil";
		} catch(EJBException ex) {

			// TODO décortiquer l'exception, voir ou est l'erreur, préparer un message d'erreur adéquat
			LOGGER.log(Level.INFO, "oups...", ex);
			return null;
		}
	}	
}
