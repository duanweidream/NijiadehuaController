/**
 * @description：该功能需要使用Jquery-cookie功能，需要先引入jquery cookie插件.
 * @date: 2019-8-17 11:27:10
 * @author: wyg
 */

/**
 * 下载时加载等待层设置，并实时检查cookieName来控制加载层的关闭。
 * @param cookieName cookie名称-后端处理使用
 * @param url 要请求的下载链接和参数
 */
function downloadExcel(cookieName,url) {
    var loading_wait_layer = layer.load(1);
    //var expiresDate= new Date();
    //expiresDate.setTime(expiresDate.getTime() + (30 * 60 * 1000));
    //$.cookie(cookieName, expiresDate.getTime(), { expires: expiresDate});
    //url中传入cookieName,后端处理
    //document.location = url;
    $("#iframe_download_report").attr("src",url);
    setTimeout(checkDownloadCookie(cookieName,loading_wait_layer), 1500);
}


/**
 * 检查下载cookie标记是否处理完成，关闭加载等待层
 * @param cookieName cookie名称-后端处理使用
 * @param loadingLayer 要关闭的加载等待层
 */
var downloadTimeout;
var checkDownloadCookie = function(cookieName,loadingLayer) {
    var cookieObj = $.cookie(cookieName);
    console.info('================>cookieObj');
    console.info(cookieObj);
    if (cookieObj && cookieObj==cookieName) {
        //关闭加载等待层
        layer.close(loadingLayer);
    } else {
        //1 second 后重新读取
        downloadTimeout = setTimeout(checkDownloadCookie(cookieName,loadingLayer), 1000);
    }
}



function exportExcelForm(obj){
    var timer,flag=0;
    var downloadToken=obj.time,
        url=obj.url,
        cookieName = obj.key,
        triggerDelay = 1000;
    var loading_wait_layer = layer.load(3);
    timer=setTimeout(function() {
        function checkToken(){
            var timerll=setInterval(function(){
                var sertoken=getCookie(cookieName);
                console.info("=============> cookieName : " + cookieName);
                console.info("=============> sertoken : " + sertoken);
                if(sertoken && sertoken == downloadToken ){
                    clearTimeout( downloadTimer );
                    clearInterval( timerll );
                    //frame.remove();
                    $.cookie(cookieName,null,{ path: '/'});
                    flag=0;
                    //关闭加载等待层
                    layer.close(loading_wait_layer);
                }else if(sertoken){
                    clearTimeout( downloadTimer );
                    clearInterval( timerll );
                    $.cookie(cookieName,null,{ path: '/'});
                    //关闭加载等待层
                    layer.close(loading_wait_layer);
                }
            },100);
        }
        if(!flag){
            flag=1;
            //var frame=$('<iframe />').attr('src', url).attr('id','iframe_download_report').hide().appendTo('body');
            document.location=url;
            var downloadTimer=setTimeout(checkToken,1000);
        }
    }, triggerDelay);
}


function getCookie(name){
    var strcookie = document.cookie;//获取cookie字符串
    var value = null;
    if(strcookie.length>0){
        console.info("=============> getCookie() : " + name);
        var arrcookie = strcookie.split("; ");//分割
        //遍历匹配
        for ( var i = 0; i < arrcookie.length; i++) {
            var arr = arrcookie[i].split("=");
            if (arr[0] == name){
                value = arr[1];
                break;
            }
        }
    }
    console.info("==============> cookie value : " + value);
    return value;
}
