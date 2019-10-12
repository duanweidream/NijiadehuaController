package com.wooboo.dsp.web.controller.user;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.base.enums.Status;
import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.model.SysLogger;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.service.report.ReportService;
import com.wooboo.dsp.service.system.SystemService;
import com.wooboo.dsp.system.util.ApiError;
import com.wooboo.dsp.system.util.DateUtil;
import com.wooboo.dsp.system.util.PictureUtil;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.util.MD5Util;
import com.wooboo.dsp.util.StringUtil;

@Controller
@RequestMapping(value="/user")
public class UserController{
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private ReportService reportService;

    
	
	@RequestMapping(value="/index",method = {RequestMethod.GET,RequestMethod.POST})
	public String toIndex(){
		return "redirect:/user/dashboard";
	}
	
	
	
    @RequestMapping(value="/dashboard",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView dashboard(){  
    	ModelAndView model = new ModelAndView("/user/dashboard");
		Date beginDate = DateUtil.getFistDayOfMonth();
    	//Object[] total = reportService.queryReportUserDateTotal(beginDate, new Date(),null);
    	model.addObject("total", null);
    	    	
    	model.addObject("companyNotHandledItem", 0);
    	model.addObject("stuffNotHandledItem", 0);
    	model.addObject("chargeNotHandledItem", 0);
    	model.addObject("noticeList", null);
    	return model;
    }  
    
    /**
     * 个人设置->修改密码->进入修改密码
     * @return
     */
    @RequestMapping(value="/password/to")
    public String password(){
    	return "/user/password";
    }  
    
    /**
     * 个人设置->修改密码->修改
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/password/update")
    public Result passwwordUpdate(String npassword,String rnpassword){
    	
    	SysUser user = SessionHelper.getSysUser();
    	if(StringUtil.isEmpty(npassword,rnpassword)){
    		return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误"));
    	}
    	if(!StringUtil.equals(npassword, rnpassword)){
    		return new Result(ApiError.Type.INVALID_PARAM.toException("两次输入的密码不一致!"));
    	}
    	
        if(user!=null){
        	SysUser u = systemService.findSysUserByName(user.getUserName());
        	if(null==u){
        		return new Result(ApiError.Type.INVALID_PARAM.toException("原密码错误!"));
        	}
        	u.setPassword(new MD5Util().getMD5ofStr(npassword));
        	systemService.updateObject(u);
        	
        	SessionHelper.set(ATTSystem.SESSION_USER, u);//设置session
        	
        	SysLogger logger = new SysLogger();
    		logger.setBusinessCode(Status.BusinessCode.sysuser.toString());
    		logger.setOperateName(Status.BusinessCode.sysuser.toName());
    		logger.setOperateDesc("用户["+user.getUserName()+"]进行了密码修改. ");
    		logger.setBusinessId(user.getId());
    		//保存日志
    		SessionHelper.saveLogger(logger);
        }
    	
    	
    	
    	return new Result();
    } 
   
    @RequestMapping(value = "/logo")  
    public void getImage(HttpServletRequest request,  HttpServletResponse response) throws IOException {  
        // 禁止图像缓存。  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
        // 将图像输出到Servlet输出流中。  
        ServletOutputStream sos = response.getOutputStream();  
        ImageIO.write(PictureUtil.createImg(), "jpeg", sos);  
        sos.close();  
    }  

}
