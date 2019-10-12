/**
 * ajax 分页
 * author:www.longtuo.com  by luoyouhua
 * date 2009-09-13
 */

 function WbPage(action,callback,fdiv){
     var T = this;
     T.totalRows = 0;//总行数
     T.pageSize = 5;//每页显示行数
     T.totalPage = 0;
     T.currentPage = null;//当前页号
     T.from = 0;//第几行开始
     T.data = {};
     T.pageSize = 10;//页数
     T.current = 1;//当前第几页
     T.back = callback;
     T.fdiv=fdiv;
     T.callback = function(d){
           var data =  eval("("+d+")");
           data.page = T; T.data = data;
           data.foot.totalpage&&(T.totalPage = data.foot.totalpage);
           T.back(data);
           T.fdiv&&T.setFoot_01(T.fdiv);
        };
     //设置分页数目
     T.setPageSize = function(size){T.pageSize=size;};
     //设置第几页
     T.currentPage = function(n){T.current = n;};
     T.gotopage = function(n){
         T.currentPage(n);
         T.execute();
      };
      //上一页
     T.prePage = function(){
        (T.current>1)&&T.gotopage(--T.current);
     }
     //下一页
     T.nextPage = function(){
        (T.current<=T.totalPage)&&T.gotopage(++T.current);
     }
     //第几行开始
     T.setFrom = function(){T.from = T.current==1?0:(T.current-1)*T.pageSize};
     
     //设置页脚
     T.setFoot = function(footDiv){ 
        var foot = T.data.foot;
        var b=getbegin(foot.currentPage,foot.totalpage),e=getEnd(foot.currentPage,foot.totalpage);//开始页和结束页
        var f = document.createDocumentFragment();//文档碎片
        //总行数
        var tol  = util.cElem("span",{cssText:"color:#3B96DC;font-weight:bold;"},"共"+foot.totalrow+"条数据&nbsp;&nbsp;"); 
        f.appendChild(tol);
        //上一页
        var prev  = util.cElem("a",{href:"javascript:void(0);",className:(foot.currentPage==1)?"disabled":""},'<img src="/img/up.jpg" width="52" height="22" />');
        prev.onclick = util.call(T.prePage,T);
        f.appendChild(prev);
        //页码
        for(;b<=e;b++){
             if(foot.currentPage==b){
                f.appendChild(util.cElem("span",{className:'current'},''+b));
             } else{
                var a = util.cElem("a",{href:'javascript:void(0);'},""+b);
                a.onclick = util.call(T.gotopage,T,b);
                f.appendChild(a);
             }
        }
        if(e!=foot.totalpage){
               f.appendChild(document.createTextNode("..."));
               var a = util.cElem("a",{href:'javascript:void(0);'},foot.totalpage);
               a.onclick = util.call(T.gotopage,T,foot.totalpage);
               f.appendChild(a);
        }
        
        //下一页
        var next  = util.cElem("a",{href:"javascript:void(0);"},'<img src="/img/down.jpg" width="52" height="22" />');
        (T.current!=foot.totalpage)&&(next.onclick = util.call(T.nextPage,T));
        (T.current==foot.totalpage)&&(next.disabled='disabled');
        f.appendChild(next);
        
        footDiv.innerHTML = '';
        footDiv.appendChild(f);
        
     }
     

     T.xhr = new Xhr(action,T.callback);
     T.setParameter = function(k,v){T.xhr.setParameter(k,v);return T;}
     T.setForm=function(frm){T.xhr.setForm(frm);return T;}
     T.execute = function(){
        


       T.setParameter('pgSize',T.pageSize);
       T.setFrom();
       T.setParameter('pgFrom',T.from);
       T.xhr.execute();
     }
 }
 
 
  //====================画分页控件=====================//
  function getbegin(c,t){//c 当前页 t 总页 算出总开始页 显示5页
    var  b = 1;
	if(c-2>1) b = c-2;
	if(t-(c+2)<0){b = b+(t-(c+2));}
	if(b<1){b=1;}
	return b;
  }
  function getEnd(c,t){//c 当前页 t 总页 算出结束页 显示5页
  		var e = t;
		if(e>5){
			if(c+2<t) e=c+2;
			if(c-2<=0) e = e-(c-2)+1;
		}
		return e;
  }
  