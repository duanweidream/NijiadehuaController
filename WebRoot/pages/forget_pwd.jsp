<%@page import="com.wooboo.dsp.system.util.Config"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<!DOCTYPE html>
<!-- -->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
         <title>蜂巢精销运营支撑系统 <%=Config.getValue("version")%></title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="//static.wooboo.com.cn/theme/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN PAGE LEVEL STYLES -->
        <link href="//static.wooboo.com.cn/theme/assets/pages/css/login-3.min.css" rel="stylesheet" type="text/css" />
        <!-- END PAGE LEVEL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <!-- END THEME LAYOUT STYLES -->
        <link rel="shortcut icon" href="/pages/images/bee.ico" /> 
        
        <style type="text/css">
             .login .content p {color: #777;}
        </style>
        </head>
    <!-- END HEAD -->

    <body class=" login">
        <!-- BEGIN LOGO -->
        <div class="logo" style="visibility: ">
            
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="forget-form" action="#" method="post" id="forget-form">
                <input type="hidden" value="${ufp.id}" id="id" name="id">
                <input type="hidden" value="${ufp.uid}" id="uid" name="uid">
                
                <h3>用户 ${ufp.userName} 密码重置</h3>
                <p> 您正在进行邮件重置密码. </p>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码.." id="newPwd" name="newPwd" /> </div>
                </div>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码确认.." id="rptPwd" name="rptPwd" /> </div>
                </div>
                
                <div class="form-actions">
                    <button type="button" id="back-btn" class="btn grey-salsa btn-outline"> 回退 </button>
                    <button id="submit" type="submit" class="btn green pull-right"> 提交 </button>
                </div>
            </form>
            
            <!-- END LOGIN FORM -->
            <!-- BEGIN REGISTRATION FORM -->
            
            <!-- END REGISTRATION FORM -->
        </div>
         <div class="copyright" style="color:#fff;"> 2017 &copy; HiveData.Express. version <%=Config.getValue("version")%> </div>
        <!-- END LOGIN -->
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
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <script src="//static.wooboo.com.cn/theme/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <script src="/pages/js/common/xhr.js" type="text/javascript"></script>
	
			<script>
		    var handleForgetPassword = function() {
            $('.forget-form').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    newPwd: {
                        required: true
                    },rptPwd: {
                        equalTo : '#newPwd'
                    }
                },

                messages: {
                    newPwd: {
                        required: "请输入新密码"
                    },rptPwd: {
                        required: "两次密码不一致"
                    }
                },

                invalidHandler: function(event, validator) { //display error alert on form submit   

                },

                highlight: function(element) { // hightlight error inputs
                    $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function(label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function(error, element) {
                    error.insertAfter(element.closest('.input-icon'));
                },

                submitHandler: function(form) {
                     $("#submit").attr("disabled",1);
                     
                     Xhr.cfg("/forget/rest",{id:$('#id').val(),uid:$('#uid').val(),newPwd:$('#newPwd').val()},function(data){
              			if(data.code==200){
              				alert("重置密码成功,请登录");
              				location.href = "/user/index";
              			}else{
              			    $("#submit").attr("disabled",false);
              				alert("重置链接失效，请重试");
              			}
              		}).execute("#forget-form");
                    
                    
                }
            });
           
        }
        
        
        
        jQuery(document).ready(function() {
           jQuery('.forget-form').show();
            handleForgetPassword();
            
        });
        
        </script>



    </body>

</html>