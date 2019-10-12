var params = getParams();
var url_show_status = getQueStr(location.href,"showStatus");

//初始化页面
$(document).ready(function(){
    $('a[href="#"]').attr('href','javascript:void(0);');

    //获取列表数据
    if (getcookie("operate_type") == 'true'){
        showList(Number(getcookie("current_page")).toFixed(0));
    } else if (url_show_status != null){
        var showStatus = getQueStr(location.href,"showStatus");
        var eqnum = showStatus == '' ? '0' : showStatus == '0' ? '1' : showStatus == '1' ? '2' : showStatus == '2' ? '3' : showStatus == '3' ? '4' : showStatus == '4' ? '5' : showStatus == '10' ? '6' : '0';
        params['showStatus'] = showStatus;
        $('#showStatus').find("option").eq(eqnum).attr("selected", true);    //选中
        showList(0);
    } else {
        showList(0);
    }

	$(".forchge").change(function() {
        $("#list").setGridParam({postData:getParams(), page:1}).trigger("reloadGrid");
	});
	
	$(".search-btn").click( function () {
        $("#list").setGridParam({postData:getParams(), page:1}).trigger("reloadGrid");
	});
	
	$("#planName").keydown(function (e) {
        if (e.keyCode == 13) {
            $(".search-btn").trigger("click");
        }
    });

	//状态修改
	commonFn.btnDownFn(function(){ //确定
		console.log("确定");
	},function(){ //取消
		console.log("取消");
	});
});

//获取查询条件
function getParams() {
	var serializeObj = {};
	$($("#searchForm").formToArray()).each(function() {
		serializeObj[this.name] = $.trim(this.value);
	});
    return serializeObj;
}

$('#batchDelete').on('click',function(){
    if(clickFlag == 0){
        if($('#showStatus').val() == 10){//已删除状态
            layer.msg('广告计划已处于删除状态');
        }else{
            patchDelPlan();
        }
    }
});

//批量删除计划
function patchDelPlan(){
	var selectedRows =  $("#list").jqGrid('getGridParam', 'selarrrow');
	var i=0;
	var len = selectedRows.length;
	if(len <= 0){
        layer.msg('请选择要批量删除的广告计划');
        return;
    }
	var pIds =new Array();
	while(i<len){
		pIds.push($($('#list').jqGrid('getCell',selectedRows[i],3)).text());
        i++;
	}
	var url = "/advertisePlan/plan/delPlan";
	var json = {};
	json["pIds"] = pIds;
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;color:#cd0a0a;'> 批量删除 </span>选中的计划？选中计划下的所有广告组与广告创意将会同步删除，且删除后不可恢复，请谨慎操作。", {
            icon:"3",
            title: "温馨提示",
            btn: ['确定','取消'],
            skin: 'layui-layer-skyBlue',
            closeBtn: false,
            shade: 0.1,
            shadeClose: true
        }, function(index){
            clickFlag = 1;
            deal(url,json,index);
        }, function(index){
            layer.close(index);
        });
    }
}

//单个删除计划
function singleDelPlan(planId){
	var pIds =new Array();
	pIds.push(planId);
	var url = "/advertisePlan/plan/delPlan";
	var json = {};
	json["pIds"] = pIds;
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;color:#cd0a0a;'> 删除 </span>本计划？该计划下的所有广告组与广告创意将会同步删除，且删除后不可恢复，请谨慎操作。", {
            icon:"3",
            title: "温馨提示",
            btn: ['确定','取消'],
            skin: 'layui-layer-skyBlue',
            closeBtn: false,
            shade: 0.1,
            shadeClose: true
        }, function(index){
            clickFlag = 1;
            deal(url,json,index);
        }, function(index){
            layer.close(index);
        });
    }
}

$('#status2_sure').on('click',function(){
	if(clickFlag == 0){
        if($('#showStatus').val() == 10){//已删除状态
            layer.msg('广告计划已处于删除状态，不可投放');
        }else{
            patchSwitchStatus();
		}
	}
});

