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
                                <a>首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a>系统设置</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a>系统菜单</a>
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
                    
                   <%-- 
                    <div class="row">
                       <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                         	<button id="modal_ajax_demo_btn" class="btn green" onclick="javascript:BeeModel.openModel('/system/menu/to');">添加主菜单 <i class="icon-plus"></i></button>
                       </div>
                    </div>
                    <div class="clearfix"> </div>
                   --%>
                   
                   <div class="row">
		                   <c:forEach items="${clist}" var="c">
					        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					        <h3 style="margin:10px 0;clear:both;"><span class="shi" onclick="cg('${c.id}_div',this)">&nbsp;&nbsp;</span>${c.menuName} <a href="javascript:void(0);" onclick="javascript:BeeModel.openModel('/system/menu/to?menuId=${c.id}');"   style="width:80px;font-weight:normal;font-size:12px;">编辑</a> <a href="javascript:void(0);" onclick="javascript:BeeModel.openModel('/system/menu/to?pid=${c.id}');"   style="width:80px;font-weight:normal;font-size:12px;">添加子菜单</a> <span style="color:#ff6666;font-weight:normal;font-size:12px;">${c.menuSeq}</span></h3>
					        <dl style="padding-left:0px;display:none;" id="${c.id}_div">
						        <c:forEach items="${c.list}" var="m">
						                <dt style="margin:0;clear:both;padding:0;"><span class="yi">&nbsp;&nbsp;</span><span class="shi" onclick="bcg('dd',${m.id},this)">&nbsp;&nbsp;</span>${m.menuName} <span style="color:#ff6666;">${son.menuSeq}</span>
						                &nbsp;<a href="javascript:void(0);" onclick="javascript:BeeModel.openModel('/system/menu/to?menuId=${m.id}');"   style="width:80px;">编辑</a>
						                &nbsp;<a href="javascript:void(0);" onclick="javascript:BeeModel.openModel('/system/menu/to?pid=${m.id}');"   style="width:80px;">添加子菜单</a></dt>
						                
						                <c:forEach items="${m.list}" var="son">
						                       <dd parent="${m.id}" style="margin:0;clear:both;padding:0; display:none; text-indent:0px;"><span class="yi"></span><span class="yi"></span> <c:choose><c:when test="${fn:length(son.list)>0}"><span class="shi" onclick="bcg('dd',${son.id},this);"></span></c:when><c:otherwise><span class="yi">&nbsp;&nbsp;</span></c:otherwise></c:choose>${son.menuName}(${son.menuHref}) <span style="color:#ff6666;">${son.menuSeq}</span>
						                       &nbsp;<a href="javascript:void(0);" onclick="javascript:BeeModel.openModel('/system/menu/to?menuId=${son.id}');"   style="width:80px;">编辑</a>
						                       </dd> 
						                </c:forEach>
						        </c:forEach>
					        </dl>
					        </div>
					    </c:forEach>
                   
                   
                   </div>
                   
                   
                   
                   
                   
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
        
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script src="/pages/js/common/bee-model.js"></script>  
        
        <script type="text/javascript" src="/scripts/util.js"></script>    
	    <script src="/pages/js/common/xhr.js"></script>  
        <script>
		jQuery(document).ready(function() {    
		  App.init();
		  BeeModel.init();
		});

		function cg(id,th){
		     var o = util.$(id);
		     if(o){
		       o.style.display=o.style.display=='none'?'':'none';
		       th.className=o.style.display=='none'?'shi':'shiyi';
		     }
		   }
		   function bcg(tag,id,th){
		      var r = document.getElementsByTagName(tag);
		      var nr = filterpid(r,id);
		      util.each(nr,function(e,i){
		         e.style.display=e.style.display=='none'?'':'none';
		         (i==0)&&(th.className==e.style.display=='none'?'shi':'shiyi');
		      })
		   }
		   function filterpid(r,id){
		      var nr=[];
		      util.each(r,function(e){
		         if(e.getAttribute('parent')==id){
		            nr.push(e);
		         }
		      });
		      return nr;
		   }
		  function ca(id,th){
		     var r = filterCk(document.getElementsByName("ids"),id);
		     util.each(r,function(e){
		       e.checked=th.checked;
		     });
		  }
		  function ac(id){
		    var r = filterCk(document.getElementsByName("ids"),id);
		    var b = false;
		    util.each(r,function(e){
		        if(e.checked){
		          b=true;
		          return 1;
		        }
		    });
		    var t = util.$(id+'_ck');
		    t&&(t.checked=b);
		  }
		  function filterCk(list,id){
		    var r=[];
		    util.each(list,function(e){
		      (e.getAttribute('pid')==id)&&r.push(e);
		    });
		    return r;
		  }
		  function ck_check(){
		      
		  }
		  
		  
		  //页面模块
		  var Page=(function(){
		    	return {
		    		validate:function(){
		    			
		    			var form1 = $('#form1');
		                var error1 = $('.alert-error', form1);
		                var success1 = $('.alert-success', form1);
		    			
		                form1.validate({
		    				 errorElement: 'span',
		    				 errorClass: "help-block help-block-error", // default input error message class
		    	             focusInvalid: false, // do not focus the last invalid input
		    	             ignore: "",
		    				    rules: {
		    				    	menuName: "required",
		    				    	menuHref: "required"
		    				      
		    				    },
		    				    messages: {
		    				    	menuName: "请输入菜单名",
		    				    	menuHref: "请输入菜单链接"
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
		    	                    Xhr.cfg("/system/menu/su",{},function(response){
		    	                    	if(response.code==200){
		    	                    		BeeModel.closeModel();
		    	                    		window.location.reload();
		    	                    	}else{
		    	                    		success1.hide();
		    	                    		$('.alert-error').html(response.message);
		    	                    		$('.alert-error').show();
		    	                    		$("#user_submit").removeClass("disabled");
		    	                    	}
		    	                    	
		    	                    }).execute_form("#form1");
		    	                    
		    	                    
		    	                }
		    				    
		    				});
		    		},
		    		submitform:function(){
		    			 $('#form1').submit();
		    		}
		    	}
		    })();
		  
		  
		  
		  
		  var HOME = (function(){
			     return {
			        hide:function(url){
			          Lay.hide();
			          location.reload();
			        }
			     }
			  })();
		
		
	</script>
        
        
        
    </body>

</html>