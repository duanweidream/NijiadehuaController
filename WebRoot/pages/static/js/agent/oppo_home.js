var clickFlag = 0;

//初始化页面
$(function(){

    //加载头部统计数据和消耗数据
    loadHeaderData();
    loadTotalCostData();

    //获取页面数据
    showList();

    //选择框时间
    /*$(".forchge").change(function() {
        layer.load(3);
        $("#list").setGridParam({postData:getParams(), page:1})
            .trigger("reloadGrid");
    });*/


    $(".search-btn").click( function () {
        //layer.load(3);
        $("#list").setGridParam({postData:getParams(), page:1})
            .trigger("reloadGrid");
    });

    $(document).keyup(function(e){
        if($(".search-box input[type=text]").is(":focus") && e.keyCode === 13){
            $(".search-btn").trigger("click");
        }
    });

});


/**
 * 获取查询条件
 */
function getParams() {
    var serializeObj = {};
    $($("#searchForm").formToArray()).each(function() {
        serializeObj[this.name] = $.trim(this.value);
    });

    return serializeObj;
}

/**
 * 显示列表数据
 */
function showList(){
    var $notice = $("div.notice-new-box"); //通知
    var noticeH = 0;
    if($notice.length > 0 && !$notice.is(":hidden")){ //是否有通知栏显示
        noticeH = 35;
    }
    var bodyH = document.documentElement.clientHeight || document.body.clientHeight;
    var bodyW = (document.documentElement.clientWidth || document.body.clientWidth)-20;
    var totalH = bodyH-noticeH-350;
    if(bodyW < 1260){
        bodyW = 1260;
    }
    //layer.load(3);
    $("#list").jqGrid({
        url: '/agent/oppo/list',
        postData: getParams(),
        datatype : "json",//请求数据返回的类型。可选json,xml,txt
        page: 0,
        rowNum : 10,
        rowList : [ 10, 20, 50, 100],
        width: bodyW,
        height: totalH,
        scrollrows:true,
        rownumbers:true,
        colNames : ['客户名称', '客户ID', '财务ID', '审核状态', '行业类型', '开户时间', '现金余额（元）', '返货余额（元）', '赠送金额（元）','消耗金额（元）', '操作' ],//jqGrid的列显示名字
        colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
            {name : 'ownerName',index : 'ownerName',width : 140, sortable: false},
            {name : 'ownerId',index : 'ownerId',key:true,width : 80,align : "center", sortable: false},
            {name : 'accId',index : 'accId',width : 80,align : "center", sortable: false},
            {name : 'statusText',index : 'statusText',width : 80,align : "center", sortable: false},
            {name : 'industryName',index : 'industryName',width : 80,align : "center", sortable: false},
            {name : 'createTime',index : 'createTime',width : 150, align: "center", sortable: false},
            {name : 'cashBal',index : 'cashBal',width : 120, align: "center", sortable: true, formatter: function(cellvalue, options, rowObject){
                    return cellvalue ? cellvalue.toFixed(2) : '0.00';
                }},
            {name : 'rebateBal',index : 'rebateBal',width : 120, align: "center", sortable: true, formatter: function(cellvalue, options, rowObject){
                    return cellvalue ? cellvalue.toFixed(2) : '0.00';
                }},
            {name : 'giveBal',index : 'giveBal',width : 120, align: "center",sortable : true, formatter: function(cellvalue, options, rowObject){
                    return cellvalue ? cellvalue.toFixed(2) : '0.00';
                }},
            {name : 'sumCost',index : 'sumCost',width : 120, align: "center",sortable : true, formatter: function(cellvalue, options, rowObject){
                    return cellvalue ? cellvalue.toFixed(2) : '0.00';
                }},
            {name : 'operation',index : 'ownerId',width : 120, align: "center", sortable: false, formatter: function(cellvalue, options, rowObject) {
                    if(rowObject.ownerId=='' || rowObject.ownerId==null){
                        return '<a href="javascript:void(0);" class="m-r-5 grey-color">进入</a>';
                        //+'<a href="javascript:void(0);" class="m-r-5 grey-color">转账</a>'
                    }else{
                        return '<a href="/user/oppo/home/'+rowObject.ownerId+'" target="_blank" class="m-r-5">进入</a>';
                        //+'<a href="javascript:void(0);" class="m-r-5 grey-color">转账</a>'
                    }
                }}
        ],
        multiselect : true,
        multiSort: false,					//设置组合排序
        //sortname :"cashBal,rebateBal",	//设置默认组合排序参数
        loadui: "disable",
        sortable:true,						//设置单个排序
        sortname :"sumCost",				//设置默认单个排序参数
        sortorder : "desc",
        jsonReader : {
            root:"data.page_list",
            page: "data.currentPage",
            total: "data.total",
            records: "data.records",
            repeatitems: false,
            cell: "",
            id : "id"
        },
        beforeRequest:function(){
            //发起请求前事件
            layer.load(3);
        },
        loadComplete:function(xhr){
            //服务器返回响应事件
            layer.close(layer.index);
        },
        loadError:function(xhr,status,error){
            //服务器调用失败事件
            layer.close(layer.index);
            layer.msg("服务器响应异常，加载列表数据失败!",{time:3000});
        },
        onPaging: function(pgButton){
            //翻页事件
            layer.load(3);
        },
        onSortCol: function (colModelIndex,tableColIndex,sortorder) {
            //点击排序事件
            layer.load(3);
        },
        viewrecords:true,		//是否显示总条数
        hidegrid:false,
        emptyrecords:"无数据",	//列表数据为空是显示文字
        pager: '#pager2'//表格页脚的占位符(一般是div)的id
    });
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2019-05-01 08:09:05.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2019-5-1 8:9:5.18
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

