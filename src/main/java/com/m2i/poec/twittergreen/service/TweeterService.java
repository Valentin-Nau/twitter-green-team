package com.m2i.poec.twittergreen.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.m2i.poec.twittergreen.bean.UserCreateBean;
import com.m2i.poec.twittergreen.entity.Tweet;
import com.m2i.poec.twittergreen.entity.User;

@Stateless
public class TweeterService {

	@PersistenceContext(unitName = "TwitterGreenPU")

	private EntityManager em;

	public void createTweet(Long author_id, String content, Date creation_Date) {

		Tweet tweet = new Tweet();
		tweet.setContent(content);
		tweet.setDate_creation(creation_Date);

		User user = em.find(User.class, author_id);

		tweet.setAuthor(user);

		em.persist(tweet);
	}

	public void createUser(String username, String password, String email, String picture) {
		
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setPicture(picture);
		user.setUsername(username);
		em.persist(user);
	}

}
