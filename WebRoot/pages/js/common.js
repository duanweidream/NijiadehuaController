/*
==================================================================
LTrim(string):去除左边的空格
==================================================================
*/
function LTrim(str)
{
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    if (whitespace.indexOf(s.charAt(0)) != -1)
    {
        var j=0, i = s.length;
        while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
        {
            j++;
        }
        s = s.substring(j, i);
    }
    return s;
}
/*
==================================================================
RTrim(string):去除右边的空格
==================================================================
*/
function RTrim(str)
{
    var whitespace = new String(" \t\n\r");
    var s = new String(str);
    if (whitespace.indexOf(s.charAt(s.length-1)) != -1)
    {
        var i = s.length - 1;
        while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
        {
            i--;
        }
        s = s.substring(0, i+1);
    }
    return s;
}
/*
==================================================================
Trim(string):去除前后空格
==================================================================
*/
function Trim(str)
{
    return RTrim(LTrim(str));
}
/*
==================================================================
checkSpecialString(string):验证非法字符
==================================================================
*/
function checkSpecialString(str) {
    var reg = new RegExp(/[!\"\#\$\%\&\'\(\)\=\`\|\~\{\+\*\}\<\>\?\_\-\^\\\@\[\;\:\]\,\.\/]+/);
    if (reg.test(str)) {
       return true;
    }
    return false;
}
/*
==================================================================
checkSpecialString(string):验证非法字符 取消对 - 的验证
==================================================================
*/
function checkSpecialStrings(str) {
    var reg = new RegExp(/[!\"\#\$\%\&\'\(\)\=\`\|\~\{\+\*\}\<\>\?\_\^\\\@\[\;\:\]\,\.\/]+/);
    if (reg.test(str)) {
       return true;
    }
    return false;
}
/*
==================================================================
isEmail(string):验证email
==================================================================
*/
function isEmail(str){
	var rt = new Array();
//	rt[0] = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	rt[0] = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	rt[1] = "请输入正确的EMAIL格式";
	var reg = new RegExp(rt[0]);
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
isZhEmail(string):验证中文email
==================================================================
*/
function isZhEmail(emailStr) {
    if (emailStr.length == 0) {
	   return true;
    }
    var emailPat=/^(.+)@(.+)$/;
    var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
    var validChars="\[^\\s" + specialChars + "\]";
    var quotedUser="(\"[^\"]*\")";
    var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
    var atom=validChars + '+';
    var word="(" + atom + "|" + quotedUser + ")";
    var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
    var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
    var matchArray=emailStr.match(emailPat);
    if (matchArray == null) {
	    return false;
    }
    var user=matchArray[1];
    var domain=matchArray[2];
    if (user.match(userPat) == null) {
	    return false;
    }
    var IPArray = domain.match(ipDomainPat);
    if (IPArray != null) {
	    for (var i = 1; i <= 4; i++) {
		   if (IPArray[i] > 255) {
			  return false;
		   }
	    }
	    return true;
    }
    var domainArray=domain.match(domainPat);
    if (domainArray == null) {
 	    return false;
    }
    var atomPat=new RegExp(atom,"g");
    var domArr=domain.match(atomPat);
    var len=domArr.length;
    if ((domArr[domArr.length-1].length < 2) ||
	    (domArr[domArr.length-1].length > 3)) {
	    return false;
    }
    if (len < 2) {
	    return false;
    }
    return true;
}
/*
==================================================================
onlyZh(string):只可输入中文
==================================================================
*/
function onlyZh(str){
	var rt = new Array();
	rt[0] = "^[\u0391-\uFFE5]+$";
	rt[1] = "请输入中文";
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
onlyEn(string):只可输入英文
==================================================================
*/
function onlyEn(str){
	var rt = new Array();
	rt[0] = "^[A-Za-z]+$";
	rt[1] = "请输入英文";
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
onlyEn2(string):只可输入英文或带.的英文。如 shang.hai 或shanghai
==================================================================
*/
function onlyEn2(str){
	var rt = new Array();
	rt[0] = "^[A-Za-z]*([*.]*[ \t\n\r]*[*.]*[A-Za-z])+$";
	rt[1] = "请输入英文";
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
onlyEnOrNum(string):只可输入英文和数字
==================================================================
*/
function onlyEnOrNum(str){
	var rt = new Array();
	rt[0] = "^[A-Za-z0-9]+$";
	rt[1] = "只能输入英文和数字,且不能有空格！";
	
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
isInt(value,type):验证整数

@param value 是String类型
    type 是

    为空		任意整数
	'0+'	非负整数
	'+'		正整数
	'-0'	非正整数
	'-' 	负整数
==================================================================
*/
function isInt(value,type){
	var rt = new Array();
	if(type=="0+"){
		rt[0] = "^\\d+$";
		rt[1] = "请输入非负整数!";
	}else if(type=="+"){
		rt[0] = "^\\d*[1-9]\\d*$";
		rt[1] = "请输入正整数!";
	}else if(type=="-0"){
		rt[0] = "^((-\\d+)|(0+))$";
		rt[1] = "请输入非正整数!";
	}else if(type=="-"){
		rt[0] = "^-\\d*[1-9]\\d*$";
		rt[1] = "请输入负整数!";
	}else{
		rt[0] = "^-?\\d+$";
		rt[1] = "请输入整数值!";
	}
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(value)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}

/*
==================================================================
isNum(value):验证整数

@param value 是String类型
==================================================================
*/
function isNum(value){
	var rt = new Array();

	rt[0] = "^\\d*[1-9]\\d*$";
	rt[1] = "请输入正整数!";
	
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(value)){
		return true;
	}else{
		//alert(rt[1]);
		return false;
	}
}

/*
==================================================================
isFloat(value,type):验证浮点数

@param value 是String类型
    type 是

    为空		任意浮点数
	'0+'	非负浮点数
	'+'		正浮点数
	'-0'	非正浮点数
	'-' 	负浮点数
==================================================================
*/
function isFloat(value,type){
	var rt = new Array();
	if(type=="0+"){
		rt[0] = "^\\d+(\.\\d+)?$";
		rt[1] = "请输入非负浮点数!";
	}else if(type=="+"){
		rt[0] = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";
		rt[1] = "请输入正浮点数!";
	}else if(type=="-0"){
		rt[0] = "^((-\\d+(\.\\d+)?)|(0+(\\.0+)?))$";
		rt[1] = "请输入非正浮点数!";
	}else if(type=="-"){
		rt[0] = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";
		rt[1] = "请输入负浮点数!";
	}else{
		rt[0] = "^(-?\\d+)(\\.\\d+)?$";
		rt[1] = "请输入浮点数值!";
	}
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(value)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
isPhone(String):验证电话号码
==================================================================
*/
function isPhone(str){
	var rt = new Array();
	rt[0] = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$";
	rt[1] = "请输入正确的电话号码格式！";
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
isMobile(String):验证手机号码
==================================================================
*/
function isMobile(str){
	var rt = new Array();
	rt[0] = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?13\\d{9}$";
	rt[1] = "请输入正确的手机号码格式！";
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
isEnUrl(String):验证英文URL
==================================================================
*/
function isEnUrl(str){
	var rt = new Array();
	rt[0] = "\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\"\"])*$";
	//rt[0] = "[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\"\"])*$";
	rt[1] = "请输入正确的URL！";
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
isZhUrl(String):验证中文URL
==================================================================
*/
function isZhUrl(str){
	var rt = new Array();
	rt[0] = "\/[A-Za-z0-9\u0391-\uFFE5]+\.[A-Za-z0-9\u0391-\uFFE5]+[\/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\"\"])*$";
	//rt[0] = "[A-Za-z0-9\u0391-\uFFE5]+\.[A-Za-z0-9\u0391-\uFFE5]+[\/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\"\"])*$";
	rt[1] = "请输入正确的URL！";
	var reg = new RegExp(rt[0] ,"i");
	if(reg.test(str)){
		return true;
	}else{
		alert(rt[1]);
		return false;
	}
}
/*
==================================================================
checkDate(String):验证日期(是否小于当前日期)
==================================================================
*/
function checkDate(obj) {
    //输入日期(obj) = 2007-08-01
	if(obj!="" && obj!=null){
		var time = obj.split("-");
		var times = new Date();
		
		if(Date.parse((times.getMonth() + 1)+"/"+times.getDate()+"/"+times.getFullYear()) - Date.parse(time[1]+"/"+time[2]+"/"+time[0])>0){
			alert("输入的日期小于当前日期 ！！");
			return false;
		}
	}
	return true;
}
/*
==================================================================
checkTime(String):验证时间(是否小于当前时间)
==================================================================
*/
function checkTime(obj){
    //输入时间(obj) = 2007-08-01 12:59:59:234
    var day = obj.split(" ");
    var days = day[0].split("-");
    var times = new Date();
    var nowDate = Date.parse((times.getMonth() + 1)+"/"+times.getDate()+"/"+times.getFullYear()+" "+times.getHours()+":"+times.getMinutes()+":"+times.getSeconds()); 
    var newDate = Date.parse(days[1]+"/"+days[2]+"/"+days[0]+" "+day[1]); 
   
    if(nowDate>newDate) { 
      alert("输入的时间小于当前时间!");
      return false;
    }   
    return true;
}
/*
==================================================================
endDateBigStartDate(String,String):验证时间(结束时日期是否小于开始日期)
==================================================================
*/
function endDateBigStartDate(startDate,endDate){
	
	var temp_start = startDate.split("-");
	var temp_end = endDate.split("-");
	
	if(Date.parse(temp_start[1]+"/"+temp_start[2]+"/"+temp_start[0]) - Date.parse(temp_end[1]+"/"+temp_end[2]+"/"+temp_end[0])>0){
	  
	  return true;
    }
    
    return false;
}
/*
==================================================================
endTimeBigStartTime(String,String):验证时间(结束时时间是否小于开始时间)
==================================================================
*/
function endTimeBigStartTime(startTime,endTime){
	
	var temp_start = startTime.split(" ");
	var temp_starts = temp_start[0].split("-");
	
	var temp_end = endTime.split(" ");
	var temp_ends = temp_end[0].split("-");
	
    var startDatetime = Date.parse(temp_starts[1]+"/"+temp_starts[2]+"/"+temp_starts[0]+" "+temp_start[1]); 
    var endDatetime = Date.parse(temp_ends[1]+"/"+temp_ends[2]+"/"+temp_ends[0]+" "+temp_end[1]); 
   
    if(startDatetime>endDatetime) { 
      
      return true;
    }   
    return false;
}

/*
JSON convertToJson
 */
function convertToJson(formValues) {

	var result = {};
	for ( var formValue, j = 0; j < formValues.length; j++) {
		formValue = formValues[j];
		var name = formValue.name;
		var value = formValue.value;
		if (name.indexOf('.') < 0) {
			result[name] = value;
			continue;
		} else {
			var simpleNames = name.split('.');
			// 构建命名空间
			var obj = result;
			for ( var i = 0; i < simpleNames.length - 1; i++) {
				var simpleName = simpleNames[i];
				if (simpleName.indexOf('[') < 0) {
					if (obj[simpleName] == null) {
						obj[simpleName] = {};
					}
					obj = obj[simpleName];
				} else { // 数组
					// 分隔
					var arrNames = simpleName.split('[');
					var arrName = arrNames[0];
					var arrIndex = parseInt(arrNames[1]);
					if (obj[arrName] == null) {
						obj[arrName] = []; // new Array();
					}
					obj = obj[arrName];
					multiChooseArray = result[arrName];
					if (obj[arrIndex] == null) {
						obj[arrIndex] = {}; // new Object();
					}
					obj = obj[arrIndex];
				}
			}

			if (obj[simpleNames[simpleNames.length - 1]]) {
				var temp = obj[simpleNames[simpleNames.length - 1]];
				obj[simpleNames[simpleNames.length - 1]] = temp;
			} else {
				obj[simpleNames[simpleNames.length - 1]] = value;
			}

		}
	}
	return result;
}

/*
==================================================================
div(String,String):验证时间(结束时时间是否小于开始时间)
==================================================================
*/
function div(a,b){
   if(b <= 0){
   	 return 0;
   }else{
   	return a / b;
   }
}

