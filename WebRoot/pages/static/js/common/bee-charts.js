
var BeeCharts = function () {
	
	 var option = {
     	    title: {
     	        //text: '本月数据'
     	    },
     	    tooltip: {
     	        trigger: 'axis'
     	    },
     	    legend: {
     	        data:['展示数','点击数','点击率','点击消耗'],
     	        selected:{'点击率':false}
     	    },
     	    grid: {
     	        left: '2%',
     	        right: '2%',
     	        bottom: '2%',
     	        containLabel: true
     	    },
     	    toolbox: {
     	        feature: {
     	            //saveAsImage: {}
     	        }
     	    },
     	    xAxis: {
     	        type: 'category',
     	        boundaryGap: false,
     	        data: ['03/21','03/22','03/23','03/24','03/25','03/26','03/27','03/28','03/29']
     	    },
     	    yAxis: {
     	        type: 'value'
     	    },
     	    series: [
     	        {
     	            name:'展示数',
     	            type:'line',
     	            stack: '总量',
     	            data:[120, 132, 101, 134, 90, 230, 210,280,212]
     	        },
     	        {
     	            name:'点击数',
     	            type:'line',
     	            stack: '总量',
     	            data:[220, 182, 191, 234, 290, 3, 310,220,280]
     	        },
     	        {
     	            name:'点击率',
     	            type:'line',
     	            stack: '总量',
     	            data:[0.98, 0.56, 0.32, 0.34, 0.45, 0.43, 0.12,0.32,0.56]
     	        },
     	        {
     	            name:'点击消耗',
     	            type:'line',
     	            stack: '总量',
     	            data:[320, 332, 301, 334, 390, 330, 320,219,260]
     	        }
     	        
     	    ]
     	};
	
	var $charts;
	
	
    return {
        init: function (ids) {
            $charts=echarts.init(document.getElementById(ids));
        },
        setOption:function(data){
        	var options = $.extend(option, data);
        	$charts.setOption(option);
        }
    
    
    
    

    };

}();