//批量切换状态
function patchSwitchStatus(){
	var selectedRows =  $("#list").jqGrid('getGridParam', 'selarrrow');
	var i=0;
	var len = selectedRows.length;
	if(len <= 0){
		layer.msg('请选择要修改状态的广告计划');
		return;
	}
	var pIds =new Array();
	while(i<len){
		pIds.push($($('#list').jqGrid('getCell',selectedRows[i],3)).text());
        i++;
	}
	var status2 = $("input:radio[name=status2]:checked").val();
	var url = "/advertisePlan/plan/switchStatus";
	var json = {};
	json["pIds"] = pIds;
	json["status2"] = status2;
	deal(url, json);
}

//单个切换状态
function singleSwitchStatus(planId, status2){
    if($('#showStatus').val() == 10){//已删除状态
        layer.msg('广告计划已处于删除状态，不可投放');
        return;
    }
	var pIds =new Array();
	pIds.push(planId);
	var url = "/advertisePlan/plan/switchStatus";
	var json = {};
	json["pIds"] = pIds;
	json["status2"] = status2;
	deal(url, json);
}

/**
 * 后台统一处理方法
 * @param url
 * @param json
 */
function deal(url, json, index){
	var current_page = $('#list').jqGrid('getGridParam', 'page');
	$.ajax({
        url:url,
        type:"post",
        data : json,
        dataType: "json",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        error: function(resp){
            layer.alert(resp.errorMsg, {
            	icon:"2",
                title:'异常提示',
                skin: 'layui-layer-skyBlue'
            });
            clickFlag = 0;
        },
        success: function (resp){
            layer.close(index);
            if(resp.code == 200){
                $("#list").trigger("reloadGrid", { fromServer: true, page: current_page });
            }else{
                layer.alert(resp.message, {
                    icon:"5",
                    title:'温馨提示',
                    skin: 'layui-layer-skyBlue'
                });
            }
            clickFlag = 0;
        }
    });
}
function exportGrid(){
	$("#list").jqGrid('exportToExcel', 
		{
		  includeLabels : true,
		  includeGroupHeader : true,
		  includeFooter: true,
		  fileName : "jqGridExport.xlsx",
		  mimetype : "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
		  maxlength : 40,
		  onBeforeExport : null,
		  replaceStr : null
		}
	);
}

/**
 * 报表下载功能
 * @param url
 * @param name
 */
function exportEx(url,name) {
	var dataCount = $("#sp_1_pager2").text();
    if (dataCount <= 0) {
    	layer.msg("列表数据为空，无需导出");
        return;
    }
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;'> 导出 </span>当前查询条件的广告计划列表？", {
            icon:"3",
            title: "温馨提示",
            btn: ['确定','取消'],
            skin: 'layui-layer-skyBlue',
            closeBtn: false,
            shade: 0.1,
            shadeClose: true
        }, function(index){
            layer.close(index);
            clickFlag = 1;

            //参数设置
            var form = $('#' + name)[0];
            var params = '';
            if (form) {
                var els = form.elements;
                for (var i=0;i<form.length;i++) {
                    var element = form.elements[i];
                    var eName = element.name;
                    var eValue = element.value;
                    if (eName && eValue && eName != 'status2') {
                        params += eName + "=" + eValue + "&";
                    }
                }
            }
            if (params.length > 0) {
                params = params.substring(0, params.length - 1);
                url = url + '?' + params;
            }
            document.location = url;

            //url = url + '?planName=' + $('#planName').val() + "&chnId=" + $('#chnId').val() + "&extensionType=" + $('#extensionType').val() + "&showStatus=" + $('#showStatus').val();
            clickFlag = 0;
        }, function(index){
            layer.close(index);
        });
    }
}

/**
 * 分页列表数据
 */
