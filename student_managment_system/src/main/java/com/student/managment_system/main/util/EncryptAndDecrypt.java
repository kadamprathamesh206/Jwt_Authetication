package com.student.managment_system.main.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Component;

@Component
public class EncryptAndDecrypt {

	public String encryption(String password) {
		Encoder encoder= Base64.getEncoder();
		String encrypted_password= encoder.encodeToString(password.getBytes());
		return encrypted_password;
	}


	public String decryption(String password) {
		Decoder decoder= Base64.getDecoder();
		byte[] s=decoder.decode(password);
		String decrypted_password=new String(s);

		return decrypted_password;
	}
}
