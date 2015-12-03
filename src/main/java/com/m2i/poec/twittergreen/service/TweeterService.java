package com.m2i.poec.twittergreen.service;

import java.util.logging.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import com.m2i.poec.twittergreen.bean.TweetCreateBean;
import com.m2i.poec.twittergreen.entity.Tweet;
import com.m2i.poec.twittergreen.entity.Users;
import com.m2i.poec.twittergreen.exception.DuplicateNameException;
import com.m2i.poec.twittergreen.exception.WrongPasswordException;
import com.m2i.poec.twittergreen.security.PasswordBCrypt;

@Stateless
public class TweeterService {

	@PersistenceContext(unitName = "TwitterGreenPU")
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(TweetCreateBean.class.getName());

	public void createTweet(Users user, String content) {

		Tweet tweet = new Tweet();
		tweet.setContent(content);

		tweet.setAuthor(user);

		em.persist(tweet);
		em.flush();
		em.refresh(tweet);

		user.addTweet(tweet);

	}

	public void createUser(String username, String password, String email, String picture)
			throws DuplicateNameException {

		Users user = new Users();
		user.setEmail(email);
		user.setPassword(PasswordBCrypt.cryptPassWord(password));
		user.setPicture(picture);
		user.setUsername(username);
		try {
			em.persist(user);
		} catch (Exception e) {
			throw new DuplicateNameException();
		}

	}

	public Users logUser(String username, String password)
			throws NoResultException, WrongPasswordException, IllegalArgumentException {
		LOGGER.info("username " + username + " " + password);
		Users user = em.createQuery("SELECT u " + "FROM Users AS u " + "WHERE username = :pusername", Users.class)
				.setParameter("pusername", username).getSingleResult();
		LOGGER.info(user.toString());
		if (!PasswordBCrypt.verifyPassword(password, user.getPassword())) {
			throw new WrongPasswordException();

		} else {
			LOGGER.info("On s'est bien loggé");
			return user;
		}
	}

	public List<Users> findAllUsers() {

		return em.createQuery("SELECT DISTINCT u " + "FROM Users AS u", Users.class).getResultList();
	}
}
