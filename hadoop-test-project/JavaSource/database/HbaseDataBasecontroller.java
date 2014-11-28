package database;

import java.util.List;

import models.Message;
import models.User;

/**
 * Classe responsável pelo acesso ao banco via HBASE.
 * 
 * @author Will Glük
 * @created 20/11/2014
 */
public class HbaseDataBasecontroller implements IDataBaseController {

	@Override
	public void insertMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Message> fetchLastMessages(int amount, User user, User userFrom) {
		return null;		
	}

	@Override
	public List<User> fetchUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
