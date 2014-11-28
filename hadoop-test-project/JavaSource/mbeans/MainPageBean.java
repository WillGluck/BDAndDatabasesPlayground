package mbeans;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import models.Message;
import models.User;
import chat.ChatController;
import database.HbaseDataBasecontroller;
import database.PostgreSQLDataBaseController;

/**
 * 
 * @author Will Glück
 * @created 20/11/2014
 *
 */
@ManagedBean(name="mainPage")
@RequestScoped
public class MainPageBean {
	
	//Atributos de tela diversos.
	
	private ChatController chatController;
	
	private String dbSelected;
	
	private List<Message> messages;
	private List<User> users;
	
	private int amountOfMessages;
	private Message message;
	private User userFrom;
	private User userTo;
		
	private int userFromCode;
	private int userToCode;
	private long searchResultTime;
	private long insertResultTime;

	/**
	 * Construtor.
	 */
	public MainPageBean() {
		this.initModels();
		this.changeToPostgreSQL();
		this.users = this.chatController.getUsers();
	}
	
	/**
	 * Inicializa modelos.
	 */
	private void initModels() {
		this.userFrom = new User();
		this.userTo = new User();
		this.message = new Message();		
	}
	
	/**
	 * Muda para o banco postgresql.
	 */
	public void changeToPostgreSQL() {
		this.chatController = new ChatController(new PostgreSQLDataBaseController());
		this.dbSelected = "PostgreSQL";
	}	
	
	/**
	 * Muda par ao banco HBASE.
	 */
	public void changeToHBASE() {
		this.chatController = new ChatController(new HbaseDataBasecontroller());
		this.dbSelected = "HBASE";
	}
	
	/**
	 * Envia mensagens.
	 */
	public void sendMessage() {		
		this.prepareMessage();
		long startTime = System.nanoTime();
		for (int i = 0; i < this.amountOfMessages; i++) {
			this.chatController.sendMessage(this.message);	
		}		
		long endTime = System.nanoTime();
		this.insertResultTime = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
	}
	
	/**
	 * Lê mensagens.
	 */
	public void readMessage() {
		long startTime = System.nanoTime();
		this.userTo = this.getUser(this.userToCode);
		this.userFrom = this.getUser(this.userFromCode);
		this.chatController.getMessages(this.userTo, this.userFrom, this.amountOfMessages);
		long endTime = System.nanoTime();
		this.searchResultTime = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
	}
	
	/** 
	 * @param code ID do usuário desejado.
	 * @return Usuário da ID passada.
	 */
	private User getUser(int code) {
		for (User user : this.users) {
			if (user.getCode() == code) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Prepara a mensagem.
	 */
	private void prepareMessage() {
		this.message.setUserFrom(this.getUser(this.userFromCode));
		this.message.setUserTo(this.getUser(this.userToCode)); 
	}
	
	//Getters and setters.

	public String getDbSelected() {
		return dbSelected;
	}

	public void setDbSelected(String dbSelected) {
		this.dbSelected = dbSelected;
	}

	public int getAmountOfMessages() {
		return amountOfMessages;
	}

	public void setAmountOfMessages(int amountOfMessages) {
		this.amountOfMessages = amountOfMessages;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public long getSearchResultTime() {
		return searchResultTime;
	}

	public void setSearchResultTime(long searchResultTime) {
		this.searchResultTime = searchResultTime;
	}

	public long getInsertResultTime() {
		return insertResultTime;
	}

	public void setInsertResultTime(long insertResultTime) {
		this.insertResultTime = insertResultTime;
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public int getUserFromCode() {
		return userFromCode;
	}
	
	public void setUserFromCode(int userFromCode) {
		this.userFromCode = userFromCode;
	}
	
	public int getUserToCode() {
		return userToCode;
	}
	
	public void setUserToCode(int userToCode) {
		this.userToCode = userToCode;
	}
	
}
