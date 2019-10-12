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

		
        <!-- BEGIN GLOBAL PAGEHAND -->
         <%@ include file="/include/global/page_hand.jsp"%>
		<link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
	   	<link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
	   	<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
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
            <% request.setAttribute("servlet.menu.sidebar.uri", "/stock/outbound/search");%>
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
                                <a href="/stock/outbound/search">出库记录</a>
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
                        <p> 运营快速、查询、添加、修改产品出库入口。 </p>
                    </div>
                    
                   <div class="row">
                         <div class="col-md-6">
                             <div class="btn-group">
								 <%-- 	                                
                                  <button class="btn sbold green" onclick="javascript:BeeModel.openModel('/stock/inbound/add/to');"> 产品入库
                                     <i class="fa fa-plus"></i>
                                 </button>
                                 --%>
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
                                        <span class="caption-subject bold uppercase">出库记录</span>
                                    </div>
                                    <div class="tools"></div>
                                </div>
                                 <!-- END TITLE -->
                                <div class="portlet-body">
                                    <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                                        
                                       <!--开始表头搜索 --> 
                                       <form class="form-search" action="/stock/outbound/search"> 
	                                       <div class="row">
	                                            <div class="col-md-3">
		                                            <input type="number" placeholder="请输入产品ID.." class="form-control input-sm input-small input-inline" name="productInfo.product_id" value="<c:out value="${goodsStockOutbound.productInfo.product_id}"></c:out>">
                                                </div>
                                                <div class="col-md-3">
		                                            <input type="text" placeholder="请输入产品名称.." class="form-control input-sm input-small input-inline" name="productInfo.product_name" value="<c:out value="${goodsStockOutbound.productInfo.product_name}"></c:out>">
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
														<th>出库ID</th>
														<th>产品ID</th>
														<th>产品名称</th>
														<th>总库存</th>
														<th>预占</th>
														<th>实占</th>
														<th>可用库存</th>
														<th>本次出库</th>
														<th>出库状态</th>
														<th>最后操作日期</th>
														<th>最后操作人</th>
														<th>操作</th>
										            </tr>
		                                        </thead>
                                                <tbody>
                                            
	                                            
	                                             <c:forEach items="${page_list.list}" var="prod">
												     <tr class="odd gradeX">
												     	<td>${prod[0]}</td>
			                                            <td>${prod[1]}</td>
														<td>${prod[2]}</td>
														<td>${prod[3]}</td>
														<td>${prod[4]}</td>
														<td>${prod[5]}</td>
														<td>${prod[6]}</td>
														<td><span style="color: darkgreen;font-weight: bolder;">- ${prod[7]}</span></td>
														<td class="hidden-480">
													       <c:choose>
													         <c:when test="${prod[8]==1}"><span class="label label-success">有效</span></c:when>
													         <c:otherwise><span class="label label-warning">无效</span></c:otherwise>
													       </c:choose>
													    </td>
														<td class="hidden-480"><fmt:formatDate value="${prod[10]}" pattern="yyyy-MM-dd HH:mm"/></td>
														<td class="hidden-480"><app:sysuser_name uid="${prod[9]}"/></td>
														<td > 
														 <div class="btn-group">
	                                                        <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">操作
	                                                            <i class="fa fa-angle-down"></i>
	                                                        </button>
	                                                        <ul class="dropdown-menu" role="menu">
														        <li><a href="javascript:void();" onclick="javascript:BeeModel.openModel('/stock/outbound/detail/to/${prod[0]}');"><i class="icon-info"></i>出库详情</a></li>
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
        
        
        <div class="modal fade" id="user-modal" role="basic" aria-hidden="true" data-width="760">
            
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
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
		
		<script src="//static.wooboo.com.cn/theme/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="/pages/js/common/components-select2.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="/pages/js/common/bee-extended-model.js"></script>
        <script src="/pages/js/common/xhr.js"></script>
        
        <script type="text/javascript" src="/js/layer/layer.js"></script>
		<script type="text/javascript" src="/js/layer/extend/layer.ext.js" charset="utf-8"></script>
		
		
        <!-- END THEME LAYOUT SCRIPTS -->
    <script>
	
	var Page=(function(){
  	    	return {
  	    		validate:function(){
  	    			var form1 = $('#user_form');
  	                var error1 = $('.alert-error', form1);
  	                var success1 = $('.alert-success', form1);
  	    			
  	                form1.validate({
  	    				 errorElement: 'span',
  	    				 errorClass: "help-block help-block-error", // default input error message class
  	    	             focusInvalid: false, // do not focus the last invalid input
  	    	             ignore: "",
  	    				    rules: {
  	    				      product_id: "required",
  	    				      in_type: "required",
  	    				      in_stock:"required"
  	    				    },
  	    				    messages: {
  	    				      product_id: "请选择产品",
  	    				      in_type: "请选择入库类型",
  	    				      in_stock:"请输入入库数量"
  	    				    },
  	    				  invalidHandler: function(e, t) {
	    		                success1.hide();
	    		                error1.show();
	    		            },
	    		            highlight: function(e) {
	    		                $(e).closest(".form-group").addClass("has-error")
	    		            },
	    		            unhighlight: function(e) {
	    		                $(e).closest(".form-group").removeClass("has-error")
	    		            },
	    		            success: function(e) {
	    		                e.closest(".form-group").removeClass("has-error")
	    		            },
  	    	                submitHandler: function (form) {
  	    	                    success1.show();
  	    	                    error1.hide();
  	    	                    //单次点击
  	    	                    $("#user_submit").addClass("disabled");
  	    	                    Xhr.cfg("/stock/inbound/add/su",{},function(response){
  	    	                    	if(response.code==200){
  	    	                    		
  	    	                    		toastr["success"](response.message, "操作成功");
  	    	                    		BeeModel.closeModel();
  	    	                    		window.location.reload();
  	    	                    	}else{
  	    	                    		success1.hide();
  	    	                    		toastr["error"](response.message, "操作失败");
  	    	                    		$("#user_submit").removeClass("disabled");
  	    	                    	}
  	    	                    	
  	    	                    }).execute_form("#user_form");
  	    	                    
  	    	                    
  	    	                }
  	    				    
  	    				});
  	    		},
  	    		submitform:function(){
  	    			 $('#user_form').submit();
  	    			
  	    		}
  	    	}
  	    })();
	
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