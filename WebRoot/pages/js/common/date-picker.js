var ComponentsDateTimePickers = function () {
    var handleDateRangePickers = function () {
        if (!jQuery().daterangepicker) {
            return;
        }
        $('#defaultrange').daterangepicker({
                opens: (App.isRTL() ? 'left' : 'right'),
                format: 'YYYY-MM-DD',
                separator: ' 到 ',
                startDate: moment().subtract('days', 29),
                endDate: moment(),
                ranges: {
                    '今天': [moment(), moment()],
                    '昨天': [moment().subtract('days', 1), moment().subtract('days', 1)],
                    '过去7天': [moment().subtract('days', 6), moment()],
                    '过去30天': [moment().subtract('days', 29), moment()],
                    '本月': [moment().startOf('month'), moment().endOf('month')],
                    '上月': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                },
                format: 'YYYY-MM-DD',
                locale: {
                  format: 'YYYY-MM-DD',
                  applyLabel: '确定',
                  fromLabel: 'From',
                  cancelLabel: '取消',
                  toLabel: 'To',
                  customRangeLabel: '自定义',
                  daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                  monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                  firstDay: 1
              }
            },
            function (start, end) {
                $('#defaultrange input').val(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
                $("#beginDate").val(start.format('YYYY-MM-DD'));
                $("#endDate").val(end.format('YYYY-MM-DD'));
                Page&&Page.query();
            }
        );
        $('#defaultrange input').val(moment().subtract('days', 29).format('YYYY-MM-DD') + ' 至 ' + moment().format('YYYY-MM-DD'));
        $("#beginDate").val(moment().subtract('days', 29).format('YYYY-MM-DD'));
        $("#endDate").val(moment().format('YYYY-MM-DD'));
    }


    return {
        //main function to initiate the module
        init: function () {
            handleDateRangePickers();
        }
    };

}();

if (App.isAngularJsApp() === false) { 
    jQuery(document).ready(function() {    
        ComponentsDateTimePickers.init(); 
    });
}