package com.m2i.poec.twittergreen.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.m2i.poec.twittergreen.entity.Tweet;

@Stateless
public class TweeterService {

	@PersistenceContext(unitName="TwitterGreenPU")
	private EntityManager em;

	public void createTweet(Long author_id, String content, Date creation_Date) {
		// TODO Auto-generated method stub
		Tweet tweet = new Tweet();
		tweet.setContent(content);
		tweet.setDate_creation(creation_Date);

		User user = em.find(author_id, User.class);

		tweet.setAuthor(user);

		em.persist(tweet);
	}
}
