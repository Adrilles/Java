package br.com.ticketballws.conexao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.ticketballws.utils.Config;
import br.com.ticketballws.utils.Crypt;

public class Conexao {

	private Connection conn = null;
	private String status = "";
	private InputStream inputStream;

	public Conexao() {
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties prop = Config.getProp(inputStream);
			
			// Carregando o JDBC Driver padrão
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);

			// Configurando a nossa conexão com um banco de dados//
			String serverName = Crypt.decryptAES(prop.getProperty("prop.server.serverName").split(";")); // caminho do servidor do BD
			String mydatabase = Crypt.decryptAES(prop.getProperty("prop.server.database").split(";")); // nome do seu banco de dados
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = Crypt.decryptAES(prop.getProperty("prop.server.username").split(";")); // nome de um usuário de seu BD
			String password = Crypt.decryptAES(prop.getProperty("prop.server.password").split(";")); // sua senha de acesso

			conn = DriverManager.getConnection(url, username, password);

			// Testa sua conexão//

			if (conn != null) {
				setStatus("STATUS--->Conectado com sucesso!");
			} else {
				setStatus("STATUS--->Não foi possivel realizar conexão");
			}

		} catch (ClassNotFoundException e) { // Driver não encontrado
			setStatus("O driver expecificado nao foi encontrado. " + e.getMessage());
		} catch (Exception e) {
			// Não conseguindo se conectar ao banco
			setStatus("Nao foi possivel conectar ao Banco de Dados. " + e.getMessage());
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public boolean close() {
		try {

			conn.close();
			setStatus("STATUS--->Conexao Fechada!");
			return true;
		} catch (SQLException e) {
			setStatus("STATUS--->"+ e.getMessage());
			return false;
		}

	}

}
