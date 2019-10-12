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
                                <a href="index.html">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="index.html">系统设置</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">菜单列表</a>
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
                                        <span class="caption-subject bold uppercase"> 广告主报表</span>
                                    </div>
                                    <div class="actions">
                                       
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="table-toolbar">
                                     
                                     <!-- 开始搜索区 -->
                                     <div class="row">
                                            <div class="col-md-4">
                                                <div class="input-group" id="defaultrange">
                                                        <input type="text" class="form-control">
                                                        <span class="input-group-btn">
                                                            <button class="btn default date-range-toggle" type="button">
                                                                <i class="fa fa-calendar"></i>
                                                            </button>
                                                        </span>
                                                    </div>
                                            </div>
                                            <div class="col-md-2">
                                               
                                            </div>
                                            <div class="col-md-6">
                                                
                                            </div>
                      </div>
                      <!-- 结束搜索区 -->              
                      <!-- 开始总计区 -->                 
                      <div class="row" style="padding-top:10px;">
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5" style="padding-left: 15px;">
                            <a class="dashboard-stat dashboard-stat-v2 blue-madison" href="#">
                                <div class="visual">
                                    <i class="fa fa-comments"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="" id="request">${totalData.push}</span>
                                    </div>
                                    <div class="desc">请求数 </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5">
                            <a class="dashboard-stat dashboard-stat-v2 red-pink" href="#">
                                <div class="visual">
                                    <i class="fa fa-bar-chart-o"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="" id="show">${totalData.show}</span> </div>
                                    <div class="desc"> 展示数 </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5">
                            <a class="dashboard-stat dashboard-stat-v2 green-dark" href="#">
                                <div class="visual">
                                    <i class="fa fa-shopping-cart"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="" id="click">${totalData.click}</span>
                                    </div>
                                    <div class="desc"> 点击数 </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5">
                            <a class="dashboard-stat dashboard-stat-v2 yellow-casablanca" href="#">
                                <div class="visual">
                                    <i class="fa fa-cny"></i>
                                </div>
                                <div class="details">
                                    <div class="number">+
                                        <span data-counter="counterup" data-value="" id="clickrate"><fmt:formatNumber value="${(totalData.click / totalData.show) * 100}" pattern="#0.00"/></span>% </div>
                                    <div class="desc"> 点击率 </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5">
                            <a class="dashboard-stat dashboard-stat-v2 blue-dark" href="#">
                                <div class="visual">
                                    <i class="fa fa-cny"></i>
                                </div>
                                <div class="details">
                                    <div class="number"> ¥ 
                                        <span data-counter="counterup" data-value="0" id="money">${totalData.money}</span></div>
                                    <div class="desc"> 消费 </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5" style="padding-right: 15px;">
                            <a class="dashboard-stat dashboard-stat-v2 purple" href="#">
                                <div class="visual">
                                    <i class="fa fa-cny"></i>
                                </div>
                                <div class="details">
                                    <div class="number"> ¥ 
                                        <span data-counter="counterup" data-value="" id=ecpm><fmt:formatNumber value="${totalData.money * 1000 / totalData.show}" pattern="#0.00"/></span></div>
                                    <div class="desc"> ecpm </div>
                                </div>
                            </a>
                        </div>
                    </div>
                  <!-- 结束总计区 -->             
                  <!-- 开始图表区 -->                 
                  <div class="row">
                      <div class="col-md-12 col-sm-12 chart">
                          <!-- BEGIN PORTLET-->
                           <div id="chart_2" class="chart"> </div>
                          <!-- END PORTLET-->
                      </div>
                  </div>
                  <!-- 结束图表区 --> 
         </div>
         <table class="table table-striped table-bordered table-hover dataTable" id="table_page">
             <thead>
                 <tr class="">
                     <th> 账号名称</th>
                     <th> 公司名称 </th>
                     <th> 请求 </th>
                     <th> 展示 </th>
                     <th> 点击 </th>
                     <th> 填充率 </th>
                     <th> 点击率 </th>
                     <th> 消费 </th>
                     <th> 单次点击 </th>
                     <th> 每千次展示 </th>
                 </tr>
             </thead>
             <tbody>
                 <c:forEach items="${page_list.list}" var="adver">
                 <tr class="odd gradeX">
                     <td> ${adver[1].login_name} </td>
                     <td> ${adver[1].company_name} </td>
                     <td> ${adver[0].push} </td>
                     <td> ${adver[0].show} </td>
                     <td> ${adver[0].click} </td>
                     <td> <fmt:formatNumber value="${(adver[0].show / adver[0].push) * 100}" pattern="#0.00"/>% </td>
                     <td> <fmt:formatNumber value="${(adver[0].click / adver[0].show) * 100}" pattern="#0.00"/>% </td>
                     <td> ${adver[0].money} </td>
                     <td> <fmt:formatNumber value="${(adver[0].money / adver[0].click)}" pattern="#0.00"/></td>
                     <td> <fmt:formatNumber value="${(adver[0].money / adver[0].show) * 1000}" pattern="#0.00"/></td>
                 </tr>
                 </c:forEach>
             </tbody>
         </table>
         

         <!-- BEGIN FOOT -->
         <app:page></app:page>
         <!-- END FOOT -->

         
         
         
         
         
         
         
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
        <script src="/pages/js/common/bee-charts.js"></script>  
      <script>

	        
	    var myChart = echarts.init(document.getElementById('chart_2'));
        option = {
        	    title: {
        	        //text: '本月数据'
        	    },
        	    tooltip: {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:['展示数','点击数','点击率','消费'],
        	        selected:{'点击率':false}
        	    },
        	    grid: {
        	        left: '3%',
        	        right: '4%',
        	        bottom: '3%',
        	        containLabel: true
        	    },
        	    toolbox: {
        	        feature: {
        	            saveAsImage: {}
        	        }
        	    },
        	    xAxis: {
        	        type: 'category',
        	        boundaryGap: false,
        	        data: ['03/21','03/22','03/23','03/24','03/25','03/26','03/27','03/28','03/29']
        	    },
        	    yAxis: {
        	        type: 'value'
        	    },
        	    series: [
        	        {
        	            name:'展示数',
        	            type:'line',
        	            stack: '总量',
        	            data:[120, 132, 101, 134, 90, 230, 210,210,210]
        	        },
        	        {
        	            name:'点击数',
        	            type:'line',
        	            stack: '总量',
        	            data:[220, 182, 191, 234, 290, 3, 310,210,210]
        	        },
        	        {
        	            name:'点击率',
        	            type:'line',
        	            stack: '总量',
        	            data:[0.98, 0.56, 0.32, 0.34, 0.45, 0.43, 0.12,0.32,0.56]
        	        },
        	        {
        	            name:'消费',
        	            type:'line',
        	            stack: '总量',
        	            data:[320, 332, 301, 334, 390, 330, 320,210,210]
        	        }
        	        
        	    ]
        	};

	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);

	    </script>
        
        
    </body>

</html>