package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Message;
import models.User;

/**
 * Classe responsável pelo acesso ao banco via postgresql.
 * 
 * @author Will Glück
 * @created 20/11/2014
 *
 */
public class PostgreSQLDataBaseController implements IDataBaseController {
	
	//Atributos
	
	private Connection connection;
	
	//Campos estáticos
			
	private final static String URL = "jdbc:postgresql://localhost/postgres";
	private final static String USER = "postgres";
	private final static String PASSWORD = "pfafveiou";
	
	/**
	 * Construtor.
	 */
	public PostgreSQLDataBaseController() {
		this.connect();
	}

	@Override
	public void insertMessage(Message message) {
		
		Statement statement;
		try {
			
			statement = this.connection.createStatement();
			statement.executeUpdate(this.getInsertSQL(message));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<Message> fetchLastMessages(int amount, User user, User userFrom) {
		
		List<Message> messages = new ArrayList<Message>();
		ResultSet rs = null;
		PreparedStatement pst = null;
						
		try {	
			
			pst = this.connection.prepareStatement(this.getFetchSQL(user, userFrom, amount));
			pst.execute();
			rs = pst.getResultSet();
			
			while (rs.next()) {
				
				Message message = new Message();
				message.setMessage(rs.getString(Message.MESSAGE));
				message.setUserFrom(userFrom);
				message.setUserTo(user);
				messages.add(message);
				
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}	
		
		return messages;
	}
	

	@Override
	public List<User> fetchUsers() {
		
		List<User> users = new ArrayList<User>();
		ResultSet rs = null;
		PreparedStatement pst = null;
						
		try {	
			
			pst = this.connection.prepareStatement("Select * from chatuser");
			pst.execute();
			rs = pst.getResultSet();
			
			while (rs.next()) {
				
				User user = new User();
				user.setCode(rs.getInt(User.CODE));
				user.setName(rs.getString(User.NAME));
				users.add(user);
				
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}	
		
		return users;
	}
	
	/**
	 * Conecta ao banco.
	 */
	private void connect() {	      
	      try {
	         this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
	      } catch (SQLException sqle) {
	         sqle.printStackTrace();
	      }		
	}
	
	/**
	 * 
	 * @param message Mensagem que foi enviada.
	 * @return SQL para sua inserção.
	 */
	private String getInsertSQL(Message message) {
		return "insert into message (" + Message.MESSAGE + ", " + Message.USER_FROM + ", " + Message.USER_TO + ")"  +  
				" values ('" + message.getMessage() + "', " + message.getUserFrom().getCode() + ", " + message.getUserTo().getCode() + ")";
	}
	
	/**
	 * 
	 * @param user Usuário que irá receber as mensagem.
	 * @param userFrom Usuário que enviou as mensagens.
	 * @param amount Quantidade de mensagens desejada. 
	 * @return SQL para busca.
	 */
	private String getFetchSQL(User user, User userFrom, int amount) {
		return "select * from message m where m.userTo=" + user.getCode() + " and m.userFrom=" + userFrom.getCode() +  " limit " + amount;
	}

}
