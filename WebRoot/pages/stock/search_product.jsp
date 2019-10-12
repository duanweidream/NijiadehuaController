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
            <% request.setAttribute("servlet.menu.sidebar.uri", "/stock/product/search") ;%>
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
                                <a>库存管理</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="/stock/product/search">产品库存</a>
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
                        <p> 运营快速、查询、添加、修改产品库存入口。 </p>
                    </div>
                    
                   <div class="row">
                         <div class="col-md-6">
                             <div class="btn-group">
                                 <!-- 
                                 <button id="sample_editable_1_new" class="btn sbold green" onclick="javascript:BeeModel.openModel('/ams/plan/add/to');"> 新建合同
                                     <i class="fa fa-plus"></i>
                                 </button>
                                  -->
                                  <button id="sample_editable_1_new" class="btn sbold green" onclick="javascript:add();"> 新建产品
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
                                        <span class="caption-subject bold uppercase">产品库存</span>
                                    </div>
                                    <div class="tools"></div>
                                </div>
                                 <!-- END TITLE -->
                                <div class="portlet-body">
                                    <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                                        
                                       <!--开始表头搜索 --> 
                                       <form class="form-search" action="/stock/product/search"> 
	                                       <div class="row">
	                                            <div class="col-md-3">
		                                            <input type="number" placeholder="请输入产品ID.." class="form-control input-sm input-small input-inline" name="product_id" value="${goodsProductInfo.product_id}">
                                                </div>
                                                <div class="col-md-3">
		                                            <input type="text" placeholder="请输入产品名称.." class="form-control input-sm input-small input-inline" name="product_name" value="${goodsProductInfo.product_name}">
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
														<th>产品ID</th>
														<th>产品名称</th>
														<th>总库存</th>
														<th>预占</th>
														<th>实占</th>
														<th>可用库存</th>
														<th>最后操作日期</th>
														<th>最后操作人</th>
														<th>操作</th>
										            </tr>
		                                        </thead>
                                                <tbody>
                                            
	                                            
	                                             <c:forEach items="${page_list.list}" var="prod">
												     <tr class="odd gradeX">
			                                            <td>${prod.product_id}</td>
														<td>${prod.product_name}</td>
														<td>${prod.total_stock}</td>
														<td>${prod.pre_stock}</td>
														<td>${prod.rel_stock}</td>
														<td>${prod.ava_stock}</td>
														 <td class="hidden-480"><fmt:formatDate value="${prod.modify_time}" pattern="yyyy-MM-dd HH:mm"/></td>
														 <td class="hidden-480"><app:sysuser_name uid="${prod.modifyor}"/></td>
														 <td > 
														 <div class="btn-group">
	                                                        <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">操作
	                                                            <i class="fa fa-angle-down"></i>
	                                                        </button>
	                                                        <ul class="dropdown-menu" role="menu">
														        <li><a href="javascript:void();" onclick="javascript:modify('${prod.product_id}');"><i class="icon-tag"></i> 修改 </a></li>
														        <li><a href="javascript:void();" onclick="javascript:remove('${prod.product_id}');"><i class="icon-drop"></i> 删除 </a></li>
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
		document.location = "/stock/product/add/to";
	}
	
	function modify(id){
		document.location = "/stock/product/modify/to?id="+id;
	}
	
	function remove(id){
		
 	   if(confirm("您正在删除操作？")){
 	   //confirm
	   	   
		App.blockUI({ boxed: true, textOnly:true, message:"处理中..."  });
		$.ajax({
			type : "POST",
			url : '/stock/product/remove/su?id='+id,
			success : function(msg) {
			    App.unblockUI();
			    if(msg.code == 200){
					toastr["success"]("信息已删除", "操作完成");
					setTimeout(function(){
						document.location = "/stock/product/search";
					},3000);
			    }else{
			    	
			    	toastr["error"](msg.message,"操作失败");
			    	setTimeout(function(){
						document.location = "/stock/product/search";
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