/**
 * time(时间戳) type(0-显示年、月、日，1-显示年、月、日、时、分、秒) separator1(用来分隔年月日)
 * separator2(用来分隔时分秒)
 * 
 */
function date_format(time, type, separator1, separator2) {

	if(time){
		//var TExp = /\d{13,13}/;
		//if (!TExp.test(time))
		//	return false;
		if (separator1 == undefined)
			separator1 = "-";
		if (separator2 == undefined)
			separator2 = ":";

		var D = new Date(time);
		var y = D.getFullYear();
		var m = D.getMonth() + 1;
		var d = D.getDate();
		var h = D.getHours();
		var i = D.getMinutes();
		var s = D.getSeconds();

		time = y + separator1 + formatD(m) + separator1 +formatD(d);
		if (type) {
			time += " " + formatD(h) + separator2 + formatD(i) + separator2 + formatD(s);
		}

		return time;
	}else{
		return "";
	}
	
	

}
function formatD(d){
	
	if(d<10){
		return "0"+d;
	}else{
		return ""+d;
	}
}