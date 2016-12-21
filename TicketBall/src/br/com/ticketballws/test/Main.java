package br.com.ticketballws.test;

import java.sql.SQLException;

import br.com.ticketballws.Utils.Crypt;
import br.com.ticketballws.conexao.Conexao;
import br.com.ticketballws.dao.UserDAO;
import br.com.ticketballws.model.Users;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		
		try {
			main.testConexao();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void testCrypt() throws Exception{
		byte[] encrytp = Crypt.encryptAES("root");
		
		StringBuilder str = new StringBuilder();
		for (int i=0; i<encrytp.length; i++){
			str.append(new Integer(encrytp[i])+";");
			System.out.print(new Integer(encrytp[i])+";");
		}
           		
		String encrytpText = new String(encrytp);
		
		System.out.print("Texto Encriptado: " + encrytpText);
		
		
		String textodecriptado = Crypt.decryptAES(encrytp);
         
        System.out.println("Texto Decriptado 1: " + textodecriptado);
        
        String[] testEncrypt = str.toString().split(";");
        byte[] encrypt = new byte[testEncrypt.length];
        for (int i = 0; i < encrypt.length; i++) {
        	encrypt[i] = Byte.parseByte(testEncrypt[i]);
		}
        
        textodecriptado = Crypt.decryptAES(encrypt);
        
        System.out.println("Texto Decriptado 2: " + textodecriptado);
        
        //extodecriptado = Crypt.decryptAES(encrytpText.getBytes());
        
        //System.out.println("Texto Decriptado 3: " + textodecriptado);
		
	}
	
	public void testConexao(){
		Conexao con = null;
		UserDAO userDao = null;
		Users user = null;
		
		try {
			con = new Conexao();
			System.out.println(con.getStatus());
			userDao = new UserDAO(con);
			user = userDao.buscaLogin("test");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(con.getStatus());
		System.out.println(user.toString());
	}

}
