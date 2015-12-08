package com.m2i.poec.twittergreen.service;

import java.util.logging.Logger;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.m2i.poec.twittergreen.entity.Retweet;
import com.m2i.poec.twittergreen.entity.Tweet;
import com.m2i.poec.twittergreen.entity.User;
import com.m2i.poec.twittergreen.exception.DuplicateEmailException;
import com.m2i.poec.twittergreen.exception.DuplicateNameException;
import com.m2i.poec.twittergreen.exception.WrongPasswordException;
import com.m2i.poec.twittergreen.security.PasswordBCrypt;

@Stateless
public class TweeterService {

	@PersistenceContext(unitName = "TwitterGreenPU")
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(TweeterService.class.getName());

	public void createTweet(User user, String content) {

		Tweet tweet = new Tweet();
		tweet.setContent(content);
		tweet.setAuthor(user);
		LOGGER.info(tweet.getAuthor().toString());
		em.persist(tweet);
		em.flush();
		em.refresh(tweet);
		user.addTweet(tweet);

	}

	public User createUser(String username, String password, String email, String picture)
		throws DuplicateNameException, DuplicateEmailException {

		User user = new User();
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

	public User logUser(String username, String password)
			throws NoResultException, WrongPasswordException, IllegalArgumentException {

		User user = em.createQuery("SELECT u " + "FROM User AS u " + "WHERE username = :pusername", User.class)
				.setParameter("pusername", username).getSingleResult();
		if (!PasswordBCrypt.verifyPassword(password, user.getPassword())) {
			throw new WrongPasswordException();
		}
		else {
			return user;
		}
	}

	public List<User> findAllUsers() {

		return em.createQuery("SELECT DISTINCT u " + "FROM User AS u", User.class).getResultList();
	}
	
	public void reTweet(User retweetUser, Tweet tweet) {

		Objects.requireNonNull(retweetUser, "Pour retweeter nous avons besoin de la personne qui retweete");
		Objects.requireNonNull(tweet, "Pour retweeter nous avons besoin du tweet retweeter");
		
		if (retweetable(retweetUser, tweet)) {
			
			Retweet retweet = new Retweet();
			retweet.setTweet(tweet);
			
			retweet.setAuthor(retweetUser);
			
			LOGGER.info(retweet.toString());
	
			em.persist(retweet);
	
			retweetUser.addReTweet(retweet);
			
		}
	}
	
	public boolean retweetable(User retweetUser, Tweet tweet) {

		// L'utilisateur ne retweete son tweet.
		if (retweetUser.getId() == tweet.getAuthor().getId()) {
			return false;
		}
		
		// le tweet n'est pas déjà retweeté par cet utilisateur
		Retweet retweet = findOneReTweet(retweetUser.getId(), tweet.getId());

		if (retweet != null) {
			return false;
		}
		
		return true; 
	}

	public User getUser(String username) {
			User user = em.createQuery("SELECT u " + "FROM User AS u " + "WHERE username = :pusername", User.class)
					.setParameter("pusername", username).getSingleResult();
		return user;
	}
	
	public Retweet findOneReTweet(Long idUser, Long idTweet) {
		
		Retweet retweet = null;
		try {
			retweet = em.createQuery("SELECT t FROM Retweet as t WHERE idtweet = :pidTweet and iduser = :pidUser", Retweet.class)
				.setParameter("pidTweet", idTweet)
				.setParameter("pidUser", idUser)
				.getSingleResult();
		} catch (Exception e) {
			;
		}
		
		return retweet;
		
	}

	public Tweet findATweet(Long id) {
		return em.find(Tweet.class, id);
	}
}
