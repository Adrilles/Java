package br.com.ticketballws.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ticketballws.conexao.Conexao;
import br.com.ticketballws.model.Users;

public class UserDAO {
	
	Conexao con = null;
	public UserDAO(Conexao con){
		this.con = con;
	}
	
	
	public Users buscaLogin(String login) throws SQLException{
		
		Users user = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_USER, NAME, CPF, EMAIL, LOGIN, PASS ");
		sql.append("FROM TB_USERS ");
		sql.append("WHERE LOGIN = ?");
		
		PreparedStatement prep = con.getConn().prepareStatement(sql.toString());
		prep.setString(1, login);
		
		ResultSet rs = prep.executeQuery();
		
		while (rs.next()) {
			user = new Users();
			user.setIdUser(rs.getInt("ID_USER"));
			user.setName(rs.getString("NAME"));
			user.setCpf(rs.getString("CPF"));
			user.setEmail(rs.getString("EMAIL"));
			user.setLogin(rs.getString("LOGIN"));
			user.setPass(rs.getString("PASS"));	
		}
		
		rs.close();
		prep.close();
		
		return user;
	}
	
	public Users buscaUser(int idUser) throws SQLException{
		
		Users user = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID_USER, NAME, CPF, EMAIL, LOGIN, PASS ");
		sql.append("FROM TB_USERS ");
		sql.append("WHERE ID_USER = ?");
		
		PreparedStatement prep = con.getConn().prepareStatement(sql.toString());
		prep.setInt(1, idUser);
		
		ResultSet rs = prep.executeQuery();
		
		while (rs.next()) {
			user = new Users();
			user.setIdUser(rs.getInt("ID_USER"));
			user.setName(rs.getString("NAME"));
			user.setCpf(rs.getString("CPF"));
			user.setEmail(rs.getString("EMAIL"));
			user.setLogin(rs.getString("LOGIN"));
			user.setPass(rs.getString("PASS"));	
		}
		
		rs.close();
		prep.close();
		
		return user;
	}
	
	public int insertUser(Users user) throws SQLException{
		int retorno;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_USERS (NAME, CPF, EMAIL, LOGIN, PASS)");
		sql.append("VALUES (?,?,?,?,?)");
		
		PreparedStatement prep = con.getConn().prepareStatement(sql.toString());
		prep.setString(1, user.getName());
		prep.setString(2, user.getCpf());
		prep.setString(3, user.getEmail());
		prep.setString(4, user.getLogin());
		prep.setString(5, user.getPass());
		
		retorno = prep.executeUpdate();
		
		return retorno;
	}

}
