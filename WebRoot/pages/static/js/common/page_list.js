/**
 * ajax 分页
 * author:www.longtuo.com  by luoyouhua
 * date 2009-09-13
 */
/**
 *
 * @param config
 * @constructor
 *  {
 *     url:"".//地址
 *     success:function, //成功回调
 *     error:function,//失败回调
 *     nodata:function,无数据
 *  }
 */

 function WbPage(config){
     var T = this;
     var index;
     //分页数据
     T.page={};
     //配置信息
     T.config=config;
     //设置分页数目
     T.setPageSize = function(size){T.page.pgSize=size;};
     //设置第几页
     T.currentPage = function(n){
         T.page.current = n;
         T.page.pgFrom=T.page.current==1?0:(T.page.current-1)*T.page.pgSize;
     };
     T.gotopage = function(n){
         T.currentPage(n);
         T.execute();
      };
      //上一页
     T.prePage = function(){
        (T.page.current>1)&&T.gotopage(T.page.current-1);
     }
     //下一页
     T.nextPage = function(){

         if(T.page.current<=T.page.totalPage){
             T.page.current=T.page.current+1;

             T.gotopage(T.page.current);
         }
        //(T.page.current<=T.page.totalPage)&&T.gotopage(++T.page.current);
     }

     //设置页脚 {pgFrom:0,last:1,totalCount:2,current:1,pgSize:0,first:1,totalPage:0}
     T.setFoot = function(foot){
         T.page=foot;
         var $foot = $('.page-section .page-main');
         $foot.html('');

         //首页 //上一页
         if(foot.first=='1'){
             $foot.append('<li class="text-dom index"><a href="javascript:;" class="disabled">首页</a></li>');
             $foot.append('<li class="text-dom pre"><a href="javascript:;" class="disabled">上一页</a></li>');
         }else{
             var $first = $('<li class="text-dom index"><a href="javascript:;" >首页</a></li>');
             $first.bind("click",function(){
                 T.gotopage(1);
             });
             $foot.append($first);

             $prepage=$('<li class="text-dom pre"><a href="javascript:;" >上一页</a></li>');
             $prepage.bind("click",function(){
                 T.prePage(1);
             });
             $foot.append($prepage);
         }


         var b=getbegin(foot.current,foot.totalPage)
         var e=getEnd(foot.current,foot.totalPage);//开始页和结束页
         //页码

         for(;b<=e;b++){
             const bd=b;
             if(foot.current==b){
                 $foot.append('<li><a href="javascript:;" class="active">'+b+'</a></li>');
             } else{
                 $page=$('<li><a href="javascript:;">'+b+'</a></li>');
                 $page.bind("click",function(){
                     T.gotopage(bd);
                 })
                 $foot.append($page);
             }
         }

         if(e!=foot.totalPage){
             $foot.append('<li class="text-dom last"><a href="javascript:;" >...</a></li>');
             var $lastpage = $('<li class="text-dom next"><a href="javascript:;">'+foot.totalPage+'</a></li>');
             $lastpage.bind("click",function(){
                 T.gotopage(foot.totalPage);
             })
             $foot.append($lastpage);
         }


         if(foot.last=='1'){
             $foot.append('<li class="text-dom next"><a href="javascript:;" class="disabled">下一页</a></li>');
             $foot.append('<li class="text-dom last"><a href="javascript:;" class="disabled">末页</a></li>');
         }else{
             var $next = $('<li class="text-dom next"><a href="javascript:;">下一页</a></li>');
             $next.bind("click",function(){
                 T.nextPage();
             });
             $foot.append($next);

             var $last = $('<li class="text-dom last"><a href="javascript:;" >末页</a></li>');
             $last.bind("click",function () {
                 T.gotopage(T.page.totalPage);
             })
             $foot.append($last);
         }



     }
     T.setPage=function(){
         for(var p in T.page){
             //console.log(p+" "+T.page[p]);
             T.setParameter(p,T.page[p]);
         }
     }
     T.setParameter = function(k,v){
         var $els = $('#'+k);
         if($els.length==1){
             $els.val(v);
         }else{
             $("#search_form").append('<input type="hidden" id="'+k+'" name="'+k+'" value="'+v+'" />');
         }
     }
     T.setForm=function(frm){T.xhr.setForm(frm);return T;}
     T.reload=function(){
        T.execute();
     }
     T.execute = function(){
         //T.setFrom();
         //设置分页数据
         T.setPage();

         if(layer){
             index = layer.load(1, {
                 shade: [0.5,'#fff'] //0.1透明度的白色背景
             });
         }

         $.ajax({
             type : "POST",
             url: T.config.url,
             data:$("#search_form").serialize(),
             success: function(response){

                 var page = response.data.page
                 if(response.code==200){
                     if(response.data.list==0){
                         T.config.nodata&&T.config.nodata();
                     }else{
                         T.config.success&&T.config.success(response.data);
                     }
                     //分页信息
                     T.setFoot(page);
                 }else{
                     if(T.config.error){
                         T.config.error(response.data);
                     }else{
                         alert(response.message);
                     }
                 }
                 if(layer){
                    layer.close(index);
                 }
             }
         });

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
  