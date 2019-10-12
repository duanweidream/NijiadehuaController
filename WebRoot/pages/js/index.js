$(function(){
	//TEXTAREA、input 文本提示	
	$("input[focucmsg],TEXTAREA[focucmsg]") .each (function(){
		$(this).val($(this).attr("focucmsg"));
		$(this).val($(this).attr("focucmsg")).css("color","#979393");
		$(this).focus(function(){
		if($(this).val() == $(this).attr("focucmsg"))
			{
				$(this).val('');
				$(this).val('').css("color","#6b6969");
			}
		});
		$(this).blur(function(){
			if(!$(this).val()){
				$(this).val($(this).attr("focucmsg"));
				$(this).val($(this).attr("focucmsg")).css("color","#979393");
			}
		});
	});
	
	//select
	$(".select").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("a").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	})
	//
	$("#chooseD a").click(function(){
		$("#chooseD a").removeClass("choose-d");
		$(this).addClass("choose-d");
		
	})
	
	//单选
	$(".numOtimes label").click(function(){
		
		var thisIndex=$(".numOtimes label").index(this);
		
		$(".numOtimes .imp").find('em').css("background-position","0px 0px")
		$(".numOtimes .click").find('em').css("background-position","-14px 0px")
		$(".numOtimes .ctr").find('em').css("background-position","-28px 0px")
		$(".pictureForms").hide();
		$(".pictureForms").eq(thisIndex).show();
		$(".numOtimes label").eq(thisIndex).find('em').css("background-position",'-'+thisIndex*14+'px '+'-14px');
	})
	
	//图表显示收缩
	var hei;
	$(".shrink b").click(function(){
		var rfh=$(".reportForms").height();
		
		if(rfh > 0){
			hei=rfh;
			rfh=0;
			$(".reportForms").animate({"height":rfh+"px"},300);
			$(".shrink b").css({
				"top":"39px",
				"background-position":"0px -182px"
			});
		}else if(rfh==0){
			//console.log(hei);
			$(".reportForms").animate({"height":hei+"px"},300);
			$(".shrink b").css({
				"top":"-8px",
				"background-position":"0px -170px"
			});
		}
		
	})
	
	
});





