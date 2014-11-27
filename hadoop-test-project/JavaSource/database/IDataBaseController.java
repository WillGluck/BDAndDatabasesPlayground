package database;

import java.util.List;

import models.Message;
import models.User;

/**
 * Interface que define os comandos de acesso ao banco de dados.
 * @author Will Glück
 * @created 27/11/2014
 *
 */
public interface IDataBaseController {
	
	public void insertMessage(Message message);
	
	public List<Message> fetchLastMessages(int amount, User user, User userFrom); 
	
}
