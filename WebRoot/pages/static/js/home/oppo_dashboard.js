var orangeStyle = {
    normal:{
        color:function(params) {
            //自定义颜色
            return '#ff6700';
        },
        lineStyle:{
            color:'#ff6700'
        }
    }
};
var yellowStyle = {
    normal:{
        color:function(params) {
            //自定义颜色
            return '#f5b031';
        },
        lineStyle:{
            color:'#f5b031'
        }
    }
};

// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('echarts_main'));

/**
 * Echarts初始化默认参数设置
 * */
function initEchartsDefualtOption(){
    var option = {
        title : {
            text: '广告统计图表',
            subtext: '维度统计',
            show:false
        },
        tooltip : {
            trigger: 'axis'
        },
        toolbox: {
            show : true,
            feature : {
                magicType : {show: true, type: ['line', 'bar']},
                saveAsImage : {show: true}
            }
        },
        legend: {
            left:'center',
            data: ['title1', 'title2']
        },
        calculable : true,
        xAxis: {
            type : 'category',
            data: [0,0]
        },
        yAxis:[{
            name: 'title1',
            type: 'value',
            position : 'left',
            splitLine:{show: false}
            //max: 2000,
            //splitLine:{show: false}
        },{
            name: 'title2',
            type: 'value',
            position : 'right',
            splitLine:{show: false}
            //nameLocation: 'start',
            //max: 10,
            //splitLine:{show: false}
        }],
        series: [{
            name: 'title1',
            smooth: true,
            yAxisIndex:0,
            type: 'line',
            data: [0,0],
            itemStyle:orangeStyle,
            axisLine : {
                lineStyle : {}
            },
            axisLabel : {
                formatter : '{value}'
            }
        },{
            name: 'title2',
            smooth: true,
            yAxisIndex:1,
            type: 'line',
            data: [0,0],
            itemStyle:yellowStyle,
            axisLine : {
                lineStyle : {}
            },
            axisLabel : {
                formatter : '{value}'
            }
        }]
    };
    myChart.setOption(option);
}

//页面加载数据
$(function(){

    initEchartsDefualtOption();

    commonFn.datapickerSet(false, true);
    //加载搜索框事件
    adPlanSearchAddListenerEvent();
    //显示头部广告主数据
    showHeard();
    //显示广告主计划列表
    showOwnerPlanList('');
    //加载全部Top榜数据
    getAllTopData();
    //加载折线图
    loadPlanEcharts();
});


/**
 * 获取顶部广告主统计数据方法
 */
function showHeard() {
    var ownerId = $("#ownerId").val();
    $.ajax({
        type : "POST",
        url : '/user/oppo/dashboard/heard/data',
        data : {"ownerId": ownerId},
        dataType : "json",
        success : function(data) {
            var code = data.code;
            if (code == 200) {
                var v = data.data;
                $("#totalAmount").html(v.totalAmount + "元");
                $("#cashBal").html(v.cashBal + "元");
                $("#mrys").html(v.accDayBudget + "元");
                $("#todayCost").html(v.todayCost + "元");
                $('#yesterdayCost').html(v.yesterdayCost + "元");

                $("#totalCount").html(v.totalCount);
                $("#startCount").html(v.startCount);
                $("#auditing").html(v.auditing);
                $("#auditFail").html(v.auditFail);
                $("#notEnough").html(v.notEnough);
                var errorMsg = '';
                if(v.balanceMsg){
                    errorMsg = v.balanceMsg;
                }
                if(v.adCountMsg){
                    errorMsg+= '<br>' + v.adCountMsg;
                }
                if(errorMsg && errorMsg.length>0){
                    layer.alert(errorMsg, {
                        icon : 5,
                        title : '异常提示',
                        skin : 'layui-layer-orange'
                    });
                }
            }else{
                var backMsg = data.message ? data.message : '加载顶部统计数据异常，请稍后刷新重试！或联系管理人员查找问题！';
                layer.alert(backMsg, {
                    icon : 5,
                    title : '异常提示',
                    skin : 'layui-layer-orange'
                });
            }
        },
        error: function(data) {
            layer.alert('服务异常！', {
                icon : 5,
                title : '异常提示',
                skin : 'layui-layer-orange'
            });
        }
    });
}

