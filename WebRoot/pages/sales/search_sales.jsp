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
		
		<link href="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css" />
		<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
		<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
		
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
            <% request.setAttribute("servlet.menu.sidebar.uri", "/goods/sales/search");%>
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
                                	商品管理
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="/goods/sales/search">商品列表</a>
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
                        <p> 运营快速、查询、添加、修改商品入口。 </p>
                    </div>
                    
                   <div class="row">
                         <div class="col-md-6">
                             <div class="btn-group">
                                  <button class="btn sbold green" onclick="javascript:add();"> 新建商品
                                     <i class="fa fa-plus"></i>
                                 </button>
                             </div>
                         </div>
                   </div>
                   
                   
                   <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
                                <!-- BEGIN TITLE -->
                                <div class="portlet-title">
                                    <div class="caption font-dark">
                                        <i class="icon-list font-dark"></i>
                                        <span class="caption-subject bold uppercase">商品列表</span>
                                    </div>
                                    <div class="tools"></div>
                                </div>
                                 <!-- END TITLE -->
                                <div class="portlet-body">
                                    <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                                        
                                       <!--开始表头搜索 --> 
                                       <form class="form-search" action="/goods/sales/search"> 
	                                       <div class="row">
	                                            <div class="col-md-2">
		                                            <input type="text" placeholder="请输入商品名称.." class="form-control input-sm input-small input-inline" name="sales_name" value="<c:out value="${goodsSalesInfo.sales_name}"></c:out>">
                                                </div>
                                                <div class="col-md-2">
                                                  <select class="form-control input-sm select2-multiple" id="artist_id" name="artist_id">
								                  	<option value="">请选择艺术家...</option>
								                  	<app:ArtistSelect artistId="${goodsSalesInfo.artist_id }"/>
										           </select>
                                                </div>
	                                           <div class="col-md-2">
		                                            <select class="form-control input-sm select2-multiple" id="product_id" name="product_id">
									                  <option value="">请选择产品...</option>
									                  <app:ProductSelect productId="${goodsSalesInfo.product_id }"/>
										           </select>
                                                </div>
	                                            <div class="col-md-6">
			                                            <div id="sample_1_filter" class="dataTables_filter">
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
														<th>ID</th>
														<th>产品ID</th>
														<th>商品名称</th>
														<th>状态</th>
														<th>最后操作日期</th>
														<th>最后操作人</th>
														<th>操作</th>
										            </tr>
		                                        </thead>
                                                <tbody>
                                            
	                                             
	                                             <c:forEach items="${page_list.list}" var="coin">
												     <tr class="odd gradeX">
			                                            <td>${coin.sales_id}</td>
														<td>${coin.product_id}</td>
														<td>${coin.sales_name}</td>
														<td>
															<c:choose>
																<c:when test="${coin.sales_status == 0}"><a href="javascript:void(0)" onclick="openOff(${coin.sales_id},1)"><img src="/images/thumb_close.png" title="关闭中，点击开启"/></a></c:when>
																<c:when test="${coin.sales_status == 1}"><a href="javascript:void(0)" onclick="openOff(${coin.sales_id},0)"><img src="/images/thumb_open.png" title="开启中，点击关闭"/></a></c:when>
															</c:choose>
															
														</td>
														 <td class="hidden-480"><fmt:formatDate value="${coin.modify_time}" pattern="yyyy-MM-dd HH:mm"/></td>
														 <td class="hidden-480"><app:sysuser_name uid="${coin.modifyor}"/></td>
														 <td > 
														 <div class="btn-group">
	                                                        <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">操作
	                                                            <i class="fa fa-angle-down"></i>
	                                                        </button>
	                                                        <ul class="dropdown-menu" role="menu">
														        <li><a href="javascript:void();" onclick="javascript:modify('${coin.sales_id}');"><i class="icon-tag"></i> 修改 </a></li>
														        <li><a href="javascript:void();" onclick="javascript:remove('${coin.sales_id}');"><i class="icon-drop"></i> 删除 </a></li>
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
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="/pages/js/common/components-select2.js" type="text/javascript"></script>
        
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
		document.location = "/goods/sales/add/to";
	}
	
	function modify(id){
		document.location = "/goods/sales/modify/to?sales_id="+id;
	}
	
	function remove(sales_id){
		
 	   if(confirm("您正在删除操作？")){
 	   //confirm
	   	   
		App.blockUI({ boxed: true, textOnly:true, message:"处理中..."  });
		$.ajax({
			type : "POST",
			url : '/goods/sales/remove/su?sales_id='+sales_id,
			success : function(msg) {
			    App.unblockUI();
			    if(msg.code == 200){
			        
					toastr["success"](msg.message, "操作完成");
					setTimeout(function(){
						document.location = "/goods/sales/search";
					},3000);
			    }else{
			    	
			    	toastr["error"](msg.message,"操作失败");
			    	setTimeout(function(){
						document.location = "/goods/sales/search";
					},3000);
			    }
			}
		});
		
		//confirm
		}
	}
	
	
	function openOff(sales_id,sales_status){
	
	  var info = "";
	  if(sales_status == 0){
	  	info = "关闭";
	  }else{
	  	info = "开启";
	  }
 	   
 	   if(confirm("您正在执行"+info+"操作？")){
 	   //confirm
	   	   
		App.blockUI({ boxed: true, textOnly:true, message:"处理中..."  });
		$.ajax({
			type : "POST",
			url : '/goods/sales/onOff?sales_id='+sales_id+'&sales_status='+sales_status,
			success : function(msg) {
			    App.unblockUI();
			    if(msg.code == 200){
					toastr["success"](msg.message, "操作完成");
					setTimeout(function(){
						document.location = "/goods/sales/search";
					},3000);
			    }else{
			    	toastr["error"](msg.message,"操作失败");
			    	setTimeout(function(){
						document.location = "/goods/sales/search";
					},3000);
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