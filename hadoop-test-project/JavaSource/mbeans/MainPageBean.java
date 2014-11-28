package mbeans;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import models.Message;
import models.User;
import chat.ChatController;
import database.HbaseDataBasecontroller;
import database.PostgreSQLDataBaseController;


@ManagedBean(name="mainPage")
@RequestScoped
public class MainPageBean {
	
	private ChatController chatController;
	
	private String dbSelected;
	
	private int amountOfMessages;
	private Message message;
	private User userFrom;
	private User userTo;
		
	private int searchResultTime;
	private int insertResultTime;

	public MainPageBean() {
		this.userFrom = new User();
		this.userTo = new User();
		this.message = new Message();
	}
	
	public void changeToPostgreSQL() {
		this.chatController = new ChatController(new PostgreSQLDataBaseController());
		this.dbSelected = "PostgreSQL";
	}	
	
	public void changeToHBASE() {
		this.chatController = new ChatController(new HbaseDataBasecontroller());
		this.dbSelected = "HBASE";
	}
	
	public void sendMessage() {
		int startTime = (int) new Date().getTime();
		this.chatController.sendMessage(this.message);
		int endTime = (int) new Date().getTime();
		this.insertResultTime = endTime - startTime;
	}
	
	public void readMessage() {
		int startTime = (int) new Date().getTime();
		this.chatController.getMessages(this.userTo, this.userFrom, this.amountOfMessages);
		int endTime = (int) new Date().getTime();
		this.searchResultTime = endTime - startTime;
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

	public int getSearchResultTime() {
		return searchResultTime;
	}

	public void setSearchResultTime(int searchResultTime) {
		this.searchResultTime = searchResultTime;
	}

	public int getInsertResultTime() {
		return insertResultTime;
	}

	public void setInsertResultTime(int insertResultTime) {
		this.insertResultTime = insertResultTime;
	}
	
}
