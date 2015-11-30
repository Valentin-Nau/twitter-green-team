package com.m2i.poec.twittergreen.password;

import org.mindrot.jbcrypt.BCrypt;


/**
 * @author Xezaluri
 */
public class PasswordTreatment {

	private BCrypt bCrypt;
	private static int keyValue = 12;

	/**
	 * Permit to crypt the password.
	 * @param password
	 * @return String
	 */
	public String cryptPassWord(String password){
		return bCrypt.hashpw(password, BCrypt.gensalt(keyValue));
	}

	/**
	 * permit to decrypt the password.
	 * @param passwordEnter
	 * @param passwordHashStored
	 * @return Boolean
	 */
	public Boolean decryptPassword(String passwordEnter, String passwordHashStored){
		return bCrypt.checkpw(passwordEnter, passwordHashStored);
	}

}
