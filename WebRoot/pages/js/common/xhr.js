var Xhr=(function(){
	var T=this;
	var pageData;
	var param={};
	var callback;
	var url;
	
	(function(){
		if(typeof(toastr)!="undefined"){
		toastr.options = {
				  "closeButton": true,
				  "debug": true,
				  "positionClass": "toast-bottom-full-width",
				  "onclick": null,
				  "showDuration": "1000",
				  "hideDuration": "1000",
				  "timeOut": "3000",
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				}
		};
	})();
	
	
	
	return {
		cfg:function(u,data,cb){
			url=u;
			callback=cb;
			if(data){
				for(var p in data){
					param[""+p+""]=data[""+p+""];
				}
			}
			return this;
		},
		execute:function(){
			$.ajax({ 
				type : "POST",
				url: url,
				//data:{startIndex:param.startIndex,itemCount:param.itemCount},
				data:param,
				success: function(response){
					loading=false;
				    callback(response);
				}
			});
		},
		execute_form:function(ids){//表单提交
			$.ajax({
				type : "POST",
				url: url,
				data:$(ids).serialize(),
				success: function(response){
					loading=false;
				    callback(response);
				}
			});
		}
	}
})();






