package com.m2i.poec.twittergreen.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;


import com.m2i.poec.twittergreen.service.TweeterService;

@RequestScoped
@Named
public class UserCreateBean implements Serializable{
	private HashMap<String, String> message;

	private static final Logger LOGGER = Logger.getLogger(UserCreateBean.class.getName());

	@Inject
	private TweeterService tweetService;

	private String username;

	private String password;

	private String email;

	private String picture;

	public TweeterService getTweetService() {
		return tweetService;
	}

	public void setTweetService(TweeterService tweetService) {
		this.tweetService = tweetService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public static Logger getLogger() {
		return LOGGER;
	}
	
	public void setMessage(HashMap<String, String> message) {
		this.message = message;
	}

	public String createUser(String username, String password, String email, String picture) {
		try {
			
			// message.put("username", "OK");
			
			tweetService.createUser(username, password, email, picture);
			return "Tweet?message="+ message +"faces-redirect=true"; // profil à remettre

		} catch (Exception e) {

			e.printStackTrace();
			
			System.out.println("ON EST ICI");
			
			message.put("username", "username est vide");
			
			return "/User?message="+ message +"faces-redirect=true";

			// if (e.getCause() instanceof PersistenceException) {
			// if (e.getCause().getCause() instanceof
			// ConstraintViolationException) {

			// ConstraintViolationException ex = (ConstraintViolationException)
			// e.getCause().getCause();

			// LOGGER.log(Level.INFO, e.toString());
			// LOGGER.log(Level.INFO, "constraint name: " +
			// ex.getConstraintName());
			// LOGGER.log(Level.INFO, "message :" + ex.getMessage());
			// LOGGER.log(Level.INFO, "sql : " + ex.getSQL());
			// LOGGER.log(Level.INFO, "error code : " + ex.getErrorCode());
			// LOGGER.log(Level.INFO, "Sql state : " + ex.getSQLState());
			//
			// String msg = "Duplicate entry: " + ex.getConstraintName();
			// if (ex.getErrorCode() == 1062) { // duplicate entry (title)
			// throw new DuplicateUsernameException(msg, Status.CONFLICT);
			// } else if (ex.getErrorCode() == 1048) { // null constraint
			// (content)
			// throw new WebApplicationException(msg, Status.BAD_REQUEST);
			// } else if (ex.getErrorCode() == 1452) { // referential integrity
			// (author)
			// throw new WebApplicationException(msg, Status.BAD_REQUEST);
			// }
		}

		// }

		// catch (DuplicateUsernameException e) {
		//
		// errors.put("username", "This username exists");
		//
		// } catch (DuplicateSpecialEmailException e) {
		//
		// errors.put("email", "This special email exists");
		//
		// } catch (Exception e) {
		//
		// errors.put("other", "unknown errors");
		// }

		// on rentre dans la vue avec les erreurs insérées dans le hashmap
		// qu'on a passé.

	}

	// public static boolean checkUsernameExists(String username) {
	//
	// return false;
	// }
	//
	// public static boolean checkSpecialEmailExists(String email) {
	//
	// return false;
	// }

}
