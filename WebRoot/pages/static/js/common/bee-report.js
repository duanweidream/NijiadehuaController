var BeeReport = function () {

	//options = $.extend(this.element.data(), options);
	var param={};
    var pages={pgSize:10,pgFrom:0};
    var uri;
    var cb;
    var init=false;
    var tableContainer;
    var form;
    return {
    	opt:function(f,t){
    		form=$(f);
    		tableContainer=$(t);
    	},
        load: function (url,data,callback) {
        	
        	uri=url
        	cb=callback;
        	param=$.extend(param, pages);
        	BeeReport.queryParam();
        	BeeReport.send(param);
        },
        setForm:function(f){
        	form=f;
        },
        query:function(e){
        	//console.log("Beereport.query()");
        	BeeReport.queryParam();
        	BeeReport.send(param);
        },
        queryParam:function(){
        	if(form&&form.elements){
        		var els =form.elements;
        		for(var i=0;i<els.length;i++){
        			var element = els[i];
        			if(element.name){
        				param[""+element.name+""]=$(element).val();
        			}
        		}
                
        	}
        	//console.log(param);
        },
        topage:function(n){
        	param=$.extend(param, {pgFrom:(n-1)*pages.pgSize});
        	BeeReport.send(param);
        },
        prev:function(){
        	if("1"!=pages.first){
        		 param=$.extend(param, {pgFrom:pages.pgFrom-pages.pgSize});
        		 BeeReport.send(param);
        	}
        },
        next:function(){
        	 if("1"!=pages.last){
        		 param=$.extend(param, {pgFrom:pages.pgSize+pages.pgFrom});
        		 BeeReport.send(param);
        	 }
        },
        //总计部分
        total:function(total){
        	for(var p in total){
        		var obj = $("#"+p);
        	    $(obj).html(total[""+p+""]);
        	}
        },
        //页脚部分
        footer:function(page,foot){
        	var part1='<div class="col-md-5 col-sm-12">';
        	 part1+='<div class="dataTables_info" id="sample_1_info" role="status" aria-live="polite">显示 第'+page.current+'页 总共'+page.totalPage+'页 '+page.totalCount+'条数据</div>';
             part1+='</div>';
             
             var b=getbegin(page.current,page.totalPage),e=getEnd(page.current,page.totalPage);//开始页和结束页
             var part2='<div class="col-md-7 col-sm-12">';
             part2+='<div class="dataTables_paginate paging_bootstrap_number pull-right" id="sample_1_paginate">';
             part2+='<ul class="pagination" style="visibility: visible;">';
             
             part2+= '1'==page.first?' <li class="prev disabled"><a href="javascript:void(0)" title="Prev"><i class="fa fa-angle-left"></i></a></li>':'<li class="prev "><a href="javascript:void(0)" title="Prev"><i class="fa fa-angle-left"></i></a></li>';
             for(;b<=e;b++){
            	 part2+= page.current==b?'<li class="active"><a href="javascript:void(0)">'+b+'</a></li>':'<li><a href="javascript:void(0)">'+b+'</a></li>';
             }
             part2+= '1'==page.last? '<li class="next disabled"><a href="javascript:void(0)" title="Next"><i class="fa fa-angle-right"></i></a></li>':'<li class="next"><a href="javascript:void(0)" title="Next"><i class="fa fa-angle-right"></i></a></li>';
             part2+='</ul></div>';
             part2+='</div>';
             $("#"+foot).html(part1+part2);
            if(!init){
            	$("#"+foot).on("click",'li',function(e){
            		
                	var disabled = $(this).hasClass("disabled");
                	var active = $(this).hasClass("active");
                	if(disabled||active) return;
                	var label = e.target.innerHTML;
                	if($(this).hasClass("prev")){//上一页
                		BeeReport.prev();
                	}else if($(this).hasClass("next")){
                		BeeReport.next();
                	}else if(!isNaN(label)){
                		BeeReport.topage(label);
                	}
                });
            	init=true;
            }
            //缓存分页信息
            $.extend(pages, page);
        },
        send:function(data){
            //loading        	
        	 App.blockUI({ boxed: true, textOnly:true, message:"加载中..."  });
        	//tableContainer&&App.blockUI({ message: 'Loading...',target: tableContainer,overlayColor: 'none',  cenrerY: true,   boxed: true });
        	Xhr.cfg(uri,data,function(response){
//        		tableContainer&&App.unblockUI(tableContainer);
        		App.unblockUI();
        		cb(response)
        	}).execute();
        }
    };

}();


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


