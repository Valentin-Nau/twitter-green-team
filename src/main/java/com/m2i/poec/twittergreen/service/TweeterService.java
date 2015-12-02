package com.m2i.poec.twittergreen.service;

import java.util.logging.Logger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.m2i.poec.twittergreen.bean.TweetCreateBean;
import com.m2i.poec.twittergreen.entity.Tweet;
import com.m2i.poec.twittergreen.entity.User;
import com.m2i.poec.twittergreen.exception.WrongPasswordException;
import com.m2i.poec.twittergreen.password.PasswordBCrypt;

@Stateless
public class TweeterService {

	@PersistenceContext(unitName = "TwitterGreenPU")
	private EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(TweetCreateBean.class.getName());
	
	public void createTweet(User user, String content) {

		Tweet tweet = new Tweet();
		tweet.setContent(content);

		tweet.setAuthor(user);
		
		em.persist(tweet);
		em.flush();
		em.refresh(tweet);
		
		user.addTweet(tweet);
		
		/*refreshUser(user);*/
	}

	public void createUser(String username, String password, String email, String picture) {

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setPicture(picture);
		user.setUsername(username);

		em.persist(user);

	}

	public User logUser(String username, String password) throws NoResultException, WrongPasswordException {

		User user = em.createQuery("SELECT u "
								 + "FROM User AS u "
								 + "INNER JOIN  u.tweets "
								 + "WHERE username = :pusername", User.class).setParameter("pusername", username).getSingleResult();
				
		if(!PasswordBCrypt.verifyPassword(password, user.getPassword())){
			throw new WrongPasswordException();

		}
		else {
			return user;
		}
	}
	
	/*public void refreshUser(User user){
		user = em.createQuery("SELECT u "
				 + "FROM User AS u "
				 + "INNER JOIN  u.tweets "
				 + "WHERE username = :pusername", User.class).setParameter("pusername", user.getUsername()).getSingleResult();
	}*/

	public List<User> findAllUsers() {

		return em.createQuery("SELECT DISTINCT u "
				 			+ "FROM User AS u", User.class).getResultList();
	}
}
