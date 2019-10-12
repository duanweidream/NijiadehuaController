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
                            <li> <a href="#">首页</a> <i class="fa fa-circle"></i> </li>
                            <li><a href="#">系统设置</a><i class="fa fa-circle"></i> </li>
                            <li><a href="#">日志查询</a></li>
                        </ul>
                       
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title"> </h3>
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
                                        <span class="caption-subject bold uppercase">日志查询</span>
                                    </div>
                                    <div class="tools"> </div>
                                </div>
                                 <!-- END TITLE -->
                                <div class="portlet-body">
                                    <div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
                                       
                                           
                                       <!--开始表头搜索 -->
                                       <form action="" id="search_form" name="search_form">
                                       <input type="hidden" name="beginDate" id="beginDate" value="">
                                       <input type="hidden" name="endDate"  id="endDate" value="">
                                       <div class="row" >
	                                         <div class="col-md-4">
                                                <div class="input-group" id="defaultrange">
                                                        <input type="text" class="form-control">
                                                        <span class="input-group-btn">
                                                            <button class="btn default date-range-toggle" type="button" style="line-height: 14px;">
                                                                <i class="fa fa-calendar"></i>
                                                            </button>
                                                        </span>
                                                    </div>
                                            </div>
	                                           
	                                       <div class="col-md-4">
		                                            类型：<input type="search" name="operateName" class="form-control input-sm input-small input-inline" placeholder="" aria-controls="sample_1">
                                           </div>
	                                            
                                            <div class="col-md-4">
		                                            <div id="sample_1_filter" class="dataTables_filter">
		                                            <button type="button" class="btn btn-success" onclick="BeeReport.query(this);">搜索</button>
		                                            </div>
                                            </div>
                                         </div>
                                       </form>  
                                         <!--结束表头搜索 -->  
                                       <div class="table-scrollable">
                                        <table class="table table-bordered table-striped table-condensed flip-content" id="table_page" >
		                                        <thead>
		                                            <tr>
		                                            <th width="20%"> 时间 </th>
		                                            <th width="8%"> 类型 </th>
		                                            <th width="8%"> 用户 </th>
		                                            <th width="10%"> ip </th>
		                                            <th> 日志 </th>
		                                            </tr>
		                                        </thead>
                                                <tbody>
                                                <tr>
                                                   <td rowspan="3"></td>
                                                </tr>
                                               </tbody>
                                    </table>
                                    </div>
                                    
                                    
                                    <!-- BEGIN FOOT -->
	                                <div class="row" id="table-footer">
	                                    
	                                    
	                                </div>
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
        <script src="/pages/js/common/bee-footer.js"></script>  
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
        
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/moment.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
        
        
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="//static.wooboo.com.cn/theme/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script src="http://echarts.baidu.com/dist/echarts.min.js"></script>   
        <script src="/pages/js/common/date-picker.js"></script>     
        <script src="/pages/js/common/xhr.js"></script>  
        <script src="/pages/js/common/bee-report.js"></script>   
        <!-- END THEME LAYOUT SCRIPTS -->
        <script>
        BeeReport.opt("#search_form","#table_page");
        //报表
        BeeReport.load("/system/log/search/data",{},function(response){
        	
        	//tbody
        	var _html='';
        	if(response.data.page_list.length==0){
        		$("#table_page tbody").html('<tr><td colspan="3" style="text-align:center;color:#ff6666;">未检索到数据...</td></tr>');
        	}else{
        		$.each(response.data.page_list,function(){
            		_html+='<tr class="">'
            		$.each(this,function(){
            			var d=this;
            			_html+='<td>'+d+'</td>';
            		})
            		_html+='</tr>';
            	});
            	$("#table_page tbody").html(_html);
        	}
        	
        	BeeReport.footer(response.data.footer,"table-footer");
        	
        	
        });
        
        
	
        </script>
        
        
        
    </body>

</html>