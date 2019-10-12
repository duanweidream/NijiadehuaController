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
                                <a href="#">首页</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">系统设置</a>
                                <i class="fa fa-circle"></i>
                            </li>
                            <li>
                                <a href="#">菜单设置</a>
                            </li>
                        </ul>
                       
                    </div>
                    <!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <h3 class="page-title">
                       
                    </h3>
                    <!-- END PAGE TITLE-->
                    <!-- END PAGE HEADER-->
                    <div class="note note-info">
                        <p> 给系统角色分配菜单操作</p>
                    </div>
                    
                    
                    
                 <form action="/system/menu/roleset" name="pst" method="post">
                   
                    <div class="row">
                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                 <select name="roleId" id="position" class="form-control" onchange="ck_check(this);" style="width:300px;">
								        <option value="">请选择用户角色...</option>
								        <c:forEach items="${roleList}" var="role">
								          		<option value="${role.roleId}">${role.roleName}</option>
								        </c:forEach>
								      </select>
                       </div>
                    </div>
                    <div class="clearfix"> </div>
                   
                   
                   <div class="row">
                   
					     <c:forEach items="${clist}" var="c">
					         <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					        <h3 style="margin:0;clear:both;"><span class="shi" onclick="cg('${c.id}_div',this)">&nbsp;&nbsp;</span>${c.menuName} <input type="checkbox" name="ids" onclick="ca('${c.id}',this);" value="${c.id}" id="${c.id}_ck" /></h3>
					        <dl style="padding-left:0px;display:none;" id="${c.id}_div">
					        <c:forEach items="${c.list}" var="m">
					                <dt style="margin:0;clear:both;padding:0;"><span class="yi">&nbsp;&nbsp;</span><span class="shi" onclick="bcg('dd',${m.id},this)">&nbsp;&nbsp;</span><input type="checkbox" name="ids" onclick="ca('${m.id}',this);ac('${c.id}');" value="${m.id}" pid="${c.id}" id="${m.id}_ck" />${m.menuName}</dt>
					                <c:forEach items="${m.list}" var="son">
					                       <dd parent="${m.id}" style="margin:0;clear:both;text-indent: 0px;display:none;"><span class="yi">&nbsp;&nbsp;</span><span class="yi">&nbsp;&nbsp;</span>
					                       <c:choose><c:when test="${fn:length(son.list)>0}"><span class="shi" onclick="bcg('dd',${son.id},this)" ></span></c:when><c:otherwise><span class="yi">&nbsp;&nbsp;</span></c:otherwise></c:choose>
					                       <input type="checkbox" onclick="ac('${m.id}');ca(${son.id},this,'operIds');"  name="ids" id="${son.id}_ck" value="${son.id}" pid="${m.id}" />${son.menuName}(${son.menuHref})</dd> 
					                </c:forEach>
					                
					        </c:forEach>
					        </dl>
					      </div>
					    </c:forEach>
                   
                   
                   </div>
                   
                   
                   
                   
                   
                  
                   
                   
                   
                   
                   
                   
                   
                   </form>
                   
                   <div class="subd"><input type="button" value="保存菜单设置 " class="btn blue" onclick="save_do()" /></div>
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                    
                    
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
        <script src="/pages/js/common/bee-model.js"></script>  
        
        <script type="text/javascript" src="/scripts/util.js"></script>    
	    <script src="/pages/js/common/xhr.js"></script>  
        
        
        <script type="text/javascript" >
   function cg(id,th){
     var o = util.$(id);
     if(o){
       o.style.display=o.style.display=='none'?'':'none';
       th.className=o.style.display=='none'?'shi':'shiyi';
     }
   }
   function bcg(tag,id,th){
      var r = document.getElementsByTagName(tag);
      var nr = filterpid(r,id);
      util.each(nr,function(e,i){
         e.style.display=e.style.display=='none'?'':'none';
         (i==0)&&(th.className==e.style.display=='none'?'shi':'shiyi');
      })
   }
   function filterpid(r,id){
      var nr=[];
      util.each(r,function(e){
         if(e.getAttribute('parent')==id){
            nr.push(e);
         }
      });
      return nr;
   }
   
  function ca(id,th,nm){
     var nm = nm||"ids";
     var r = filterCk(document.getElementsByName(nm),id);
     util.each(r,function(e){
       e.checked=th.checked;
       ca(e.value,e);
     });
  }
  function ac(id,nm){
    var nm = nm||"ids";
    var r = filterCk(document.getElementsByName(nm),id);
    var b = false;
    util.each(r,function(e){
        if(e.checked){
          b=true;
          return 1;
        }
    });
    var t = util.$(id+'_ck');
    t&&(t.checked=b);
    t&&(nm=="operIds")&&(ac(t.getAttribute("pid")));
  }
  function filterCk(list,id){
    var r=[];
    util.each(list,function(e){
      (e.getAttribute('pid')==id)&&r.push(e);
    });
    return r;
  }
  function ck_check(th){
      if(th.value!=""){
         util.dusk_div("boxId");
         function callback(d){
            util.close_dusk("boxId");
            var o = eval("("+d+")");
            
            alert("haha");
            checkDo("ids",o.menuReg||"");
         }
         
         Xhr.cfg("/system/menu/search",{roleId:th.value},function(response){
        	 util.close_dusk("boxId");
        	 if(response.code==200){
        		 checkDo("ids",response.data.menuReg||"");
        	 }
         }).execute();
         
         //new xhr("/system/menu/search",callback).setParameter("roleId",th.value).execute();
      }
  }
  function checkDo(name,reg){
     var r = document.getElementsByName(name);
     var regxp = new RegExp("^("+reg+")$");
     util.each(r,function(e){
        e.checked = regxp.test(e.value);
     })
  }
  function save_do(){
    if(confirm("确定保存该设置?")){
       var position = util.$('position');
       if(position&&position.value!=""){
         document.pst.submit();
       }else{
         alert("请选择一设置菜单的角色!");
       }
    }
  }
</script>
        
        
        
    </body>

</html>