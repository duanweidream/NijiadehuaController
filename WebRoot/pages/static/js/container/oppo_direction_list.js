//初始化页面
$(function(){
	//获取页面数据
	showList();
	
	$(".forchge").change(function() {
		$("#list").setGridParam({postData:getParams()})
	    .trigger("reloadGrid");
	});
	
	
	$(".search-btn").click( function () {
		$("#list").setGridParam({postData:getParams()})
	    .trigger("reloadGrid");
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

//单个删除定向
function singleDel(targetId){
	var url = "/direction/oppo/deletedirection";
	var json = {"targetId":targetId};
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;color:#cd0a0a;'> 删除 </span>该定向包？删除后不可恢复，请谨慎操作。", {
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

function showList(){
	var $notice = $("div.notice-new-box"); //通知
	var noticeH = 0;
	if($notice.length > 0 && !$notice.is(":hidden")){ //是否有通知栏显示
		noticeH = 30;
	}
	var bodyH = document.documentElement.clientHeight || document.body.clientHeight;
	var bodyW = (document.documentElement.clientWidth || document.body.clientWidth)-20;
	var totalW = bodyH-noticeH-253;
	if(bodyW < 1260){
		bodyW = 1260;
	}
	$("#list").jqGrid({
		url: '/direction/oppo/list',
		postData: getParams(),
		datatype : "json",//请求数据返回的类型。可选json,xml,txt
        rowNum : 10,
        rowList : [ 10, 20, 50, 100],
        width : bodyW,
        height : totalW,
		colNames : [ 'id', '定向包ID', '定向包名称', '关联的广告组', '状态', '操作'  ],//jqGrid的列显示名字
		colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
			{name : 'id',key: true, hidden: true, index : 'id',width : 20},
			{name : 'targetId', index : 'targetId', width : 120, align : 'center',  sortable : false},
			{name : 'targetName',index : 'targetName',width : 120, align : 'center',  sortable : false},
			{name : 'targetId',index : 'targetId',width : 120, align : 'center',  sortable : false, formatter: function(cellvalue, options, rowObject){
				return '<a href="javascript:void(0);" class="main-color" onclick="getGroup('+rowObject.targetId+');">查看</a>';
			}},
			{name : 'delStatus',index : 'delStatus',width : 80,align : "center", formatter: function(cellvalue, options, rowObject){
				cellvalue = cellvalue == 0 ? '正常' : cellvalue == 1 ? '已删除' : '';
				return cellvalue;
			}},
			{name : 'delStatus',index : 'delStatus',width : 150, align: "center", formatter: function(cellvalue, options, rowObject) {
				if(cellvalue=='0') {
					return '<a href="javascript:void(0);" onclick="edit('+rowObject.targetId+')" class="m-r-5">编辑</a>' +
						'<a href="/direction/oppo/todirection?targetId='+rowObject.targetId+'&operType=c" class="m-r-5">复制</a>' +
						'<a href="javascript:void(0);" onclick="singleDel('+rowObject.targetId+');" class="m-r-5">删除</a>';
				}else if(cellvalue=='1') {
					return '<a href="javascript:void(0);" class="m-r-5 grey-color">编辑</a>' +
						'<a href="javascript:void(0);" class="m-r-5 grey-color">复制</a>' +
						'<a href="javascript:void(0);" class="m-r-5 grey-color">删除</a>';
					}
			}}
			],
		jsonReader : {
			root:"data.page_list",
			page: "data.currentPage",
			total: "data.total",
			records: "data.records",
			cell: "",
			id : "id"
		},
        viewrecords : true,		//是否显示总条数
        hidegrid : false,
        emptyrecords : "无数据",	//列表数据为空是显示文字
        pager : '#pager2',//表格页脚的占位符(一般是div)
        loadComplete:function(){
            var current_page = $('#list').jqGrid('getGridParam', 'page');
            setcookie("current_page",current_page,"d1");//当前页页码，一天有效期
            setcookie("operate_type",false,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
        }
	});
}

function getGroup(id) {
	$("#group_table").empty();
	var _html = "<tr><th>广告组</th><th>广告计划</th><th>操作</th></tr>";

	$.ajax({
		type : "POST",
		url : '/direction/queryOrientGroup',
		data : {"id": id},
		dataType : 'json',
		// contentType : 'application/json',
		success : function(d) {
			// var d = eval("(" + d + ")");

			console.log(d);
			if (d.code == 200) {
				var list = d.data.list;

				for (var i = 0; i < list.length; i++) {
					var map = list[i];
					_html = _html + "<tr>"
					_html = _html + "<td>" + map["ad_group_name"] + "</td><td>" + map["plan_name"] + "</td>"
						+ "<td class=\"main-color\"><a href=\"/advertiseGroup/group/add/to?pId="+ map["plan_id"] +"&gId="+ map["ad_group_id"] + "\">编辑</a></td>";
					_html = _html + "</tr>"
				}

				$("#group_table").html(_html);
				$("div.tools-people-add").show();
			} else {
				layer.msg('查询失败');
			}
		},
		error: function() {
			layer.msg('查询失败');
		}
	});
}

//后台处理
function deal(url, json, index){
	$.ajax({
        url:url,
        type:"post",
        data : JSON.stringify(json),
        dataType: "json",
        async:false,
        contentType:'application/json;charset=UTF-8',
        error: function(resp){
            layer.alert(resp.errorMsg, {
                title:'异常提示',
                skin: 'layui-layer-skyBlue'
            });
            clickFlag = 0;
        },
        success: function (resp){
            layer.close(index);
            if(resp.code == 200){
            	$("#list").trigger("reloadGrid");
            }else{
                layer.alert(resp.message, {
                    title:'异常提示',
                    skin: 'layui-layer-skyBlue'
                });
            }
            clickFlag = 0;
        }
    });
}

function edit(targetId){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
    window.location.href = "/direction/oppo/todirection?targetId=" + targetId + "&operType=e";
}