<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="app" uri="/application" %>

<div class="modal-header" id="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">添加角色信息</h3>
  </div>
  <div class="modal-body">
  
  
   <form action="#" id="user_form" class="form-horizontal" novalidate="novalidate">
  <input name="roleId" value="${role.roleId}" type="hidden" />
  <div class="alert alert-error hide">表单填写有误，请查看表单信息</div>
  <div class="alert alert-success hide">表单验证成功!</div>
 <div>
   
   
   <div class="form-group">
     
   <div class="form-group">
      <label class="control-label col-md-3">角色名<span class="required">*</span></label>
       <div class="col-md-6">
           <input id="nickName" name="roleName" type="text" class="form-control" value="${role.roleName}"/>
      </div>
   </div>
   
    <div class="form-group" style="display:none;">
     <label class="control-label col-md-3">级别<span class="required">*</span></label>
          <div class="col-md-6">
	          <select class="form-control" name="roleLevel">
					<option value="5" <c:if test="${role.roleLevel=='5'}">selected="selected"</c:if>>level 5 </option>
		            <option value="4" <c:if test="${role.roleLevel=='4'}">selected="selected"</c:if>>level 4 </option>
		            <option value="3" <c:if test="${empty role.roleLevel ||  role.roleLevel=='3'}">selected="selected"</c:if>>level 3 </option>
		            <option value="2" <c:if test="${role.roleLevel=='2'}">selected="selected"</c:if>>level 2 </option>
		            <option value="1" <c:if test="${role.roleLevel=='1'}">selected="selected"</c:if>>level 1 </option>
			 </select>
		 </div>
     </div>
     <div class="form-group">
       <label class="control-label col-md-3">备注</label>
       <div class="col-md-6">
           <input id="nickName" name="roleDesc" type="text" class="form-control" value="${role.roleDesc}"/>
       </div>
    </div>
</div>
</form>
  
        
  
  </div>
  <div class="modal-footer yuan">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
    <button id="user_submit" class="btn btn-primary green" onclick="javascript:Page.submitform();">保存</button>
</div>