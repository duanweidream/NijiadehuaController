(function(){
	//推广落地页 通用
	$(".currency-layout-select select").on("change", function(){
		var $t = $(this),
			$seled = $t.find("option:selected");
		var val = $t.val(),
            txt = $seled.text();
		var img = '';
		var $layout = $('.layout-img'),
			$img = $layout.find("img");
		var appPackage = $("#appPackage").val();

		$layout.removeClass("none");
		if(txt.indexOf("A版") > -1){
			img = "v2-example-A.png";
			$("INPUT[NAME=targetUrl]").val("https://adsfs.oppomobile.com/mp/app/union/AN/index.html?pkg="+appPackage);
		}else if(txt.indexOf("B版") > -1){
			img = "v2-example-B.png";
			$("INPUT[NAME=targetUrl]").val("https://adsfs.oppomobile.com/mp/app/union/BN/index.html?pkg="+appPackage);
		}else if(txt.indexOf("C版") > -1){
			img = "v2-example-C.png";
			$("INPUT[NAME=targetUrl]").val("https://adsfs.oppomobile.com/mp/app/union/CN/index.html?pkg=appPackage"+appPackage);
		}else if(txt.indexOf("D版") > -1){
			img = "v2-example-D.jpg";
			$("INPUT[NAME=targetUrl]").val("https://adsfs.oppomobile.com/mp/app/union/DN/index.html?pkg="+appPackage);
		}else if(txt.indexOf("E版") > -1){
			img = "v2-example-E.jpg";
			$("INPUT[NAME=targetUrl]").val("https://adsfs.oppomobile.com/mp/app/union/EN/index.html?pkg="+appPackage);
		}
		if(img != ""){
			$img.attr("src", "/frontpage/static/images/"+img);
		}
		// $(".currency-layout-select select option").eq(inx).attr("selected",true).siblings().attr("selected",false);
		});


	//应用介绍
	$(".app-info-textarea").focus(function(){
		var self = $(this),
			val = $.trim(self.val());
		if(val == "应用介绍，100字以内"){
			self.removeClass("grey-font").val("");
		}
	}).blur(function(){
		var self = $(this),
			val = $.trim(self.val());
		if(val == ""){
			self.addClass("grey-font").val("应用介绍，100字以内");
		}
	});
})();

function showOutPrice(optionId){
	var self = $("#"+optionId);
	//self.addClass("active").siblings(".active").removeClass("active");
	if(self.val() == "2"){
		$(".target-connect").removeClass("none");
	}else{
		$(".target-connect").addClass("none");
	}
}

function showOrientItem(optionId, type, v) {
	if (type == "0")
		return ;

	var self = $("#" + optionId),
		$divl = self.parents(".item-child").siblings("div");

	self.val(v == "" ? type : v);
	self.siblings(".type-block").removeClass("active");
	self.siblings("span[data-id='" + type + "']").addClass("active");

	if ($divl.length > 0) {
		if ($divl.length == 1) {
			$divl.removeClass("none");

			if (v) {
				$divl.find("span.type-block").each(function(i, n) {
					var id = $(this).data("id");
					if (v.indexOf(id) > -1) {
						$(this).addClass("active");
					}
				});
			}
		} else {
			var $showdiv = self.parents(".item-child").siblings("div[data-id='" + type + "']");
			$showdiv.removeClass("none");

			if (v) {
				$showdiv.find("span.type-block").each(function(i, n) {
					var id = $(this).data("id");
					if (v.indexOf(id) > -1) {
						$(this).addClass("active");
					}
				});
			}
		}
	}
}


toastr.options = {
		"closeButton" : true,
		"debug" : true,
		"positionClass" : "toast-bottom-full-width",
		"onclick" : null,
		"showDuration" : "1000",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "fadeIn",
		"hideMethod" : "fadeOut"
};	

//广告计划 选择
$("#i_planId").on("change", function(){
	var formMain = $("div.add-source-body");
    formMain.addClass("none");
});




