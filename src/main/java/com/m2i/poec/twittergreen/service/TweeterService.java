package com.m2i.poec.twittergreen.service;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.m2i.poec.twittergreen.entity.Tweet;
import com.m2i.poec.twittergreen.entity.User;
import com.m2i.poec.twittergreen.exception.WrongPasswordException;
import com.m2i.poec.twittergreen.password.PasswordTreatment;

@Stateful
public class TweeterService {

	@PersistenceContext(unitName = "TwitterGreenPU")

	private EntityManager em;

	public void createTweet(Long authorId, String content) {

		Tweet tweet = new Tweet();
		tweet.setContent(content);
		User user = em.find(User.class, authorId);
		tweet.setAuthor(user);
		em.persist(tweet);
	}

	public void createUser(String username, String password, String email, String picture) {
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(PasswordTreatment.cryptPassWord(password));
		user.setPicture(picture);
		user.setUsername(username);
		em.persist(user);
	}

	public void logUser(String username, String password) throws NoResultException, WrongPasswordException {
		User user = em.createQuery("SELECT id, username, password "
								 + "FROM User "
								 + "WHERE username = :pusername", User.class).setParameter("pusername", username).getSingleResult();
				
		if(!PasswordTreatment.decryptPassword(password, user.getPassword())){
			throw new WrongPasswordException();
		}
	}

}
