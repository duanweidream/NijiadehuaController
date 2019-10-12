package com.wooboo.dsp.web.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.enums.Status;
import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.model.AuthInfo;
import com.wooboo.dsp.model.MenuInfo;
import com.wooboo.dsp.model.RoleInfo;
import com.wooboo.dsp.model.SysLogger;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.service.system.SystemService;
import com.wooboo.dsp.system.util.ApiError;
import com.wooboo.dsp.system.util.DateUtil;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.system.util.Page.ATTPage;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.system.util.Page.PagerFactory;
import com.wooboo.dsp.util.MD5Util;
import com.wooboo.dsp.util.StringUtil;
import com.wooboo.dsp.web.controller.report.ReportController;

@Controller
@RequestMapping(value="/system")
public class SystemController{
	private static Logger logger = Logger.getLogger(ReportController.class);
	
	@Autowired
	private SystemService systemService;
	
    /**
     * 系统设置->菜单管理
     * @param request
     * @return
     */
    @RequestMapping("/menu/list")  
    public ModelAndView menuList(HttpServletRequest request){  
    	ModelAndView model = new ModelAndView("/system/menu_list");
    	List<MenuInfo> list = systemService.queryRootMenu();
    	model.addObject("clist", list);
    	return model;
    }  
    
    /**
     * 系统设置->菜单管理->添加菜单
     * @param menuId 菜单id 如传则是修改菜单.
     * @return
     */
    @RequestMapping("/menu/to")  
    public ModelAndView menuto(Integer menuId,Integer pid){  
    	ModelAndView model = new ModelAndView("/system/menu_to");
    	if(!StringUtil.isEmpty(menuId)){
    		MenuInfo menu = systemService.getObject(menuId, MenuInfo.class);
    		model.addObject("menu", menu);
    	}else{
    		model.addObject("pid", pid);
    	}
    	return model;
    }
    /**
     * 系统设置->菜单管理->菜单添加|更新
     * @param menuId
     * @return
     */
    @ResponseBody
    @RequestMapping("/menu/su")  
    public Result menusu(@ModelAttribute MenuInfo menu,Integer menuId){  
    	Result result = new Result();
    	MenuInfo m = null;
    	if(!StringUtil.isEmpty(menu.getId())){
    		m = systemService.getObject(menu.getId(), MenuInfo.class);
    	}
    	if(null==m){
    		systemService.saveMenuInfo(menu);
    	}else{
    		systemService.updateMenuInfo(m, menu);
    	}
    	return result;
    }
    
    
    /**
     * 系统设置->权限设置->角色管理
     * @param id
     * @param pid
     * @return
     */
    @RequestMapping("/role/list")  
    public ModelAndView rolelist(@ModelAttribute RoleInfo role){  
    	ModelAndView model = new ModelAndView("/system/role_list");
    	Page page = PagerFactory.createPage();
    	systemService.searchRoleInfo(page,role);
    	model.addObject("role", role);
    	model.addObject(ATTPage.PAGE_LIST, page);
    	return model;
    }
    
    @ResponseBody
    @RequestMapping("/role/del")
    public Object del(Integer id) {
    	
    	Map<String, Integer> retVal = new HashMap<String, Integer>() ;
    	
    	try {
    		systemService.deleteById(id);
    		retVal.put("code", 0) ;
    	} catch(Exception e) {
    		e.printStackTrace();
    		retVal.put("code", 1) ;
    	}
    	
    	return retVal ;
    }
    
    @ResponseBody
    @RequestMapping("/role/isroleuser")
    public Object isroleuser(Integer id) {
    	
    	Map<String, Integer> retVal = new HashMap<String, Integer>() ;
    	
    	try {
    		// 判断此角色是否有用户在使用
    		List<SysUser> users = systemService.getUsersByRoleId(id) ;
    		if(users != null && users.size() > 0) {
    			retVal.put("code", 0) ;
    		} else {
    			retVal.put("code", 1) ;
    		}
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    		retVal.put("code", 1) ;
    	}
    	
    	return retVal ;
    }
    
