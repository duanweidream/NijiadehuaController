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
				    callback(response);
				}
			});
		},
		post:function(url,postData,fn){
            var index = 0;
            if(layer){
                index = layer.load(3, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
			}
			$.ajax({
                type : "POST",
                url: url,
                data:postData,
                success: function(response){
                    if(layer){
                        layer.close(index);
                    }
                    fn(response);
                }
            });
		},
        post_form:function(url,ids,fn){
            var index=0;
            if(layer){
                index = layer.load(3, {shade: [0.1,'#fff']});
            }
            $.ajax({
                type : "POST",
                url: url,
                data:$(ids).serialize(),
                success: function(response){
                    if(layer){
                        layer.close(index);
                    }
                    console.log(response);
                    fn(response);
                }
            });
        },
		post_file:function(url,form,fn){
			var formData = new FormData($(form)[0]);
            $.ajax({
                type: 'post',
                url: url,
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                fn(data);
            }).error(function () {
                console.log("error")
            });
        }
	}
})();






