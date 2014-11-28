package models;

/**
 * Classe do Modelo de usuário.
 * 
 * @author Will Glück
 * @created 20/11/2014
 *
 */
public class User extends BaseModel {
	
	//Nome dos campos.
	
	public final static String CODE = "code";
	public  final static String NAME = "name";
	
	//Atributos
	private String name;
	
	//Construtor
	public User() {
		this.name = "";
	}
	
	//Getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
