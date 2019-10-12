package com.wooboo.dsp.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.base.enums.Status;
import com.wooboo.dsp.dao.system.SystemDao;
import com.wooboo.dsp.model.AuthInfo;
import com.wooboo.dsp.model.MenuInfo;
import com.wooboo.dsp.model.RoleAuth;
import com.wooboo.dsp.model.RoleInfo;
import com.wooboo.dsp.model.RoleMenu;
import com.wooboo.dsp.model.SysCityInfo;
import com.wooboo.dsp.model.SysDepartMent;
import com.wooboo.dsp.model.SysLogger;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.service.base.BaseService;
import com.wooboo.dsp.system.util.FrameCache;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.system.util.Page.Page;
import com.wooboo.dsp.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class SystemService extends BaseService {

	@Autowired
	private SystemDao systemDao;

	public List<SysCityInfo> queryCityInfoBycode(String code){
		return systemDao.queryCityInfoByCode(code);
	}

	public List<SysCityInfo> queryCityInfoBycode2(String code){
		return systemDao.queryCityInfoByCode2(code);
	}


	public List<SysUser> getListUser(){
		return systemDao.getListUser();
	}
	

	/**
	 * 根据id逻辑删除
	 * @param id
	 * @author wangff
	 */
	public void deleteById(Integer id) {
		systemDao.deleteRoleById(id) ;
	}

	/**
	 * 根据角色id查找用户
	 * @param id
	 * @return
	 */
	public List<SysUser> getUsersByRoleId(Integer id) {
		return systemDao.getUsersByroleId(id) ;
	}

	/**
	 * 根据Id查找部门信息
	 * @param id
	 * @return
	 * @author wangff
	 */
	public SysDepartMent queryDepartMentById(Integer id) {
		return (SysDepartMent) systemDao.getObject(SysDepartMent.class, id) ;
	}

	public void searchSysUserInfo(Page page,SysUser user, Integer roleId){
		systemDao.searchSysuser(page, user, roleId);
	}


	public void updateUserForLogin(SysUser user){
		updateUserLogin(user);//更新登录用户
		user.setUserTheme(StringUtil.getHoldes4user(user, "29x29", "xing", "10"));
		SessionHelper.set(ATTSystem.SESSION_USER, user);//设置session
		//记录日志
		//...

		SysLogger logger = new SysLogger();
		logger.setBusinessCode(Status.BusinessCode.sysuser.toString());
		logger.setOperateName(Status.BusinessCode.sysuser.toName());
		logger.setOperateDesc("用户["+user.getUserName()+"] 登录运营支撑系统. ");
		logger.setBusinessId(user.getId());
		//保存日志
		SessionHelper.saveLogger(logger);
    }
	private void updateUserLogin(SysUser user){
		// 记录次数修改
		Integer times =  user.getLoginTime() == null ? 0 : user.getLoginTime();
		times++;
		user.setLoginTime(times);
		// 记录登录时间
		user.setLastDate(new Date());
		// 记录登录ip
		//String ip = HttpHelp.getIp();
		//HttpHelp.addCookie(null, ATTSystem.DAYS_TIMES, "/", ATTSystem.COOKIE_NAME, user.getUserName());//设置cookie
		systemDao.updateObject(user);
	}

	public SysUser findSysUserByName(String username,String password){
		return systemDao.findSysUserByName(username,password);
	}
	public SysUser findSysUserByName(String username){
		return systemDao.findSysUserByName(username);
	}

	public JSONArray loadRoleName() {
		String sql = "SELECT sri.role_id role_id, sri.role_name role_name FROM sys_role_info sri WHERE !ISNULL(sri.role_name) AND sri.role_name <> '' ORDER BY sri.role_name";
		List<Map<String, Object>> list = systemDao.getJdbcTemplate().queryForList(sql);

		JSONArray json = new JSONArray();
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				Map<String, String> maps = new HashMap<String, String>();
				maps.put("roleId", map.get("role_id").toString());
				maps.put("roleName", map.get("role_name").toString());

				json.add(maps);
			}
		}
		return json;
	}

	public void updateRoleInfo(RoleInfo role,RoleInfo newRole){
		role.setRoleName(newRole.getRoleName());
		role.setRoleDesc(newRole.getRoleDesc());
		role.setRoleLevel(newRole.getRoleLevel());
		//role.setPartId(newRole.getPartId());
		systemDao.updateObject(role);
	}
	public void updateAuthInfo(AuthInfo auth,AuthInfo newAuth){
		auth.setAuthName(newAuth.getAuthName());
		auth.setAuthCode(newAuth.getAuthCode());
		auth.setAuthRegexp(newAuth.getAuthRegexp());
		//auth.setModel(ATTSystem.LOGIC_TRUE.equals(newAuth.getModel())?ATTSystem.LOGIC_TRUE:ATTSystem.LOGIC_FALSE);
		systemDao.updateObject(auth);
	}
	public void saveAuthInfo(AuthInfo auth){
		auth.setModel(ATTSystem.LOGIC_TRUE);
		//auth.setModel(ATTSystem.LOGIC_TRUE.equals(auth.getModel())?ATTSystem.LOGIC_TRUE:ATTSystem.LOGIC_FALSE);
		systemDao.saveObject(auth);
	}
	public void saveMenuInfo(MenuInfo m){
		MenuInfo menu = new MenuInfo();
		menu.setMenuName(m.getMenuName());
		menu.setMenuHref(m.getMenuHref());
		menu.setMenuDesc(m.getMenuDesc());
		menu.setMenuSeq(m.getMenuSeq());
		menu.setPId(m.getPId());
		menu.setVisitility(ATTSystem.LOGIC_TRUE.equals(m.getVisitility())?ATTSystem.LOGIC_TRUE:ATTSystem.LOGIC_FALSE);
		menu.setIcoClass(m.getIcoClass());
		menu.setAuthCode(m.getAuthCode());
		systemDao.saveObject(menu);
	}
	public void updateMenuInfo(MenuInfo menu,MenuInfo newM){
		menu.setMenuName(newM.getMenuName());
		menu.setMenuHref(newM.getMenuHref());
		menu.setMenuDesc(newM.getMenuDesc());
		menu.setMenuSeq(newM.getMenuSeq());
		menu.setVisitility(ATTSystem.LOGIC_TRUE.equals(newM.getVisitility())?ATTSystem.LOGIC_TRUE:ATTSystem.LOGIC_FALSE);
		menu.setIcoClass(newM.getIcoClass());
		menu.setAuthCode(newM.getAuthCode());
		systemDao.updateObject(menu);
	}
	public List<MenuInfo> queryRootMenu(){
		return systemDao.queryRootMenu();
	}
	public List<AuthInfo> queryRootAuth(){
		return systemDao.queryRootAuth();
	}
	public List<RoleInfo> queryRoleInfo(){
		return systemDao.queryRoleInfo();
	}
	public void searchRoleInfo(Page page,RoleInfo role){
		systemDao.searchRoleInfo(page,role);
	}


	public List<MenuInfo> queryMenuInfoByRoleId(String visitility,Integer roleId){
		return systemDao.queryMenuInfoByRoleId(visitility,roleId);
	}
	public JSONObject queryMenuInfoByRoleId4json(String visitility,Integer roleId){
		List<MenuInfo> list = systemDao.queryMenuInfoByRoleId(visitility,roleId);
			StringBuffer menuReg= new StringBuffer("");
			boolean b = true;
			for(MenuInfo menu:list){
				if(b){
					menuReg.append(menu.getId());
					b=false;
				}else{
					menuReg.append("|"+menu.getId());
				}
			}
			JSONObject obj = new JSONObject();
			obj.element("menuReg", menuReg.toString());
			return obj;
	}

	public JSONObject queryAuthInfoByRoleId4json(Integer roleId){
		List<AuthInfo> list = systemDao.queryAuthInfoByRoleId(roleId);
		StringBuffer menuReg= new StringBuffer("");
		boolean b = true;
		for(AuthInfo au:list){
			if(b){
				menuReg.append(au.getId());
				b=false;
			}else{
				menuReg.append("|"+au.getId());
			}
		}
		JSONObject obj = new JSONObject();
		obj.element("menuReg", menuReg.toString());
		return obj;

	}
	public List<AuthInfo> queryAuthInfoByRoleId(Integer roleId){
		return systemDao.queryAuthInfoByRoleId(roleId);
	}
	
	public void saveForSetMenuByRole(Integer roleId,Integer[] ids){
		systemDao.deleteRoleMenuByRoleId(roleId);
	    saveRoleMenuByMenuIds(ids, roleId);
	    FrameCache.cache=false;
	    FrameCache.clear();
	}

	public void saveRoleMenuByMenuIds(Integer[] ids,Integer roleId){
		List<RoleMenu> list = new ArrayList<RoleMenu>();
		for(Integer id:ids){
			RoleMenu rm = new RoleMenu();
			rm.setMenuId(id);
			rm.setRoleId(roleId);
			list.add(rm);
		}
		savebatchObject(list);
	}
	
	public void saveForSetAuthByRole(Integer roleId,Integer[] ids){
	    systemDao.deleteRoleAuthByRoleId(roleId);
	    saveRoleAuthByMenuIds(ids, roleId);
	    FrameCache.cache=false;
	    FrameCache.clear();
	}
	public void saveRoleAuthByMenuIds(Integer[] ids,Integer roleId){
		if(null!=ids){
			List<RoleAuth> list = new ArrayList<RoleAuth>();
			for(Integer id:ids){
				RoleAuth rm = new RoleAuth();
				rm.setAuthId(id);
				rm.setRoleId(roleId);
				list.add(rm);
			}
			savebatchObject(list);
		}
	}

	public List<SysDepartMent> queryRootDepartMent(){
	   return	systemDao.queryRootDepartMent();
	}

	public List<SysDepartMent> queryChildDepartMentByParentId(Integer parentId){
		return systemDao.queryChildDepartMentByParentId(parentId);
	}


	public List<SysLogger> querySysLogger(Long businessId,String businessCode,Integer size){
		return systemDao.querySysLogger(businessId, businessCode, size);
	}
	public void searchSysLogger(Page page,String operateName,Date beginDate,Date endDate){
		systemDao.searchSysLogger(page, operateName, beginDate, endDate);
	}

	/**
	 * 角色广告主审核，待办事项
	 */
	public int getNotHandledItem(){
		return systemDao.getNotHandledItem();
	}

	public List<SysUser> querySysUserByRoleId(Integer roleId){
		return systemDao.querySysUserByRoleId(roleId);
	}

	
	public List<SysUser> querySuperSysUser(){
		return systemDao.querySuperSysUser();
	}

	public List<SysUser> querySysUserByEmail(String email){
		return systemDao.querySysUserByEmail(email);
	}

}
