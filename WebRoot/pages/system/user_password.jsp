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
            
           <% request.setAttribute("servlet.menu.sidebar.uri", "/system/user/search"); %>
            
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
                                <a href="#">系统用户</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">重置密码</a>
                            </li>
                        </ul>
                       
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                       
                    </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                  <!--   <div class="note note-info">
                        <p>页面功能描述 </p>
                    </div> -->
                   
                   <!--  -->
                   <div class="row">
                        <div class="col-md-12">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-bubble font-green-sharp"></i>
                                        <span class="caption-subject font-green-sharp sbold">重置用户 【${user.nickName}】密码</span>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <form action="#" class="form-horizontal" id="form1">
                                     <input type="hidden" name="userId" value="${user.id}" />
                                     <div class="alert alert-success display-hide">
                                         <button class="close" data-close="alert"></button> 表单验证成功！ </div>
                                     <div class="alert alert-danger display-hide">
                                         <button class="close" data-close="alert"></button> 表单验证失败，请检查表单数据! </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label" for="title">用户：</label>
                                            <div class="col-md-5">
                                                <input id="notific8_text" type="text" class="form-control" value="${user.userName}" placeholder="" disabled /> </div>
                                        </div>
                                         <div class="form-group">
                                            <label class="col-md-3 control-label" for="title">新密码：</label>
                                            <div class="col-md-5">
                                                <input id="npassword" name="npassword" type="password" class="form-control" value="" placeholder="" /> </div>
                                        </div>
                                         <div class="form-group">
                                            <label class="col-md-3 control-label" for="title">密码确认：</label>
                                            <div class="col-md-5">
                                                <input id="rnpassword" name="rnpassword" type="password" class="form-control" value="" placeholder="" /> </div>
                                        </div>
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button type="submit" class="btn green" id="submit">确定修改</button>
                                                    <button type="reset" class="btn grey-salsa btn-outline">重置</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
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
        <script src="/pages/js/common/xhr.js"></script>
        <script src="/js/password_check.js"></script>
        
        <!-- END THEME LAYOUT SCRIPTS -->
       <script>
		
        jQuery(document).ready(function() {    
        	Form.validate();
        	//toastr["info"]("您的密码修改已经修改完成!", "密码修改完成")
        	
        	// 密码验证强度
    		$.validator.addMethod("NPassword", function(value) {
    			var password = $("#npassword").val();
    			var S_level = checkStrong(password);
    			console.log(S_level);

    			var pascheck = true;
    			if (S_level <= 2)
    				pascheck = false;

    			return pascheck;
    		}, "密码格式不正确");
  		});
        
        //页面模块
		  var Form=(function(){
		    	return {
		    		validate:function(){
		    			var form1 = $('#form1');
		                var error1 = $('.alert-danger', form1);
		                var success1 = $('.alert-success', form1);
		                form1.validate({
		    				 errorElement: 'span',
		    				 errorClass: "help-block help-block-error", 
		    	             focusInvalid: false, 
		    	             ignore: "",
		    				    rules: {
		    				    	npassword:{
		    				    		required:true,
		    				    		NPassword:true
		    				    		//minlength:6
		    				    	},
		    				    	rnpassword:{
		    				    		required:true,
		    				    		//minlength:6,
		    				    		equalTo: "#npassword"
		    				    	}
		    				    },
		    				    messages: {
		    				    	npassword: {
		    				    		required:"请输入新密码",
		    				    		NPassword:"请使用8位以上，数字、小写字母、大写字母、特殊符号4类中至少3类组合"
		    				    		//minlength:"密码不小于6位"
		    				    	},
		    				    	rnpassword: {
		    				    		required:"请输入密码确认",
		    				    		//minlength:"密码不小于6位",
		    				    		equalTo: "两次密码输入不一致"
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
	  	    	                    success1.show();
	  	    	                    error1.hide();
	  	    	                    
	  	    	                    $("#submit").attr("disabled",1);
	  	    	                     Xhr.cfg("/system/user/password/update",{},function(response){
	  	    	                    	 
	  	    	                    	if(response.code==200){
	  	    	                    	    success1.hide();
	  	    	                    	  toastr.options.onHidden=function(){
	  	    	                    		  window.history.go(-1);
	  	    	                    	  }
	  		  	    	                    toastr["info"]("您的密码修改已经修改完成!", "密码修改完成")
	  	    	                    		//window.location.reload();
	  	    	                    	}else{
	  	    	                    	    success1.hide();
	  		  	    	                    toastr["error"](response.message,"密码修改异常!");
	  		  	    	                    $("#submit").attr("disabled",false);
	  		  	    	                   //$("#form1").reset();
	  	    	                    	}
	  	    	                    	
	  	    	                    }).execute_form("#form1"); 
	  	    	                }
		    				});
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