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
                                <a>本月数据</a>
                            </li>
                        </ul>
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                       
                    </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
				   <%-- 	
                   <div class="row">
                        <div class="col-md-6 col-sm-6 bee-padding5">
                            <!-- BEGIN PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-bell-o"></i>
                                        <span class="caption-subject font-dark bold uppercase">工作提醒</span>
                                        <!-- <span class="caption-helper">您的工作...</span> -->
                                    </div>
                                    
                                </div>
                                <div class="portlet-body">
                                   <table>
                                   		<tr><td>广告主待审核：</td><td><a href="/advertisers/audit/search/pending?status=8">${companyNotHandledItem}</a></td></tr>
                                   		<tr><td>工单待审核：</td><td><a href="/advertising/audit/search/pending?status=8">${stuffNotHandledItem}</a></td></tr>
                                   		<tr><td>充值待审核：</td><td><a href="/finance/charge/check?status=8">${chargeNotHandledItem}</a></td></tr>
                                   </table>
                                </div>
                            </div>
                            <!-- END PORTLET-->
                        </div>
                        <div class="col-md-6 col-sm-6 bee-padding5">
                            <!-- BEGIN PORTLET-->
                            <div class="portlet light bordered ">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="fa fa-bullhorn"></i>
                                        <span class="caption-subject font-dark bold uppercase">公告栏</span>
                                        <!-- <span class="caption-helper">公告信息</span> -->
                                    </div>
                                    
                                </div>
                                <div class="portlet-body" style="min-height: 65px;">
                                  <table>
                                    <c:forEach items="${noticeList}" var="notice" varStatus="v">
                                        <c:if test="${v.index < 3 }">
	                                   		<tr><td><a href="/notice/pre/view?id=${notice.id}">${notice.title}</a></td></tr>
                                        </c:if>
                                    </c:forEach>
                                   </table>
                                </div>
                            </div>
                            <!-- END PORTLET-->
                        </div>
                    </div>
                   --%>
                   
                   
                   
                   
                    <div class="row">
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5">
                            <a class="dashboard-stat dashboard-stat-v2 blue-madison" href="#">
                                <div class="visual">
                                    <i class="fa fa-comments"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="0">0</span>
                                    </div>
                                    <div class="desc"> 本月新增订单 </div>
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
                                        <span data-counter="counterup" data-value="0">0</span> </div>
                                    <div class="desc"> 本月支付订单 </div>
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
                                        <span data-counter="counterup" data-value="0">0</span>
                                    </div>
                                    <div class="desc"> 本月成交率 </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5">
                            <a class="dashboard-stat dashboard-stat-v2 yellow-casablanca" href="#">
                                <div class="visual">
                                    <i class="fa fa-cny"></i>
                                </div>
                                <div class="details">
                                    <div class="number"> 
                                        ¥<span data-counter="counterup" data-value="0"></span> </div>
                                    <div class="desc"> 本月成交金额 </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5">
                            <a class="dashboard-stat dashboard-stat-v2 blue-dark" href="#">
                                <div class="visual">
                                    <i class="fa fa-exchange"></i>
                                </div>
                                <div class="details">
                                    <div class="number"> 
                                        <span data-counter="counterup" data-value="0"></span> </div>
                                    <div class="desc"> 本月取消订单 </div>
                                </div>
                            </a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-6 col-xs-12 bee-padding5">
                            <a class="dashboard-stat dashboard-stat-v2 purple" href="#">
                                <div class="visual">
                                    <i class="fa fa-line-chart"></i>
                                </div>
                                <div class="details">
                                    <div class="number"> 
                                        ¥<span data-counter="counterup" data-value="0"></span> </div>
                                    <div class="desc"> 本月取消金额 </div>
                                </div>
                            </a>
                        </div>
                    </div>
                     
                     
                     
                   
                   <div class="row">
                        <div class="col-md-12 col-sm-12 chart">
                            <!-- BEGIN PORTLET-->
                             <div id="chart_2" class="chart"> </div>
                            <!-- END PORTLET-->
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
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
        <script src="//static.wooboo.com.cn/theme/assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
        
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
      
        
<!--    <script src="//echarts.baidu.com/dist/echarts.min.js"></script>
 -->
        <script src="//static.wooboo.com.cn/theme/echarts.min.js" type="text/javascript"></script>
        <script src="/pages/js/common/xhr.js"></script>  
        <script src="/pages/js/common/bee-charts.js"></script>  
        <script src="/pages/js/jquery.cookie.js"></script>  
        
        <script>
        
        
        var Page=(function(){
  	    	return {
  	    		reportChart:function(){
  	    			App.blockUI({ boxed: true, textOnly:true, message:"加载中..."  });
  	    			
  	    			
  	    			 Xhr.cfg("/report/dashboard/data",{},function(response){
  	    				App.unblockUI();
  	    				 if(response.code==200){
                              
	  	          			  var option= response.data;
                              BeeCharts.init('chart_2');
                              BeeCharts.setOption(option);
	  	          		}
	  	          	}).execute(); 
  	    		}
  	    	}
  	    })();
        
        Page.reportChart();
        
        
        
		
	    </script>
        
        
        
    </body>

</html>