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
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
	    <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
	    
       <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
	   <link href="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />
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
                                <a href="javascript:void(0);">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="javascript:void(0);">报表中心</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="javascript:void(0);">流量领取记录</a>
                            </li>
                        </ul>
                       
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                       
                    </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    
                    
                   
                   
                   
                   <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
                                <!-- BEGIN TITLE -->
                                <div class="portlet-title">
                                    <div class="caption font-dark">
                                        <i class="icon-list font-dark"></i>
                                        <span id="history_ym" class="caption-subject bold uppercase">流量领取记录</span>
                                    </div>
                                    <div class="tools"> 
	                                    <div class="btn-group pull-right">
			                                <button class="btn green  btn-outline dropdown-toggle" data-toggle="dropdown">工具
			                                    <i class="fa fa-angle-down"></i>
			                                </button>
			                                <ul class="dropdown-menu pull-right">
			                                    <li>
			                                        <a href="javascript:exportExcel();"><i class="fa fa-print"></i> 打包下载 [最大5万条]</a>
			                                    </li>
			                                </ul>
			                            </div>
                                    </div>
                                </div>
                                 <!-- END TITLE -->
                                <div class="portlet-body">
                                    <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                                       <!--开始表头搜索 -->  
                                       <form action="/report/phone/gift/history" method="post">
                                       <div class="row">
	                                            <div class="col-md-12">
	                                            <label>时间：
		                                            <%-- <select class="form-control input-small input-inline" style="width: 96px!important;" name="yearAndMon" id="position" citycode="${yearAndMon}">
													</select> --%>
													<input id="bDate" name="bDate" value="${bDate}" type="text" style="width:100px;border:#999 1px solid !important;height:24px !important;background:#fff url(../../images/datePicker.gif) no-repeat left !important;" /> 
														<span>-</span>
										            	<input id="eDate" name="eDate" value="${eDate}" type="text" style="width:100px;border:#999 1px solid !important;height:24px !important;background:#fff url(../../images/datePicker.gif) no-repeat left !important;" />
		                                            </label>
	                                            
		                                            <label>手机：<input type="text" id="phone_number" name="phone_number" class="form-control  input-small input-inline" placeholder="手机号..." value="${phone_number}" /></label>
                                                    <label>状态：
	                                                    <select id="status" name="status" class="form-control input-small input-inline" style="width: 96px!important;">
	                                                       <option value="">请选择...</option>
	                                                       <option value="normal"  <c:if test="${status=='normal'}"> selected="selected" </c:if>>初始</option>
	                                                       <option value="fail"  <c:if test="${status=='fail'}"> selected="selected" </c:if>>失败</option>
	                                                       <option value="success"  <c:if test="${status=='success'}"> selected="selected" </c:if>>成功</option>
	                                                    </select>
                                                    </label>
                                                    <label>合同：<input type="text" id="plan_name" name="plan_name" class="form-control  input-small input-inline" placeholder="" value="${plan_name}" /></label>
                                                    <label>素材：<input type="text" id="idea_name" name="idea_name" class="form-control  input-small input-inline" placeholder="" value="${idea_name}" /></label>
		                                            
		                                            <div id="sample_1_filter" class="dataTables_filter">
		                                            <button type="submit" class="btn btn-success">搜索</button>
		                                            </div>
                                            </div>
                                         </div>
                                         </form>
                                         <!--结束表头搜索 -->  
                                           <!--  * 0:时间 1：流水 2：电话 3：计划id 4:名称 5:流量  6：金额  7：状态 -->
                                            
                                       <div class="table-scrollable">
                                            
                                        <table class="table table-striped table-bordered table-hover dataTable dtr-inline" id="sample_1" role="grid" aria-describedby="sample_1_info" style="width: 917px;">
		                                        <thead>
		                                            <tr class="table-head">
		                                            <th> 合同(素材) </th>
		                                            <th> 时间 </th>
		                                            <th> 手机 </th>
		                                            <th> 归属地市 </th>
		                                            <th> 流量(M) </th>
		                                            <th> 价格(元) </th>
		                                            <th> 状态 </th>
		                                            </tr>
		                                        </thead>
                                                <tbody>
                                             
                                             <c:forEach items="${page_list.list }" var="data">
                                                   <tr  class="even">
		                                              <td> ${data[4]}(${data[8]}) </td>
		                                              <td><fmt:formatDate value="${data[0]}" pattern="yyyy-MM-dd HH:mm:ss"/>  </td>
		                                              <td> ${data[2]} </td>
		                                              <td> ${data[10]} </td>
		                                              <td> ${data[5]} </td> 
		                                              <td><fmt:formatNumber value="${data[6]}" pattern="#0.00"></fmt:formatNumber>    </td> 
		                                              <td> 
		                                              
		                                              <c:choose>
		                                                <c:when test="${data[7]=='normal'}"><span class="label label-default">初始</span></c:when>
		                                                <c:when test="${data[7]=='fail'}"> <span class="label label-warning">失败</span></c:when>
		                                                <c:when test="${data[7]=='success'}"><span class="label label-success">成功</span></c:when>
		                                              </c:choose>
		                                              
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
                           
                           
                           
                           <div class="note note-info">
                        <p style="color:#999;"> 流量领取记录：查看广告合同的广告领取情况。<br/>
                            初始：领取初始化状态，未收到流量汇结果通知。<br/>
                            失败：流量领取失败。<br/>
                            成功：流量领取成功，从流量汇账户中扣除相应的金额。<br/>
                         </p>
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
        <script type="text/javascript" src="/js/laydate/laydate.js"></script>
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
        <script src="//static.wooboo.com.cn/theme/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
      <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="/pages/js/common/bee-extended-model.js"></script>  
        
        <script type="text/javascript" src="/scripts/util.js"></script>    
	    <script src="/pages/js/common/xhr.js"></script>  
        
        
        <!-- END THEME LAYOUT SCRIPTS -->

        <script>
        $(document).ready(function(){
        	var start = {
                    elem: '#bDate',
                    format: 'YYYY-MM-DD hh',
                    istime: true,
                    istoday: false,
                    choose: function (datas) {
                        end.min = datas; //开始日选好后，重置结束日的最小日期
                        //end.start = datas //将结束日的初始值设定为开始日
                        var enddate = $("#eDate").val();
                        var bedate = datas.split("-")[1];
                        if (enddate && (enddate.split("-")[1] != bedate)) {
                        	$("#bDate").val("");
                        	alert("选择日期不能跨月");
                        }
                    }
                };
                var end = {
                    elem: '#eDate',
                    format: 'YYYY-MM-DD hh',
                    istime: true, //是否开启时间选择
                    istoday: false,
                    isclear: true, //是否显示清空
                    issure: true, //是否显示确认
                    choose: function (datas) {
                        start.max = datas; //结束日选好后，重置开始日的最大日期
                        var bedate = $("#bDate").val();
                        var enddate = datas.split("-")[1];
                        if (bedate && (enddate != bedate.split("-")[1])) {
                        	$("#eDate").val("");
                        	alert("选择日期不能跨月");
                        }
                    }
                };
                laydate(start);
                laydate(end);
        	//loadUserRole();
        });
        function loadUserRole() {
        	$.ajax({
        		type : "POST",
        		url : "/report/phone/gift/historyList",
        		//data : "roleName=" + value,
        		dataType:"json",
        		async : false,
        		success : function(data) {
        			//console.log(JSON.stringify(data));
        			for (var i in data) {
        				var cityData = data[i];
        				
        				document.getElementById("position").options.add(new Option(cityData[1], cityData[0]));
        			}
        			var citycode = $("#position").attr("citycode");
        			$("#position").find("option[value='" + citycode + "']").attr("selected",true);
        			
        			var txt = $("#position").find("option:selected").text();
        			if (txt)
        				$("#history_ym").html("流量领取【" + txt + "】记录");
        		}
        	});
        }
        
        var Page=(function(){
	    	return {
	    		validate:function(){
	    			
	    		},
	    		check:function(s,id){
	    			$("#status").val(s);
	    			if(confirm("确定操作?")){
	    				$("#success_button").attr("disabled",true);
	    				$("#fail_button").attr("disabled",true);
	    				Xhr.cfg("/finance/charge/update",{},function(response){
	                    	if(response.code==200){
	                    		 toastr.options = {
	       	    					  "closeButton": true,
	       	    					  "debug": true,
	       	    					  "positionClass": "toast-bottom-full-width",
	       	    					  "onclick": null,
	       	    					  "showDuration": "1000",
	       	    					  "hideDuration": "1000",
	       	    					  "timeOut": "3000",
	       	    					  "extendedTimeOut": "1000",
	       	    					  "showEasing": "swing",
	       	    					  "hideEasing": "linear",
	       	    					  "showMethod": "fadeIn",
	       	    					  "hideMethod": "fadeOut",
	       	    					  onHidden: function () {
	       	    						  BeeModel.closeModel();
	       	    						  window.location.reload();
	       	    					  }
	       	    					}
	       	    			       toastr['info']('充值审核通过，订单金额已经增加到充值账户中。充值状态变更为 [充值完成]。', '充值审核完成');
	                    	}else{
	                    		toastr.options = {
		       	    					  "closeButton": true,
		       	    					  "debug": true,
		       	    					  "positionClass": "toast-bottom-full-width",
		       	    					  "onclick": null,
		       	    					  "showDuration": "1000",
		       	    					  "hideDuration": "1000",
		       	    					  "timeOut": "3000",
		       	    					  "extendedTimeOut": "1000",
		       	    					  "showEasing": "swing",
		       	    					  "hideEasing": "linear",
		       	    					  "showMethod": "fadeIn",
		       	    					  "hideMethod": "fadeOut",
		       	    					  onHidden: function () {
		       	    						  BeeModel.closeModel();
		       	    						  window.location.reload();
		       	    					  }
		       	    					}
	                    		   toastr["error"](response.message,"充值审核失败!");
	                    	}
	                    	
	                    }).execute_form("#form1");
	    			}
	    		}
	    	}
	    })();
        
        
	
       
	   function exportExcel(){
	        document.location = "/report/export/gift/history?sequence=&phone_number="+$("#phone_number").val()+"&status="+$("#status").val()+"&plan_name="+encodeURIComponent(encodeURIComponent($("#plan_name").val()))+"&idea_name="+encodeURIComponent(encodeURIComponent($("#idea_name").val()))+"&bDate="+$('#bDate').val()+"&eDate="+$('#eDate').val();
		} 
		
        </script>
    </body>

</html>