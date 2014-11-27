package chat;

import java.util.List;

import models.Message;
import models.User;
import database.IDataBaseController;

/**
 * Classe intermediária para controle das inserções e busca de mensagens.
 * 
 * @author Will GLück
 * @created 27/11/2014
 *
 */
public class ChatController {

	private IDataBaseController dbController;
	
	public ChatController(IDataBaseController dbController) {
		this.dbController = dbController;
	}

	public void sendMessage(Message message) {
		this.dbController.insertMessage(message);
	}
	
	public List<Message> getMessages(User user, User userFrom, int amount) {
		return this.dbController.fetchLastMessages(amount, user, userFrom);
	}
	
}