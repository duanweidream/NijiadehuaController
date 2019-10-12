(function(){
    $('a[href="#"]').attr('href','javascript:void(0);');
})();

window.onload=function(){
    // 编辑时设置重复条件数据回显
    initReq();
};


/**
 * 验证输入的数字小数点位数不能超过两位
 */
function checkDecimalPlaces(value){
    var flag = false;
    var values = value.split(".");	//截取字符串
    if(values.length==2){
        if(values[1].length > 2)	//判断小数点后的字符串长度
            flag = true;
    }

    return flag;
}

/**
 * 取消按钮点击事件
 */
function toback(){
    window.location.href = "/advertiseIdea/idea/list";
}

/**
 * 校验重复条件中的基础出价
 */
function checkPrice(){
    var flag = false;
    var $ReqLi = $("#setRequirement li"),
        $chked, time, price;
    var reqArr = [],
        chkArr = [];
    for (var i=0; i < $ReqLi.length; i++) {
        chkArr = [];
        $chked = $ReqLi.eq(i).find("input.week:checked");
        time = $ReqLi.eq(i).find("select").val();
        price = $ReqLi.eq(i).find("input.price").val();
        for (var j=0; j < $chked.length; j++){
            chkArr.push($chked.eq(j).val());
        }
        if (chkArr.length > 0) {
            if (price != "") {
                if (checkDecimalPlaces(price)) {
                    layer.msg("重复条件中的基础出价，小数点后保留两位小数");
                }else if ((Number(price)) < Number(1.20)) {
                    layer.msg("重复条件中的基础出价，最小金额为1.20元");
                }else{
                    flag = true;
                    reqArr.push((i + 1)+ "#" + chkArr.join(",") + "#" + time + "#" + price);
                }
            } else {
                layer.msg("请设置重复条件中的基础出价");
            }
        }else{
            flag = true;
        }
    }
    var arrtmpcdbs = reqArr.join("&_&");
    if(arrtmpcdbs == null || arrtmpcdbs == ''){
        $("#tmpcdbs").val('');
    }else{
        $("#tmpcdbs").val(arrtmpcdbs);
    }

    return flag;
}

/**
 * 校验重复条件中的基础出价
 */
function checkPrice1(){
    var flag = false;
    var $ReqLi = $("#setRequirement li"),
        $chked, time, price;
    var reqArr = [],
        chkArr = [];
    for (var i=0; i < $ReqLi.length; i++) {
        chkArr = [];
        $chked = $ReqLi.eq(i).find("input.week:checked");
        time = $ReqLi.eq(i).find("select").val();
        price = $ReqLi.eq(i).find("input.price").val();
        for (var j=0; j < $chked.length; j++){
            chkArr.push($chked.eq(j).val());
        }
        if (chkArr.length > 0) {
            if (price != "") {
                if (checkDecimalPlaces(price)) {
                    layer.msg("重复条件中的基础出价，小数点后保留两位小数");
                }else if ((Number(price)) < Number(0.30)) {
                    layer.msg("重复条件中的基础出价，最小金额为0.30元");
                }else{
                    flag = true;
                    reqArr.push((i + 1)+ "#" + chkArr.join(",") + "#" + time + "#" + price);
                }
            } else {
                layer.msg("请设置重复条件中的基础出价");
            }
        }else{
            flag = true;
        }
    }
    var arrtmpcdbs = reqArr.join("&_&");
    if(arrtmpcdbs == null || arrtmpcdbs == ''){
        $("#tmpcdbs").val('');
    }else{
        $("#tmpcdbs").val(arrtmpcdbs);
    }

    return flag;
}