    /**
     * 系统设置->菜单设置
     * @param auth
     * @param id
     * @return
     */
    @RequestMapping("/menu/set")  
    public ModelAndView menuset(@ModelAttribute AuthInfo auth,Integer id){  
    	ModelAndView model = new ModelAndView("/system/menu_set");
    	List<RoleInfo> list = systemService.queryRoleInfo();
		List<MenuInfo> menuList = systemService.queryRootMenu();
		model.addObject("roleList", list);
		model.addObject("clist", menuList);
    	return model;
    }
    /**
     * 系统设置->菜单设置->菜单查询
     * @param roleId
     */
    @ResponseBody
    @RequestMapping("/menu/search")
    public Result loadMenuByRoleId(Integer roleId){
    	JSONObject o = systemService.queryMenuInfoByRoleId4json(null,roleId);
    	Result result = new Result();
    	result.setData(o);
    	return result;
    }
    /**
     * 系统设置->菜单设置->菜单角色设置
     * @param ids
     * @param roleId
     * @return
     */
    @RequestMapping("/menu/roleset")
    public String roleset(Integer[] ids,Integer roleId){
		if (!StringUtil.isEmpty(roleId) && null != ids) {
			systemService.saveForSetMenuByRole(roleId, ids);
		}
    	return "redirect:/system/menu/set";
    }
    /**
     * 系统设置->权限设置->角色权限
     * @param auth
     * @param id
     * @return
     */
    @RequestMapping("/auth/set")  
    public ModelAndView authset(@ModelAttribute AuthInfo auth,Integer id){  
    	ModelAndView model = new ModelAndView("/system/auth_set");
    	List<RoleInfo> list = systemService.queryRoleInfo();
		List<AuthInfo> authList = systemService.queryRootAuth();
		model.addObject("authList", list);
		model.addObject("clist", authList);
    	return model;
    }
    /**
     * 系统设置->权限设置->角色权限设置
     * @param ids
     * @param roleId
     * @return
     */
    @RequestMapping("/auth/roleset")
    public String authroleset(Integer[] ids,Integer roleId){
    	
    	systemService.saveForSetAuthByRole(roleId, ids);
    	return "redirect:/system/auth/set";
    }
    
