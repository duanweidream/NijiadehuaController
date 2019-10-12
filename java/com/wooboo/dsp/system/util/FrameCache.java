package com.wooboo.dsp.system.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.model.AuthInfo;
import com.wooboo.dsp.model.MenuInfo;
import com.wooboo.dsp.model.RoleInfo;
import com.wooboo.dsp.service.system.SystemService;
import com.wooboo.dsp.util.SpringUtil;
import com.wooboo.dsp.util.StringUtil;

import edu.emory.mathcs.backport.java.util.Arrays;


public class FrameCache {
	private static Logger logger = Logger.getLogger(FrameCache.class);
	private static FrameCache frameCache = null;
	private static Map<String, List<MenuInfo>> menuCache = new HashMap<String, List<MenuInfo>>();//角色菜单
	private static Map<String, List<AuthInfo>> authCache = new HashMap<String, List<AuthInfo>>();//角色菜单
	private static Map<String, List<String>> menuAuth = new HashMap<String, List<String>>();
	
	
    public static boolean cache=false;
    public static SystemService systemService;
    
    public synchronized static FrameCache getInstance() {
    	if(frameCache==null){
    		frameCache = new FrameCache();
    		systemService = SpringUtil.getBean("systemService", SystemService.class);
    	}
    	return frameCache;
    }
    
    public void initCache(){
    	if(!cache){
    		cacheMenuCache();
    		cacheAuthCache();
    		cache=true;
    	}
    
    }
    
    public static void clear(){
    	menuCache.clear();
    	menuAuth.clear();
    	authCache.clear();
    }
    
    //缓存角色菜单
    public static void cacheMenuCache(){
    	List<RoleInfo> list = systemService.queryRoleInfo();
    	List<MenuInfo> ml = systemService.queryRootMenu();
    	for(RoleInfo role:list){
    		logger.logInfo("  cache menu   roleId="+role.getRoleId());
    		List<MenuInfo> l = systemService.queryMenuInfoByRoleId(null,role.getRoleId());
    		String regexp = createRegexp(l);    		
    		List<MenuInfo>  roleMenuList = filterMenuInfoByRole(ml,regexp);
    		List<String> menuAuthList = new ArrayList<>();
    		menuAuth(roleMenuList, menuAuthList);
    		menuAuth.put(role.getRoleId()+"", menuAuthList);
    		menuCache.put(role.getRoleId()+"", roleMenuList);
    	}
    	logger.logInfo(" done ! cache menu.......");
    }
    
    public static void cacheMenuCacheByRoleId(Integer roleId){
    	List<MenuInfo> ml = systemService.queryRootMenu();
    	logger.logInfo("  cache menu   roleId="+roleId);
		List<MenuInfo> l = systemService.queryMenuInfoByRoleId(null,roleId);
		String regexp = createRegexp(l);    		
		List<MenuInfo>  roleMenuList = filterMenuInfoByRole(ml,regexp);
		List<String> menuAuthList = new ArrayList<>();
		menuAuth(roleMenuList, menuAuthList);
		menuAuth.put(roleId+"", menuAuthList);
		menuCache.put(roleId+"", roleMenuList);
    }
    
    
    public static void menuAuth(List<MenuInfo> list,List<String> auth){
    	for(MenuInfo m:list){
    		if(!StringUtil.isEmpty(m.getAuthCode())&&m.getAuthCode().length()>=5){
    			auth.add(m.getAuthCode());
    		}
    		menuAuth(m.getList(), auth);
    	}
    }
    
    //缓存角色权限
    public static void cacheAuthCache(){
    	logger.logInfo("begin cache auth......");
    	List<RoleInfo> list = systemService.queryRoleInfo();
    	for(RoleInfo role:list){
    		List<AuthInfo> l = systemService.queryAuthInfoByRoleId(role.getRoleId());
    		authCache.put(role.getRoleId()+"", l);
    	}
    	logger.logInfo("done cache auth......");
    }
    private static List<MenuInfo> filterMenuInfoByRole(List<MenuInfo> fullList,String regexp){
    	List<MenuInfo> list = new ArrayList<MenuInfo>();
    	for(MenuInfo m:fullList){
    		MenuInfo cm = new MenuInfo();
    		if(ATTSystem.LOGIC_TRUE.equals(m.getVisitility())&&FrameCache.regex(regexp,StringUtil.dealNull(m.getId()))){			
    			cm.setId(m.getId());
    			cm.setMenuDesc(m.getMenuDesc());
    			cm.setMenuHref(m.getMenuHref());
    			cm.setMenuLev(m.getMenuLev());
    			cm.setMenuName(m.getMenuName());
    			cm.setMenuSeq(m.getMenuSeq());
    			cm.setOwner(m.getOwner());
    			cm.setPId(m.getPId());
    			cm.setVisitility(m.getVisitility());
    			cm.setIcoClass(m.getIcoClass());
    			cm.setAuthCode(m.getAuthCode());
    			cm.setList(filterMenuInfoByRole(m.getList(),regexp));
    			list.add(cm);
    		}
    	}
    	return list;
    }

