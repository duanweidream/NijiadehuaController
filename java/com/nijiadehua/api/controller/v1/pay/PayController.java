package com.nijiadehua.api.controller.v1.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.http.HttpClient;
import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.pay.response.NotifyResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.service.PayService;
import com.nijiadehua.api.util.StringUtil;
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
	
	@Autowired
	PayService payService;
	
	@ResponseBody
	@RequestMapping(value="/unifiedorder",method=RequestMethod.POST)
	public Result unifiedorder(@RequestBody String json) throws ServiceException{
		
		if(StringUtil.isEmpty(json)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			//OrderCreateResponse orderCreateResponse = orderService.createOrder(json);
			
			return new Result(null);
		}catch (Exception e) {
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
		
		try{
			
			return payService.notify(xml);
			
		}catch (ServiceException e) {
			NotifyResponse notifyResponse = new NotifyResponse();
			notifyResponse.setReturn_code("FAIL");
			notifyResponse.setReturn_msg(e.getMessage());
			return notifyResponse;
		}
		
	}
	
	public static void main(String[] args) {
		
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><id>1</id><name>xxl</name><age>24</age></xml>");
		HttpClient.postXmlString("http://localhost:8080/v/1/pay/notify", xml.toString(), null);
		
	}
	
}
