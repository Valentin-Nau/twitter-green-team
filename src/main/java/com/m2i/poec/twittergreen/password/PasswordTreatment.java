package com.m2i.poec.twittergreen.password;

import org.mindrot.jbcrypt.BCrypt;


/**
 * @author Xezaluri
 */
public class PasswordTreatment {

	private static int keyValue = 12;
	
	/**
	 * Permit to crypt the password.
	 * @param password
	 * @return String
	 */
	public static String cryptPassWord(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt(keyValue));
	}

	/**
	 * permit to decrypt the password.
	 * @param passwordEnter
	 * @param passwordHashStored
	 * @return Boolean
	 */
	public static Boolean decryptPassword(String passwordEnter, String passwordHashStored){
		return BCrypt.checkpw(passwordEnter, passwordHashStored);
	}

}