function editBtn() {
    $("#editBtn").addClass("none");
    $("#editBox").removeClass("none");
    $("#mrys").addClass("none");
    $("#newDayBudget").val($("#mrys").html().replace('元', ''));
}

function editCancel() {
    $("#editBtn").removeClass("none");
    $("#editBox").addClass("none");
    $("#mrys").removeClass("none");
    $("#newDayBudget").val("");
}

/**
 * 修改客户日预算方法
 */
function doEdit() {
    var ownerId = $("#ownerId").val();
    var oldDayBudget = $("#mrys").html().replace('元', '');
    var accDayBudget = $("#newDayBudget").val();
    var pattern = /^[0-9]+(\.[0-9]{1,2})?$/;
    var errorMsg = "每日预算必须为大于0的数字格式，且最多保留小数点后2位(最小精度到分 如：800.85)";
    if(!pattern.test(accDayBudget)){
        layer.alert(errorMsg, {
            icon: 0,
            title : '操作提示',
            skin : 'layui-layer-orange'
        });
        return ;
    }
    if(accDayBudget <= 0){
        layer.alert(errorMsg, {
            icon: 0,
            title : '操作提示',
            skin : 'layui-layer-orange'
        });
        return ;
    }
    if(Number(oldDayBudget) == Number(accDayBudget)){
        layer.msg("与原值相等无需修改！");
        return ;
    }
    var loadWait = layer.load(3);
    $.ajax({
        type : "POST",
        url : '/user/oppo/set/dayBudget',
        data : {"ownerId": ownerId, "accDayBudget": accDayBudget},
        dataType : 'json',
        success : function(data) {
            var code = data.code;
            var icon;
            $("#editBtn").removeClass("none");
            $("#editBox").addClass("none");
            $("#mrys").removeClass("none");
            if (code || code == 0) {
                //$("#mrys").html(accDayBudget + "元");
                $("#newDayBudget").val("");
                switch (code) {
                    //成功时
                    case 200: icon=6;$("#mrys").html(accDayBudget + "元");break;
                    //失败
                    case 400: icon=5; break;
                    //其它失败提示
                    default: icon=0; break;
                }
                //直接提示oppo方返回的错误消息
                var msg = data.message;
                layer.alert(msg, {
                    icon: icon,
                    title : '操作提示',
                    skin : 'layui-layer-orange'
                });
            } else {
                $("#newDayBudget").val("");
                layer.msg("[修改日预算]异常中断，请稍后重试！或联系管理人员查找原因!");
            }
        },
        error: function() {
            $("#editBtn").removeClass("none");
            $("#editBox").addClass("none");
            $("#mrys").removeClass("none");
            $("#newDayBudget").val("");
            layer.msg("修改失败！");
        },
        complete:function(){
            layer.close(loadWait);
        }
    });
}

/**
 * 展示左侧广告主-广告计划列表方法
 * @param adName
 */
