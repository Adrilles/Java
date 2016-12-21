package br.com.ticketballws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {
	
	private int idUser;
	private String name;
	private String cpf;
	private String email;
	private String login;
	private String pass;
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString() {
		return "Users [idUser=" + idUser + ", name=" + name + ", cpf=" + cpf + ", email=" + email + ", login=" + login
				+ ", pass=" + pass + "]";
	}

}
