package com.hualouhui.weixin.action;
import org.apache.log4j.Logger;
import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.util.Parameters;
/**
 * ClassName:Publish</br> Function: 信息发布 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2016-9-14 上午10:09:34</br>
 * 
 */
public class ToPublish extends ActionBase {
	
	private static Logger log = Logger.getLogger(ToPublish.class);
	
	public ToPublish() {
		needCheckAuth = true;
	}

	@Override
	public void initParameters(Parameters params) throws ApiError {}

	@Override
	public Result invokeService() throws ApiError {
		Result result = new Result();
		
		log.debug("[ToPublish]user.getId:"+user.getId());
		
		result.forword("/pages/to.publish.html");
		return result;

	}
	


	
}