function showOwnerPlanList(adName) {
    var ownerId = $("#ownerId").val();
    var loadWait = layer.load(3);
    $.ajax({
        type : "POST",
        url : '/user/oppo/dashboard/owner/planList',
        data : {"ownerId": ownerId,"adName":adName},
        dataType : 'json',
        success : function(data) {
            var code = data.code;
            if (code == 200) {
                var v = data.data;
                var dataList = v.dataList;
                $("#ad_list").empty();
                if(dataList && dataList.length > 0){
                    var _html = '';
                    var item,planId,planName;
                    for (var i = 0; i < dataList.length; i++) {
                        item = dataList[i];planId = item.planId;planName = item.planName;
                        //"/user/oppo/overview/plan/'+ planId+'"
                        _html =_html + '<li class="scan-menu">'
                            + '<p class="scan-tit">'
                            + '<a href="#"  onclick="gotoOwnerAdDetailQueryPage('+ownerId+','+planId+');" >' + planName + '</a>'
                            + '</p>'
                            +'</li>';
                    }
                    $("#ad_list").append(_html);
                }
            }else{
                var msg = data.message ? data.message : "获取左侧广告主计划列表失败!";
                layer.alert(msg, {
                    icon: 5,
                    title : '操作提示',
                    skin : 'layui-layer-orange'
                });
            }
        },
        error: function() {
            layer.alert('服务器响应异常，获取左侧广告主计划列表失败!', {
                icon: 5,
                title : '操作提示',
                skin : 'layui-layer-orange'
            });
        },
        complete:function(){
            layer.close(loadWait);
        }
    });
}

/**
 *  跳转广告数据详情查询页面
 **/
function gotoOwnerAdDetailQueryPage(ownerId,planId){
    window.location.href = "/user/oppo/detail/query/"+ownerId+"/"+planId;
}

/**
 * 跳转到广告管理中-广告【计划】、【创意】列表中
 */
function gotoAdManagementListPage(index){
    var page_href = '#';
    switch (index) {
        //全部广告
        case 1 : page_href='/advertiseIdea/idea/list';break;
        //有效广告
        case 2 : page_href='/advertiseIdea/idea/list?showStatus=110';break;
        //正在审核广告
        case 3 : page_href='/advertiseIdea/idea/list?showStatus=220223';break;
        //审核失败广告
        case 4 : page_href='/advertiseIdea/idea/list?showStatus=222';break;
        //预算不足计划
        case 5 : page_href='/advertisePlan/plan/list?showStatus=4';break;
    }
    window.location.href = page_href;
}

/**
 * 给计划搜索框添加事件-按回车键进行搜索
 */
function adPlanSearchAddListenerEvent(){
    $("#search_ad").keyup(function(event) {
        var adNameValue = event.target.value;
        var keycode = event.keyCode;
        if (keycode == 13) {
            showOwnerPlanList(adNameValue);
        }
    });
}

/**
 * 页面加载时 获取全部TOP榜数据
 */
function getAllTopData(){
    getOwnerTodayTopData('exposeSelect','exposeTopList');
    getOwnerTodayTopData('clickSelect','clickTopList');
    getOwnerTodayTopData('costSelect','costTopList');
    getOwnerTodayTopData('downloadSelect','downloadTopList');
}

/**
 * 获取广告主当天TOP数据
 * @param selectId 下拉选择框id
 * @param topListId top数据列表id
 */
function getOwnerTodayTopData(selectId,topListId){
    var loadWait = layer.load(3);
    var demision = $("#"+selectId).val();
    var topListTag = $("#"+topListId);
    var ownerId = $("#ownerId").val();
    $.ajax({
        type: "POST",
        url: '/user/oppo/dashboard/remote/top5',
        data: {"ownerId": ownerId, "demision": demision},
        dataType: 'json',
        success: function (data) {
            var code = data.code;
            if (code == 200) {
                var v = data.data;
                var dataList = v.topList;
                //1.清空列表项
                topListTag.empty();
                if(dataList && dataList.length > 0){
                    //2.根据结果集填充到列表项中
                    var _html = '';
                    var item,adId,adName,orderData;
                    var num=0;
                    for (var i = 0; i < dataList.length; i++) {
                        item = dataList[i];adId = item.adId;adName = item.adName,orderData=item.orderData,num=i+1;
                        _html =_html + '<li class="clearfix">'
                            + '<a href="#" class="text-ellipsis">' + num + '.' + adName + '</a>'
                            + '<span class="f-r">' + orderData + '</span>'
                            +'</li>';
                    }
                    topListTag.append(_html);
                }
            } else {
                var msg = data.message ? data.message : "远程获取当日TOP榜数据失败!";
                layer.alert(msg, {
                    icon: 5,
                    title: '操作提示',
                    skin: 'layui-layer-orange'
                });
            }
        },
        error: function () {
            layer.alert('服务器响应异常，远程获取当日TOP榜数据失败!', {
                icon: 5,
                title: '操作提示',
                skin: 'layui-layer-orange'
            });
        },
        complete: function () {
            layer.close(loadWait);
        }
    });
}

