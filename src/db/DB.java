package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

//	Objeto de conexão com o JDBC
	private static Connection conn = null;  
	
//	Método para conectar com o banco de dados
	public static Connection getConnection() {
//		Se conn for nulo, fazer a conexão com o banco de dados
		if(conn == null) {
			try {
//				pegar a propriedades com o método loadProperties()
				Properties props = loadProperties();
//				Pegar a url do banco de dados
				String url = props.getProperty("dburl");
//				Obter a conexão com o banco
				conn = DriverManager.getConnection(url, props);
//				Conectar com o banco de dados é instânciar um objeto do tipo Connection
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
//	Fechar conexão
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
//	Carregar os dados de db.properties dentro de um objeto
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}	
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch (SQLException e) {
				throw new DbException(e.getMessage());
			}	
		}
	}
	
}
