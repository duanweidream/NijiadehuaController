var dataCount = 0;
var thSize = $("#table_page th").size();
var Page = (function () {
    return {
        query: function () {
            //禁用搜索按钮
            $('#searchButton').attr("disabled", "disabled");
            BeeReport.query();
        },
        report: function (url) {
            BeeReport.load(url, {}, function (response) {
                var noDataStr = '<tr><td colspan="' + thSize + '" style="text-align:center;color:#ff6666;">未搜索到符合条件的数据...</td></tr>';
                var tbodyTag = $("#table_page tbody");
                if (response.data) {
                    //总计
                    BeeReport.total(response.data.total);
                    //tbody
                    var _html = '';
                    if (response.data.page_list.length == 0) {
                        tbodyTag.html(noDataStr);
                    } else {
                        dataCount = response.data.page_list.length;
                        $.each(response.data.page_list, function (value, index, arr) {
                            _html += '<tr class="">';
                            $.each(this, function (value, index, arr) {
                                var d = this;
                                _html += '<td>' + d + '</td>';
                            })
                            _html += '</tr>';
                        });
                        tbodyTag.html(_html);
                        BeeReport.footer(response.data.footer, "table-footer");
                    }
                    //$('#endDate').val(response.data.advertiserId);
                } else {
                    tbodyTag.html(noDataStr);
                    layer.alert('查询失败，未返回数据！请联系管理员查找原因！', {
                        title: '错误信息',
                        icon: "2",
                        skin: 'layui-layer-skyBlue'
                    });
                }
                //启用搜索按钮
                $('#searchButton').removeAttr("disabled");
            });
        },
        init: function (url) {
            ComponentsDateTimePickers.init();
            BeeReport.setForm(document.forms["search_form"]);
            Page.report(url);
        }
    }
})();

//导出Excel
function exportExcel(url, name) {
    if (dataCount <= 0) {
        layer.alert('没有数据，无法导出！', {
            title: '提示信息',
            icon: "0",
            skin: 'layui-layer-skyBlue'
        });
        return;
    }
    var form = $('#' + name);
    var params = '';
    if (form && form.elements) {
        var els = form.elements;
        for (var i = 0; i < els.length; i++) {
            var element = els[i];
            var eName = element.name;
            var eValue = $(element).val();
            if (element.name && eValue) {
                params += eName + "=" + eValue + "&";
            }
        }

    }
    if (params.length > 0) {
        params = params.substring(0, params.length - 1);
        url = url + '?' + params;
    }
    document.location = url;
}