package database;

import java.util.List;

import models.Message;
import models.User;

/**
 * Interface que define os comandos de acesso ao banco de dados.
 * @author Will Glück
 * @created 20/11/2014
 *
 */
public interface IDataBaseController {
	
	/**
	 * Insere mensagem no banco de dados.
	 * @param message Mensagem a ser inserida.
	 */
	public void insertMessage(Message message);
	
	/** 
	 * @param amount Quantidade de mensagens desejada.
	 * @param user Usuário que irá recebê-las.
	 * @param userFrom Usuário que enviou
	 * @return Mensagens do usuário.
	 */
	public List<Message> fetchLastMessages(int amount, User user, User userFrom); 
	
	/**
	 * @return Todos usuários do banco de dados.
	 */
	public List<User> fetchUsers();
	
}
