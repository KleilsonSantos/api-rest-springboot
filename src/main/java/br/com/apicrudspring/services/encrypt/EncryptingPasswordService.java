package br.com.apicrudspring.services.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Configuration;

import br.com.apicrudspring.services.exceptions.ServiceException;

@Configuration
public class EncryptingPasswordService {

	public String encryptPassword(String password) {
		MessageDigest encryptpassword;
		byte messageDigest[] = null;
		String hash = "";
		try {
			encryptpassword = MessageDigest.getInstance("SHA-256");
			messageDigest = encryptpassword.digest(password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			new ServiceException("Encrypt error: " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			new ServiceException("Encoding error: " + e.getMessage());
		}
		for (byte b : messageDigest) {
			hash += String.format("%02X", 0xFF & b);
		}
		return hash;
	}
}