/**
 * 加载代理商首页头部-所有广告主统计数据
 */
function loadHeaderData(){
    var loadWait = layer.load(3);
    $.ajax({
        url:"/agent/oppo/headerData",
        type:"get",
        dataType: "json",
        async:true,
        error: function(resp){
            var error = '服务器响应异常，请检查网络是否畅通或联系管理人员查找问题！'
            layer.alert(error, {
                icon : 5,
                title : '异常提示',
                skin : 'layui-layer-orange'
            });
        },
        success: function (resp){
            if(resp && resp.code && resp.code==200){
                var data = resp.data;
                $("#totalAccount").text(data.totalAccount + '元');
                $("#cashAccount").text(data.cashBal + '元');
                $("#rebateAccount").text(data.rebateBal + '元');
                $("#virRebateAccount").text(data.virBal + '元');
                $("#total").text(data.total);
                $("#audit").text(data.audit);
                $("#waitAudit").text(data.waitAudit);
            }else{
                var backMsg = resp.message ? resp.message : '加载顶部统计数据异常，请稍后刷新重试！或联系管理人员查找问题！';
                layer.msg(backMsg, {icon: 5});
            }
        },
        complete:function(){
            refeshBtn = false;
            layer.close(loadWait);
        }
    });
}

/**
 * 加载代理商首页头部-广告主统计消耗数据(当日消耗 和 昨日消耗)
 */
function loadTotalCostData(){
    var loadWait = layer.load(3);
    $.ajax({
        url:"/agent/oppo/costData",
        type:"get",
        dataType: "json",
        async:true,
        error: function(resp){
            var error = '服务器响应异常，请检查网络是否畅通或联系管理人员查找问题！'
            layer.alert(error, {
                icon : 5,
                title : '异常提示',
                skin : 'layui-layer-orange'
            });
        },
        success: function (resp){
            if(resp && resp.code && resp.code==200){
                var data = resp.data;
                $("#todayAllTotalCost").text(data.todayCost + '元');
                $("#yesterdayAllTotalCost").text(data.yesterdayCost + '元');
            }else{
                var backMsg = resp.message ? resp.message : '加载顶部日消耗数据异常，请稍后刷新重试！或联系管理人员查找问题！';
                layer.msg(backMsg, {icon: 5});
            }
        },
        complete:function(){
            refeshBtn = false;
            layer.close(loadWait);
        }
    });
}

/**
 * 下载客户列表
 */
function exportExcel(type){

    var dataCount = $("#sp_1_pager2").text();
    if (dataCount <= 0) {
        layer.msg("列表数据为空，无需导出");
        return;
    }
    var spanS = "<span style='color:red;font-weight: bold' color='red' >";
    var spanE = "</span>";
    var msg = "您确定要导出";
    var parameters = getParams();
    if(type && type == 'selected'){
        var ownerIds = '';
        var tableObj = $("#list");
        var rowsNum = tableObj.jqGrid("getGridParam","selarrrow");
        if(!rowsNum || rowsNum.length == 0){
            //layer.msg("请选择需要导出的记录！");
            layer.alert("请选择需要导出的记录！", {
                icon : 0,
                title : '操作提示',
                skin : 'layui-layer-orange'
            });
            return;
        }
        var num = rowsNum.length;
        if(num > 0){
            var rowData;
            for (var i = 0; i < num; i++){
                rowData = tableObj.jqGrid("getRowData",rowsNum[i]);
                ownerIds+= rowData.ownerId + ",";
            }
            if(ownerIds.length > 0){ownerIds = ownerIds.substring(0,ownerIds.length-1);}
            parameters['exportIds'] = ownerIds;
        }
        msg += "选择的这"+spanS+num+spanE+"</span>条记录吗？";
    }else{
        msg += spanS+"全部"+spanE+"记录吗？";
    }

    var filedName = $("#list").jqGrid('getGridParam','sortname');
    var orderRule = $("#list").jqGrid('getGridParam','sortorder');
    parameters['sidx'] = filedName;
    parameters['sord'] = orderRule;

    var data = "";
    for(var p in parameters){
        data+= "" + p + "=" + parameters[""+p+""] + "&";
    }
    if(data.length != 0){data = data.substring(0,data.length-1);}
    //询问框
    layer.confirm(msg, {
        icon:"3",
        title: "温馨提示",
        btn: ['确定','取消'],
        skin: 'layui-layer-orange',
        closeBtn: false,
        shade: 0.1,
        shadeClose: true
    },function(index){
        layer.close(index);
        //clickFlag = 1;
        var loadWait = layer.load(3);
        document.location = "/agent/oppo/export/excel?"+data;
        layer.close(loadWait);
    }, function(index){
        layer.close(index);
    });
}

/**
 * 【当日消耗】实时更新按钮功能方法
 * @type {boolean}
 */
var refeshBtn = false;
function updateTodayConsume(){
    if (refeshBtn) return;
    refeshBtn =  true;
    var loadWait = layer.load(3);
    $.ajax({
        url:"/agent/oppo/updateTodayConsume",
        type:"get",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        async:true,
        error: function(resp){
            var error = '服务器响应异常，请检查网络是否畅通或联系管理人员查找问题！'
            layer.alert(error, {
                icon : 5,
                title : '异常提示',
                skin : 'layui-layer-orange'
            });
        },
        success: function (resp){
            refeshBtn = false;
            if(resp && resp.code && resp.code==200){
                var data = resp.data;
                $("#todayAllTotalCost").text(data.todayCostVal + '元');
                layer.msg('[当日总消耗]实时数据更新成功!', {icon: 6});
            }else{
                layer.msg('[当日总消耗]更新失败!', {icon: 5});
            }
        },
        complete:function(){
            refeshBtn = false;
            layer.close(loadWait);
        }
    });
}
