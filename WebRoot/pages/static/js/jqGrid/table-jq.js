function add0(m){return m<10?'0'+m:m }
function format(date)
{
  var time = new Date(date);
  var y = time.getFullYear();
  var m = time.getMonth()+1;
  var d = time.getDate();
  return y+'-'+add0(m)+'-'+add0(d);
}
function getDateDiff(startDate,endDate){  //时间间隔计算
  var startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime();    
  var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime();    
  var dates = Math.abs((startTime - endTime))/(1000*60*60*24)+1;   
  return  dates;   
}
function getStartDay(endDate, diff){
	var endTime = new Date(Date.parse(endDate.replace(/-/g, "/"))).getTime();  
	var startTime = format(endTime - (1000*60*60*24)*(diff-1));
	return startTime;
}

var start1="", end1="", start2="", end2="";
start1 = end1 = moment().subtract('days', 1).format('YYYYMMDD');
start2 = end2 = moment().subtract('days', 2).format('YYYYMMDD');
function dataPickerFn(dom, inx, maxDate, dataDiff, callback){
	var yestarday = moment().subtract('days', 1),
		beforeY = moment().subtract('days', 2);
	var maxD = maxDate || yestarday;
	var endDate = maxD;
	var startDate = maxD;
	if(dataDiff > 0){
		if(typeof endDate != 'string'){
			endDate = endDate.format('YYYY-MM-DD');
		}
		startDate = getStartDay(endDate, dataDiff);
	}
	if(maxDate == ""){
		$(dom).find(".datepicker").html(yestarday.format('YYYY-MM-DD') + ' - ' + yestarday.format('YYYY-MM-DD'));
	}else if(dataDiff == 1 && endDay == null){
	    $(dom).find(".datepicker").html(beforeY.startOf('day').format('YYYY-MM-DD') + ' - ' + beforeY.endOf('day').format('YYYY-MM-DD'));
	    maxD = beforeY;
	}else{
		$(dom).find(".datepicker").html(startDate + ' - ' +endDate);
	}
	
	$(dom).daterangepicker(
    {
        startDate: startDate,
        endDate: endDate,
        //minDate: '01/01/2012',    //最小时间
        maxDate : maxD, //最大时间
        dateLimit : {
            days : 180
        }, 
        opens : 'right', //日期选择框的弹出位置
        buttonClasses : [ 'btn btn-default' ],
        applyClass : 'btn-small btn-primary blue',
        cancelClass : 'btn-small',
        format : 'YYYY-MM-DD', //控件中from和to 显示的日期格式
        separator: "~",
        locale : {
            applyLabel : '确定',
            cancelLabel : '取消',
            fromLabel : '起始时间',
            toLabel : '结束时间',
            customRangeLabel : '自定义',
            daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
            monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                '七月', '八月', '九月', '十月', '十一月', '十二月' ],
            firstDay : 1,
            format: 'YYYY-MM-DD'
        }
    }, function(start, end) {//格式化日期显示框
    	var st = ""; var et = "";
    	 var startD = start.format('YYYY-MM-DD'),
         	 endD =  end.format('YYYY-MM-DD');
	     var diff = getDateDiff(startD, endD);
	     if(dataDiff > 0 && diff != dataDiff){
	       alert("左右边日期框的间隔日期请选择一致");
	       $(dom).find('.datepicker').html("请选择");
	       return;
	     }
    	if(inx == 1){
    		start1 = start.format('YYYYMMDD');
    		end1 = end.format('YYYYMMDD');
    	}else{
    		start2 = start.format('YYYYMMDD');
    		end2 = end.format('YYYYMMDD');
    	}
    	//console.log(timeLevel, start1, end1);
        $(dom).find('.datepicker').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
        callback && callback(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'));
    });
}

function handler(v,o,row){

    if(v&& /^.*%$/.test(v)){
    	console.log("==================="+v)
		return duibi(v);
	}else{
    	return v;
	}

	//var name = o.name;

	//console.log(o.colModel);

}

function setTableMenu(colModel) {  //设置表头导航
    var model = [];
    var obj = null;
    for(var i=0; i<colModel.length; i++){
        obj = colModel[i];
        if(obj == "id"){
            model.push({name: obj, index: obj, width: 20, hidden: true,formatter:function(v,o,row){return handler(v,o,row);}});
        }else{
            model.push({name: obj, index: obj, width: 80, align : "center", sortable : false,formatter:function(v,o,row){return handler(v,o,row);}});
        }
    }
    return model;
}
	
//table 值设置
function tableFn(dom, url, colNames, colModel, pageId, groupHeaders, height){
	var bodyW = (document.documentElement.clientWidth || document.body.clientWidth)-20;
	var bodyH = document.documentElement.clientHeight || document.body.clientHeight;
	var tableW = colModel.length * 80;
	var ht = 400;
	var wt = 0;
	if(bodyW < 1260){
		bodyW = 1260;
	}
	if(bodyW < tableW){
		wt = tableW;
	}else{
		wt = bodyW - 20;
	}
	if(height){
		ht = bodyH - height;
	}

	/*$(dom).remove();
	$(groupHeaders).before("<table class='list-table' id='list'></table>");*/
	$("#aaaaa").html("");
	$("#aaaaa").append("<table class='list-table' id='list'></table><div id='pager2'></div>");
	//console.log(model,'=====model');
	$(dom).empty().jqGrid({
        url: url, //组件创建完成之后请求数据的url
		datatype : "json",//请求数据返回的类型。可选json,xml,txt
		page: 0,
		colNames : colNames,//jqGrid的列显示名字
		colModel : colModel, //jqGrid每一列的配置信息。包括名字，索引，宽度,对齐方式.....
	    multiselect : false, //是否有checkbox
	    jsonReader : {
			root:"data.page_list", // json中代表实际模型数据的入口
			page: "data.currentPage", // json中代表当前页码的数据
			total: "data.total", // json中代表页码总数的数据
			records: "data.records", // json中代表数据行总数的数据
			cell: "", 
			id : "id"
	    },
	    viewrecords : true,		//是否显示总条数
        hidegrid : false,
        emptyrecords : "无数据",	//列表数据为空是显示文字
		width: wt,
		height: ht,
		rowNum: 10,
		pager: pageId, //表格页脚的占位符(一般是div)的id
        loadui: "disable", //不显示 加载中
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
            layer.close(layer.index);
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