package com.wooboo.dsp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.system.util.FrameCache;

/**
 * 
 * @author luoyouhua
 * 权限判断
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = Logger.getLogger(AuthorityInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		//用于菜单判断
		request.setAttribute("servlet.menu.sidebar.uri", uri);
		HttpSession session = request.getSession();
		logger.info("AuthorityInterceptor.preHandle(uri="+uri+")");
		//session中获取user
		SysUser user=null;
		Object obj = session.getAttribute(ATTSystem.SESSION_USER);
		if(null!=obj){
			if(obj.getClass().equals(SysUser.class)){
				user = (SysUser)obj;
			}	
		}

		if(null==user){
			//未登录
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return false;
		}else{
			Integer roleId = user.getRoleInfo().getRoleId();
		    if(!FrameCache.getInstance().checkAuth(roleId, uri)){
		    	request.getRequestDispatcher("/pages/auth_error.jsp").forward(request, response);
				return false;
		    }

		}
		return true;
	}
	
}
