<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="/application" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="zh-cmn-Hans">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <meta charset="utf-8" />

        <link href="/theme/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="/theme/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="/theme/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
        <!-- BEGIN GLOBAL PAGEHAND -->
         <%@ include file="/include/global/page_hand.jsp"%>
	    <!-- END GLOBAL PAGEHAND -->

        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="/theme/assets/pages/css/error.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
  
        </head>
    <!-- END HEAD -->

    <body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
        <!-- BEGIN HEADER -->
         <%@ include file="/include/global/hander.jsp"%>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
             <%@ include file="/include/global/sidebar.jsp"%>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                   
                    <!-- BEGIN PAGE BAR -->
                    <div class="page-bar">
                        <ul class="page-breadcrumb">
                            <li>
                                <a href="index.html">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="index.html">权限错误</a>
                            </li>
                        </ul>
                       
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                     <h3 class="page-title"> 权限错误
                        <small>没有访问的权限。</small>
                    </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                   
                   
                   <!--  -->
                   <div class="row">
                        <div class="col-md-12 page-500">
                            <div class=" number font-red">500 </div>
                            <div class=" details" style="font-weight: bold;">
                                <h3>您没有您所访问的url权限。</h3>
                                <p> 您可以联系管理员,管理员可以给您分配权限。<br/> </p>
                                <p><a href="/" class="btn red btn-outline"> 回到首页 </a><br> </p>
                            </div>
                        </div>
                    </div>
                   <!--  -->
                   
                   
                   
                   
                   
                   
                    
                    
                </div>
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END CONTAINER -->
        <!-- 
        <div class="modal fade" id="ajax" role="basic" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-body">
                                                    <img src="../assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
                                                    <span> &nbsp;&nbsp;Loading... </span>
                                                </div>
                                            </div>
               </div>
        </div> -->
        
        
        <div class="modal fade" id="user-modal" role="basic" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <!-- <div class="modal-body">
                                                    <img src="../assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
                                                    <span> &nbsp;&nbsp;Loading... </span>
                                                </div> -->
                                            </div>
               </div>
        </div>
        
         
        
        
        <!-- BEGIN FOOTER -->
        <script src="/pages/js/common/bee-footer.js" type="text/javascript"></script>
        <!-- END FOOTER -->
        <!--[if lt IE 9]>
			<script src="/theme/assets/global/plugins/respond.min.js"></script>
			<script src="/theme/assets/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/theme/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="/theme/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        
        <script src="/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script>
		
	</script>
        
        
        
    </body>

</html>