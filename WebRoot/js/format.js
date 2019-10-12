/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 * 
 * @param num
 *            数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */  
function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
    num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  
function format(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num)){
    	
    	return 0;
    }  

    num = Math.floor(num*100+0.50000000001);  
  
    return Math.floor(num/100);  
  
}   
/**
 * 将数值四舍五入(保留1位小数)后格式化成金额形式
 * 
 * @param num
 *            数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.4'
 * @type String
 */  
function formatCurrencyTenThou(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
    num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*10+0.50000000001);  
    cents = num%10;  
    num = Math.floor(num/10).toString();  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  
  
// 添加金额格式化
jQuery.extend({  
    formatFloat:function(src, pos){  
        var num = parseFloat(src).toFixed(pos);  
        num = num.toString().replace(/\$|\,/g,'');  
        if(isNaN(num)) num = "0";  
        sign = (num == (num = Math.abs(num)));  
        num = Math.floor(num*100+0.50000000001);  
        cents = num%100;  
        num = Math.floor(num/100).toString();  
        if(cents<10) cents = "0" + cents;  
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
        num = num.substring(0,num.length-(4*i+3))+','+num.substring(num.length-(4*i+3));  
        return (((sign)?'':'-') + num + '.' + cents);  
    }  
});
Date.prototype.format = function(format){ 
	var o = { 
	"M+" : this.getMonth()+1, // month
	"d+" : this.getDate(), // day
	"h+" : this.getHours(), // hour
	"m+" : this.getMinutes(), // minute
	"s+" : this.getSeconds(), // second
	"q+" : Math.floor((this.getMonth()+3)/3), // quarter
	"S" : this.getMilliseconds() // millisecond
	} 
	
	if(/(y+)/.test(format)) { 
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 
	
	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	} 
	} 
	return format; 
	} 
	