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
 * @created 27/11/2014
 *
 */
public class PostgreSQLDataBaseController implements IDataBaseController {
	
	private Connection connection;
	
			
	private final static String URL = "jdbc:postgresql://localhost/postgres";
	private final static String USER = "postgres";
	private final static String PASSWORD = "pfafveiou";
	
	public PostgreSQLDataBaseController() {
		this.connect();
	}

	@Override
	public void insertMessage(Message message) {
		
		Statement statement;
		try {
			
			statement = this.connection.createStatement();
			statement.executeUpdate(this.getInsertSQL(message));
			this.connection.close();
			
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
			
			this.connection.close();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}	
		
		return messages;
	}
	
	private void connect() {	      
	      try {
	         this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
	      } catch (SQLException sqle) {
	         sqle.printStackTrace();
	      }		
	}
	
	private String getInsertSQL(Message message) {
		return "insert into message (" + Message.MESSAGE + ", " + Message.USER_FROM + ", " + Message.USER_TO + ")"  +  
				" values (" + message.getMessage() + ", " + message.getUserFrom().getCode() + ", " + message.getUserTo().getCode() + ")";
	}
	
	private String getFetchSQL(User user, User userFrom, int amount) {
		return "select * from message m where m.userTo=" + user.getCode() + " and m.userFrom=" + userFrom.getCode() +  " limit " + amount;
	}
}
