package com.nijiadehua.api.controller.v1.pay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.http.HttpClient;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.pay.response.NotifyResponse;
import com.nijiadehua.api.controller.v1.pay.response.UnifiedorderResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.service.PayService;
import com.nijiadehua.api.util.StringUtil;

import net.sf.json.JSONObject;
/**
 * ClassName:PayController</br> Function: 支付相关 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2020-01-15 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value="/v/1/pay")
public class PayController{
	
	private static Logger log = Logger.getLogger("api");
	
	@Autowired
	PayService payService;
	
	@ResponseBody
	@RequestMapping(value="/unifiedorder",method=RequestMethod.POST)
	public Result unifiedorder(@RequestBody String json,HttpServletRequest request) throws ServiceException{
		log.logInfo("[/v1/pay/unifiedorder]request:"+json);
		if(StringUtil.isEmpty(json)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			JSONObject jsonObject = JSONObject.fromObject(json);
			Long user_id = jsonObject.getLong("user_id");
			String order_id = jsonObject.getString("order_id");
			
			String ip = StringUtil.isEmpty(request.getHeader("remote_client_ip")) ? request.getRemoteAddr() : request.getHeader("remote_client_ip");
			
			UnifiedorderResponse unifiedorderResponse = payService.unifiedorder(user_id, ip, order_id);
			
			log.logInfo("[/v1/pay/unifiedorder]response:"+JSONObject.fromObject(unifiedorderResponse).toString());
			return new Result(unifiedorderResponse);
			
		}catch (Exception e) {
			log.logInfo("[/v1/pay/unifiedorder]error:"+e.getMessage());
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	/**
     * 接收支付成功后，支付成功后通知
     * @Title: notify 
     * @Description: TODO     
     * @return void    
     * @date 2020年01月14日 下午4:41:15 
     * @author duanwei
     * @throws Exception 
     */
	@ResponseBody
	@RequestMapping(value="/notify",method=RequestMethod.POST,produces={"application/xml; charset=UTF-8"})
	public NotifyResponse notify(@RequestBody String xml) throws Exception{
		log.logInfo("[/v1/pay/notify]request:"+xml);
		try{
			
			NotifyResponse notifyResponse = payService.notify(xml);
			
			log.logInfo("[/v1/pay/notify]request:"+JSONObject.fromObject(notifyResponse).toString());
			return notifyResponse;
		}catch (ServiceException e) {
			log.logInfo("[/v1/pay/notify]error:"+e.getMessage());
			NotifyResponse notifyResponse = new NotifyResponse();
			notifyResponse.setReturn_code("FAIL");
			notifyResponse.setReturn_msg(e.getMessage());
			return notifyResponse;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/orderquery",method=RequestMethod.GET)
	public Result orderquery(Long user_id,String order_id) throws ServiceException{
		
		if(StringUtil.isEmpty(user_id,order_id)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
		
			int result = payService.queryOrderPayStatus(user_id, order_id);
			
			return new Result(result);
			
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
}
