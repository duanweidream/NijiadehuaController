<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header" id="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">添加系统用户</h3>
  </div>
  <div class="modal-body">
  
  
    <form action="#" id="user_form" class="form-horizontal" novalidate="novalidate">
	  <input type="hidden" value="${user.id}" name="id"/>
		<div class="alert alert-danger display-hide"> <button class="close" data-close="alert"></button> 表单填写有误，请查看表单信息 </div>
		<div class="alert alert-success display-hide"> <button class="close" data-close="alert"></button> 表单验证成功! </div>
	    <div class="form-group">
	      <label class="control-label col-md-3">登录名<span class="required">*</span></label>
	      <div class="col-md-6">
	           <input id="userName" name="userName" type="text" class="form-control" value="${user.userName}" maxlength="32"  <c:if test="${!empty user }">disabled</c:if>  />
	      </div>
	   </div>
	    
	    <div class="form-group">
	       <label class="control-label col-md-3">姓名<span class="required">*</span></label>
	       <div class="col-md-6">
	            <input id="nickName" name="nickName" type="text" class="form-control" value="${user.nickName}" maxlength="32"/>
	       </div>
	    </div>
	    <div class="form-group">
	     <label class="control-label col-md-3">角色<span class="required">*</span></label>
	       <div class="col-md-6">
		          <select class="form-control" name="roleId">
						<option value="">请选择...</option>
						<c:forEach items="${list}" var="role">
		          		<option  value="${role.roleId}"  <c:if test="${user.roleInfo.roleId==role.roleId}">selected="selected"</c:if>>${role.roleName}</option>
		                </c:forEach>
				 </select>
			 </div>
	     </div>
	     

	     
	     <div class="form-group">
	       <label class="control-label col-md-3">电话<span class="required">*</span></label>
	       <div class="col-md-6">
	            <input id="nickName" name="userPhone" type="text" class="form-control" value="${user.userPhone}" maxlength="32"/>
	       </div>
	    </div>
	    
	    <div class="form-group">
	       <label class="control-label col-md-3">邮件<span class="required">*</span></label>
	       <div class="col-md-6">
	            <input id="nickName" name="email" type="text" class="form-control" value="${user.email}"  />
	       </div>
	    </div>
	    
	   <div class="form-group">
	          <label class="control-label col-md-3">状态</label>
	           <div class="col-md-6">
		          <select class="form-control" name="state">
		                <option value="">请选择...</option>
						<option value="1" <c:if test="${user.state!=4}">selected="selected"</c:if>>正常</option>
						<option value="0" <c:if test="${user.state==0}">selected="selected"</c:if>>禁止</option>
						
				 </select>
			 </div>
	     </div>
	     
</form>

  </div>
  <div class="modal-footer yuan">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
    <button id="user_submit" class="btn btn-primary green" onclick="javascript:Page.submitform();">保存</button>
</div>