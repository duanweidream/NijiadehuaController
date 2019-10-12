var BeeTable = function () {

	//options = $.extend(this.element.data(), options);
    

    return {
        load: function (url,data,callback) {
        	Xhr.cfg(url,data,function(response){
        		callback(response)
        	}).execute();
        },
        //总计部分
        total:function(total){
        	for(var p in total){
        		var obj = $("#"+p);
        	    $(obj).html(total[""+p+""]);
        	}
        },
        page_footer:function(footer){
        	var part1="";
        }
    };

}();

