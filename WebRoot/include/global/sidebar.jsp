<%@page import="com.wooboo.dsp.model.MenuInfo"%>
<%@page import="com.wooboo.dsp.system.util.FrameCache"%>
<%@page import="com.wooboo.dsp.system.util.SessionHelper"%>
<%@page import="com.wooboo.dsp.model.SysUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String uri = (String)request.getAttribute("servlet.menu.sidebar.uri");
    SysUser user = SessionHelper.getSysUser();
    List<MenuInfo> list = null;
    if(null!=user){
    	list =  FrameCache.getInstance().getMenuCacheByRoleUseUri(user.getRoleInfo().getRoleId(), uri);
    }
    request.setAttribute("list", list);
%>
<div class="page-sidebar-wrapper">
                <!-- BEGIN SIDEBAR -->
                <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                <div class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
                    <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
                    <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
                    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
                    <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
                    <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
                    <ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
                        <!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
                        <li class="sidebar-toggler-wrapper hide">
                            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                            <div class="sidebar-toggler">
                                <span></span>
                            </div>
                            <!-- END SIDEBAR TOGGLER BUTTON -->
                        </li>
                      <c:forEach items="${list}" var="menu" varStatus="status">
                          <li class="nav-item <c:if test="${status.first}">start</c:if> <c:if test="${status.last}">last</c:if> <c:if test="${menu.current=='1'}">active</c:if>">
                            <c:choose><c:when test="${menu.menuHref=='#'}"><a href="javascript:;"  class="nav-link nav-toggle"></c:when> <c:otherwise><a href="${menu.menuHref}"  class="nav-link nav-toggle"></c:otherwise></c:choose>
                                <i class="${menu.icoClass}"></i> 
                                <span class="title">${menu.menuName}</span>
                                <c:if test="${menu.current=='1'}"><span class="selected"></span></c:if>
						        <c:if test="${fn:length(menu.list)>0}"><span class="arrow open"></span></c:if>
                            </a>
                            <c:if test="${fn:length(menu.list)>0}">
						       <ul class="sub-menu">
						           <c:forEach items="${menu.list}" var="son">
								         <li class="nav-item  <c:if test="${status.first}">start</c:if>   <c:if test="${son.current=='1'}">active open</c:if>"   >
			                                    <a href="${son.menuHref}" class="nav-link ">
			                                       <c:if test="${!empty son.icoClass}"><i class="${son.icoClass}"></i></c:if>
			                                        <span class="title">${son.menuName}</span>
			                                        <span class="selected"></span>
			                                    </a>
                                        </li>
						           </c:forEach>
						       </ul>
						    </c:if>
                        </li>
                      </c:forEach>
                    </ul>
                    <!-- END SIDEBAR MENU -->
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>