function showList(page){
	var bodyW = (document.documentElement.clientWidth || document.body.clientWidth)-20;
    var totalH = getHeight();
	if(bodyW < 1466){
		bodyW = 1466;
	}

	$("#list").jqGrid({
		url : '/advertisePlan/plan/local/list',
        datatype : "json",//请求数据返回的类型。可选json,xml,txt
        postData : params,
		page : page,
        rowNum : 10,
        rowList : [ 10, 20, 50, 100],
        width : bodyW,
        height : totalH,
		colNames : [ 'id', '计划名称', '计划ID', '推广渠道', '推广目的', '计划日预算', '曝光量', '点击次数', '点击率', '下载次数', '下载率', '消耗金额', '计划状态', '投放开关', '操作'  ],//jqGrid的列显示名字
		colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
			{name : 'id',key: false, hidden: true, index : 'id',width : 20},
			{name : 'planName',index : 'planName', width : 180, align : "left", sortable: false, formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 0){
					return '<a href="/advertiseGroup/group/list?pId='+rowObject.planId+'" title="点击跳转至该计划的广告组列表" class="m-r-5">'+cellvalue+'</a>';
                }else{
					return cellvalue;
				}
			}},
			{name : 'planId',index : 'planId',width : 100, align : "center", sortable: false, formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 0){
					return '<a href="/advertiseGroup/group/list?pId='+rowObject.planId+'" title="点击跳转至该计划的广告组列表" class="m-r-5">'+cellvalue+'</a>';
				}else{
					return cellvalue;
				}
			}},
			{name : 'chnId',index : 'chnId',width : 90,align : "center", sortable: false, formatter:function(cellvalue, options, rowObject){
				cellvalue = cellvalue=='1'?"软件商店":cellvalue=='2'?"非软件商店":cellvalue=='3'?"联盟":"";
				return cellvalue;
			}},
			{name : 'extensionType',index : 'extensionType',width : 90,align : "center", sortable: false, formatter:function(cellvalue, options, rowObject){
			 cellvalue = cellvalue=='1'?"链接推广":cellvalue=='2'?"应用推广":"";
			 	return cellvalue;
			}},
			{name : 'dayBudget',index : 'dayBudget',width : 100,align : "center", sortable: true, formatter:function(cellvalue, options, rowObject){
				var cellHtml = '';
				if(cellvalue=='' || null == cellvalue || rowObject.dayLimit == 0)
					cellvalue='不限额';
				else if(cellvalue%100==0)
					cellvalue=cellvalue/100+".00";
				else if(cellvalue%100!=0)
					cellvalue=cellvalue/100;
				cellHtml = '<span class="edit-text">'+ cellvalue +'</span><span class="edit-inp-box none"><input type="number" data-val="'+ cellvalue + '" value="'+ cellvalue + '" class="set-money-inp input-28" /><span class="index-icon-box icon-close" onclick="cancelBudgetFn(this)"></span><span class="index-icon-box icon-submit" onclick="submitBudgetFn(this, '+ rowObject.planId + ')"></span></span><span class="index-icon-box icon-edit" onclick="setBudgetFn(this)"></span>';
				return cellvalue == '不限额' ? cellvalue : cellHtml;
			}},
			{name : 'expose',index : 'expose',width : 80,align : "center",sortable : true},
			{name : 'click',index : 'click',width : 90,align : "center", sortable: true},
			{name : 'clickrate',index : 'clickrate',width : 80, align : "center", sortable: true, formatter: function(cellvalue, options, rowObject){
				var radices = rowObject.expose;
				if(radices == 0) {
					return "0.00%";
				} else {
				var str = Number((rowObject.click/radices)*100).toFixed(2);
					str+="%";
					return str;
				}
			}},
			{name : 'download',index : 'download',width : 90, align : "center", sortable: true},
			{name : 'downloadrate',index : 'downloadrate',width : 80, align : "center", sortable: true, formatter: function(cellvalue, options, rowObject) {
				var radices = rowObject.expose;
				if(radices == 0) {
					return "0.00%";
				}else {
					var str = Number((rowObject.download/radices)*100).toFixed(2);
					str+="%";
					return str;
				}
			}},
			{name : 'cost',index : 'cost',width : 90, align : "center", sortable: true, formatter: function(cellvalue, options, rowObject){
				return Number(cellvalue/100).toFixed(2);
			}},
			{name : 'showStatus',index : 'showStatus',width : 120, align: "center", sortable: false, formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 1){
                    cellvalue = "已删除";
				}else{
                    cellvalue = cellvalue=='0'?"启动中":cellvalue=='1'?"暂停":cellvalue=='2'?"余额不足":cellvalue=='3'?"达到账户预算":cellvalue=='4'?"达到计划预算":"";
                }
				return cellvalue;
			}},
			{name : 'status2',index : 'status2',width : 80, align: "center", sortable: false,formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 1){
                    return '<span id=status2'+rowObject.planId+' onclick="singleSwitchStatus('+rowObject.planId+', 0)" class="switch-box"><i class="switch-circle"></i></span>';
				} else {
                    if (cellvalue == '0') {
                        return '<span id=status2' + rowObject.planId + ' onclick="singleSwitchStatus(' + rowObject.planId + ', 1)" class="switch-box open"><i class="switch-circle"></i></span>';
                    } else {
                        return '<span id=status2' + rowObject.planId + ' onclick="singleSwitchStatus(' + rowObject.planId + ', 0)" class="switch-box"><i class="switch-circle"></i></span>';
                    }
                }
			}},
			{name : 'status',index : 'status',width : 180, align: "center", sortable: false, formatter: function(cellvalue, options, rowObject) {
				var is_valid = rowObject.isValid;
				if(is_valid == 1){
                    return '<div class="txt-nowrap">' +
                        '<a href="javascript:void(0);" class="m-r-5 grey-color" title="编辑">编辑</a>' +
                        '<a href="javascript:void(0);" class="m-r-5 grey-color" title="复制">复制</a>' +
                        '<a href="javascript:void(0);" class="m-r-5 grey-color" title="删除">删除</a>' +
                        '<a href="javascript:void(0);" class="m-r-5 grey-color" title="折线图">折线图</a></div>';
				} else {
					return '<div class="txt-nowrap">' +
						'<a href="javascript:void(0);" onclick="editPlan('+rowObject.planId+')" class="m-r-5" title="编辑">编辑</a>' +
                        '<a href="javascript:void(0);" onclick="copyPlan('+rowObject.planId+')" class="m-r-5" title="复制">复制</a>' +
						'<a href="javascript:void(0);" onclick="singleDelPlan('+rowObject.planId+')" class="m-r-5" title="删除">删除</a>' +
                        '<a href="javascript:void(0);" onclick="chartLinePlan('+rowObject.planId+')" class="m-r-5" title="折线图">折线图</a></div>';
				}
			}}
		],
        ordering : true,
		multiselect : true,
        //multiSort: true,			//设置组合排序
        //sortname :"expose,click",	//设置默认组合排序参数
        sortable:true,				//设置单个排序
        //sortname :"expose",		//设置默认单个排序参数
        sortorder : "desc",
		jsonReader : {
			root :"data.page_list",
			page : "data.currentPage",
			total : "data.total",
			records : "data.records",
			cell : "",
			id : "id"
		},
        viewrecords : true,		//是否显示总条数
        hidegrid : false,
        emptyrecords : "暂无数据",	//列表数据为空是显示文字
		pager : '#pager2',//表格页脚的占位符(一般是div)的id
        loadComplete:function(resp){
		    if(resp.code != 200){
                layer.alert(resp.message, {
                    icon:"5",
                    title:'温馨提示',
                    skin: 'layui-layer-skyBlue'
                });
            }
            var current_page = $('#list').jqGrid('getGridParam', 'page');
            setcookie("current_page",current_page,"d1");//当前页页码，一天有效期
            setcookie("operate_type",false,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
		}
	});
}

