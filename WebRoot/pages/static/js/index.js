var indexFns = {
    initFn: function(b){
        indexFns.datepickerFn(b);
        indexFns.top5TypeFn();
        indexFns.definedColumn();
    },
    accountSet: function(){ //每日预算
        $(".icon-edit").on("click", function(){
            var self = $(this),
                edit = self.siblings(".edit-inp-box"),
                set = self.siblings(".account-set");
            set.addClass("none");
            edit.removeClass("none");
        });
    },
	datepickerFn: function(b){

		var $reportrange = $('#reportrange'),
			$datepicker = $reportrange.find('.datepicker');
			//时间插件
        if(!b){
            if (!$datepicker.html()) {
                $datepicker.html(moment().format('YYYY-MM-DD') + ' - ' + moment().format('YYYY-MM-DD'));
            }

            $reportrange.find('input.datapicker-show').val($datepicker.html());

            $reportrange.find('input.begin-date').val(moment().format('YYYY-MM-DD'));
            $reportrange.find('input.end-date').val(moment().format('YYYY-MM-DD'));
        }



        $reportrange.daterangepicker(
        {
            // startDate: moment().startOf('day'),
            //endDate: moment(),
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
                '今日': [moment().startOf('day'), moment()],
                '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],
               '最近7日': [moment().subtract('days', 6), moment()],
               '最近30日': [moment().subtract('days', 29), moment()]
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
        }, function(start, end, label) {//格式化日期显示框
            $('#reportrange .datepicker').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
			$('#reportrange input.datapicker-show').val($('#reportrange .datepicker').html());
			$reportrange.find('input.begin-date').val(start.format('YYYY-MM-DD'));
            $reportrange.find('input.end-date').val(end.format('YYYY-MM-DD'));
        });
	},

    top5TypeFn: function(){ //前5排行-调整
        var topItem = $(".top5-type");
        function top5Display(self){
            if(self.hasClass("active")){
                self.removeClass("active");
            }else{
                self.addClass("active");
            }
        }
        $(document).on("click", function(e){
            topItem.removeClass("active");
        });
         topItem.find(".top5-type-tit").on("click", function(e){ //类型选择
            e.stopPropagation();
            var self = $(this).parents(".top5-type");
            self.parents(".top5-item-box").siblings(".top5-item-box").find(".active").removeClass("active");
            top5Display(self);
        });
    },
    definedColumn: function(){ //定义列
        // var definedList= $(".defined-list-box");
        // $(document).on("click", function(e){ //
        //     var $tag = $(e.target);
        //     if($tag.hasClass("defined-list") || $tag.parents(".defined-list").length > 0){
        //         return true;
        //     }
        //     definedList.find(".defined-show").removeClass("defined-show");
        // });

        // definedList.find("a.defined-list-btn").on("click", function(e){ //自定义列 按钮
        //     var self = $(this),
        //         $p = self.parents(".defined-list");
        //     if($p.hasClass("defined-show")){
        //         $p.removeClass("defined-show");
        //     }else{
        //         $p.addClass("defined-show");
        //     }
        // });

        // definedList.find(".btn-def").on("click", function(e){ //取消
        //     var self = $(this),
        //         $p = self.parents(".defined-list");
        //     $p.removeClass("defined-show");
        // });

        // definedList.find(".btn-def").on("click", function(e){ //确定
        //     var self = $(this),
        //         $p = self.parents(".defined-list");
        //     $p.removeClass("defined-show");

        //     //ajax……

        // });
        $(".overview-tit").on("click", function(){ //标题 - 排行
            var self = $(this);
            var sibTit = self.parents("th").siblings("th").find(".overview-tit");
            sibTit.removeClass("down");
            sibTit.removeClass("up");
            if(!self.hasClass("down") && !self.hasClass("up")){
                self.addClass("down");
            }else if(self.hasClass("down")){
                self.removeClass("down").addClass("up");
            }else if(self.hasClass("up")){
                self.removeClass("up");
            }
        });
        commonFn.btnDownFn(function(){
            console.log('确定');
        });
    }
}

function setLftHeight() { //账户总览 高度
    var winH = $(window).height(), //屏幕高度
        lftH = $("#scanList").height(),
        useH = winH - 205;
    var listH; //列表高度
    if (lftH > useH) {
        listH = lftH - 100;
    } else {
        listH = useH - 100;
    }
    $("#ad_list").height(listH);
}
setLftHeight();

