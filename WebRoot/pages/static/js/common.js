var clickFlag = 0;  // 防止重复点击标志位，0可点击，1不可点击(add@zhangxiaofei)
var currentId;
//header
var commonFn = {
	init: function(){
		//公司
		// $("#showMoreCompony").mouseenter(function(){
		// 	$(this).siblings(".user-info-detail").show();
		// }).mouseleave(function(){
		// 	$(this).siblings(".user-info-detail").hide();
		// });
		//下拉
		$("#hdUser").on("click", function(e){
			var self = $(this),
				$parent = self.parents(".hd-user");
			e.stopPropagation();
			e.preventDefault();
			$parent.siblings(".show").removeClass("show"); //移除其他显示
			if($parent.hasClass("show")){
				$parent.removeClass("show");
			}else{
				$parent.addClass("show");
			}
		});
		document.addEventListener("click", function(e){ //点击其他地方 收起
			var $this = $(e.target);
			if(!$this.hasClass("message-wrap") && $this.parents(".message-wrap").length < 1){
				$(".show").removeClass("show");
			}
		});
		commonFn.msgFn();
		commonFn.scrollTopFn();
		commonFn.navActiveFn();
		// commonFn.btnDownFn();
	},
	scrollTopFn: function(){
		var $notice = $("div.notice-new-box"),
			$ul = $notice.find("ul");
		if($notice.find("li").length > 1){
			setInterval(function(){
				$notice.find("ul").animate({
		        	marginTop: "-30px"
			    },
			    500,
			    function() {
			        $ul.css({
			            marginTop: "0"
			        }).find("li:first").appendTo(this);
			    });
			},3000);
		}
	},
    setTableHeight() { //设置table列表高度
        var tableH = getHeight();
        var listH = $("table.list-table").height();
        var $table = $("table.list-table");
        if ((tableH - listH) > 30 && $table.length > 0 && !$table.parents(".wb-table").hasClass("no-set-height")) { //若有通知栏的操作 切高度需要设置的
            $table.setGridHeight(tableH);
        }
    },
    msgHttpFn: function($msg, $msgUrl){
        $.ajax({
            type : "POST",
            url: "/message/info/show",
            data:{"id":currentId||""},
            success: function(response){
                // console.log(response);
                $msgUrl.html('');
                if(response.code==200){
                    if(response.data.list.length>0){
                        var th = response.data.list[0];
                        currentId=th.id;
                        var topTipCont, topTipClose;
                        var msgItem = '<li class="clearfix"><span class="message-icon"></span>  <a href="/message/info" class="txt">'+th.createdTime+' | '+th.context+'</a><a href="javascript:;" class="close"></a>';
                        $msgUrl.html(msgItem); //添加节点
                        if (window.localStorage) {
                            topTipCont = window.localStorage.getItem('topTipCont');
                            topTipClose = window.localStorage.getItem('topTipClose');
                            if (topTipCont == (th.createdTime+' | '+th.context) && topTipClose) {
                                $msg.addClass("none");
                            } else {
                                if (topTipCont != (th.createdTime+' | '+th.context)) {
                                    window.localStorage.setItem("topTipCont", th.createdTime+' | '+th.context);
                                }
                                $msg.removeClass("none");
                            }
                        } else if (!$msg.hasClass("close")) {
                            $msg.removeClass("none");
                        }

                        commonFn.setTableHeight();
                    }else{
                        $msg.addClass("none");
                    }
                }else {
                    $msg.addClass("none");
                }
            }
        });
    },
	msgFn: function(){
	    var $msg = $(".notice-new-box"),
            $msgUrl = $msg.find("ul");
        commonFn.msgHttpFn($msg, $msgUrl); //页面加载后先执行1遍
        setInterval(function(){ //1分钟 循环执行1遍
            commonFn.msgHttpFn($msg, $msgUrl);
        }, 1000*60);
        $msg.on("click", ".close", function(){ //通知 关闭按钮
            $msg.addClass("none close");
            window.localStorage && window.localStorage.setItem("topTipClose", true);
            commonFn.setTableHeight();
        });
	},
	btnDownFn: function(confirm, cancel){ //按钮下拉
		var definedList= $(".btn-down-box");
        $(document).on("click", function(e){ //
            var $tag = $(e.target);
            if($tag.hasClass("btn-down-box") || $tag.parents(".btn-down-box").length > 0){
                return true;
            }
            definedList.removeClass("defined-show");
        });

        definedList.find("a.btn-down").on("click", function(e){ //自定义列 按钮
            var self = $(this),
                $p = self.parents(".btn-down-box");
            $p.siblings(".defined-show").removeClass("defined-show");
            if($p.hasClass("defined-show")){
                $p.removeClass("defined-show");
            }else{
                $p.addClass("defined-show");
            }
        });

        definedList.find(".btn-def").on("click", function(e){ //取消
            var self = $(this),
                $p = self.parents(".btn-down-box");
            $p.removeClass("defined-show");

            cancel && cancel();
        });

        definedList.find(".btn-submit").on("click", function(e){ //确定
            var self = $(this),
                $p = self.parents(".btn-down-box");
            $p.removeClass("defined-show");

            //ajax……
            confirm && confirm();

        });
	},
	navActiveFn: function(){
		var url = window.location.href;
		var $navBox = $(".header .nav-box"),
			nav = $navBox.find("li.nav-li");
		$navBox.find(".active").removeClass("active");
		if(url.indexOf("/user/oppo/dashboard") > -1 || url.indexOf("/user/oppo/detail/query") > -1){
			nav.eq(0).find("a").addClass("active");
		}else if(url.indexOf("/advertise") > -1){
			nav.eq(1).find("a").addClass("active");
		}else if(url.indexOf("/data/report") > -1){
			nav.eq(2).find("a").addClass("active");
		}else if(url.indexOf("/user/oppo/toolbox") > -1 || url.indexOf("/message/") > -1 || url.indexOf("/direction") > -1){
			nav.eq(3).find("a").addClass("active");
		}else if(url.indexOf("/finance") > -1){
			nav.eq(4).find("a").addClass("active");
		}
	},
    datapickerSet: function(isYesterday, endDay, clear) { //isYesterday是否是默认昨天，endDay 最近7天、最近30天由昨天算起, clear是否清空
        var $reportrange = $('#reportrange'),
            $datepicker = $reportrange.find('.datepicker'),
            $showInp = $reportrange.find('input.datapicker-show'),
            $startInp = $reportrange.find('input.date-start');
            $endInp = $reportrange.find('input.date-end');
        function dataSetVal(startDate, endDate){ //存储+显示 选择的时间
            $datepicker.html(startDate ? (startDate + ' - ' + endDate) : '请选择时间...');
            $showInp.val(startDate ? (startDate + ' - ' + endDate) : '');
            $startInp.val(startDate ? startDate : "");
            $endInp.val(endDate ? endDate : "");
        }
        var today = moment().format('YYYY-MM-DD'),
            yesterday = moment().subtract('days', 1).startOf('day').format('YYYY-MM-DD');
        var week = [moment().subtract('days', 6), moment()],
            month = [moment().subtract('days', 29), moment()];
        if (endDay) {
            week = [moment().subtract('days', 7), yesterday];
            month = [moment().subtract('days', 30), yesterday];
        }

        var startDate = today,
            endDate = today;
        if (isYesterday != "请选择") {
            if (typeof isYesterday == "boolean" && isYesterday) {
                startDate = yesterday;
                endDate = yesterday;
            }
            dataSetVal(startDate, endDate);
        } else {
            $datepicker.html('请选择时间...');
            $reportrange.on("hideCalendar.daterangepicker", function(ev, picker){
                startDate = picker.startDate.format('YYYY-MM-DD');
                endDate = picker.endDate.format('YYYY-MM-DD');
                dataSetVal(startDate, endDate);
            });
        }

        $reportrange.daterangepicker(
        {
            startDate: startDate,
            endDate: endDate,
            //minDate: '01/01/2012',    //最小时间
            maxDate : moment(), //最大时间
            dateLimit : {
                days : 180
            }, //起止时间的最大间隔
            // showDropdowns : true,
            // showWeekNumbers : false, //是否显示第几周
            // timePicker : false, //是否显示小时和分钟
            // timePickerIncrement : 60, //时间的增量，单位为分钟
            // timePicker12Hour : false, //是否使用12小时制来显示时间
            ranges : {
                '今日': [today, today],
                '昨日': [yesterday, yesterday],
                '最近7日': week,
                '最近30日': month
            },
            opens : 'right', //日期选择框的弹出位置
            buttonClasses : [ 'btn btn-default' ],
            applyClass : 'btn-small btn-primary blue',
            cancelClass : 'btn-small',
            format : 'YYYY-MM-DD', //控件中from和to 显示的日期格式
            separator: "~",
            locale : {
                applyLabel : '确定',
                cancelLabel : clear ? '清空' : '取消',
                fromLabel : '起始时间',
                toLabel : '结束时间',
                customRangeLabel : '自定义',
                daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
                monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                firstDay : 1,
                format: 'YYYY-MM-DD'
            }
        }, function(start, end, label) {//格式化日期显示框
            dataSetVal(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'));
        }).on('cancel.daterangepicker', function(ev, picker) {
            if (clear) {
                dataSetVal('', '');
            }
        })
    }
}
commonFn.init();

/**
 * 获取参数值
 * @param url
 * @param ref
 * @returns
 */
function getQueStr(url, ref) {
    var str = url.substr(url.indexOf('?') + 1);
    if (str.indexOf('&') != -1) {
        var arr = str.split('&');
        for (var i in arr) {
            if (arr[i].split('=')[0] == ref){
                return arr[i].split('=')[1];
            }
        }
    } else {
        if(str.indexOf(ref) != -1){
            return url.substr(url.indexOf('=') + 1);
        }
        //console.log("2---" + url.substr(url.indexOf('=') + 1));
        return null;
    }
}

/**
 * 设置参数值
 * @param url
 * @param ref
 * @param value
 * @returns {String}
 */
function setQueStr(url, ref, value){
    var str = "";
    if (url.indexOf('?') != -1)
        str = url.substr(url.indexOf('?') + 1);
    else
        return url + "?" + ref + "=" + value;
    var returnurl = "";
    var setparam = "";
    var arr;
    var modify = "0";
    if (str.indexOf('&') != -1) {
        arr = str.split('&');

        for (i in arr) {
            if (arr[i].split('=')[0] == ref) {
                setparam = value;
                modify = "1";
            }
            else {
                setparam = arr[i].split('=')[1];
            }
            returnurl = returnurl + arr[i].split('=')[0] + "=" + setparam + "&";
        }

        returnurl = returnurl.substr(0, returnurl.length - 1);

        if (modify == "0")
            if (returnurl == str)
                returnurl = returnurl + "&" + ref + "=" + value;
    }
    else {
        if (str.indexOf('=') != -1) {
            arr = str.split('=');

            if (arr[0] == ref) {
                setparam = value;
                modify = "1";
            }
            else {
                setparam = arr[1];
            }
            returnurl = arr[0] + "=" + setparam;
            if (modify == "0")
                if (returnurl == str)
                    returnurl = returnurl + "&" + ref + "=" + value;
        }
        else
            returnurl = ref + "=" + value;
    }
    return url.substr(0, url.indexOf('?')) + "?" + returnurl;
}

/**
 * 删除参数值
 * @param url
 * @param ref
 * @returns
 */
function delQueStr(url, ref) {
    var str = "";
    if (url.indexOf('?') != -1)
        str = url.substr(url.indexOf('?') + 1);
    else
        return url;
    var arr = "";
    var returnurl = "";
    var setparam = "";
    if (str.indexOf('&') != -1) {
        arr = str.split('&');
        for (i in arr) {
            if (arr[i].split('=')[0] != ref) {
                returnurl = returnurl + arr[i].split('=')[0] + "=" + arr[i].split('=')[1] + "&";
            }
        }
        return url.substr(0, url.indexOf('?')) + "?" + returnurl.substr(0, returnurl.length - 1);
    }
    else {
        arr = str.split('=');
        if (arr[0] == ref)
            return url.substr(0, url.indexOf('?'));
        else
            return url;
    }
}

function getsec(str){
    var str1=str.substring(1,str.length)*1;
    var str2=str.substring(0,1);
    if (str2=="s"){
        return str1*1000;
    }else if (str2=="h"){
        return str1*60*60*1000;
    }else if (str2=="d"){
        return str1*24*60*60*1000;
    }
}

/**
 * 创建cookie
 * @param name
 * @param value
 * @param time
 */
function setcookie(name,value,time){
    var strsec = getsec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strsec*1);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

/**
 * 获取cookie值
 * @param name
 * @returns
 */
function getcookie(name){
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

/**
 * 删除cookie
 * @param name
 */
function delcookie(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getcookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

// 表格 获取高度
function getHeight() {
    var bodyH = document.documentElement.clientHeight || document.body.clientHeight;
        $notice = $("div.notice-new-box"), //通知
        toolH = $(".tools-handle, .ads-handle-box").outerHeight(true),
        noticeH = 0,
        tableH = 0;
    if($notice.length > 0 && !$notice.is(":hidden")){ //是否有通知栏显示
        noticeH = 35;
    }
    tableH = bodyH - noticeH - toolH - 206;
    return tableH;
}
//获取查询条件
function getParams(id) {
    var serializeObj = {};
    $($(id).formToArray()).each(function() {
        serializeObj[this.name] = $.trim(this.value);
    });
    return serializeObj;
}

//返回
function back(url) {
    window.location.href = url;
}