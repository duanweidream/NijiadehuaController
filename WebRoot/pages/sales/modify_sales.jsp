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

       
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-multi-select/css/multi-select.css" rel="stylesheet" type="text/css" />
		<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
		<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
		
        <link rel="stylesheet" href="/pages/static/css/base.css">
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
                                <a href="/goods/sales/add/to">修改商品</a>
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
                        <div class="col-md-12">
                            <!-- BEGIN VALIDATION STATES-->
                            <div class="portlet light portlet-fit portlet-form bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class=" icon-layers font-green"></i>
                                        <span class="caption-subject font-green sbold uppercase">修改商品</span>
                                    </div>
                                    <!-- 
                                    <div class="actions">
                                        <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                            <i class="icon-cloud-upload"></i>
                                        </a>
                                        <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                            <i class="icon-wrench"></i>
                                        </a>
                                        <a class="btn btn-circle btn-icon-only btn-default" href="javascript:;">
                                            <i class="icon-trash"></i>
                                        </a>
                                    </div>
                                     -->
                                </div>
                                <div class="portlet-body">
                                
                                    <!-- BEGIN FORM-->
                                    <form action="#" class="form-horizontal form-row-seperated" id="form1">
                                       <div class="alert alert-success display-hide"><button class="close" data-close="alert"></button> 表单验证成功！ </div>
                                       <div class="alert alert-danger display-hide"><button class="close" data-close="alert"></button> 表单验证失败，请检查表单数据! </div>
                                        <input type="hidden" id="sales_id" name="sales_id" value="${goodsSalesInfo.sales_id }"/>
                                        <input type="hidden" id="sales_status" name="sales_status" value="${goodsSalesInfo.sales_status }"/>
                                        <input type="hidden" id="creator" name="creator" value="${goodsSalesInfo.creator }"/>
                                        <input type="hidden" id="create_time" name="create_time" value="${goodsSalesInfo.create_time }"/>
                                         
                                        <div class="form-body">
                                            <h3 class="form-section">商品分类</h3>
                                            <!-- BEGIN SORT-->
                                            <div class="form-group">
                                                <label class="control-label col-md-3">商品分类（书法）</label>
                                                <div class="col-md-9">
                                                    <select multiple="multiple" class="multi-select" id="sales_sort" name="sales_sort">
                                                    	<c:forEach items="${salesSort}" var="sales">
															<option value="${sales.id}" <c:if test="${fn:contains(goodsSalesSort,sales.id)}">selected</c:if>>${sales.sortName}</option>
                                                    	</c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <!-- END SORT-->
                                            
                                            <h3 class="form-section">基本信息</h3>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">商品名称：<span class="required"> * </span></label>
                                                <div class="col-md-4">
                                                    <div class="input-icon right">
                                                        <input type="text" id="sales_name" name="sales_name" value="${goodsSalesInfo.sales_name}" class="form-control" /> </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">副标题：<span class="required"> * </span></label>
                                                <div class="col-md-4">
                                                    <div class="input-icon right">
                                                        <input type="text" id="subtitle" name="subtitle" value="${goodsSalesInfo.subtitle}" class="form-control" /> </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
										      <label class="control-label col-md-3">产品<span class="required">*</span></label>
										       <div class="col-md-6">
										           <select class="form-control input-sm select2-multiple" id="product_id" name="product_id">
										                  <option value="">请选择产品...</option>
										                  <app:ProductSelect productId="${goodsSalesInfo.product_id}"/>
										           </select>
										      </div>
										   </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">尺寸规格：<span class="required"> * </span></label>
                                                <div class="col-md-4">
                                                    <div class="input-icon right">
                                                        <input type="text" id="size" name="size" value="${goodsSalesInfo.size}" class="form-control" /> </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">市场价格：<span class="required"> * </span></label>
                                                <div class="col-md-4">
                                                    <div class="input-icon right">
                                                        <input type="number" id="mkt_price" name="mkt_price" value="${goodsSalesInfo.mkt_price }" class="form-control" /> </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-md-3">销售价格：<span class="required"> * </span></label>
                                                <div class="col-md-4">
                                                    <div class="input-icon right">
                                                        <input type="number" id="sales_price" name="sales_price" value="${goodsSalesInfo.sales_price}" class="form-control" /> </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
										      <label class="control-label col-md-3">艺术家<span class="required">*</span></label>
										       <div class="col-md-6">
										           <select class="form-control input-sm select2-multiple" id="artist_id" name="artist_id">
										                  <option value="">请选择艺术家...</option>
										                  <app:ArtistSelect artistId="${goodsSalesInfo.artist_id }"/>
										           </select>
										      </div>
										   </div>
                                            <h3 class="form-section">商品图片</h3>
                                            <!-- 组图 开始 -->
											<div class="form-group group-ad-img" id="picArry">
												<input type="hidden" id="sales_img" name="sales_img" value="">
												<label class="control-label col-md-3">商品图片<span class="required"> * </span></label>
												<div class="col-md-9 portlet light portlet-fit bordered">
													<div class="file-input-box ad-imgs-upload1">
														<div class="file-input-style grey-font text-center" data-provides="fileinput">
															<span class="default-prompt" data-trigger="fileinput">
																<i class="add-icon"></i><br />
																jpg,jpeg,png格式<br />
																< 1024KB
															</span>
														</div>
														<input type="file" class="image-file" name="pictureFile" accept="image/png, image/gif, image/jpg, image/jpeg">
														<input type="hidden" class="file-name" name="ad-imgs-file-name" value="">
													</div>
													<div class="file-input-box ad-imgs-upload2">
														<div class="file-input-style grey-font text-center" data-provides="fileinput">
															<span class="default-prompt" data-trigger="fileinput">
																<i class="add-icon"></i><br />
																jpg,jpeg,png格式<br />
																< 1024KB
															</span>
														</div>
														<input type="file" class="image-file" name="pictureFile" accept="image/png, image/gif, image/jpg, image/jpeg">
														<input type="hidden" class="file-name" name="ad-imgs-file-name" value="">
													</div>
													<div class="file-input-box ad-imgs-upload3">
														<div class="file-input-style grey-font text-center" data-provides="fileinput">
															<span class="default-prompt" data-trigger="fileinput">
																<i class="add-icon"></i><br />
																jpg,jpeg,png格式<br />
																< 1024KB
															</span>
														</div>
														<input type="file" class="image-file" name="pictureFile" accept="image/png, image/gif, image/jpg, image/jpeg">
														<input type="hidden" class="file-name" name="ad-imgs-file-name" value="">
													</div>
												</div>
											</div>
											<!-- 组图 结束 -->
                                            
                                        </div>
                                        
                                        </div>
                                        
                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button type="submit" class="btn green" id="submit">保存</button>
                                                    <button type="button" class="btn default" id="cancel">取消</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <!-- END FORM-->
                                </div>
                            </div>
                            <!-- END VALIDATION STATES-->
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
                                                    <img src="//static.wooboo.com.cn/theme/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
                                                    <span> &nbsp;&nbsp;Loading... </span>
                                                </div>
                                            </div>
               </div>
        </div> -->
        
        
        <div class="modal fade" id="user-modal" role="basic" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <!-- <div class="modal-body">
                                                    <img src="//static.wooboo.com.cn/theme/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
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
        
        
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/moment.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/clockface/js/clockface.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        
        

        <script src="/pages/static/js/layer.js" type="text/javascript"></script>
        
	    <script src="/pages/static/js/upload/js/jquery.ui.widget.js" type="text/javascript"></script>
		<script src="/pages/static/js/upload/js/jquery.iframe-transport.js" type="text/javascript"></script>
		<script src="/pages/static/js/upload/js/jquery.fileupload.js" type="text/javascript"></script>
		<script src="/pages/static/js/validate/jquery.validate.js" type="text/javascript"></script>
		<script src="/pages/static/js/fileinput-preview.js" type="text/javascript"></script>
        
        
        <script src="/pages/js/common/xhr.js"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        
         
    <script>
		jQuery(document).ready(function() {    
	        Form.validate();
	        
	        $("#sales_sort").multiSelect();
	        
			$("#cancel").click(function(){
   				document.location = "/goods/sales/search";
			});
	  	});
        
        (function(){
			
			$(".group-ad-img .ad-imgs-upload1").filePreview({ //组图1
				//"width": 320,
				//"height": 210,
				"close": true,
				"maxSize": 1024,
				"files":'${sales_img[0]}'
			});
			$(".group-ad-img .ad-imgs-upload2").filePreview({ //组图2
				//"width": 320,
				//"height": 210,
				"close": true,
				"maxSize": 1024,
				"files":'${sales_img[1]}'
			});
			$(".group-ad-img .ad-imgs-upload3").filePreview({ //组图3
				//"width": 320,
				//"height": 210,
				"close": true,
				"maxSize": 1024,
				"files":'${sales_img[2]}'
			});
 
		})();
        
        
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
		    				    	sales_sort: {
		    				        	required:true
		    				        },
		    				    	sales_name: {
		    				    		required:true
		    				    	},
		    				    	subtitle:{
		    				    		required:true
		    				    	},
		    				    	product_id:{
		    				    		required:true
		    				    	},
		    				    	size:{
		    				    		required:true
		    				    	},
		    				    	mkt_price:{
		    				    		required:true
		    				    	},
		    				    	sales_price:{
		    				    		required:true
		    				    	},
		    				    	artist_id:{
		    				    		required:true
		    				    	},
		    						sales_img: {
					                	required: function(){
				                			/**自定义校验、赋值start*/
				                			var img_req = false;
				                			var imgUrl = new Array();
											var allChildInput=$('#picArry').find("input[class=file-name]");
											for(var a=0;a<allChildInput.length;a++){
												imgUrl[a] = allChildInput.eq(a).val();
												if(imgUrl[a] == ''){
													img_req = true;
													break;
												}
											}
											if(img_req){
					                			return true;
					                		}else{
					                			var c = imgUrl.join(",");
												$("#sales_img").val(c); 
					                			return false;
					                		}
											/**自定义校验、赋值end*/
					                	}
					                	
					                }
		    				    
		    				    },
		    				    messages: {
		    				    	sales_sort:{
		    				    		required:"请输入商品分类"
		    				    	},
		    				    	sales_name: {
		    				    		required:"请输入商品名称"
		    				    	},
		    				    	subtitle:{
		    				    		required:"请输入副标题"
		    				    	},
		    				    	product_id: {
		    				    		required:"请选择产品"
		    				    	},
		    				    	size: {
		    				    		required:"请输入尺寸规格"
		    				    	},
		    				    	mkt_price: {
		    				    		required:"请输入市场价格"
		    				    	},
		    				    	sales_price: {
		    				    		required:"请输入销售价格"
		    				    	},
		    				    	artist_id: {
		    				    		required:"请选择艺术家"
		    				    	},
		    				    	sales_img:{
		    				    		required:"请按规则上传商品图片"
		    				    	}
		    				    },
		    				    invalidHandler: function(e, t) {
		    		                success1.hide();
		    		                error1.show();
		    		            },
		    		            highlight: function(e) {
		    		                $(e).closest(".form-group").addClass("has-error");
		    		            },
		    		            unhighlight: function(e) {
		    		                $(e).closest(".form-group").removeClass("has-error");
		    		            },
		    		            success: function(e) {
		    		                e.closest(".form-group").removeClass("has-error");
		    		            },
	  	    	                submitHandler: function (form) {
	  	    	                    success1.show();
	  	    	                    error1.hide();
	  	    	                    $("#submit").attr("disabled",1);
	  	    	                    
	  	    	                     Xhr.cfg("/goods/sales/modify/su",{},function(response){
	  	    	                    	if(response.code==200){
	  	    	                    	    success1.hide();
	  		  	    	                    toastr["success"]("已保存当前信息!", "成功");
	  		  	    	                    setTimeout(function(){
												document.location = "/goods/sales/search";
											},3000);
											
	  	    	                    	}else{
	  	    	                    	    success1.hide();
	  		  	    	                    toastr["error"](response.message,"失败");
	  		  	    	                   $("#submit").attr("disabled",false);
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