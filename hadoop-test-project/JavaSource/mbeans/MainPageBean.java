package mbeans;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import models.Message;
import models.User;
import database.HbaseDataBasecontroller;
import database.IDataBaseController;
import database.PostgreSQLDataBaseController;


@ManagedBean(name="mainPage")
@RequestScoped
public class MainPageBean {
	
	private IDataBaseController dbController;
	
	private String dbSelected;
	
	private int amountOfMessages;
	private Message message;
	private User userFrom;
	private User userTo;
		
	private int searchResultTime;
	private int insertResultTime;

	public MainPageBean() {
		
	}
	
	public void changeToPostgreSQL() {
		this.dbController = new PostgreSQLDataBaseController();
	}
	
	public void changeToHBASE() {
		this.dbController = new HbaseDataBasecontroller();
	}
	
	public void sendMessage() {
		
	}
	
	public void readMessage() {
			
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