// 设置重复条件通用JS-start
function setReqHtml(dom, data, has) {
    var arr = [];
    if(data) {
        arr = data.split("#");
    }
    var html = '<li>每';
    var weekArr = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"];
    var timeHtml = '<select class="m-l-10 input">';
    for (var i = 0; i < weekArr.length; i++) {
        if (arr.length > 0 && arr[1].indexOf(i+1) > -1) {
            html += '<label class="m-l-10"><input type="checkbox" class="week" value="' + (i+1) + '" checked>' + weekArr[i] + '</label>';
        } else {
            html += '<label class="m-l-10"><input type="checkbox" class="week" value="' + (i+1) + '">' + weekArr[i] + '</label>';
        }
    }
    for (var j = 0; j < 24; j++) {
        if (arr.length > 0 && arr[2].indexOf(j) > -1) {
            timeHtml += '<option value="' + j + '" selected>' + (j < 10 ? ('0' + j) : j) + ':00</option>';
        } else {
            timeHtml += '<option value="' + j + '">' + (j < 10 ? ('0' + j) : j) + ':00</option>';
        }
    }
    timeHtml += '</select>';
    html += timeHtml;
    if(getcookie("basepriceflag") != null){
        html = html + '<label class="m-l-10">设置基础出价为：<input type="number" class="input w160 price" placeholder="最低出价0.30元" value="' + (arr.length > 0 ? arr[3] : "") + '" />元</label>'+
            '<a href="javascript:;" class="jian-icon m-l-10 v-middle" onClick="moveReqFn(this)" '+ (has != 'jian' ? 'style="display: none;"' : "") + '></a>'+
            '<a href="javascript:;" class="add-icon m-l-10 v-middle" onClick="addReqFn(this)"'+ (has == 'jian' ? 'style="display: none;"' : "") + '></a></li>';
    }else{
        html = html + '<label class="m-l-10">设置基础出价为：<input type="number" class="input w160 price" placeholder="最低出价1.20元" value="' + (arr.length > 0 ? arr[3] : "") + '" />元</label>'+
            '<a href="javascript:;" class="jian-icon m-l-10 v-middle" onClick="moveReqFn(this)" '+ (has != 'jian' ? 'style="display: none;"' : "") + '></a>'+
            '<a href="javascript:;" class="add-icon m-l-10 v-middle" onClick="addReqFn(this)"'+ (has == 'jian' ? 'style="display: none;"' : "") + '></a></li>';
    }
    dom.append(html);
}

function initReq() {
    var tmpcdbs = $("#tmpcdbs").val();
    var reqArr = [];
    var $ul = $("#setRequirement ul");
    if (tmpcdbs == "") {
        setReqHtml($ul);
    } else {
        reqArr = tmpcdbs.split("&_&");
        for (var i=0; i < reqArr.length; i++) {
            if (i == (reqArr.length - 1)) {
                setReqHtml($ul, reqArr[i]);
            } else {
                setReqHtml($ul, reqArr[i], 'jian');
            }
        }
    }
}
function addReqFn(t) {
    var pDom = $(t).parent();
    $(t).hide();
    pDom.find(".jian-icon").show();
    setReqHtml(pDom.parent());
}
function moveReqFn(t) {
    $(t).parent().remove();
}
// 设置重复条件通用JS-ended

// 优化目标点击切换事件（信息流）
$(".has-target-connect .type-block").on("click", function(){
    var self = $(this),
        txt = self.text();
    if(self.hasClass("active")){
        return;
    }
    self.addClass("active").siblings(".active").removeClass("active");
    if(txt == "转化"){
        $(".target-connect").removeClass("none");
        $(".ocpc-target-connect .type-block").eq(0).addClass("active").siblings(".active").removeClass("active");
        $(".ocpc-target-connect .type-block").siblings("input[type=hidden]").val(1);
    }else{
        $(".target-connect").addClass("none");
    }
    self.siblings("input[type=hidden]").val(self.attr("data-id"));
});

// 转化类型点击切换事件（信息流）
$(".ocpc-target-connect .type-block").on("click", function(){
    var self = $(this),
        txt = self.text();
    if(self.hasClass("active")){
        return;
    }
    self.addClass("active").siblings(".active").removeClass("active");
    self.siblings("input[type=hidden]").val(self.attr("data-id"));
});

// 应用介绍（信息流）
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


/**
 * 暂时无用
 * @param optionId
 * @param type
 * @param v
 */
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