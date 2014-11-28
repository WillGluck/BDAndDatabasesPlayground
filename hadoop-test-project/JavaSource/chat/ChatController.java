package chat;

import java.util.List;

import models.Message;
import models.User;
import database.IDataBaseController;

/**
 * Classe intermediária para controle das inserções e busca de mensagens.
 * 
 * @author Will GLück
 * @created 20/11/2014
 *
 */
public class ChatController {

	private IDataBaseController dbController;
	
	/**
	 * Construtor.
	 * @param dbController controller de banco de dados.
	 */
	public ChatController(IDataBaseController dbController) {
		this.dbController = dbController;
	}

	/**
	 * Envia uma mensagem.
	 * @param message mensagem para ser enviada.
	 */
	public void sendMessage(Message message) {
		this.dbController.insertMessage(message);
	}
	
	/**
	 * Recupera mensagens.
	 * @param user Usuário que irá receber as mensagens.
	 * @param userFrom Usuário que enviou.
	 * @param amount Quantidade desejada.
	 * @return Lista de mensagens do usuário.
	 */
	public List<Message> getMessages(User user, User userFrom, int amount) {
		return this.dbController.fetchLastMessages(amount, user, userFrom);
	}
	
	/** 
	 * @return Usuários do banco.
	 */
	public List<User> getUsers() {
		return this.dbController.fetchUsers();
	}
	
}