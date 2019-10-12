<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal-header" id="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">添加|编辑菜单信息</h3>
  </div>
  <div class="modal-body">
  
   <form action="#" id="form1" class="form-horizontal" novalidate="novalidate">
                                <input name="id" value="${menu.id}" type="hidden" />
                                <input name="PId" value="${pid}" type="hidden" />
                             <div class="form-body">
                                 <div class="alert alert-danger display-hide">
                                     <button class="close" data-close="alert"></button> 表单填写有误，请查看表单信息 </div>
                                 <div class="alert alert-success display-hide">
                                     <button class="close" data-close="alert"></button> 表单验证成功! </div>
                                 <div class="form-group">
                                     <label class="control-label col-md-3">菜单名称
                                         <span class="required" aria-required="true"> * </span>
                                     </label>
                                     <div class="col-md-6">
                                         <input type="text" name="menuName" value="${menu.menuName}" maxlength="20" class="form-control" />
                                      </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="control-label col-md-3">菜单链接
                                         <span class="required" aria-required="true"> * </span>
                                     </label>
                                     <div class="col-md-6">
                                         <input id="menuHref" name="menuHref" type="text" value="${menu.menuHref}"  class="form-control"/>
                                         <span class="help-block"> </span>
                                     </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="control-label col-md-3">排序
                                     </label>
                                     <div class="col-md-6">
                                          <input id="menuSeq" name="menuSeq" type="number" class="form-control" value="${menu.menuSeq}" onkeypress="util.justNum();"/>
                                         <span class="help-block"> </span>
                                     </div>
                                 </div>
                                 
                                 <div class="form-group">
                                     <label class="control-label col-md-3">菜单权限
                                     </label>
                                     <div class="col-md-6">
                                          <input id="authCode" name="authCode" type="text" class="form-control" value="${menu.authCode}"/>
                                         <span class="help-block"> </span>
                                     </div>
                                 </div>
                                  <div class="form-group">
                                     <label class="control-label col-md-3">菜单样式
                                     </label>
                                     <div class="col-md-6">
                                         <input name="icoClass" type="text" class="form-control" value="${menu.icoClass}">
                                         <span class="help-block"> </span>
                                     </div>
                                 </div>
                                 <div class="form-group">
                                     <label class="control-label col-md-3">是否显示
                                         <span class="required" aria-required="true"> * </span>
                                     </label>
                                     <div class="col-md-6">
                                          <select class="form-control" name="visitility">
								                <option value="">请选择...</option>
												<option value="1" <c:if test="${menu.visitility!=0}">selected="selected"</c:if>>显示</option>
												<option value="0" <c:if test="${menu.visitility==0}">selected="selected"</c:if>>隐藏</option>
		                                 </select>
                                     </div>
                                 </div>
                             </div>
  </form>
  
  
        
  
  </div>
  <div class="modal-footer yuan">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
    <button id="user_submit" class="btn btn-primary green" onclick="javascript:Page.submitform();">保存</button>
</div>