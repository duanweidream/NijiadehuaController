<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="app" uri="/application" %>
<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="//static.wooboo.com.cn/theme/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        
<div class="modal-header" id="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">添加入库</h3>
  </div>
  <div class="modal-body">
  
  
  <form action="#" id="user_form" class="form-horizontal" novalidate="novalidate">
  <div class="alert alert-error hide">表单填写有误，请查看表单信息</div>
  <div class="alert alert-success hide">表单验证成功!</div>
 <div>
   
   
   <div class="form-group">
     
   <div class="form-group">
      <label class="control-label col-md-3">产品<span class="required">*</span></label>
       <div class="col-md-6">
           <select class="form-control input-sm select2-multiple" id="product_id" name="product_id">
                  <option value="">请选择产品...</option>
                  <app:ProductSelect/>
           </select>
      </div>
   </div>
    <div class="form-group">
     <label class="control-label col-md-3">入库类型<span class="required">*</span></label>
          <div class="col-md-6">
          <select class="form-control input-sm select2-multiple" id="in_type" name="in_type">
                <option value="">请选择入库类型...</option>
                <option value="1">初始入库</option>
                <option value="2">补货入库</option>
          </select>
		 </div>
     </div>
    <div class="form-group">
     <label class="control-label col-md-3">入库数量<span class="required">*</span></label>
          <div class="col-md-6">
          	<input id="in_stock" name="in_stock" type="number" class="form-control"/>
		 </div>
     </div>
     <div class="form-group">
       <label class="control-label col-md-3">备注</label>
       <div class="col-md-6">
           <textarea id="remark" name="remark" class="form-control" cols="20" rows="5"></textarea>
       </div>
    </div>
</div>
</form>
  
        
  
  </div>
  <div class="modal-footer yuan">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
    <button id="user_submit" class="btn btn-primary green" onclick="javascript:Page.submitform();">保存</button>
</div>

<script src="//static.wooboo.com.cn/theme/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="/pages/js/common/components-select2.js" type="text/javascript"></script>