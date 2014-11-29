package database;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import models.Message;
import models.User;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Classe responsável pelo acesso ao banco via HBASE.
 * 
 * @author Will Glük
 * @created 20/11/2014
 */
public class HbaseDataBasecontroller implements IDataBaseController {
	
	private HBaseConfiguration hc;
	private org.apache.hadoop.conf.Configuration config;
	private HTable messageTable;
	private HTable userTable;
	
	@SuppressWarnings("deprecation")
	public HbaseDataBasecontroller() {
		 this.hc = new HBaseConfiguration(new Configuration());
		 this.config = HBaseConfiguration.create();
		 try {
			this.messageTable = new HTable(config, "message");
			this.userTable = new HTable(config, "chatuser");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertMessage(Message message) {		
		Put p = new Put(Bytes.toBytes(message.getUserTo().getCode() + message.getUserFrom().getCode()));
		p.add(Bytes.toBytes("text"), Bytes.toBytes(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS)),Bytes.toBytes(message.getMessage()));
		try {
			this.messageTable.put(p);
		} catch (RetriesExhaustedWithDetailsException e) {
			e.printStackTrace();
		} catch (InterruptedIOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Message> fetchLastMessages(int amount, User user, User userFrom) {
		
		List<Message> messages = new ArrayList<Message>();
		
		try {
			Scan s = new Scan();
			Get g = new Get(Bytes.toBytes(user.getCode() + userFrom.getCode()));
			g.setMaxVersions(amount);
			
			Result result = this.messageTable.get(g);
			CellScanner cellScanner = result.cellScanner();			
			
			while (cellScanner.advance()) {
				Message message = new Message();
				String text = new String(cellScanner.current().getValue());
				message.setUserFrom(userFrom);
				message.setUserTo(user);
				message.setMessage(text);				
				messages.add(message);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return messages;		
	}

	@Override
	public List<User> fetchUsers() {
		
		List<User> users = new ArrayList<User>();
		
		try {
			Scan s = new Scan();
			ResultScanner scanner = this.userTable.getScanner(s);			
			
			for (org.apache.hadoop.hbase.client.Result rr = scanner.next(); rr != null; rr = scanner.next()) {
				User user = new User();
				String name = new String(rr.getValue(Bytes.toBytes("name"), Bytes.toBytes("")));
				int code =  Integer.valueOf(new String((rr.getValue(Bytes.toBytes("code"), Bytes.toBytes("")))));
				user.setCode(code);
				user.setName(name);
				users.add(user);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return users;
	}

}
