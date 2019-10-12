<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="app" uri="/application" %>
<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        
<div class="modal-header" id="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">入库详情</h3>
  </div>
  <div class="modal-body">
  
  
  <form action="#" id="user_form" class="form-horizontal" novalidate="novalidate">
  <div class="alert alert-error hide">表单填写有误，请查看表单信息</div>
  <div class="alert alert-success hide">表单验证成功!</div>
 <div>
   
   
   <div class="form-group">
     
   <div class="form-group">
      <label class="control-label col-md-3">产品:</label>
       <div class="col-md-6">
          <label class="control-label">${goodsStockInbound.productInfo.product_name}</label>
      </div>
   </div>
    <div class="form-group">
     <label class="control-label col-md-3">入库类型:</label>
          <div class="col-md-6">
          <label class="control-label">
	          <c:if test="${goodsStockInbound.in_type == 1}">初始入库</c:if>
	          <c:if test="${goodsStockInbound.in_type == 2}">补货入库</c:if>
	      </label>
		 </div>
     </div>
    <div class="form-group">
     <label class="control-label col-md-3">入库数量:</label>
          <div class="col-md-6">
          	<label class="control-label">${goodsStockInbound.in_stock}</label>
		 </div>
     </div>
     <div class="form-group">
     <label class="control-label col-md-3">入库状态:</label>
          <div class="col-md-6">
          	<label class="control-label">
          	<c:if test="${goodsStockInbound.valid == 1}">有效</c:if>
	        <c:if test="${goodsStockInbound.valid == 0}">无效</c:if>
	        </label>
		 </div>
     </div>
     <div class="form-group">
     <label class="control-label col-md-3">入库时间:</label>
          <div class="col-md-6">
          	<label class="control-label"><fmt:formatDate value="${goodsStockInbound.create_time}" pattern="yyyy-MM-dd HH:mm"/></label>
		 </div>
     </div>
     <div class="form-group">
     <label class="control-label col-md-3">入库操作人:</label>
          <div class="col-md-6">
          	<label class="control-label"><app:sysuser_name uid="${goodsStockInbound.creator}"/></label>
		 </div>
     </div>
     <div class="form-group">
       <label class="control-label col-md-3">备注:</label>
       <div class="col-md-6">
           <label class="control-label">${goodsStockInbound.remark }</label>
       </div>
    </div>
</div>
</form>
  
        
  
  </div>
  <div class="modal-footer yuan">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
</div>

<script src="//static.wooboo.com.cn/theme/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="/pages/js/common/components-select2.js" type="text/javascript"></script>