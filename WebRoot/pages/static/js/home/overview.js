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

$(function(){
    //初始化默认值
    initEchartsDefualtOption();

    /*$(window).resize(function(){
        $("#list").setGridWidth($(window).width());
    });*/

    commonFn.datapickerSet(false, true);
    //加载搜索框事件
    adSearchAddListenerEvent();
    //加载左侧广告列表树
    loadLeftAdDataList('');

    //先初始化grid列表
    loadGridList();

    //默认点击节点事件
    clickTreeNodeChangeData(data_Id,data_Name,data_Type,false);

    //加载Echarts图表数据
    //loadEchartsAndGridListData();
    //加载GridList列表数据
    //loadGridList();
});

/**
 * 给广告搜索框添加事件-按回车键进行搜索
 */
function adSearchAddListenerEvent(){
    $("#search_ad").keyup(function(event) {
        var adNameValue = event.target.value;
        var keycode = event.keyCode;
        if (keycode == 13) {
            loadLeftAdDataList(adNameValue);
        }
    });
}

/**
 * 加载左侧广告列表树
 * @param adName
 */
function loadLeftAdDataList(adName){
    var ownerId = $("#ownerId").val();
    var loadWait = layer.load(3);
    $.ajax({
        type : "GET",
        url : '/user/oppo/detail/query/list',
        data : {"dataId": ownerId,"dataName":adName},
        dataType : 'json',
        success : function(data) {
            var code = data.code;
            if (code == 200) {
                var v = data.data;
                var treeList = v.treeList;
                $("#adList").treeFn({
                    data: treeList,
                    refresh:true,
                    select: data_Id
                }, function(id, name, type){
                    clickTreeNodeChangeData(id, name, type,true);
                });
            }else{
                var msg = data.message ? data.message : "获取左侧广告列表失败!";
                layer.alert(msg, {
                    icon: 5,
                    title : '操作提示',
                    skin : 'layui-layer-orange'
                });
            }
        },
        error: function() {
            layer.alert('服务器响应异常，获取左侧广告列表失败!', {
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
 * 点击树节点事件方法
 * @param id 数据id
 * @param name 数据名称
 * @param type 数据类型
 */
function clickTreeNodeChangeData(id, name, type,refresh){
    //1.修改全局变量
    data_Id = id;
    data_Name = name;
    data_Type = type;
    //2.修改页面显示值
    $("#ad_title").html(data_Name);
    var editBarText = '', editHref = 'javascript:void(0);';
    switch (data_Type) {
        case 'plan': editBarText = '编辑广告计划';editHref = "/advertisePlan/plan/add/to?pId="+data_Id+"&operateType=E";break;
        case 'group': editBarText = '编辑广告组';editHref = "/advertiseGroup/group/add/to?gId="+data_Id+"&operateType=E";break;
        case 'ad': editBarText = '编辑广告创意';editHref = "/advertiseIdea/idea/add/to?adId="+data_Id+"&operateType=E";break;
    }
    $("#ad_tie").html(editBarText);
    $("#ad_tie").attr("href", editHref);
    //3.根据当前参数重新加载数据
    loadEchartsAndGridListData();
    if(refresh){
        //layer.load(3);
        $("#list").setGridParam({postData:getGridListParams(), page:1}).trigger("reloadGrid");
    }
}

/**
 * 点击搜索按钮时-重新加载Echart图表和GridList列表数据
 */
function searchReloadEchartsAndGridList(){
    loadEchartsAndGridListData();
    //layer.load(3);
    $("#list").setGridParam({postData:getGridListParams(), page:1}).trigger("reloadGrid");
}

/**
 * 加载Echarts图表数据
 */
function loadEchartsAndGridListData() {
    //加载等待
    var loadWait = layer.load(3);
    //myChart.showLoading();
    var ownerId = $("#ownerId").val();
    var dataId = data_Id;
    var dataType = data_Type;
    var demisionCode1 = $("#echarts_box_chenge1").find("option:selected").val();
    var demisionCode2 = $("#echarts_box_chenge2").find("option:selected").val();
    var demisionName1 = $("#echarts_box_chenge1").find("option:selected").text();
    var demisionName2 = $("#echarts_box_chenge2").find("option:selected").text();
    var echarts_date = $("#echarts_date").val();
    var begin_date = echarts_date.split(" - ")[0];
    var end_date = echarts_date.split(" - ")[1];
    // 开始加载图表数据
    $.ajax({
        type : "GET",
        url : '/user/oppo/detail/query/echarts',
        data : {"ownerId": ownerId, "dataId" : dataId,"dataType":dataType,
            "demisionCode1": demisionCode1, "demisionCode2": demisionCode2,
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
                        text: '广告统计图表',
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
                        top:32,
                        left:'center',
                        data: [demisionName1, demisionName2]
                    },
                    calculable : true,
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


/**
 * 获取GridList加载参数
 */
function getGridListParams() {
    var dataId = data_Id;
    var dataType = data_Type;
    var ownerId = $("#ownerId").val();
    var echarts_date = $("#echarts_date").val();
    var startDate = echarts_date.split(" - ")[0];
    var endDate = echarts_date.split(" - ")[1];
    var serializeObj = {
        'ownerId':ownerId,
        'dataId':dataId,
        'dataType':dataType,
        'startDate':startDate,
        'endDate':endDate
    };
    return serializeObj;
}

/**
 * 显示列表数据
 */
function loadGridList(){
    layer.load(3);
    var bodyW = (document.documentElement.clientWidth || document.body.clientWidth)-290;
    $("#list").jqGrid({
        url: '/user/oppo/detail/query/gridList',
        mtype:'GET',
        postData: getGridListParams(),
        datatype : "json",//请求数据返回的类型。可选json,xml,txt
        page: 0,
        rowNum : 10,
        rowList : [ 10, 20, 50, 100],
        width: bodyW,
        height: "350px",
        footerrow: false,
        scrollrows:true,
        rownumbers:true,
        loadui: "disable",
        colNames : ['时间', '曝光量', '点击量', '点击率', '点击均价', '下载量', '下载率', '下载均价', '消耗金额（元）','ECPM' ],//jqGrid的列显示名字
        colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....

            {name : 'dayStr',index : 'dayStr',width : 50, align: "center", sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? cellvalue : '-'; }
            },
            {name : 'expose',index : 'expose',width : 50, align: "center", sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? cellvalue : '0'; }
            },
            {name : 'click',index : 'click',width : 50, align: "center", sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? cellvalue : '0'; }
            },
            {name : 'ctr',index : 'ctr',width : 50, align: "center", sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? ''+cellvalue+'%' : '0.00%'; }
            },
            {name : 'clickPrice',index : 'clickPrice',width : 50, align: "center", sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? cellvalue : '0.00'; }
            },
            {name : 'download',index : 'download',width : 50, align: "center",sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? cellvalue : '0'; }
            },
            {name : 'downloadRate',index : 'downloadRate',width : 50, align: "center", sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? ''+cellvalue+'%' : '0.00%'; }
            },
            {name : 'downloadPrice',index : 'downloadPrice',width : 50, align: "center", sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? cellvalue : '0.00'; }
            },
            {name : 'costY',index : 'costY',width : 50, align: "center",sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? cellvalue : '0.00'; }
            },
            {name : 'ecmp',index : 'ecmp',width : 50, align: "center",sortable: true,
                formatter: function(cellvalue, options, rowObject){ return cellvalue ? cellvalue : '0.00'; }
            }
        ],
        multiselect : false,
        multiSort: false,					//设置组合排序
        //sortname :"cashBal,rebateBal",	//设置默认组合排序参数
        sortable:true,						//设置单个排序
        sortname :"dayStr",				//设置默认单个排序参数
        sortorder : "asc",
        jsonReader : {
            root:"data.page_list",
            page: "data.currentPage",
            total: "data.total",
            records: "data.records",
            repeatitems: false,
            cell: "",
            id : "id"
        },
        viewrecords:true,		//是否显示总条数
        hidegrid:false,
        emptyrecords:"无数据",	//列表数据为空是显示文字
        pager: '#pager2',//表格页脚的占位符(一般是div)的id
        beforeRequest:function(){
            //发起请求前事件
            layer.load(3);
        },
        loadComplete:function(xhr){
            //服务器返回响应事件
            layer.close(layer.index);
            if(xhr && xhr.code != 200){
                var errorMsg = xhr.message ? xhr.message : "列表数据加载失败！";
                layer.msg(errorMsg,{time:3000});
            }
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
        }
    });
}