$('#add_plan').on('click',function(){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消
    window.location.href = "/advertisePlan/plan/add/to";
});

function editPlan(planId){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
	window.location.href = "/advertisePlan/plan/add/to?pId="+planId+"&operateType=E";
}

function copyPlan(planId){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
    window.location.href = "/advertisePlan/plan/add/to?pId="+planId+"&operateType=C";
}

function setBudgetFn(self) { //日预算 设置
	var $self = $(self),
        $text = $self.siblings(".edit-text"),
		$edit = $self.siblings(".edit-inp-box");
    $text.addClass("none");
    $self.addClass("none");
    $edit.removeClass("none");
}
function cancelBudgetFn(self) { //日预算 设置 取消
    var $self = $(self),
		$sp = $self.parent(),
        $text = $sp.siblings(".edit-text"),
        $edit = $sp.siblings(".icon-edit"),
    	$inp = $self.siblings(".set-money-inp"),
		$inpv = $inp.attr("data-val");
    $text.removeClass("none");
    $edit.removeClass("none");
    $sp.addClass("none");
    $inp.val($inpv);
}
function submitBudgetFn(self, planId) { //日预算 设置 提交
    var $self = $(self),
        $sp = $self.parent(),
        $text = $sp.siblings(".edit-text"), //编辑前样式
        $edit = $sp.siblings(".icon-edit"), //编辑按钮
        $inp = $self.siblings(".set-money-inp"); //input
	var dayBudget = $inp.val();
	if(dayBudget == null || dayBudget == ''){
		layer.msg("请输入计划日预算");
		return false;
	}
    $text.removeClass("none");
    $edit.removeClass("none");
    $sp.addClass("none");
    dayBudget = (Number(dayBudget) * 100).toFixed(0);
    var json = {};
    json["planId"] = planId;
    json["dayBudget"] = dayBudget;
    if(clickFlag == 0){
        clickFlag = 1;
        //ajax
        $.ajax({
            url: '/advertisePlan/plan/updateDayBudget',
            type: "post",
            data: json,
            dataType: "json",
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error: function(resp){
                layer.alert(resp.errorMsg, {
                    icon:"2",
                    title:'异常提示',
                    skin: 'layui-layer-skyBlue'
                });
                clickFlag = 0;
            },
            success: function (resp){
                layer.closeAll();
                if(resp.code == 200){
                    $inp.attr("data-val", dayBudget);
                    $text.text((dayBudget/100).toFixed(2));
                    layer.msg("修改成功<br>" + resp.message,{
                        time : 5000
                    });
                }else{
                    layer.alert(resp.message, {
                        icon:"5",
                        title:'温馨提示',
                        skin: 'layui-layer-skyBlue'
                    });
                }
                clickFlag = 0;
            }
        });
    }
}

