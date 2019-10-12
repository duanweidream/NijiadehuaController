<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <div class="page-logo">
                    <a href="/user/dashboard">
                         <img src="/pages/images/logo1.png" style="width:66%;height:66%;" alt="logo" class="logo-default" /> 
                        </a>
                    <div class="menu-toggler sidebar-toggler">
                        <span></span>
                    </div>
                </div>
                <!-- END LOGO -->
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
                    <span></span>
                </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN TOP NAVIGATION MENU -->
                <div class="top-menu">
                    <ul class="nav navbar-nav pull-right">
                        <!-- BEGIN NOTIFICATION DROPDOWN -->
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" id="dropdown-user-a">
                                <!-- <img alt="" style=";" class="img-circle" data-src="holder.js/29x29?theme=user" /> -->
                                <img alt="" style=";" class="img-circle" data-src="${session_user.userTheme}" />
                                <span class="username username-hide-on-mobile">${session_user.userName}   ${session_user.roleInfo.roleName }</span>
                               <!--  <i class="fa fa-angle-down"></i> -->
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                                <li>
                                    <a href="/user/dashboard">
                                        <i class="icon-home"></i> 我的首页 </a>
                                </li>
                                <li>
                                    <a href="/user/password/to">
                                        <i class="icon-user"></i> 密码修改 </a>
                                </li>
                                <li class="divider"> </li>
                                <li>
                                    <a href="/logout">
                                        <i class="icon-key"></i> 退出 </a>
                                </li>
                            </ul>
                            
                        </li>
                        
                        <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                        <li class="dropdown dropdown-quick-sidebar-toggler" >
                            <a href="/logout" class="dropdown-toggle" >
                                <i class="icon-logout"></i>
                            </a>
                        </li>
                        <!-- END QUICK SIDEBAR TOGGLER -->
                    </ul>
                </div>
                <!-- END TOP NAVIGATION MENU -->
            </div>
            <!-- END HEADER INNER -->
        </div>
        
         <script src="/pages/js/common/holder.min.js"></script>  
