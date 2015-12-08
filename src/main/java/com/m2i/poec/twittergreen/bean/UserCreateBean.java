package com.m2i.poec.twittergreen.bean;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.m2i.poec.twittergreen.check.Validator;
import com.m2i.poec.twittergreen.entity.User;
import com.m2i.poec.twittergreen.exception.ConfirmPasswordNotValidException;
import com.m2i.poec.twittergreen.exception.DuplicateEmailException;
import com.m2i.poec.twittergreen.exception.DuplicateNameException;
import com.m2i.poec.twittergreen.exception.EmailNotValidException;
import com.m2i.poec.twittergreen.exception.PasswordNotValidException;
import com.m2i.poec.twittergreen.exception.PictureNotValidException;
import com.m2i.poec.twittergreen.exception.UsernameNotValidException;

import com.m2i.poec.twittergreen.service.TweeterService;

@Named
@RequestScoped
public class UserCreateBean {

	private static final Logger LOGGER = Logger.getLogger(UserCreateBean.class.getName());

	@Inject
	private TweeterService tweeterService;

	@Inject
    private SessionBean sessionBean;

	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String picture;
	private String message;
	private Validator validator = new Validator();
	private static final String ERROR_USERNAME = "Veuillez entrer un Username avec les caractères suivant : [a-z] ou [A-Z] ou [0-9] ou \"_\"";
	private static final String ERROR_PASSWORD = "Veuillez entrer un Password avec : une longueur minimale de 8 caractères, une minuscule, une majuscule et un chiffre";
	private static final String ERROR_CONFIRM_PASSWORD = "Les champs \"Password\" doivent être identique";
	private static final String ERROR_EMAIL = "Entrez une adresse Email valide";
	private static final String ERROR_PICTURE = "Mettez une photo de profil";
	private static final String DUPLICATE_EMAIL = "Cet email est déjà utilisé";
	private static final String DUPLICATE_USERNAME = "Cet username est déja utilisé";

	public UserCreateBean() {

		picture = " ";
		message = " ";
	}

	public TweeterService getTweetService() {
		return tweeterService;
	}

	public void setTweetService(TweeterService tweetService) {
		this.tweeterService = tweetService;
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

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public String createUser() {
		try {
			validator.check(username, password, confirmPassword, email, picture);
			User user = tweeterService.createUser(username, password, email, picture);

			tweeterService.logUser(username, password);
			sessionBean.setUser(user);
			((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("user", user);

			return "Profil?faces-redirect=true";

		} catch (DuplicateNameException e) {
			message = DUPLICATE_USERNAME;
		} catch (UsernameNotValidException e) {
			message = ERROR_USERNAME;
		} catch (PasswordNotValidException e) {
			message = ERROR_PASSWORD;
		} catch (ConfirmPasswordNotValidException e) {
			message = ERROR_CONFIRM_PASSWORD;
		} catch (DuplicateEmailException e) {
			message = DUPLICATE_EMAIL;
		} catch (EmailNotValidException e) {
			message = ERROR_EMAIL;
		} catch (PictureNotValidException e) {
			message = ERROR_PICTURE;
		} catch (Exception e) {
			message = "erreur inconnue";
		}

		return "Inscription.xhtml";
	}

}
