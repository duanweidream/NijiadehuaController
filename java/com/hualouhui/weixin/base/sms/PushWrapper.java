package com.hualouhui.weixin.base.sms;

import java.util.LinkedList;

import com.hualouhui.weixin.base.db.JdbcTemplate;
import com.hualouhui.weixin.base.db.Sql;


public class PushWrapper {
	
	
    public static void push(JdbcTemplate jdbcTemplate, String accountId, String cid, String title, String msgContent, String transContent,String type) throws Exception {
    	//保存信息
    	saveIntoDb(jdbcTemplate, accountId,cid, title, msgContent, transContent,type);
    	
    	//发送信息
    	push2Phone(accountId, cid, title, msgContent, transContent, type);
    }
    
    private static void saveIntoDb(JdbcTemplate jdbcTemplate, String accountId,String cid, String title, String msgContent, String transContent,String type) throws Exception {
		Sql sql=new Sql(" insert into message_history(clientid , title, msg_content, " +
				        "trans_content,type,account_id) " +
				        "VALUES ('"+cid+"','"+title+"','"+msgContent+"','"+transContent+"','"+type+"','"+accountId+"')");
		
		jdbcTemplate.saveObject(sql);
		
    }
    
    private static void push2Phone(String accountId, String cid, String title, String msgContent, String transContent,String type) throws Exception {
    	pusher.schedulePush(accountId, cid, title, msgContent, transContent,type);
    }
    
    private static class AsyncPusher implements Runnable{
    	
    	public void schedulePush(final String accountId, final String cid, final String title, final String msgContent, final String transContent,final String type) {
    		synchronized(this) {
    			jobList.add(new Runnable() {
    				public void run() {
    			        try {
							PushHelper.push(cid, title, msgContent, transContent,type);
						} catch (Exception e) {
							//logcat.debug(e);
						}
    				}
    			});
    			if (jobThread == null) {
    				jobThread = new Thread(this);
    				jobThread.start();
    			}
    		}
    	}
    	
    	private void goNext() {
    		synchronized(this) {
    			if (jobList.size() > 0) {
    				job = jobList.removeFirst(); 
    			}else{
    				jobThread = null;
    				job = null;
    			}
    		}
    	}

		public void run() {
			for (goNext(); job != null; goNext()) {
				job.run();
			}
		}
    	
    	private LinkedList<Runnable> jobList = new LinkedList<Runnable>(); 
    	private Thread jobThread = null;
    	private Runnable job = null;
    }
    
    private static AsyncPusher pusher = new AsyncPusher();
}
