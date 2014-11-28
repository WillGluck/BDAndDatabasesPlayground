package models;

/**
 * Classe do modelo de mensagem.
 * 
 * @author Will Glück
 * @created 20/11/2014
 *
 */
public class Message extends BaseModel {
	
	//Nome dos campos.
	
	public final static String CODE = "code";
	public final static String MESSAGE = "message";
	public final static String USER_TO = "userto";
	public final static String USER_FROM = "userfrom";
	
	//Atributos.

	private String message;
	private User userFrom;
	private User userTo;
	
	//Construtor
	
	public Message() {
		this.message = "";
		this.userFrom = new User();
		this.userTo = new User();
	}
		
	//Getters and setters;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
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
	
	

}
