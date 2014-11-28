package chat;

import java.util.List;

import models.Message;
import models.User;
import database.IDataBaseController;

/**
 * Classe intermedi�ria para controle das inser��es e busca de mensagens.
 * 
 * @author Will GL�ck
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
	 * @param user Usu�rio que ir� receber as mensagens.
	 * @param userFrom Usu�rio que enviou.
	 * @param amount Quantidade desejada.
	 * @return Lista de mensagens do usu�rio.
	 */
	public List<Message> getMessages(User user, User userFrom, int amount) {
		return this.dbController.fetchLastMessages(amount, user, userFrom);
	}
	
	/** 
	 * @return Usu�rios do banco.
	 */
	public List<User> getUsers() {
		return this.dbController.fetchUsers();
	}
	
}