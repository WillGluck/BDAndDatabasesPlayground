package database;

import java.util.List;

import models.Message;
import models.User;

/**
 * Classe respons�vel pelo acesso ao banco via HBASE.
 * 
 * @author Will Gl�k
 * @created 27/11/2014
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

}