var myChart = echarts.init($(".plan-item-chart")[0]);
function planItemChart(xAxis, data) {
	var option = {
        title: {
            text: ""
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        legend: {
            data: xAxis
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : xAxis
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : data
    };
    myChart.setOption(option);
}
function chartLinePlan(planId) { //折线图
    if(clickFlag == 0){
        clickFlag = 1;
        layer.msg("加载中，请稍后...",{
            time : 0	//不延时关闭
        });
        //ajax
        $.ajax({
            url: '/advertisePlan/plan/chartLine',
            type: "post",
            data: {'planId': planId},
            dataType: "json",
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            error: function(resp){
                layer.alert(resp.message, {
                    icon:"2",
                    title:'异常提示',
                    skin: 'layui-layer-skyBlue'
                });
                clickFlag = 0;
            },
            success: function (resp){
                layer.closeAll();
                if(resp.code == 200){
                    var xAxis = [];    //横轴小时数组
                    xAxis = resp.data.hours;
                    var data = [];
                    data = resp.data.datas;
                    layer.open({
                        type: 1,
                        title: "消耗环比折线图",
                        closeBtn: true,
                        shadeClose: true,
                        skin: 'layui-layer-skyBlue',
                        area:["840px","460px"],
                        content: $(".plan-item-chart"),
                        success: function(){
                            planItemChart(xAxis, data);
                        }
                    });
                }else{
                    layer.alert(resp.message, {
                        icon:"5",
                        title:'温馨提示',
                        skin: 'layui-layer-skyBlue'
                    });
                }
                clickFlag = 0;
            }
        });
    }
}