	private static boolean regex(String regex, String str) {
		boolean trueOrFalse;
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		trueOrFalse = m.matches();
		return trueOrFalse;
	}
    
    private static String createRegexp(List<MenuInfo> l){
    	StringBuffer sbuf=new StringBuffer("(-1");
    	for(MenuInfo m:l){
    		sbuf.append("|"+m.getId());
    	}
    	sbuf.append(")");
    	return sbuf.toString();
    }
    
    public List<MenuInfo> getMenuCacheByRole(Integer roleId){
    	initCache();
    	return menuCache.get(roleId+"");
    }
    
    public List<String>  getMenuAuthCacheByRole(Integer roleId){
    	initCache();
    	return menuAuth.get(StringUtil.dealNull(roleId));
    }
    public List<AuthInfo> getAuthInfoCacheByRole(Integer roleId){
    	initCache();
    	
    	return authCache.get(roleId+"");
    }
    public Map<String, List<MenuInfo>> getMenuCache(){
    	initCache();
    	return menuCache;
    }
    public Map<String, List<AuthInfo>> getauthCache(){
    	initCache();
    	return authCache;
    }
    public MenuInfo getFirstMenuInfoByRole(Integer roleId){
    	List<MenuInfo> list = getMenuCacheByRole(roleId);
    	return getFirstMenuInfo(list);
    }
    public MenuInfo getFirstMenuInfo(List<MenuInfo> list){
    	if(null!=list && list.size()>0){
    		MenuInfo menu = list.get(0);
    		if(!StringUtil.isEmpty(menu.getMenuHref())&&!"#".equals(menu.getMenuHref())){
    			return menu;
    		}else{
    			return getFirstMenuInfo(menu.getList());
    		}
    	}
    	return null;
    	//List<MenuInfo> list = getMenuCacheByRole(roleId);
    	
    }
    
    public List<MenuInfo> getMenuCacheByRoleUseUri(Integer roleId,String uri){
    	List<MenuInfo> cacheList = getMenuCacheByRole(roleId);
    	List<MenuInfo> list = new ArrayList<>(Arrays.asList(new MenuInfo[cacheList.size()]));
    	Collections.copy(list, cacheList);
    	
    	//只取第一个菜单列表
    	if(list.size()>0){
    		MenuInfo menu = list.get(0);
    		clearCurrent(menu);

    		List<MenuInfo> mlist = menu.getList();
    		for(MenuInfo sonmenu:mlist){
    			sonmenu.setCurrent(currentTag(sonmenu, uri)?ATTSystem.LOGIC_TRUE:ATTSystem.LOGIC_FALSE);
    		}
    		return mlist;
    	}
    	return new ArrayList<>();
    
    }
    private void clearCurrent(MenuInfo menu){
    	menu.setCurrent(ATTSystem.LOGIC_FALSE);
    	for(MenuInfo m:menu.getList()){
    		clearCurrent(m);
    	}
    }
    
    
    public boolean currentTag(MenuInfo menu,String uri){
    	 //System.out.println(menu.getMenuHref()+"     "+uri+"   "+menu.getIcoClass());
    	 
    	 boolean current = StringUtil.equals(menu.getMenuHref(), StringUtil.dealNull(uri).split(";")[0]);
    	 menu.setCurrent(current?ATTSystem.LOGIC_TRUE:ATTSystem.LOGIC_FALSE);
    	 if(!current){
    		 for(MenuInfo m:menu.getList()){
    			 current = currentTag(m,uri);
    			 if(current){
    				 break;
    			 }
    		 }
    	 }
    	 return current;
    }
    
    public boolean checkAuth(Integer roleId,String uri){
    	List<AuthInfo> list =  getAuthInfoCacheByRole(roleId);
    	AuthInfo ai = findMenuAuthInfo(uri,list);
    	if(null!=ai){
    		logger.logDebug(ai.getAuthRegexp()+"");
    		return true;
    	}
    	List<String> authUri = getMenuAuthCacheByRole(roleId);
    	return authEquals(uri,authUri);
    }
	private AuthInfo findMenuAuthInfo(String uri,List<AuthInfo> list){
		if(null==list){
			return null;
		}
		AuthInfo currentAuth = null;
		for(AuthInfo mai:list){
			if(authEquals(uri, mai)){
				return mai;
			}
		}
		return currentAuth;
	}
	private boolean authEquals(String uri,List<String> menuAuth){
		for(String authReg:menuAuth){
			logger.logDebug("regex(auth="+authReg+",uri="+uri+")");
			if(StringUtil.regex(authReg, uri)){
				return true;
			}
		}
		return false;
	}
    
	private boolean authEquals(String uri,AuthInfo mai){
		uri = StringUtil.trim(uri);
		String authRegular = StringUtil.trim(mai.getAuthRegexp());
		return StringUtil.equals(mai.getModel(), ATTSystem.LOGIC_TRUE)?StringUtil.regex(authRegular, uri):StringUtil.equals(uri,authRegular);
	}
    
    
}
