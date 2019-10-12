package com.wooboo.dsp.system.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.system.util.FrameCache;



public class DefaultListener implements ServletContextListener {
	private static final Logger logger = Logger.getLogger(DefaultListener.class);
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		ATTSystem.WEB_ROOT=sce.getServletContext().getRealPath("");
		systemContext();
		initLog();
	}
	public void systemContext(){
        FrameCache.getInstance().initCache();
        //System.out.println(ConstantUtil.getInstance().findNode("001"));
        
	}
	public static void initLog(){
		PropertyConfigurator.configure(Thread.currentThread().getContextClassLoader().getResource("config/log4j.properties")); 
	}

}
