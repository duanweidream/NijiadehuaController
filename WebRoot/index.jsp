<%@page import="com.wooboo.dsp.system.util.SessionHelper"%>
<%@page import="com.wooboo.dsp.system.util.HttpHelp"%>
<%
  if(SessionHelper.hasLogin()){
	  request.getRequestDispatcher("/user/index").forward(request, response);
  }else{
	  String token = HttpHelp.getCookieValue("beeusertoken");
	  if(null!=token){
		  request.getRequestDispatcher("/login/auto").forward(request, response);
	  }else{
		  request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	  }
  }
%>