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

        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
		<link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
		
        <!-- BEGIN GLOBAL PAGEHAND -->
         <%@ include file="/include/global/page_hand.jsp"%>
	    <!-- END GLOBAL PAGEHAND -->
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
            <% request.setAttribute("servlet.menu.sidebar.uri", "/order/search");%>
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
                                <a href="/user/dashboard">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                	订单管理
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="/order/search">订单管理</a>
                            </li>
                        </ul>
                       
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                       
                    </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    <div class="note note-info">
                        <p> 运营快速、查询、添加、修改订单入口。 </p>
                    </div>
                   <%--  
                   <div class="row">
                         <div class="col-md-6">
                             <div class="btn-group">
                                  <button class="btn sbold green" onclick="javascript:add();"> 新建艺术家
                                     <i class="fa fa-plus"></i>
                                 </button>
                             </div>
                         </div>
                   </div>
                   --%>
                   
                   <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
                                <!-- BEGIN TITLE -->
                                <div class="portlet-title">
                                    <div class="caption font-dark">
                                        <i class="icon-list font-dark"></i>
                                        <span class="caption-subject bold uppercase">订单列表</span>
                                    </div>
                                    <div class="tools"></div>
                                </div>
                                 <!-- END TITLE -->
                                <div class="portlet-body">
                                    <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                                        
                                       <!--开始表头搜索 --> 
                                       <form class="form-search" action="/order/search"> 
	                                       <div class="row">
	                                            <div class="col-md-6">
		                                            <input type="text" placeholder="请输入订单号.." class="form-control input-sm input-small input-inline" name="order_no" value="<c:out value="${orderInfo.order_no}"></c:out>">
                                                </div>
	                                            <div class="col-md-6">
		                                            <div class="dataTables_filter">
		                                            <button type="submit" class="btn btn-success">搜索</button>
		                                            </div>
	                                            </div>
	                                         </div>
                                         </form>
                                         <!--结束表头搜索 -->  
                                            
                                            
                                       <div class="table-scrollable">
                                            
                                        <table class="table table-striped table-bordered table-hover dataTable dtr-inline">
		                                        <thead>
		                                           <tr>
														<th>订单号</th>
														<th>订单金额</th>
														<th>支付金额</th>
														<th>创建时间</th>
														<th>操作</th>
										            </tr>
		                                        </thead>
                                                <tbody>
                                            
	                                             
	                                             <c:forEach items="${page_list.list}" var="coin">
												     <tr class="odd gradeX">
			                                            <td>${coin.order_no}</td>
														<td>${coin.order_amount}</td>
														<td>${coin.pay_amount}</td>
														 <td class="hidden-480"><fmt:formatDate value="${coin.order_create_time}" pattern="yyyy-MM-dd HH:mm"/></td>
														 <td> 
														 <div class="btn-group">
	                                                        <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">操作
	                                                            <i class="fa fa-angle-down"></i>
	                                                        </button>
	                                                        <ul class="dropdown-menu" role="menu">
														        <li><a href="javascript:void();" onclick="javascript:modify('${coin.order_id}');"><i class="icon-tag"></i> 修改 </a></li>
														        <li><a href="javascript:void();" onclick="javascript:remove('${coin.order_id}');"><i class="icon-drop"></i> 删除 </a></li>
	                                                        </ul>
	                                                     </div>
														 </td>
												     </tr>
												  </c:forEach>
												  
                                            </tbody>
                                    </table>
                                    </div>
                                    
                                    
                                    <!-- BEGIN FOOT -->
	                                <app:page></app:page>
	                                <!-- END FOOT -->
	                                
	                                
                                </div>
                                </div>
                            </div>
                           
                        </div>
                    </div>
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
			<script src="//static.wooboo.com.cn/theme/assets/global/plugins/respond.min.js"></script>
			<script src="//static.wooboo.com.cn/theme/assets/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="//static.wooboo.com.cn/theme/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->

        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="/pages/js/common/bee-model.js"></script>
        
        <script type="text/javascript" src="/js/layer/layer.js"></script>
		<script type="text/javascript" src="/js/layer/extend/layer.ext.js" charset="utf-8"></script>
		<script type="text/javascript" src="/js/laydate/laydate.js"></script>   
		
        <!-- END THEME LAYOUT SCRIPTS -->
    <script>
	
	function add(){
		document.location = "/artist/add/to";
	}
	
	function modify(id){
		document.location = "/artist/modify/to?id="+id;
	}
	
	function remove(id){
		//toastr["info"]("已保存当前信息!", "成功");
	  
 	   if(confirm("您正在删除操作？")){
 	   //confirm
	   	   
		App.blockUI({ boxed: true, textOnly:true, message:"处理中..."  });
		$.ajax({
			type : "POST",
			url : '/artist/remove/su?id='+id,
			success : function(msg) {
			    App.unblockUI();
			    if(msg.code == 200){
			        
					toastr["sucess"](msg.message, "操作完成");
					setTimeout(function(){
						document.location = "/artist/search";
					},3000);
			    }else{
			    	
			    	toastr["error"](msg.message,"操作失败");
			    	setTimeout(function(){
						document.location = "/artist/search";
					},3000);
			    }
			}
		});
		
		//confirm
		}
	}
	
	
	function openOff(id,action){
	
	  var info = "";
 	   if(action == "plan_open"){
 	   	 info = "启用"
 	   }else if(action == "plan_close"){
 		 info = "作废"   
 	   }
 	   
 	   if(confirm("您正在执行"+info+"操作？")){
 	   //confirm
	   	   
		App.blockUI({ boxed: true, textOnly:true, message:"处理中..."  });
		$.ajax({
			type : "POST",
			url : '/http/push?id='+id+'&action='+action,
			success : function(msg) {
			    App.unblockUI();
			    if(msg.code == 0){
			        //layer.msg(msg.dataObject, {shift : 18});
					toastr["sucess"](msg.dataObject, "操作完成");
					setTimeout(function(){
						document.location = "/ams/plan/search";
					},3000);
					//document.location = "/ams/plan/search";
			    }else{
			    	//layer.msg('操作失败', {shift : 18});
			    	toastr["error"]("操作失败!","操作失败");
			    	setTimeout(function(){
						document.location = "/ams/plan/search";
					},3000);
			    	//document.location = "/ams/plan/search";
			    }
			}
		});
		
		//confirm
		}
		
	}
	
		  toastr.options = {
			  "closeButton": true,
			  "debug": true,
			  "positionClass": "toast-bottom-full-width",
			  "onclick": null,
			  "showDuration": "1000",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
		
	</script>
        
        
        
    </body>

</html>