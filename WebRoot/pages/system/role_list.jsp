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
	    <!-- END GLOBAL PAGEHAND -->
       <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
	   <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />


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
                                <a href="#">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">系统设置</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">角色管理</a>
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
                        <p> 系统菜单列表，菜单的添加编辑等操作 </p>
                    </div>
                    
                   
                   
                   <div class="row">
                         <div class="col-md-6">
                             <div class="btn-group">
                             
                             <!--  <a id="" class="btn sbold green" href="/system/role/to"> 新建角色
                                     <i class="fa fa-plus"></i>
                               </a> -->
                             
                            <button id="sample_editable_1_new" class="btn sbold green" onclick="javascript:BeeModel.openModel('/system/role/to');""> 新建角色
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
                                        <i class="icon-settings font-dark"></i>
                                        <span class="caption-subject bold uppercase">角色列表</span>
                                    </div>
                                    <div class="tools"> </div>
                                </div>
                                 <!-- END TITLE -->
                                <div class="portlet-body">
                                    <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                                    
                                       <!--开始表头搜索 -->  
                                       
                                       <form class="form-search" action="/system/role/list">
                                       <div class="row">
                                                <div class="col-md-6">
                                                 <label>角色: <input type="text" placeholder="" class="form-control input-sm input-small input-inline" name="roleName" value="${role.roleName}"></label>
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
                                            
                                        <table class="table table-striped table-bordered table-hover dataTable dtr-inline"  >
		                                        <thead>
		                                            <tr>
		                                            <th>编号</th>
		                                            <th>角色</th>
		                                            <th>备注</th>
		                                            <th>操作 </th>
		                                            </tr>
		                                        </thead>
                                                <tbody>
                                            
                                            
                                            
                                            <c:forEach items="${page_list.list}" var="role">
											    <tr  class="even">
													<td style="">${role.roleId }</td>
													<td class="">${role.roleName }</td>
													<td class="hidden-480">${role.roleDesc }</td>
													<td >
														<a href="javascript:BeeModel.openModel('/system/role/to?roleId=${role.roleId}');"> 编辑</a>
														&nbsp;|&nbsp;
														<a href="javascript:void(0);" onclick="delRole(${role.roleId })"> 删除</a>
													</td>
												</tr>
											  </c:forEach>
                                         </tbody>
                                    </table>
                                    </div>
                                    
                                    
                                    <!-- BEGIN FOOT -->
                                    
                                    <app:page></app:page>
                                    
                                    
	                                <!-- <div class="row">
	                                    
	                                    <div class="col-md-5 col-sm-12">
	                                        <div class="dataTables_info" id="sample_1_info" role="status" aria-live="polite">Showing 1 to 10 of 43 entries</div>
	                                    </div>
	                                    <div class="col-md-7 col-sm-12">
		                                    <div class="dataTables_paginate paging_bootstrap_number" id="sample_1_paginate">
		                                    <ul class="pagination" style="visibility: visible;">
		                                    <li class="prev disabled"><a href="#" title="Prev"><i class="fa fa-angle-left"></i></a></li>
		                                    <li class="active"><a href="#">1</a></li>
		                                    <li><a href="#">2</a></li>
		                                    <li><a href="#">3</a></li>
		                                    <li><a href="#">4</a></li>
		                                    <li><a href="#">5</a></li>
		                                    <li class="next"><a href="#" title="Next"><i class="fa fa-angle-right"></i></a></li>
		                                    </ul>
		                                    </div>
	                                    </div>
	                                </div> -->
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
			<script src="/theme/assets/global/plugins/respond.min.js"></script>
			<script src="/theme/assets/global/plugins/excanvas.min.js"></script> 
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
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
       <!--  <script src="/pages/js/common/bee-model.js"></script>  -->
        <script src="/pages/js/common/bee-extended-model.js"></script>  
        <script src="/pages/js/common/xhr.js"></script>     
         	      
		<script type="text/javascript" src="/js/layer/layer.js"></script>
		<script type="text/javascript" src="/js/layer/extend/layer.ext.js" charset="utf-8"></script>


        <script type="text/javascript">
		
  		 var delRole = function(id) {
  			 
  			 var msg = "您确定要删除此条数据么？" ;
  			 // 查找是否用用户使用此角色
  			 $.ajax({
  				 type : "GET",
  				 url : '/system/role/isroleuser?id=' + id,
  				 success : function(data) {
  					 if(data.code == 0) {
  						msg = "此角色用户在使用，" + msg ;
  					 }
  						 
					layer.confirm(msg, {
						btn : [ '确定', '取消' ], //按钮
						shade : false
					//不显示遮罩
					}, function() {
						$.ajax({
							type : "GET",
							url : '/system/role/del?id='+id,
							success : function(data) {
								if (data.code == '0') {
									layer.msg('操作成功！！', {
										shift : 6
									});
									window.location.reload();
								} else {
									layer.msg('操作失败！', {
										shift : 6
									});
								}
							}
						}); 
					});
  				 } 
  			 }) ;
  			
		}
        
        jQuery(document).ready(function() {    
  		
  		  BeeModel.init();
  		});

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
  	    				      roleName: "required",
  	    				      roleLevel: "required"
  	    				    },
  	    				    messages: {
  	    				      roleName: "请输入您的名字",
  	    				      roleLevel: "请输入您的姓氏"
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
  	    	                	//alert("submit");
  	    	                    success1.show();
  	    	                    error1.hide();
  	    	                    //单次点击
  	    	                    $("#user_submit").addClass("disabled");
  	    	                    Xhr.cfg("/system/role/su",{},function(response){
  	    	                    	if(response.code==200){
  	    	                    		BeeModel.closeModel();
  	    	                    		window.location.reload();
  	    	                    	}else{
  	    	                    		success1.hide();
  	    	                    		$('.alert-error').html(response.message);
  	    	                    		$('.alert-error').show();
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
        
        
        
	   </script>
    </body>

</html>