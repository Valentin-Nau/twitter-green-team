package com.m2i.poec.twittergreen.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.m2i.poec.twittergreen.entity.Tweet;
import com.m2i.poec.twittergreen.entity.User;

@Stateless
public class TweeterService {

<<<<<<< HEAD
	@PersistenceContext(unitName = "TwitterGreenPU")
=======
	@PersistenceContext(unitName="TwitterGreenPU")
>>>>>>> 3046c08c030d624343808b10af50aafd401fc09e
	private EntityManager em;

	public void createTweet(Long author_id, String content, Date creation_Date) {

		Tweet tweet = new Tweet();
		tweet.setContent(content);
		tweet.setDate_creation(creation_Date);

<<<<<<< HEAD
		User user = em.find(User.class, author_id);
=======
		User user = em.find(author_id, User.class);
>>>>>>> 3046c08c030d624343808b10af50aafd401fc09e

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
