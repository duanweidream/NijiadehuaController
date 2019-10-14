package com.hualouhui.weixin.base.sms;

import java.util.LinkedList;

import com.hualouhui.weixin.util.SmsHelper;

public class SmsWrapper {


    public static void send(String phone, String msgContent) throws Exception {
    	if (phone != null) {
    		send2Phone(phone, msgContent);
    	}
    }
    
    private static void send2Phone(String phone, String msgContent) throws Exception {
    	sender.scheduleSend(phone, msgContent);
    }
    
    private static class AsyncSender implements Runnable{
    	
    	public void scheduleSend(final String phone, final String message) {
    		synchronized(this) {
    			jobList.add(new Runnable() {
    				public void run() {
    			        try {
    			        	SmsHelper.sendMessageTo(phone, message);
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
    
    private static AsyncSender sender = new AsyncSender();

}
