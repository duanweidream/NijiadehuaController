//初始化页面
$(document).ready(function(){
    $('a[href="#"]').attr('href','javascript:void(0);');

    //获取页面数据
    if (getcookie("operate_type") == 'true'){
        showList(Number(getcookie("current_page")).toFixed(0));
    } else {
        showList(0);
    }
	
	$(".forchge").change(function() {
		$("#list").setGridParam({postData:getParams(), page:1}).trigger("reloadGrid");
	});

	$("#adGroupName").keyup(function(e){ //回车 搜索
		if (e.keyCode == 13) {
			$(".search-btn").trigger("click");
		}
	});

	$(".search-btn").click( function () {
		$("#list").setGridParam({postData:getParams(), page:1}).trigger("reloadGrid");
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

//批量删除广告组
function patchDelGroup(){
    if($('#showStatus').val() == 10){//已删除状态
        layer.msg('广告组已处于删除状态');
        return;
    }

	var selectedRows =  $("#list").jqGrid('getGridParam', 'selarrrow');
	var i=0;
	var len = selectedRows.length;
    if(len <= 0){
        layer.msg('请选择要批量删除的广告组');
        return;
    }
	var gIds =new Array();
	while(i<len){
		gIds.push($($('#list').jqGrid('getCell',selectedRows[i],3)).text());
        i++;
	}
	var url = "/advertiseGroup/group/delGroup";
	var json = {};
	json["gIds"] = gIds;

    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;color:#cd0a0a;'> 批量删除 </span>选中的广告组？选中广告组下的所有广告创意将会同步删除，且删除后不可恢复，请谨慎操作。", {
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
//单个删除广告组
function singleDelGroup(adGroupId){
    if($('#showStatus').val() == 10){//已删除状态
        layer.msg('广告组已处于删除状态');
        return;
    }

	var gIds =new Array();
	gIds.push(adGroupId);
	var url = "/advertiseGroup/group/delGroup";
	var json = {};
	json["gIds"] = gIds;
    console.log(gIds);
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;color:#cd0a0a;'> 删除 </span>本广告组？该广告组下的所有广告创意将会同步删除，且删除后不可恢复，请谨慎操作。", {
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
//批量切换状态
function patchSwitchStatus(){
    if($('#showStatus').val() == 10){//已删除状态
        layer.msg('广告组已处于删除状态，不可投放');
        return;
    }

	var selectedRows =  $("#list").jqGrid('getGridParam', 'selarrrow');
	var i=0;
	var len = selectedRows.length;
    if(len <= 0){
        layer.msg('请选择要修改状态的广告组');
        return;
    }
	var gIds =new Array();
	while(i<len){
		gIds.push($($('#list').jqGrid('getCell',selectedRows[i],3)).text());
        i++;
	}
	var status2 = $("input:radio[name=status2]:checked").val();
	var url = "/advertiseGroup/group/switchStatus";
	var json = {};
	json["gIds"] = gIds;
	json["status2"] = status2;
	deal(url, json);
}
//单个切换状态
function singleSwitchStatus(groupId, status2){
    if($('#showStatus').val() == 10){//已删除状态
        layer.msg('广告组已处于删除状态，不可投放');
        return;
    }

	var gIds =new Array();
	gIds.push(groupId);
	var url = "/advertiseGroup/group/switchStatus";
	var json = {};
	json["gIds"] = gIds;
	json["status2"] = status2;
	deal(url, json);
}

//后台处理
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
function exportEx(url, name) {
	var dataCount = $("#sp_1_pager2").text();
    if (dataCount <= 0) {
        if (dataCount <= 0) {
            layer.msg("列表数据为空，无需导出");
            return;
        }
    }
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;'> 导出 </span>当前查询条件的广告组列表？", {
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
            clickFlag = 0;
        }, function(index){
            layer.close(index);
        });
    }
}

function showList(page){
	var bodyW = (document.documentElement.clientWidth || document.body.clientWidth)-20;
    var totalH = getHeight();
	if(bodyW < 1512){
		bodyW = 1512;
	}
	$("#list").jqGrid({
		url: '/advertiseGroup/group/local/list',
		postData: getParams(),
		datatype : "json",//请求数据返回的类型。可选json,xml,txt
		page: page,
        rowNum : 10,
        rowList : [ 10, 20, 50, 100],
		width : bodyW,
        height : totalH,
		colNames : [ 'id', '组名称', '组ID', '计划名称', '推广样式', '曝光量', '点击次数', '点击率', '下载次数', '下载率', '消耗金额', '出价上限', '组状态', '投放开关', '操作'  ],//jqGrid的列显示名字
		colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
			{name : 'id',key: false, hidden: true, index : 'id',width : 20},
			{name : 'adGroupName',index : 'adGroupName',width : 160,align : "left", sortable: false, formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 0){
					return '<a href="/advertiseIdea/idea/list?gId='+rowObject.adGroupId+'" title="点击跳转至该广告组的广告创意列表" class="m-r-5">'+cellvalue+'</a>';
				}else{
					return cellvalue;
				}
			}},
			{name : 'adGroupId',index : 'adGroupId',width : 80,align : "center", sortable: false, formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 0){
					return '<a href="/advertiseIdea/idea/list?gId='+rowObject.adGroupId+'" title="点击跳转至该广告组的广告创意列表" class="m-r-5">'+cellvalue+'</a>';
				}else{
					return cellvalue;
				}
			}},
			{name : 'planName',index : 'planName',width : 160,align : "left", sortable: false, formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 0){
					return '<a href="/advertiseGroup/group/list?pId='+rowObject.planId+'" title="点击查询该计划的广告组列表" class="m-r-5">'+cellvalue+'</a>';
				}else{
					return cellvalue;
				}
			}},
			{name : 'showType',index : 'showType',width : 140,align : "center", sortable: false, formatter: function(cellvalue, options, rowObject){
				cellvalue = cellvalue=='1024'?'联盟-不限':cellvalue=='1'?'banner':cellvalue=='2'?'插屏':cellvalue=='4'?'开屏':cellvalue=='8'?'原生(联盟)':cellvalue=='16'?'信息流':cellvalue=='32'?'搜索直达(搜索推广)':cellvalue=='64'?'视频':cellvalue=='128'?'软件商店':'';
			 	return cellvalue;
			}},
			{name : 'expose',index : 'expose',width : 90,sortable : true,align : "center"},
			{name : 'click',index : 'click',width : 90,sortable: true,align : "center"},
			{name : 'clickrate',index : 'clickrate',width : 90,align : "center", sortable: true, formatter: function(cellvalue, options, rowObject){
				var radices = rowObject.expose;
				if(radices == 0) {
					return "0.00%";
				}else {
					var str = Number((rowObject.click/radices)*100).toFixed(2);
					str+="%"
					return str;
				}
			}},
			{name : 'download',index : 'download',width : 90, align : "center", sortable: true},
			{name : 'downloadrate',index : 'downloadrate',width : 90, align : "center", sortable: true, formatter: function(cellvalue, options, rowObject) {
				var radices = rowObject.expose;
				if(radices == 0) {
					return "0.00%";
				}else {
					var str = Number((rowObject.download/radices)*100).toFixed(2);
					str+="%"
					return str;
				}
			}},
			{name : 'cost',index : 'cost',width : 90, align : "center", sortable: true, formatter: function(cellvalue, options, rowObject){
                return Number(cellvalue/100).toFixed(2);
            }},
            {name : 'bidLimit',index : 'bidLimit',width : 90, align : "center", sortable: true, formatter: function(cellvalue, options, rowObject){
                return Number(cellvalue/100).toFixed(2);
            }},
			{name : 'showStatus',index : 'showStatus',width : 120, align: "center", sortable: false, formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 1){
					cellvalue = '已删除';
				}else{
                    cellvalue = cellvalue=='0'?'启动中':cellvalue=='1'?'暂停':cellvalue=='2'?'已结束':cellvalue=='3'?'未开始':cellvalue=='4'?'不在推广时段':cellvalue=='5'?'计划暂停':'';
				}
				return cellvalue;
			}},
			{name : 'status2',index : 'status2',width : 100, align: "center", sortable: false,formatter: function(cellvalue, options, rowObject){
				var is_valid = rowObject.isValid;
				if(is_valid == 1){
					return '<span id=status2'+rowObject.adGroupId+' onclick="singleSwitchStatus('+rowObject.adGroupId+', 0)" class="switch-box"><i class="switch-circle"></i></span>';
				} else {
                    if(cellvalue=='0'){
                        return '<span id=status2'+rowObject.adGroupId+' onclick="singleSwitchStatus('+rowObject.adGroupId+', 1)" class="switch-box open"><i class="switch-circle"></i></span>';
                    }else{
                        return '<span id=status2'+rowObject.adGroupId+' onclick="singleSwitchStatus('+rowObject.adGroupId+', 0)" class="switch-box"><i class="switch-circle"></i></span>';
                    }
				}
			}},
			{name : 'status',index : 'status',width : 120, align: "center", sortable: false, formatter: function(cellvalue, options, rowObject) {
				var is_valid = rowObject.isValid;
				if(is_valid == 1){
					return '<div class="txt-nowrap">' +
						'<a href="javascript:void(0);" class="m-r-5 grey-color">编辑</a>' +
                        '<a href="javascript:void(0);" class="m-r-5 grey-color">复制</a>' +
						'<a href="javascript:void(0);" class="m-r-5 grey-color">删除</a>';
				} else {
                    return '<div class="txt-nowrap">' +
                        '<a href="javascript:void(0);" onclick="editGroup('+rowObject.adGroupId+')" class="m-r-5">编辑</a>' +
                        '<a href="javascript:void(0);" onclick="copyGroup('+rowObject.adGroupId+')" class="m-r-5">复制</a>' +
                        '<a href="javascript:void(0);" onclick="singleDelGroup('+rowObject.adGroupId+')" class="m-r-5">删除</a>';
				}
			}}
		],
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
        emptyrecords : "无数据",	//列表数据为空是显示文字
		pager: '#pager2',//表格页脚的占位符(一般是div)的id
        loadComplete:function(){
            var current_page = $('#list').jqGrid('getGridParam', 'page');
            setcookie("current_page",current_page,"d1");//一天有效期
            setcookie("operate_type",false,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
        }
	});
}

$('#add_group').on('click',function(){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消
    window.location.href = "/advertiseGroup/group/add/to";
});

function editGroup(groupId){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
    window.location.href = "/advertiseGroup/group/add/to?gId=" + groupId + "&operateType=E";
}

function copyGroup(groupId){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
    window.location.href = "/advertiseGroup/group/add/to?gId=" + groupId + "&operateType=C";
}



