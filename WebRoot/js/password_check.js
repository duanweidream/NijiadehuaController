//checkStrong函数
//返回密码的强度级别
function checkStrong(sPW) {
	if (sPW.length < 9) // 密码太短
		return 0;
	var Modes = 0;
	for (var i = 0; i < sPW.length; i++)
		// 测试每一个字符的类别并统计一共有多少种模式
		Modes |= CharMode(sPW.charCodeAt(i));
	return bitTotal(Modes);
}

// CharMode函数
// 测试某个字符是属于哪一类.
function CharMode(iN) {
	if (iN >= 48 && iN <= 57)// 数字
		return 1;
	else if (iN >= 97 && iN <= 122) // 大小写
		return 2;
	else if (iN >= 65 && iN <= 90)
		return 4;
	else
		return 8; // 特殊字符
}

/*function CharMode(iN) {
	if (iN >= 48 && iN <= 57)// 数字
		return 1;
	else if ((iN >= 97 && iN <= 122) || (iN >= 65 && iN <= 90)) // 大小写
		return 2;
	else
		return 4; // 特殊字符
}*/

// bitTotal函数
// 计算出当前密码当中一共有多少种模式
function bitTotal(num) {
	var modes = 0;
	for (var i = 0; i < 4; i++) {
		if (num & 1)
			modes++;
		num /= 2;
	}
	return modes;
}