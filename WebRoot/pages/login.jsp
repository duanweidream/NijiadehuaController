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
         <title>你家的画营支撑系统 <%=Config.getValue("version")%></title>
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
             
             #slider {
				  height: 36px;
				  position: relative;
				  border-radius: 2px;
				  background-color: #dae2d0;
				  overflow: hidden;
				  text-align: center;
				  user-select: none;
				  -moz-user-select: none;
				  -webkit-user-select: none;
				}
				
				#slider_bg {
				  position: absolute;
				  left: 0;
				  top: 0;
				  height: 100%;
				  background-color: #44B6AE;
				  z-index: 1;
				}
				
				#label {
				  width: 46px;
				  position: absolute;
				  left: 0;
				  top: 0;
				  height: 36px;
				  line-height: 36px;
				  border: 1px solid #cccccc;
				
				  z-index: 3;
				  cursor: move;
				  color: #ff9e77;
				  font-size: 16px;
				  font-weight: 900;
				}
				
				#labelTip {
				  position: absolute;
				  left: 0;
				  width: 100%;
				  height: 100%;
				  font-size: 12px;
				  font-family: 'Microsoft Yahei', serif;
				  color: #787878;
				  line-height: 38px;
				  text-align: center;
				  z-index: 2;
				}
             
             
             .handler_bg{
                  background: #fff url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZDhlNWY5My05NmI0LTRlNWQtOGFjYi03ZTY4OGYyMTU2ZTYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NTEyNTVEMURGMkVFMTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NTEyNTVEMUNGMkVFMTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo2MTc5NzNmZS02OTQxLTQyOTYtYTIwNi02NDI2YTNkOWU5YmUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NGQ4ZTVmOTMtOTZiNC00ZTVkLThhY2ItN2U2ODhmMjE1NmU2Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+YiRG4AAAALFJREFUeNpi/P//PwMlgImBQkA9A+bOnfsIiBOxKcInh+yCaCDuByoswaIOpxwjciACFegBqZ1AvBSIS5OTk/8TkmNEjwWgQiUgtQuIjwAxUF3yX3xyGIEIFLwHpKyAWB+I1xGSwxULIGf9A7mQkBwTlhBXAFLHgPgqEAcTkmNCU6AL9d8WII4HOvk3ITkWJAXWUMlOoGQHmsE45ViQ2KuBuASoYC4Wf+OUYxz6mQkgwAAN9mIrUReCXgAAAABJRU5ErkJggg==") no-repeat center;
             }
             
             .handler_ok{
				  background: #fff url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3hpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDIxIDc5LjE1NTc3MiwgMjAxNC8wMS8xMy0xOTo0NDowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDo0ZDhlNWY5My05NmI0LTRlNWQtOGFjYi03ZTY4OGYyMTU2ZTYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NDlBRDI3NjVGMkQ2MTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NDlBRDI3NjRGMkQ2MTFFNEI5NDBCMjQ2M0ExMDQ1OUYiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDphNWEzMWNhMC1hYmViLTQxNWEtYTEwZS04Y2U5NzRlN2Q4YTEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NGQ4ZTVmOTMtOTZiNC00ZTVkLThhY2ItN2U2ODhmMjE1NmU2Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+k+sHwwAAASZJREFUeNpi/P//PwMyKD8uZw+kUoDYEYgloMIvgHg/EM/ptHx0EFk9I8wAoEZ+IDUPiIMY8IN1QJwENOgj3ACo5gNAbMBAHLgAxA4gQ5igAnNJ0MwAVTsX7IKyY7L2UNuJAf+AmAmJ78AEDTBiwGYg5gbifCSxFCZoaBMCy4A4GOjnH0D6DpK4IxNSVIHAfSDOAeLraJrjgJp/AwPbHMhejiQnwYRmUzNQ4VQgDQqXK0ia/0I17wJiPmQNTNBEAgMlQIWiQA2vgWw7QppBekGxsAjIiEUSBNnsBDWEAY9mEFgMMgBk00E0iZtA7AHEctDQ58MRuA6wlLgGFMoMpIG1QFeGwAIxGZo8GUhIysmwQGSAZgwHaEZhICIzOaBkJkqyM0CAAQDGx279Jf50AAAAAABJRU5ErkJggg==") no-repeat center;
			 }
             
             
             
        </style>
        </head>
    <!-- END HEAD -->

    <body class=" login">
        <!-- BEGIN LOGO -->
        <div class="logo" style="visibility: ">
            <a href="index.html">
                
                </a>
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
             <form class="login-form" action="#" method="post">
                <h3 class="form-title">登录你家的画运营支撑系统</h3>
                <div class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span> 请检验后登陆！ </span>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">用户名</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                         <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名..." name="username" id="username" /> </div>
                </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码..." name="password" id="password" /> </div>
                </div>
                
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9"></label>
                    <div class="input-icon">
							  <div id="slider">
							    <div id="slider_bg"></div>
							    <span id="label" class="handler_bg"></span> <span id="labelTip">按下拖动滑块验证</span> 
							    
							    </div>
                        
                    </div>
                </div>
                
			<div class="form-group">
				<div  class="input-icon" style="float:left;border-left: 0px solid #FFFFFF!important;">
				<input name="already" id="already" type="checkbox" checked="checked" class="mr10 mt0 placeholder-no-fix" autocomplete="off" />我已阅读并接受
				</div>
				<a href="/pages/loginClause.jsp" target="_blank" style="float:right;">
					<span class="blue_3094d1">安全规则</span>
				</a>
			</div>
                
                <div class="form-actions">
                    <label class="rememberme mt-checkbox mt-checkbox-outline">
                        <input type="checkbox" id="remember" name="remember" value="1" /> 记住我
                        <span></span>
                    </label>
                    <button  type="submit" disabled  class="btn green pull-right" id="login-button"> 登录 </button>
                </div>
                
                <div class="forget-password">

                    <h4>忘记密码 ?</h4>
                    <p>联系系统管理员重置您的密码,或者点击<a href="javascript:;" id="forget-password"> 这里 </a> 重置您的密码.</p>
                </div>
                <div class="create-account">
                    <p> 还没有账号 ?&nbsp;
                        联系系统管理员给您开通个账号.
                    </p>
                </div>
            </form>
            
            
            <form class="forget-form" method="post" action="#" id="forget-form">
                <h3>忘记密码 ?</h3>
                <p> 请输入你的用户名，您的用户邮件将会收到重置密码邮件. </p>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名..." name="userName" id="userName"/> </div>
                </div>
                <div class="form-actions">
                    <button type="button" id="back-btn" class="btn grey-salsa btn-outline"> 回退 </button>
                    <button id="forget_submit" type="submit" class="btn green pull-right"> 提交 </button>
                </div>
            </form>
            
            <!-- END LOGIN FORM -->
            <!-- BEGIN REGISTRATION FORM -->
            
            <!-- END REGISTRATION FORM -->
        </div>
         <div class="copyright" style="color:#fff;"> 2019 &copy; Nijiadehua Version <%=Config.getValue("version")%> </div>
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
        <script src="/pages/js/common/slideunlock.js"></script> 
        <script src="/pages/js/crypto/crypto-js.js"></script>
        <script src="/pages/js/common/bee-crypto.js"></script>
        


 <script>

 var Login = function() {
	       var slider_val="";
	 
            var e = function() {
                $(".login-form").validate({
                    errorElement: "span",
                    errorClass: "help-block",
                    focusInvalid: false,
                    rules: {
                        username: {
                            required:true
                        },
                        password: {
                            required: true
                        },
                        already: {
                        	required:true
                        },
                        remember: {
                            required:false
                        }
                    },
                    messages: {
                        username: {
                            required: "请输入用户名."
                        },
                        password: {
                            required: "请输入密码."
                        },
                        already: {
                        	required: "请阅读安全规则"
                        }
                    },
                    invalidHandler: function(e, r) {
                        //console.log(e);
                        //console.log(r);
                    	/* $(".alert-danger", $(".login-form")).show() */
                    },
                    highlight: function(e) {
                        $(e).closest(".form-group").addClass("has-error")
                    },
                    success: function(e) {
                        e.closest(".form-group").removeClass("has-error"),
                        e.remove()
                    },
                    errorPlacement: function(e, r) {
                        e.insertAfter(r.closest(".input-icon"))
                    },
                    submitHandler: function(e) {
                        Xhr.cfg("/login",{userName:BeeCrypto.encrypt($('#username').val()),password:BeeCrypto.encrypt($('#password').val()),remember:$("#remember").is(':checked')?"1":"0",slider:slider_val},function(data){

                    		if(data.code==200){
                				location.href = "/user/index";
                			}else{
                				 $(".alert-danger span").html(data.message);
                				 $(".alert-danger", $(".login-form")).show()
                			}
                		}).execute();
                    	// e.submit()
                    }
                }),
                $(".login-form input").keypress(function(e) {
                    return 13 == e.which ? ($(".login-form").validate().form() && $(".login-form").submit(), !1) : void 0
                })
            }
            
            return {
                init: function() {
                	e();
                	
                	var slider = new SliderUnlock("#slider",{
						successLabelTip : "欢迎使用你家的画运营支撑系统"	
					},function(){
						
						Xhr.cfg("/login/slider",{},function(response){
                			if(response.code==200){
                				$("#label").removeClass("handler_bg");
        						$("#label").addClass("handler_ok");
        						$("#login-button").attr("disabled",false);
        						slider_val=response.data.token;
                			}
                		}).execute();
						
						
		     	    });
		            slider.init();
                	
                	
                }
            }
        } ();
        
        
        
        
        var handleForgetPassword = function() {
            $('.forget-form').validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    userName: {
                        required: true
                    }
                },

                messages: {
                    userName: {
                        required: "请输入您的用户名"
                    }
                },

                invalidHandler: function(event, validator) { //display error alert on form submit   

                },

                highlight: function(element) { // hightlight error inputs
                    $(element)
                        .closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                success: function(label) {
                    label.closest('.form-group').removeClass('has-error');
                    label.remove();
                },

                errorPlacement: function(error, element) {
                    error.insertAfter(element.closest('.input-icon'));
                },

                submitHandler: function(form) {
                 	 
                 	 $("#forget_submit").attr("disabled",1);
                 	 
                     Xhr.cfg("/forget/send/email",{userName:$('#userName').val()},function(data){
                        
                        
              			if(data.code==200){
              				alert("邮件已发送，请在邮件中点击“重置密码”");
              			}else{
	                        $("#forget_submit").attr("disabled",false);
              				alert("用户名错误");
              			}
              		}).execute("#forget-form");
                    
                    
                }
            });
           

            jQuery('#forget-password').click(function() {
                jQuery('.login-form').hide();
                jQuery('.forget-form').show();
            });

            jQuery('#back-btn').click(function() {
                jQuery('.login-form').show();
                jQuery('.forget-form').hide();
            });

        }
        
        
        
        jQuery(document).ready(function() {
            Login.init();
            handleForgetPassword();
        });
        
       
        </script>



    </body>

</html>