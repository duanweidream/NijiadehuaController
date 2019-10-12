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
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="//static.wooboo.com.cn/theme/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        
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
                                <a href="#">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">报表中心</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">综合报表</a>
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
                        <p>页面功能描述 </p>
                    </div> -->
                   <!--  -->
                      <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption font-dark">
                                        <i class="icon-settings font-dark"></i>
                                        <span class="caption-subject bold uppercase"> 综合报表</span>
                                    </div>
                                    <div class="actions">
                                       
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="">
                                     
                                     <!-- 开始搜索区 -->
                                     <form action="" id="search_form" name="search_form" class="search_form">
                                     <div class="row">
                                            <!-- <div class="col-md-3">
                                                <label>广告主：</label><input name="user_name" value="" type="text" class="form-control input-inline" placeholder="请输入广告主"/>
                                            </div> -->
                                            <div class="col-md-3">
                                                <label>合同：</label> <input name="plan_name" value="" type="text" class="form-control input-inline" placeholder="请输入合同名称"/>
                                            </div>
                                            <div class="col-md-3">
                                                <label>工单：</label> <input name="group_name" value="" type="text" class="form-control input-inline" placeholder="请输入工单名称"/>
                                            </div>
                                             <div class="col-md-3">
	                                             <select id="select2-single-input-sm" class="form-control input-sm select2-multiple" name="company_id">
	                                                    <option value="">请选择广告主...</option>
	                                                    <c:forEach items="${company_list}" var="company">
	                                                        <option value="${company.id}">${company.company_name}</option>
	                                                    </c:forEach>
	                                            </select>
                                            </div>
                                            
                                            <div class="col-md-3">
                                               <button type="button" onclick="Page.query();" class="btn btn-success pull-right">搜索</button>
                                            </div>
                                            
                                     </div>
                      
                                </form>
                      <!-- 结束搜索区 -->              
                      
                       <!-- 开始总计区 -->                 
                      <div class="row" style="padding-top:10px;">
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 bee-padding4"  style="padding-left:15px;">
                            <a class="dashboard-stat dashboard-stat-v2 blue-madison" href="#">
                                <div class="visual">
                                    <i class="fa fa-comments"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        ¥<span data-counter="counterup" data-value="" id="click_y">0</span>
                                    </div>
                                    <div class="desc">点击消耗（元）</div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 bee-padding4">
                            <a class="dashboard-stat dashboard-stat-v2 red-pink" href="#">
                                <div class="visual">
                                    <i class="fa fa-bar-chart-o"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="" id="flow_y">0</span>M</div>
                                    <div class="desc">流量消耗（M）</div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 bee-padding4">
                            <a class="dashboard-stat dashboard-stat-v2 green-dark" href="#">
                                <div class="visual">
                                    <i class="fa fa-shopping-cart"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                       ¥<span data-counter="counterup" data-value="" id="click_balance">0</span></div>
                                    <div class="desc">点击余额</div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-4 col-xs-12 bee-padding4">
                            <a class="dashboard-stat dashboard-stat-v2 yellow-casablanca" href="#">
                                <div class="visual">
                                    <i class="fa fa-cny"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
									<span data-counter="counterup" data-value="" id="traffic_balance">0</span>M</div>
                                    <div class="desc">流量余额</div>
                                </div>
                            </a>
                        </div>
                    </div>
                  <!-- 结束总计区 -->
                  
                  <div class="row" style="display:none;">
                        <div class="col-md-12">
                            
                            <!-- BEGIN SAMPLE TABLE PORTLET-->
                            <div class="portlet box grey-mint">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class=""></i>图表 </div>
                                    <div class="tools">
                                        <a href="javascript:;" class="collapse"> </a>
                                    </div>
                                </div>
                                <div class="portlet-body flip-scroll">
                                   <div id="chart_2" class="chart"> </div>
                                </div>
                            </div>
                           
                        </div>
                    </div>
                        
                  <!-- 开始图表区 -->
                 
                  <!-- 结束图表区 --> 
         </div>
         <table class="table table-striped table-bordered table-hover dataTable" id="table_page">
             <thead>
                 <tr class="table-head">
                     <th> 工单ID </th>
                     <th> 工单 </th>
                     <th> 所属广告主 </th>
                     <th> 所属合同 </th>
                     <th> 点击消耗（元）</th>
                     <th> 流量消耗（M）</th>
                     <th> 点击余额（元）</th>
                     <th> 流量余额（M）</th>
                 </tr>
             </thead>
             <tbody>
                
             </tbody>
         </table>
         
         
          <div class="row" id="table-footer">
	                                    
	       </div>
         
         
         
           </div>
       </div>
       <!-- END EXAMPLE TABLE PORTLET-->
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
        
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/moment.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/global/scripts/app.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="/pages/js/common/components-select2.js" type="text/javascript"></script>

        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script src="http://echarts.baidu.com/dist/echarts.min.js"></script>   
        <script src="/pages/js/common/date-picker.js"></script>     
        <script src="/pages/js/common/xhr.js"></script>  
        <script src="/pages/js/common/bee-report.js"></script>   
        <script src="/pages/js/common/bee-charts.js"></script>  
      <script>
        
        
      
      
      var Page=(function(){
    	  return {
    		  query:function(){
    			  BeeReport.query();
    		  },
    		  report:function(){
    			  BeeReport.load("/report/calculatesum/data",{},function(response){
    				  console.log(JSON.stringify(response));
    		        	//总计
    		        	BeeReport.total(response.data.total);
    		        	//tbody
    		        	var _html='';
    		        	if(response.data.page_list.length==0){
    		        		$("#table_page tbody").html('<tr><td colspan="10" style="text-align:center;color:#ff6666;">未检索到数据...</td></tr>');
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
        		        	BeeReport.footer(response.data.footer,"table-footer");	    
    		        	}
    		        });
    		  },
    		  init:function(){
    			  ComponentsDateTimePickers.init(); 
    			  BeeReport.setForm(document.forms["search_form"]);
    			  Page.report();
    		  }
    	  }
      })();
      
      Page.init();
      
            
            //报表
	       
		
	    </script>
        
        
    </body>

</html>