function treeFn(dom, opt, callback) {
    this.dom = $(dom);
    this.cfg = $.extend({
        data:[],
        refresh: false, //是否刷新
        select: '' //已选择
    }, opt || {});
    this.callback = callback;
    if (this.cfg.refresh) {
        this.dom.empty();
    }
    this.init();
}
treeFn.prototype.addChildHtml = function(item) { //添加子节点
    var selectId = this.cfg.select;
    var listItemDom = '<li class="scan-menu">';
    var itemChild = item.child; //子菜单data
    if(itemChild && itemChild.length > 0){
        listItemDom += '<p class="'+ (selectId == item.id ? "scan-tit scan-selected" : "scan-tit")  + '"><span class="show-list jia-icon"></span><span data-id="'+ item.id +'" data-type="'+ item.type +'" class="option-txt">'+ item.name +'</span></p><ul class="scan-child">';
        for(var j=0; j<itemChild.length; j++){
            listItemDom += this.addChildHtml(itemChild[j]);
        }
        listItemDom += '</ul>';
    }else{
        listItemDom += '<p class="'+ (selectId == item.id ? "scan-tit scan-selected" : "scan-tit")  + '"><span class="show-list"></span><span data-id="'+ item.id +'" data-type="'+ item.type +'" class="option-txt">'+ item.name +'</span></p>';
    }
    listItemDom += '</li>';
    return listItemDom;
}
treeFn.prototype.addHtml = function(){ //添加节点
    var data = this.cfg.data;
    var lftListDom = $('<ul class="scan-list-dom"></ul>');
    var listItemDom = "";
    for(var i=0; i<data.length; i++){
        listItemDom += this.addChildHtml(data[i]);
    }
    lftListDom.append(listItemDom);
    this.dom.append(lftListDom);
}
treeFn.prototype.titClickFn = function() { //标题点击事件
    var self = this;
    this.dom.off("click", "span.option-txt");
    this.dom.on("click", "span.option-txt", function(){
        var $t = $(this),
            id = $t.data("id"),
            type = $t.data("type"),
            name = $t.text();
        self.dom.find(".scan-selected").removeClass("scan-selected");
        $t.parents(".scan-tit").addClass("scan-selected");
        self.callback && self.callback(id, name, type);
    });
}
treeFn.prototype.showListFn = function($t) { //展示 or 收起
    var $tp = $t.parents(".scan-tit"),
        $child = $tp.siblings(".scan-child");
    if ($t.hasClass("jia-icon")) {
        $t.addClass("jian-icon").removeClass("jia-icon");
    } else if($t.hasClass("jian-icon")) {
        $t.addClass("jia-icon").removeClass("jian-icon");
    }
    if($child.is(":hidden")) { //展开
        $child.show();
    } else {
        $child.hide();
    }
}
treeFn.prototype.showClickFn = function(){ //点击展示 or 收起
    var self = this;
    this.dom.off("click", "span.show-list");
    this.dom.on("click", "span.show-list", function(){
        self.showListFn($(this));
    });
}
treeFn.prototype.ininSelect = function(){
    var $seled = this.dom.find(".scan-selected"),
        $li = $seled.parent().parents(".scan-menu");
    for (var i=0; i<$li.length; i++) {
        this.showListFn($li.eq(i).children(".scan-tit").children(".show-list"));
    }
}

treeFn.prototype.init = function() { //初始化
    this.addHtml();
    this.titClickFn();
    this.showClickFn();
    if (this.cfg.select) {
        this.ininSelect();
    }
}

$.fn.extend({
    treeFn: function(opt, callback){
        this.each(function(i,dom){
            new treeFn(dom, opt, callback);
            return this;
        });
    }
});

// var plist = [
//     {'id': 1, 'name': 'aaaa', 'type': '计划', child: [
//             {'id': 11, 'name': 'aaaa1', 'type': '组'},
//             {'id': 12, 'name': 'aaaa2', 'type': '组'}
//         ]},
//     {'id': 2, 'name': 'bbbb', 'type': '计划'},
//     {'id': 3, 'name': 'cccc', 'type': '计划', child: [
//             {'id': 31, 'name': 'cccc1', 'type': '组'},
//             {'id': 32, 'name': 'cccc2', 'type': '组', 'child': [
//                     {'id': 321, 'name': 'cccc21', 'type': '创意'}
//                 ]}
//         ]}
// ]
// $("#adList").treeFn({
//     data: plist,
//     select: 32
// }, function(id, name, type){
//     console.log(id, name, type, '============')
// })
