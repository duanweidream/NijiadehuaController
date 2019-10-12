var params = getParams();
var url_show_status = getQueStr(location.href,"showStatus");

//初始化页面
$(document).ready(function(){
    $('a[href="#"]').attr('href','javascript:void(0);');

    //获取页面数据
    if (getcookie("operate_type") == 'true'){
        showList(Number(getcookie("current_page")).toFixed(0));
    } else if (url_show_status != null){
        var showStatus = getQueStr(location.href,"showStatus");
        if(showStatus != ''){
            params['showStatus'] = showStatus;
            if (showStatus == '110'){
                $('#showStatus').find("option").eq(1).attr("selected",true);    //选中
            } else if (showStatus == '220223'){
                $('#showStatus').find("option").eq(7).attr("selected",true);    //选中
            } else if (showStatus == '222'){
                $('#showStatus').find("option").eq(8).attr("selected",true);    //选中
            }
            showList(0);
        }
    } else {
        showList(0);
    }

    $(".forchge").change(function() {
        $("#list").setGridParam({postData:getParams(), page:1}).trigger("reloadGrid");
    });

    $("#adName").keyup(function(e){ //回车 搜索
        if (e.keyCode == 13) {
            $(".search-btn").trigger("click");
        }
    });

    $(".search-btn").click( function () {
        $("#list").setGridParam({postData:getParams(), page:1}).trigger("reloadGrid");
    });

    //状态修改
    commonFn.btnDownFn(function(){
        console.log("确定");
    },function(){
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

//单个修改广告创意出价
function singleModifyPrice(adId,price){
    var reg1 = /(^[0-9]{1,6}$)|(^[0-9]{1,6}[\.]{1}[0-9]{1,2}$)/;
    if(!reg1.test(price)){
        layer.msg('请输入正确的价格格式');
        return false;
    }
    var ideaIds = new Array();
    ideaIds.push(adId);
    var url = "/advertiseIdea/idea/batch/price";
    var json = {};
    json["ideaIds"] = ideaIds;
    json["price"] = price;
    deal(url, json);
}

// 批量修改广告创意出价
function patchModifyPrice() {
	var reg1 = /(^[0-9]{1,6}$)|(^[0-9]{1,6}[\.]{1}[0-9]{1,2}$)/;
    var patchPrice = $("input:text[name=patchPrice]").val();
    if(!reg1.test(patchPrice)){
    	layer.msg('请输入正确的价格格式');
    	return false;
    }
    var selectedRows =  $("#list").jqGrid('getGridParam', 'selarrrow');
    var i=0;
    var len = selectedRows.length;
    if(len <= 0){
        layer.msg('请选择要批量修改出价的广告创意');
        return false;
    }
    var ideaIds =new Array();
    while(i<len){
        //ideaIds.push(($('#list').jqGrid('getCell',selectedRows[i],4)));
        ideaIds.push(selectedRows[i]);
        i++;
    }
    var url = "/advertiseIdea/idea/batch/price";
    var json = {};
    json["ideaIds"] = ideaIds;
    json["price"] = patchPrice;
    deal(url, json);
}

//单个删除广告创意
function singleDelIdea(adId){
    var ideaIds =new Array();
    ideaIds.push(adId);
    var url = "/advertiseIdea/idea/batch/del";
    var json = {};
    json["ideaIds"] = ideaIds;
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;color:#cd0a0a;'> 删除 </span>该广告创意？删除后不可恢复，请谨慎操作。", {
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

//批量删除广告创意
function patchDelIdea(){
    if($('#showStatus').val() == 331) {//已删除状态
        layer.msg('广告创意已处于删除状态');
        return;
    }
    var selectedRows =  $("#list").jqGrid('getGridParam', 'selarrrow');
    var i=0;
    var len = selectedRows.length;
    if(len <= 0){
        layer.msg('请选择要批量删除的广告创意');
        return false;
    }
    var ideaIds =new Array();
    while(i<len){
        //ideaIds.push($($('#list').jqGrid('getCell',selectedRows[i],2)).text());
        ideaIds.push(selectedRows[i]);
        i++;
    }
    var url = "/advertiseIdea/idea/batch/del";
    var json = {};
    json["ideaIds"] = ideaIds;
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;color:#cd0a0a;'> 批量删除 </span>选中的广告创意？删除后不可恢复，请谨慎操作。", {
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
    if($('#showStatus').val() == 331) {//已删除状态
        layer.msg('广告创意已处于删除状态');
        return;
    }
    var selectedRows =  $("#list").jqGrid('getGridParam', 'selarrrow');
    var i=0;
    var len = selectedRows.length;
    if(len <= 0){
        layer.msg('请选择要修改状态的广告创意');
        return false;
    }
    var ideaIds =new Array();
    while(i<len){
        ideaIds.push(selectedRows[i]);
        i++;
    }
    var status2 = $("input:radio[name=status2]:checked").val();
    var url = "/advertiseIdea/idea/batch/status2";
    var json = {};
    json["ideaIds"] = ideaIds;
    json["status2"] = status2;
    deal(url, json);
}

//单个切换状态
function singleSwitchStatus(ideaId, status2 , is_valid){
    if (is_valid == 1 && status2 == 0) {//数据已删除
        layer.msg("该创意已删除，无法投放");
        return;
    }
    var ideaIds =new Array();
    ideaIds.push(ideaId);
    var url = "/advertiseIdea/idea/batch/status2";
    var json = {};
    json["ideaIds"] = ideaIds;
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
                icon: 2,
                title: '异常提示',
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
                    icon: 5,
                    title: '温馨提示',
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
        layer.msg("列表数据为空，无需导出");
        return;
    }
    if(clickFlag == 0){
        //询问框
        layer.confirm("您确定要<span style='font-weight:bold;'> 导出 </span>当前查询条件的广告创意列表？", {
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

function showChildGrid(parentRowID, parentRowKey) {
    // parentRowID = '101452053';
    // parentRowKey  = '101452053';
    $("#"+ parentRowKey).showCol('subgrid');
    $.ajax({
        url: "/advertiseIdea/idea/ideaDetail",
        type: "GET",
        data:{"id":parentRowKey},
        success: function (html) {
            $("#" + parentRowID).append(html);
        }
    });
}

function showList(page){
    var bodyW = $("#bodyHeight").width();
    var totalH = getHeight();
	// var $notice = $("div.notice-new-box"); //通知
	// var noticeH = 0;
	// if($notice.length > 0 && !$notice.is(":hidden")){ //是否有通知栏显示
	// 	noticeH = 35;
	// }
	// var bodyH = document.documentElement.clientHeight || document.body.clientHeight;
	// var totalH = bodyH-noticeH-270;
    if(bodyW < 1586){
        bodyW = 1586;
    }

    $("#list").jqGrid({
    	url : '/advertiseIdea/idea/local/list',
    	postData : getParams(),
    	datatype : "json",//请求数据返回的类型。可选json,xml,txt
        page : page,
        rowNum : 10,
        rowList : [ 10, 20, 50, 100],
        width : bodyW,
        height : totalH,
    	colNames : [ 'id', '广告名称', '广告ID', '组名称', '计划名称', '推广规格', '曝光量', '点击次数', '点击率', '点击均价', '下载次数', '下载率', '下载均价', '出价', 'ECPM', '消耗金额', '创意状态', '投放开关', '操作' ],//jqGrid的列显示名字
    	colModel : [ //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
           {name:'id', key:false, hidden:true, index:'id', width:0},
           {name:'adName', index:'adName', width:250, align:"left", classes:'toExtends', sortable:false, formatter: function(cellvalue, options, rowObject){
               var is_valid = rowObject.isValid;
               if(is_valid == 0){
                   return '<span title="" style="color: #f47837;">'+cellvalue+'</span>';
               }else{
                   return cellvalue;
               }
           }},
           {name:'adId', index:'adId', width:190, align:"center", classes:'toExtends', sortable:false, formatter: function(cellvalue, options, rowObject){
               var is_valid = rowObject.isValid;
               if(is_valid == 0){
                   return '<span title="" style="color: #f47837;">'+cellvalue+'</span>';
               }else{
                   return cellvalue;
               }
           }},
           {name:'adGroupName', index:'adGroupName', width:250, align:"center", sortable:false, formatter: function(cellvalue, options, rowObject){
               var is_valid = rowObject.isValid;
               if(is_valid == 0){
                   return '<a href="/advertiseIdea/idea/list?gId='+rowObject.adGroupId+'" title="点击查询该广告组的广告创意列表" class="m-r-5">'+cellvalue+'</a>';
               }else{
                   return cellvalue;
               }
           }},
           {name:'planName', index:'planName', width:250, align:"center", sortable:false, formatter: function(cellvalue, options, rowObject){
               var is_valid = rowObject.isValid;
               if(is_valid == 0){
                   return '<a href="/advertiseGroup/group/list?pId='+rowObject.planId+'" title="点击跳转至该计划的广告组列表" class="m-r-5">'+cellvalue+'</a>';
               }else{
                   return cellvalue;
               }
           }},
           {name:'adSpec', index:'adSpec', width:150, align:"center", sortable:false, formatter: function(cellvalue, options, rowObject){
               cellvalue = cellvalue == '1' ? '精选推荐': cellvalue == '2' ? '开机必备': cellvalue == '3' ? '安装有礼': cellvalue == '4' ? '搜索列表':
                   cellvalue == '5' ? '大图广告': cellvalue == '6' ? '小图广告': cellvalue == '7' ? '组图广告': cellvalue == '8' ? '视频广告':
                   cellvalue == '9' ? '图标广告':cellvalue == '10' ? '图片广告': '';
               return cellvalue;
           }},
           {name:'expose', index:'expose', width:150, align:"center", sortable:true},
           {name:'click', index:'click', width:180, align:"center", sortable:true},
           {name:'clickrate', index:'clickrate', width:150, align:"center", sortable:true, formatter: function(cellvalue, options, rowObject){
               var radices = rowObject.expose;
               if(radices == 0) {
                   return "0.00%";
               }else{
                   // var str = Number(rowObject.click/radices).toFixed(2);
                   var str = cellvalue + "%";
                   return str;
               }
           }},
           {name:'clickPrice', index:'clickPrice', width:180, align:"center", sortable:true, formatter: function(cellvalue, options, rowObject){
               var radices = rowObject.click;
               if(radices == 0) {
                   return "0.00";
               }else{
                   var str = Number(((rowObject.cost/100)/radices)).toFixed(2);
                   return str;
               }
           }},
           {name:'download', index:'download', width:180, align:"center", sortable:true},
           {name:'downloadrate', index:'downloadrate', width:145, align:"center", sortable:true, formatter: function(cellvalue, options, rowObject) {
               var radices = rowObject.expose;
               if(radices == 0) {
                   return "0.00%";
               }else{
                   //var str = Number(rowObject.download/radices).toFixed(2);
                   var str = cellvalue + "%";
                   return str;
               }
           }},
           {name:'downPrice', index:'downPrice', width:180, align:"center", sortable:true, formatter: function(cellvalue, options, rowObject){
               var radices = rowObject.download;
               if(radices == 0) {
                   return "0.00";
               }else{
                   var str = Number(((rowObject.cost/100)/radices)).toFixed(2);
                   return str;
               }
           }},
           {name:'price', index:'price', width:260, align:"center", editable:false, edittype:'text', sortable:true, editoptions: {size:10, maxlength: 8}, formatter: function(cellvalue, options, rowObject){
                var str = Number(cellvalue/100).toFixed(2);
                var cellHtml = '<span class="edit-text">'+ str +'</span><span class="edit-inp-box none"><input type="number" data-val="'+ str + '" value="'+ str + '" class="set-money-inp input-28" /><span class="index-icon-box icon-close" onclick="cancelBudgetFn(this)"></span><span class="index-icon-box icon-submit" onclick="submitBudgetFn(this, '+ rowObject.id + ')"></span></span><span class="index-icon-box icon-edit" onclick="setBudgetFn(this)"></span>';
                return cellHtml;
           }},
           {name:'ecpm', index:'ecpm', width:150, align:"center", sortable:true, formatter: function(cellvalue, options, rowObject){
                var expose = rowObject.expose, cost = rowObject.cost;
                if(expose == 0) {
                   return "0.00";
                }else{
                   var str = Number((cost/100)*1000/expose).toFixed(2);
                   return str;
                }
           }},
           {name : 'cost',index : 'cost',width:180, align : "center", sortable:true, formatter:function(cellvalue, options, rowObject){
               return Number(cellvalue/100).toFixed(2);
           }},
           {name : 'showStatus',index : 'showStatus',width : 150, align: "center", sortable: false, formatter: function(cellvalue, options, rowObject){
                var webShowStatus = $('#showStatus').val();
                var is_valid = rowObject.isValid;
                var audit_status = rowObject.auditStatus;
                var show_status = rowObject.showStatus;
                if(is_valid == 1){
                   cellvalue = '已删除';
                }else if(webShowStatus == '220223' || webShowStatus == '222'){
                    cellvalue = (audit_status == '0' || audit_status == '3') ? '审核中' : audit_status == '2' ? '审核拒绝' : '';
                }else{
                    cellvalue = show_status == '0' ? '启动中' : show_status == '1' ? '暂停' : show_status == '2' ? '快应用状态异常' : show_status == '3' ? '应用状态异常' :
                        show_status == '4' ? '广告组暂停' : show_status == '5' ? '计划暂停' : '';
                }

                return cellvalue;
           }},
           {name : 'status2',index : 'status2',width : 140, align: "center", sortable: false,formatter: function(cellvalue, options, rowObject){
               var is_valid = rowObject.isValid;
               if(is_valid == '1'){
                   return '<span id=status2'+rowObject.adId+' onclick="singleSwitchStatus('+rowObject.adId+', 0 ,'+rowObject.isValid+')" class="switch-box"><i class="switch-circle"></i></span>';
               }else{
                   if(cellvalue=='1'){
                       return '<span id=status2'+rowObject.adId+' onclick="singleSwitchStatus('+rowObject.adId+', 0 ,'+rowObject.isValid+')" class="switch-box"><i class="switch-circle"></i></span>';
                   }else{
                       return '<span id=status2'+rowObject.adId+' onclick="singleSwitchStatus('+rowObject.adId+', 1 ,'+rowObject.isValid+')" class="switch-box open"><i class="switch-circle"></i></span>';
                   }
               }
           }},
           {name : 'isValid',index : 'isValid',width : 300, align: "center", sortable: false, formatter: function(cellvalue, options, rowObject) {
               var is_valid = rowObject.isValid;
               if(is_valid == 1){
                   return '<div class="txt-nowrap">' +
                       '<a href="javascript:void(0);" class="m-r-5 grey-color">编辑</a>' +
                       '<a href="javascript:void(0);" class="m-r-5 grey-color">复制</a>' +
                       '<a href="javascript:void(0);" class="m-r-5 grey-color">删除</a></div>';
               }else{
                   return '<div class="txt-nowrap">' +
                       '<a href="javascript:void(0);" onclick="editIdea('+rowObject.adId+')" class="m-r-5">编辑</a>' +
                       '<a href="javascript:void(0);" onclick="copyIdea('+rowObject.adId+')" class="m-r-5">复制</a>' +
                       '<a href="javascript:void(0);" onclick="singleDelIdea('+rowObject.adId+')" id="" class="m-r-5" style="cursor:pointer;">删除</a></div>';
               }
           }}
        ],
        multiselect: true,
        subGrid: true,              //设置启用子数据表格，默认false
        cellsubmit: 'remote',
        //multiSort: true,			//设置组合排序
        //sortname:"expose,click",	//设置默认组合排序参数
        sortable: true,				//设置单个排序
        //sortname: "expose",		//设置默认单个排序参数
        sortorder: "desc",
        jsonReader: {
            root:"data.page_list",
            page: "data.currentPage",
            total: "data.total",
            records: "data.records",
            cell: "",
            id: "id"
        },
        viewrecords: true,		//是否显示总条数
        hidegrid: false,
        emptyrecords: "无数据",	//列表数据为空是显示文字
        gridComplete: function(){
            $(".ui-jqgrid .ui-jqgrid-btable .ui-sgcollapsed .ui-icon, .ui-jqgrid .ui-jqgrid-btable .ui-widget-content .ui-icon").css("display","none");
        },
        onCellSelect: function(rowid,iCol,cellcontent,e){
            // console.log(rowid,iCol,"====================rowid");
            var trClass = $("#"+rowid+"").find("span").attr("class");
            if(iCol==3 || iCol==4){
                if(trClass == 'ui-icon ui-icon-plus'){
                    $("#"+rowid+"").find("span.ui-icon-plus").click();
                    $(".ui-jqgrid .ui-jqgrid-btable .ui-sgcollapsed .ui-icon, .ui-jqgrid .ui-jqgrid-btable .ui-widget-content .ui-icon").css("display","none");
                }else{
                    $("#"+rowid+"").find("span.ui-icon-minus").click();
                    $(".ui-jqgrid .ui-jqgrid-btable .ui-sgcollapsed .ui-icon, .ui-jqgrid .ui-jqgrid-btable .ui-widget-content .ui-icon").css("display","none");
                }
            }
        },
        subGridRowExpanded: showChildGrid,
        pager: '#pager2',//表格页脚的占位符(一般是div)的id
        loadComplete: function(){
            var current_page = $('#list').jqGrid('getGridParam', 'page');
            setcookie("current_page",current_page,"d1");//一天有效期
            setcookie("operate_type",false,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
        }
    });
}

$('#add_idea').on('click',function(){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消
    window.location.href = "/advertiseIdea/idea/adds/to";
});

function editIdea(adId){
    setcookie("operate_type",true,"d1");//操作类型，是否是创建、编辑、返回、取消，一天有效期
    window.location.href = "/advertiseIdea/idea/add/to?adId=" + adId + "&operateType=E";
}

function copyIdea(adId){
    window.location.href = "/advertiseIdea/idea/add/to?adId=" + adId + "&operateType=C";
}

function setBudgetFn(self) { //出价 设置
    var $self = $(self),
        $text = $self.siblings(".edit-text"),
        $edit = $self.siblings(".edit-inp-box");
    $text.addClass("none");
    $self.addClass("none");
    $edit.removeClass("none");
}
function cancelBudgetFn(self) { //出价 设置 取消
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
function submitBudgetFn(self,adId) { //出价 设置 提交
    var $self = $(self),
        $sp = $self.parent(),
        $text = $sp.siblings(".edit-text"), //编辑前样式
        $edit = $sp.siblings(".icon-edit"), //编辑按钮
        $inp = $self.siblings(".set-money-inp"); //input
    var price = $inp.val();
    if (price == null || price == '') {
        layer.msg("请输入出价");
        return false;
    }
    $text.removeClass("none");
    $edit.removeClass("none");
    $sp.addClass("none");
    $text.html(price);
    singleModifyPrice(adId,price);
}



