package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Message;
import models.User;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Classe responsável pelo acesso ao banco via HBASE.
 * 
 * @author Will Glük
 * @created 20/11/2014
 */
public class HbaseDataBasecontroller implements IDataBaseController {
	
	HBaseConfiguration hc;
	org.apache.hadoop.conf.Configuration config;
	HTable table;
	
	public HbaseDataBasecontroller() {
		 this.hc = new HBaseConfiguration(new Configuration());
		 this.config = HBaseConfiguration.create();
		 try {
			this.table = new HTable(config, "message");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertMessage(Message message) {		
		Put p = new Put(Bytes.toBytes(message.getUserTo().getCode()));
	}

	@Override
	public List<Message> fetchLastMessages(int amount, User user, User userFrom) {
		
		
		return new ArrayList<Message>();		
	}

	@Override
	public List<User> fetchUsers() {
		
		return new ArrayList<User>();
	}

}