    /**
     * 系统设置->权限设置->权限查询
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/auth/search")
    public Result loadAuthByRoleId(Integer roleId){
    	JSONObject o = systemService.queryAuthInfoByRoleId4json(roleId);
    	Result result = new Result();
    	result.setData(o);
    	return result;
    }
    
    
    /**
     * 系统设置->权限设置->权限查询
     * @param roleId
     * @return
     */
    @RequestMapping("/user/search")
    public ModelAndView userSearch(@ModelAttribute SysUser user){
    	ModelAndView model = new ModelAndView("/system/user_search");
    	Page page = PagerFactory.createPage();
    	// logger.info("user search, user: " + user.getRoleInfo().getRoleName());
    	systemService.searchSysUserInfo(page,user,user.getRoleInfo() == null ? null : user.getRoleInfo().getRoleId());
    	model.addObject(ATTPage.PAGE_LIST, page);
    	model.addObject("user", user);
    	return model;
    }
    /**
     * 系统设置->系统用户->添加或者编辑系统用户
     * @param roleId
     * @return
     */
    @RequestMapping("/user/to")
    public ModelAndView userto(Long userId){
    	ModelAndView model = new ModelAndView("/system/user_su");
    	SysUser user = systemService.getObject(userId, SysUser.class);
    	List<RoleInfo> list = systemService.queryRoleInfo();
    	model.addObject("list", list);
    	model.addObject("user",user);
    	return model;
    }
    /**
     * 系统用户根据用户名查找用户是否存在
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/exist")
    public Object exist(String username) {
    	
    	Map<String, String> retVal = new HashMap<String, String>() ;
    	
    	SysUser findSysUserByName = systemService.findSysUserByName(username) ;
    	if(null == findSysUserByName) {
    		retVal.put("isUser", "1") ;	// 表示用户不存在
    	} else {
    		retVal.put("isUser", "0") ;	// 表示用户存在
    	}
    	
    	return retVal ;
    }

	/**
	 * 系统设置--系统用户，查询全部角色
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user/roleList")
	public JSONArray roleList() {
		return systemService.loadRoleName();
	}

    /**
     * 系统设置->系统用户->添加|编辑系统用户
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/su")
    public Result saveOrUpdateSysUser(@ModelAttribute SysUser user,Integer roleId ){
    	if(null!=user){
    	 	SysUser u = null;
    	    if(!StringUtil.isEmpty(user.getId())){
    	    	u = systemService.getObject(user.getId(), SysUser.class);
    	    }
    	    if(null!=u){
    	    	//update user;
    	    	RoleInfo role = systemService.getObject(roleId, RoleInfo.class);
    	    	u.setNickName(user.getNickName());
    	    	u.setRoleInfo(role);
    	    	u.setState(user.getState());
    	    	u.setUserPhone(user.getUserPhone());
    	    	u.setEmail(user.getEmail());
    	    	systemService.updateObject(u);
    	    }else{
    	    	u = systemService.findSysUserByName(user.getUserName());
    	    	if(null!=u){
    	    		return new Result(ApiError.Type.LOGIC_ERROR.toException("用户名["+user.getUserName()+"]已经存在!"));
    	    	}else{
    	    		u = new SysUser();
    	    		RoleInfo role = systemService.getObject(roleId, RoleInfo.class);
    	    		u.setRoleInfo(role);
    	    		u.setPassword(new MD5Util().getMD5ofStr("123456"));
    	    		u.setLevel(3); //默认级别3 预留字段
    	    		u.setUserName(user.getUserName());
    	    		u.setCreateDate(new Date());
    	    		u.setLoginTime(0);
    	    		u.setState(user.getState());
    	    		u.setNickName(user.getNickName());
    	    		u.setUserPhone(user.getUserPhone());
    	    		u.setEmail(user.getEmail());
    	    		u.setLoginFailTime(0);
    	    		systemService.saveObject(u);
    	    	}
    	    }
    	}
    	
    	Result result = new Result();
    	return result;
    }
    /**
     * 
     * @param id
     * @param pid
     * @return
     */
    @RequestMapping("/role/to")  
    public ModelAndView roleto(Integer roleId){  
    	ModelAndView model = new ModelAndView("/system/role_to");
    	RoleInfo role=null;
		if(!StringUtil.isEmpty(roleId)){
			role = systemService.getObject(roleId, RoleInfo.class);
		}
		model.addObject("role", role);
    	return model;
    }
    /**
     * 系统设置->角色列表->添加编辑角色信息
     * @param user
     * @param roleId
     */
    @ResponseBody
    @RequestMapping("/role/su")  
    public Result rolesu(@ModelAttribute RoleInfo role,Integer roleId){
    	if(null!=role){
    		RoleInfo r = null;
    		if(!StringUtil.isEmpty(role.getRoleId())){
    			r = systemService.getObject(role.getRoleId(), RoleInfo.class);
    		}
    		if(null==r){
    		
    			systemService.saveObject(role);
    		}else{
    			systemService.updateRoleInfo(r, role);
    		}
    	}
    	return new Result();
    }
    /**
     * 系统设置->日志查询.
     * @return
     */
    @RequestMapping("/log/search")  
    public ModelAndView logSearch(){
    	ModelAndView model = new ModelAndView("/system/log_search");
    	return model;
    }
    @ResponseBody
    @RequestMapping("/log/search/data")  
    public Result logSearchData(String operateName,String beginDate,String endDate){
    	Page page = PagerFactory.createPage();
    	//DateUtil.formatDate(dateStr, formatChar, dateFormat)
    	systemService.searchSysLogger(page, operateName, DateUtil.getDate(beginDate,"yyyy-MM-dd"), DateUtil.getDate(endDate,"yyyy-MM-dd"));
    	Result result = new Result();
    	JSONObject data = new JSONObject();
		JSONArray array = new JSONArray();
		for(SysLogger log:(List<SysLogger>)page.getList()){
			JSONArray son = new JSONArray();
			son.add(DateUtil.getFormatDate(log.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			son.add(log.getOperateName());
			son.add(log.getUserName());
			son.add(StringUtil.dealNull(log.getIpStr(), "127.0.0.1"));
			son.add(log.getOperateDesc());
			array.add(son);
		}
		data.put("page_list", array);
		data.put("footer", page.toPageFooter());
		result.setData(data);
    	return result;
    }
    
    @RequestMapping(value="/user/password/to")
    public ModelAndView password(Long userId){
    	ModelAndView model = new ModelAndView("/system/user_password");
    	SysUser user = systemService.getObject(userId, SysUser.class);
    	    	model.addObject("user", user);
    	return model;
    }
    
    /**
     * 个人设置->修改密码->修改
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/user/password/update")
    public Result passwwordUpdate(Long userId,String password,String npassword,String rnpassword){
    	
    	
    	if(StringUtil.isEmpty(password,npassword,rnpassword)){
    		return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误"));
    	}
    	if(!StringUtil.equals(npassword, rnpassword)){
    		return new Result(ApiError.Type.INVALID_PARAM.toException("两次输入的密码不一致!"));
    	}
    	
    	//判断操作者用户密码
    	SysUser su =  systemService.findSysUserByName(SessionHelper.getSysUser().getUserName(), new MD5Util().getMD5ofStr(password));
    	if(null==su){
    		return new Result(ApiError.Type.INVALID_PARAM.toException("操作密码错误!"));
    	}
    	
    	SysUser u = systemService.getObject(userId, SysUser.class);
    	if(null==u){
    		return new Result(ApiError.Type.INVALID_PARAM.toException("用户不存在！"));
    	}
        if(u!=null){
        	u.setPassword(new MD5Util().getMD5ofStr(npassword));
        	systemService.updateObject(u);
        	
        	SysLogger logger = new SysLogger();
    		logger.setBusinessCode(Status.BusinessCode.sysuser.toString());
    		logger.setOperateName(Status.BusinessCode.sysuser.toName());
    		logger.setOperateDesc("用户["+u.getUserName()+"]进行了密码修改. ");
    		logger.setBusinessId(u.getId());
    		//保存日志
    		//SessionHelper.saveLogger(logger);
        }
        
        return new Result();
    }

}
