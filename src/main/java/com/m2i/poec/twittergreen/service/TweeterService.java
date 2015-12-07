package com.m2i.poec.twittergreen.service;

import java.util.logging.Logger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.m2i.poec.twittergreen.entity.Tweet;
import com.m2i.poec.twittergreen.entity.Users;
import com.m2i.poec.twittergreen.exception.DuplicateEmailException;
import com.m2i.poec.twittergreen.exception.DuplicateNameException;
import com.m2i.poec.twittergreen.exception.WrongPasswordException;
import com.m2i.poec.twittergreen.security.PasswordBCrypt;

@Stateless
public class TweeterService {

	@PersistenceContext(unitName = "TwitterGreenPU")
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(TweeterService.class.getName());

	public void createTweet(Users user, String content) {

		Tweet tweet = new Tweet();
		tweet.setContent(content);

		tweet.setAuthor(user);

		LOGGER.info(tweet.getAuthor().toString());
		em.persist(tweet);
		em.flush();
		em.refresh(tweet);

		user.addTweet(tweet);

	}

	public Users createUser(String username, String password, String email, String picture) 
		throws DuplicateNameException, DuplicateEmailException {

		Users user = new Users();
		user.setEmail(email);
		user.setPassword(PasswordBCrypt.cryptPassWord(password));
		user.setPicture(picture);
		user.setUsername(username);
		try {
			em.persist(user);
		} catch (PersistenceException e) {
			if(e.getCause().getCause().getMessage().contains("username")){
				throw new DuplicateNameException();
			}
			else if(e.getCause().getCause().getMessage().contains("email")){
				throw new DuplicateEmailException();
			}
		}
		return user;
	}

	public Users logUser(String username, String password)
			throws NoResultException, WrongPasswordException, IllegalArgumentException {
		
		Users user = em.createQuery("SELECT u " + "FROM Users AS u " + "WHERE username = :pusername", Users.class)
				.setParameter("pusername", username).getSingleResult();
		if (!PasswordBCrypt.verifyPassword(password, user.getPassword())) {
			throw new WrongPasswordException();
		}
		else {
			return user;
		}
	}

	public List<Users> findAllUsers() {

		return em.createQuery("SELECT DISTINCT u " + "FROM Users AS u", Users.class).getResultList();
	}


	public Users getUser(String username) {
		LOGGER.info("test pendant getUser");
		Users user = em.createQuery("SELECT u " + "FROM Users AS u " + "WHERE username = :pusername", Users.class)
				.setParameter("pusername", username).getSingleResult();
		LOGGER.info(user.toString());
		return user;
	}
}
