package br.com.ticketballws.controller;

import java.sql.SQLException;

import br.com.ticketballws.conexao.Conexao;
import br.com.ticketballws.dao.UserDAO;
import br.com.ticketballws.model.Users;

public class UserController {
	
	public static Users buscaLogin(String login) throws SQLException{
		Users user = null;
		Conexao con = new Conexao();
		UserDAO dao = new UserDAO(con);
		user = dao.buscaLogin(login);
		con.close();
		return user;
	}
	
	public static Users buscaUser(int idUser) throws SQLException{
		Users user = null;
		Conexao con = new Conexao();
		UserDAO dao = new UserDAO(con);
		user = dao.buscaUser(idUser);
		con.close();
		return user;
	}
	
	public static int insertUser(Users user) throws SQLException{
		int retorno;
		Conexao con = new Conexao();
		UserDAO dao = new UserDAO(con);
		retorno = dao.insertUser(user);
		con.close();
		return retorno;
	}

}
