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
                                <a href="#">系统用户</a>
                            </li>
                        </ul>
                       
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                       
                    </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                <!--     <div class="note note-info">
                        <p> 系统菜单列表，菜单的添加编辑等操作 </p>
                    </div>
                     -->
                   
                   
                   <div class="row">
                         <div class="col-md-6">
                             <div class="btn-group">
                                 <button id="sample_editable_1_new" class="btn sbold green" onclick="javascript:BeeModel.openModel('/system/user/to');"> 新建用户
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
                                        <span class="caption-subject bold uppercase">系统用户列表</span>
                                    </div>
                                    <div class="tools"> </div>
                                </div>
                                 <!-- END TITLE -->
                                <div class="portlet-body">
                                    <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                                    
                                       <!--开始表头搜索 -->  
                                       
                                       <form class="form-search" action="/system/user/search">
                                       <div class="row">
	                                            <div class="col-md-8">
		                                            <label>登录名:
		                                            <input type="text" placeholder="" class="form-control input-sm input-small input-inline" name="userName" value="<c:out value="${user.userName}"></c:out>">
		                                            </label>
		                                            <label style="margin-left: 15px; height: 32px;">
		                                            <select name="roleInfo.roleId" id="position" class="form-control" roleid="${user.roleInfo.roleId}" style="width:250px; height: 30px;">
														<option value="">请选择用户角色...</option>
													</select>
		                                            </label>
		                                            
                                                </div>
	                                            <div class="col-md-4">
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
		                                            <tr class="table-head">
														<th style="">ID</th>
														<th>登录名</th>
														<th class="hidden-480">昵称</th>
														<th class="hidden-480">角色</th>
														<th class="hidden-480">电话</th>
														<th class="hidden-480">邮箱</th>
														<!-- <th class="hidden-480">登录次数</th> -->
														<!-- <th class="hidden-480">创建时间 </th> -->
														<th class="hidden-480">最近登录</th>
														<th class="hidden-480">状态</th>
														<th >操作</th>
													</tr>
		                                        </thead>
                                                <tbody>
		                                            <c:forEach items="${page_list.list}" var="user">
												     <tr  class="even">
													    <td>${user.id }</td>
													    <td>${user.userName}</td>
													    <td class="hidden-480">${user.nickName }</td>
													    <td class="hidden-480">${user.roleInfo.roleName}</td>
													    <td class="hidden-480">${user.userPhone}</td>
													    <td class="hidden-480">${user.email}</td>
													    <%-- <td class="hidden-480">${user.loginTime}</td> --%>
<%-- 													    <td class="hidden-480"><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd"/> </td>
 --%>													    <td class="hidden-480"><fmt:formatDate value="${user.lastDate}" pattern="yyyy-MM-dd"/></td>
													    <td class="hidden-480">
													       <c:choose>
													         <c:when test="${user.state=='1' }"><span class="label label-success">正常</span></c:when>
													         <c:otherwise><span class="label label-warning">禁止</span></c:otherwise>
													       </c:choose>
													    </td>
													    <td> 
													    <div class="btn-group">
	                                                        <button class="btn btn-xs green dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">操作
	                                                            <i class="fa fa-angle-down"></i>
	                                                        </button>
	                                                        <ul class="dropdown-menu" role="menu">
														        <li><a onclick="javascript:BeeModel.openModel('/system/user/to?userId=${user.id}');"><i class="icon-tag"></i> 编辑 </a></li>
														        <li><a href="/system/user/password/to?userId=${user.id}"><i class="icon-tag"></i> 密码重置 </a></li>
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



        <script>
        
        jQuery(document).ready(function() {    
        	
  		  BeeModel.init();
        	loadUserRole();
  		});
        
        function loadUserRole() {
        	$.ajax({
        		type : "POST",
        		url : "/system/user/roleList",
        		//data : "roleName=" + value,
        		dataType:"json",
        		async : false,
        		success : function(data) {
        			//console.log(JSON.stringify(data));
        			for (var i in data) {
        				var roleData = data[i];
        				
        				document.getElementById("position").options.add(new Option(roleData.roleName,roleData.roleId));
        				//var node = document.createElement("option") = roleData.roleName;
        				//node.setAttribute("value",roleData.roleId);
        				//document.getElementById("position").appendChild(node);
        			}
        			var roleid = $("#position").attr("roleid");
        			$("#position").find("option[value='" + roleid + "']").attr("selected",true);
        		}
        	});
        }
        
        // 判断用户名是否重复
        $.validator.addMethod("existUser", function(value) {
			
        	flag = false ;
        	$.ajax({
        		type : "post",
        		url : "/system/user/exist",
        		data : "username=" + value ,
        		async : false,
        		success : function(data) {
        			console.log(data.isUser) ;
					if(data.isUser == 1) {
						flag = true ;
					} 
        		}
        	}) ;
        	return flag ;
		}, "用户已存在，请您重新填写");

  	    
  	  var Page=(function(){
	    	return {
	    		validate:function(){
	    			var form1 = $('#user_form');
	                var error1 = $('.alert-error', form1);
	                var success1 = $('.alert-success', form1);
	    			
	                form1.validate({
	    				 errorElement: 'span',
	    				 errorClass: "help-block help-block-error",
	    	             focusInvalid: false, // do not focus the last invalid input
	    	             ignore: "",
	    				    rules: {
    				    	  userName : {
    				    		  required : true,
    				    		  existUser : true
    				    	  },
	    				      nickName: "required",
	    				      roleId:"required",
	    				      username: {
	    				        required: true,
	    				        minlength: 2
	    				      },
	    				      email: {
	                                required: true
	                                //email: true
	                          },
	                          userPhone:{
	                        	  required: true,
	                        	  user_phone:true
	                          }
	    				    },
	    				    messages: {
	    				      userName : {
	    				    	  required : "用户名不能为空",
	    				    	  existUser : "改用户名已存在"
	    				      },
	    				      nickName: "请输入您的姓氏",
	    				      roleId:"请选择",
	    				      username: {
	    				        required: "请输入用户名",
	    				        minlength: "用户名必需由两个字母组成"
	    				      },
	    				      email: {
	                                required: "请输入邮箱"
	                                //email: "请正确输入邮箱"
	                          },
	                          userPhone:{
	                        	  required: "请正确填写手机号码或者电话号码(如:010-12345678)"
	                          }
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
	    	                    Xhr.cfg("/system/user/su",{},function(response){
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
        
  	  
  	  
  	jQuery.validator.addMethod("user_phone", function(value, element) {
		  	  var length = value.length; 
		  	  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
		  	  var isMobile=this.optional(element)||(length == 11 && mobile.test(value));
		  	  var tel = /^\d{3,4}-?\d{7,9}$/; //电话号码格式010-12345678 
		  	  var isPhone = this.optional(element) || (tel.test(value));
		  	  return isMobile||isPhone;
		  	}, "请正确填写手机号码或者电话号码(如:010-12345678)"); 

        
        
	   </script>
    </body>

</html>