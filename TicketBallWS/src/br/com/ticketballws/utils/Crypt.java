package br.com.ticketballws.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypt {

	static String IV = "AAAAAAAAAAAAAAAA";
	static String pass = "4dr1ll3s@1985@#@";

	public static byte[] encryptAES(String textopuro) throws Exception {
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(pass.getBytes("ISO-8859-1"), "AES");
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("ISO-8859-1")));
		return encripta.doFinal(textopuro.getBytes("ISO-8859-1"));
	}

	public static String decryptAES(byte[] textoencriptado) throws Exception {
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(pass.getBytes("ISO-8859-1"), "AES");
		decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("ISO-8859-1")));
		return new String(decripta.doFinal(textoencriptado), "ISO-8859-1");
	}

	public static String decryptAES(String[] textoencriptado) throws Exception {
		byte[] encrypt = new byte[textoencriptado.length];
		for (int i = 0; i < encrypt.length; i++) {
			encrypt[i] = Byte.parseByte(textoencriptado[i]);
		}
		return decryptAES(encrypt);
	}
	
	public static String encryptStrAES (String textopuro) throws Exception{
		
		byte[] encrytp = encryptAES(textopuro);
		StringBuilder str = new StringBuilder();
		for (int i=0; i<encrytp.length; i++){
			str.append(new Integer(encrytp[i])+";");
		}
		
		return str.toString();
	}

}