/**
 * 加载首页计划Echarts图表数据
 */
function loadPlanEcharts() {
    //加载等待
    var loadWait = layer.load(3);
    //myChart.showLoading();

    var ownerId = $("#ownerId").val();
    var demisionCode1 = $("#echarts_box_chenge1").find("option:selected").val();
    var demisionCode2 = $("#echarts_box_chenge2").find("option:selected").val();
    var demisionName1 = $("#echarts_box_chenge1").find("option:selected").text();
    var demisionName2 = $("#echarts_box_chenge2").find("option:selected").text();
    var echarts_date = $("#echarts_date").val();
    var begin_date = echarts_date.split(" - ")[0];
    var end_date = echarts_date.split(" - ")[1];
    // 开始加载图表数据
    $.ajax({
        type : "POST",
        url : '/user/oppo/dashboard/plan/echarts',
        data : {"ownerId": ownerId, "demisionCode1": demisionCode1, "demisionCode2": demisionCode2,
            "demisionName1":demisionName1,"demisionName2":demisionName2,
            "startDate": begin_date, "endDate": end_date},
        dataType : 'json',
        success : function(data) {
            var code = data.code;
            if(code == 200){
                var v = data.data;
                // 填入数据
                var series = v.series;
                myChart.clear();
                myChart.setOption({
                    title : {
                        text: '广告计划统计图',
                        subtext: '维度统计',
                        show:false
                    },
                    tooltip : {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                color:'#FFFFFF',
                                fontWeight:'bold',
                                backgroundColor: 'rgba(155, 200, 50, 0.5)'
                            }
                        }
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            magicType : {show: true, type: ['line', 'bar']},
                            saveAsImage : {show: true}
                        }
                    },
                    legend: {
                        data: [demisionName1, demisionName2]
                    },
                    xAxis: {
                        type : 'category',
                        data: v.xAxisData
                    },
                    yAxis:[{
                        name: demisionName1,
                        type: 'value',
                        position : 'left',
                        splitLine:{show: false}
                        //max: 2000,
                        //splitLine:{show: false}
                    },{
                        name: demisionName2,
                        type: 'value',
                        position : 'right',
                        splitLine:{show: false}
                        //nameLocation: 'start',
                        //max: 10,
                        //splitLine:{show: false}
                    }],
                    series: [{
                        name: demisionName1,
                        smooth: true,
                        yAxisIndex:0,
                        type: 'line',
                        data: series[0].data,
                        itemStyle:orangeStyle,
                        axisLine : {
                            lineStyle : {}
                        },
                        axisLabel : {
                            formatter : '{value}'
                        }
                    },{
                        name: demisionName2,
                        smooth: true,
                        yAxisIndex:1,
                        type: 'line',
                        data: series[1].data,
                        itemStyle:yellowStyle,
                        axisLine : {
                            lineStyle : {}
                        },
                        axisLabel : {
                            formatter : '{value}'
                        }
                    }]
                });

                $("#ech1").html(v.sum1);
                $("#ech2").html(v.sum2);
            }else{
                var errorMsg = data.message ? data.message : "加载折线图数据失败！";
                layer.msg(errorMsg);
            }
        },
        error: function () {
            layer.alert('服务器响应异常，加载折线图数据失败!', {
                icon: 5,
                title: '操作提示',
                skin: 'layui-layer-orange'
            });
        },
        complete: function () {
            //myChart.hideLoading();
            layer.close(loadWait);
        }
    });
}
