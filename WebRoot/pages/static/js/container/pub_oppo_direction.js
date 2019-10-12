var directionNum = 0;
window.onload=function() {
    /** 地域初始化 **/
    var areaValue = $('#d_areaValue').val();
    if (areaValue != null && areaValue != '') {
        areaValue = areaValue.indexOf(',') > -1 ? areaValue.split(',') : [areaValue];
    } else {
        areaValue = [];
    }
    showOrientBox('area-select', 'd_areaValue', _oppo_area, areaValue, 500);

    /** 年龄初始化 **/
    var ageType = $('#d_ageType').val();
    var age = $('#d_age').val();
    showOrientItem('d_age', ageType == '0' ? '0' : ageType, age);

    /** 网络初始化 **/
    var networkType = $('#d_networkType').val();
    var network = $('#d_network').val();
    showOrientItem('d_network', networkType == '0' ? '0' : networkType, network);

    /** 机型价格初始化 **/
    var mobileType = $('#d_mobileTypl').val();
    var mobilePrice = $('#d_mobilePrice').val();
    showOrientItem('d_mobilePrice', mobileType == '0' ? '0' : mobileType, mobilePrice);

    /** APP安装初始化 **/
    var appInstallType = $('#d_appInstallType').val();
    var appInstValue = $('#d_appInstValue').val();
    appInstValue = appInstValue != undefined && appInstValue.indexOf(',') > -1 ? appInstValue.split(',') : [appInstValue];
    if (appInstallType != 0) {
        showOrientBox('app-select', 'd_appInstValue', _oppo_app, appInstValue, 30);
    } else {
        showOrientBox('app-select', 'd_appInstValue', _oppo_app, '0', 30);
    }

    /** 用户活跃初始化 **/
    var appActiveType = $('#d_appActiveType').val();
    // 按APP类别初始化
    if (appActiveType == '1') {
        var appCatactive = $('#d_appCatActive').val();
        appCatactive = appCatactive.indexOf(',') > -1 ? appCatactive.split(',') : [appCatactive];
        showOrientBox('active-app-type-select', 'd_appCatActive', _oppo_app_type, appCatactive, 30);
        showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, '0', 30);
    }
    // 按APP初始化
    else if (appActiveType == '2') {
        var isUserActivity = $('#d_isUserActivity').val();
        // 按活跃初始化
        if (isUserActivity == '0') {
            var appActive = $('#d_appActive').val();
            appActive = appActive.indexOf(',') > -1 ? appActive.split(',') : [appActive];
            //alert("按活跃初始化-appActive=" + appActive);
            showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, appActive, 1);
        }
        // 按非活跃初始化
        else if (isUserActivity == '1') {
            var appNotActive = $('#d_appNotActive').val();
            appNotActive = appNotActive.indexOf(',') > -1 ? appNotActive.split(',') : [appNotActive];
            //alert("按非活跃初始化-appNotActive=" + appNotActive);
            showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, appNotActive, 1);
        }
        showOrientBox('active-app-type-select', 'd_appCatActive', _oppo_app_type, '0', 30);
    } else {
        //新增操作时
        showOrientBox('active-app-type-select', 'd_appCatActive', _oppo_app_type, '0', 30);
        showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, '0', 1);
    }

    /** 用户兴趣初始化 **/
    var interestType = $('#d_interestType').val();
    if (interestType == '1') {
        var interestTags = $('#d_interestTags').val();
        interestTags = interestTags.indexOf(',') > -1 ? interestTags.split(',') : [interestTags];
        showOrientBox('interest-select', 'd_interestTags', _oppo_user_interest, interestTags, 200);
    } else {
        showOrientBox('interest-select', 'd_interestTags', _oppo_user_interest, '0', 200);
    }

    $('.direction-dom .type-block').each(function () {
        var self = $(this);
        if (self.hasClass('active')) {
            showType(self, 'init', true);
        }
    });

    // APP数据切换
    $('.active-app-box input.selctRadio').change(function () {
        var self = $(this),
            sibRadio = self.parent().siblings('label').find('input.selctRadio');
        var selfClass = self.attr('class').replace('selctRadio ', '').replace('_radio', ''),
            sibClass = sibRadio.attr('class').replace('selctRadio ', '').replace('_radio', '');
        var selfId = $('#' + selfClass),
            sibId = $('#' + sibClass);
        if (sibId.val() != '') {
            //alert("sibId=" + sibId.val() + "，selfId=" + selfId.val());
            //selfId.val(sibId.val());sibId.val('');//初始写法
            selfId.val('');sibId.val(sibId.val());//zxf更改
        }
    });

    //定向指向
    $(".direction-dom .type-block").on('click', function (e) {
    	e.stopPropagation();
    	e.preventDefault();
        showType($(this), 'ck', true);
    });

    //定向值指向
    $('.direction-range-box .type-block').on('click', function (e) {
    	e.stopPropagation();
    	e.preventDefault();
        var self = $(this),
            $parent = self.parents('.item-other'),
            $hidden = $parent.find('input[type=hidden]'); //隐藏框
        var val = selectMore(self, $hidden.val());
        var arr = val.split('999,');
        if (arr.length > 1) {
            val = arr.join('');
        }
        $hidden.val(val);
    });

    //单选按钮
    $('.selctRadio').on('click', function () {
        var self = $(this),
            radVal = self.val(),
            $parent = self.parent(),
            $hidden = $parent.siblings('input[type=hidden]'); //隐藏框
        $hidden.val(radVal);
    });
    //人群包排除
    initPeopleFn();

}

function selectMore(self, val){ //多选
    self.parents('.item-other').siblings("div.error").remove(); //去除错误提示语
	var arr = [],
		dataId = self.attr('data-id');
	if(val){
		arr = val.indexOf(',')>-1 ? val.split(',') : [val]
	}
	if(self.hasClass('active')){
		self.removeClass('active');
		arr.splice(arr.indexOf(dataId), 1);
	}else{
		self.addClass('active');
		arr.push(dataId);
	}
	return arr.join(',');
}

function selectOppoData(){
    var directParam={};
    //主键ID
    var id = $('#d_id').val();
    directParam['id']=id;
    //广告主ID
    var ownerId = $('#d_ownerId').val();
    directParam['ownerId']=ownerId;
	//定向包ID
	var targetId = $('#d_targetId').val();
    directParam['targetId']=targetId;
	//定向包名称
	var targetName = $('#d_targetName').val();
    directParam['targetName']=targetName;
    //定向包描述
    var targetDesc = $('#d_targetDesc').val();
    directParam['targetDesc']=targetDesc;
    //另存为的定向
    var immutable = $('#d_immutable').val();
    directParam['immutable']=immutable;
    //定向包类型
    var type = $('#d_type').val();
    directParam['type']=type;

	/** 地域指向 **/
	var regionType = $('#d_regionType').val();
	//当前地或常驻所在地
    var permanent = $('input:radio[name="permanent"]:checked').val()
    //地域值
    var areaValue = $('#d_areaValue').val();
    if(regionType=='0'){
        areaValue='999';
        $('#d_region').val(areaValue);
        $('#d_permanentRegion').val(areaValue);
        permanent = '0';
        directParam['region']=areaValue;
        directParam['permanentRegion']=areaValue;
    }else{
        if(permanent=='0'){
            $('#d_region').val(areaValue);
            directParam['region']=areaValue;
            $('#d_permanentRegion').val('999');
            directParam['permanentRegion']='999';
        }else if(permanent=='1'){
            $('#d_permanentRegion').val(areaValue);
            directParam['permanentRegion']=areaValue;
            $('#d_region').val('999');
            directParam['region']='999';
        }
    }
    directParam['regionType']=regionType;
    directParam['permanent']=permanent;

    /** 性别 **/
    var sex = $('#d_sex').val();
    directParam['sex']=sex;

    /** 年龄类别 **/
    var ageType = $('#d_ageType').val();
    //按年龄值
    var age = $('#d_age').val();
    if(ageType=='0') age='999';
    directParam['ageType']=ageType;
    directParam['age']=age;

	/** 网络类别 **/
	var networkType = $('#d_networkType').val();
	//按网络值
	var network = $('#d_network').val();
    if(networkType=='0') network='999';
    directParam['networkType']=networkType;
    directParam['network']=network;

	/** 机型价格类别 **/
	var mobileType = $('#d_mobileType').val();
    //按价格值
	var mobilePrice = $('#d_mobilePrice').val();
    if(mobileType=='0') mobilePrice='999';
    directParam['mobileType']=mobileType;
    directParam['mobilePrice']=mobilePrice;

	/** APP安装类别 **/
	var appInstallType = $('#d_appInstallType').val();
	//app安装值
	var appInstValue = $('#d_appInstValue').val();
    if(appInstallType=='0'){
        $('#d_installAppList').val('999');
        directParam['installAppList']='999';
        $('#d_notInstallAppList').val('999');
        directParam['notInstallAppList']='999';
        $('#d_neverInstallAppList').val('999');
        directParam['neverInstallAppList']='999';
    }else if(appInstallType=='1'){
        $('#d_installAppList').val(appInstValue);
        directParam['installAppList']=appInstValue;
        $('#d_notInstallAppList').val('999');
        directParam['notInstallAppList']='999';
        $('#d_neverInstallAppList').val('999');
        directParam['neverInstallAppList']='999';
    }else if(appInstallType=='2'){
        $('#d_installAppList').val('999');
        directParam['installAppList']='999';
        $('#d_notInstallAppList').val(appInstValue);
        directParam['notInstallAppList']=appInstValue;
        $('#d_neverInstallAppList').val('999');
        directParam['neverInstallAppList']='999';
    }else if(appInstallType=='3'){
        $('#d_installAppList').val('999');
        directParam['installAppList']='999';
        $('#d_notInstallAppList').val('999');
        directParam['notInstallAppList']='999';
        $('#d_neverInstallAppList').val(appInstValue);
        directParam['neverInstallAppList']=appInstValue;
    }
    directParam['appInstallType']=appInstallType;

	/** 用户活跃类别 **/
	var appActiveType = $('#d_appActiveType').val();
	//按APP类别
	var appCatActive = $('#d_appCatActive').val();
	//是否是活跃或非活跃
    var isUserActivity = $('#d_isUserActivity').val();
    //按APP-活动用户
    var appActive = $('#d_appActive').val();
    //按APP-非活动用户
    var appNotActive = $('#d_appNotActive').val();
	//活跃用户距今天数
	var appActiveDays = $('#d_appActiveDays').val();
	//非活跃用户距今天数
	var appNotActiveDays = $('#d_appNotActiveDays').val();
    if(appActiveType=='0'){
        appCatActive = '999';
        isUserActivity = '0';
        appActive = '999';
        appNotActive = '999';
        appActiveDays = '0';
        appNotActiveDays = '0';
    }else if(appActiveType=='1'){
        isUserActivity = '0';
        appActive = '999';
        appNotActive = '999';
        appActiveDays = '0';
        appNotActiveDays = '0';
    }else if(appActiveType=='2') {
        if (isUserActivity == '0') {
            if(Number(appActiveDays).toFixed(0) <= 0){
                clickFlag = 1;
                layer.msg('用户活跃天数必须大于0');
                clickFlag = 0;
                return;
            }
            appNotActive = '999';
            appNotActiveDays = '0';
            //appActive = appNotActive;//特殊处理
        } else if (isUserActivity == '1') {
            appActive = '999';
            appActiveDays = '0';
        }
    }
    // alert("isUserActivity====" + isUserActivity);
    // alert("appActive=====" + appActive + "，appActiveDays=====" + appActiveDays);
    // alert("appNotActive=====" + appNotActive + "，appNotActiveDays=====" + appNotActiveDays);

    directParam['appActiveType']=appActiveType;
    directParam['appCatActive']=appCatActive;
    directParam['isUserActivity']=isUserActivity;
    directParam['appActive']=appActive;
    directParam['appNotActive']=appNotActive;
    directParam['appActiveDays']=appActiveDays;
    directParam['appNotActiveDays']=appNotActiveDays;

	/** 用户兴趣指向 **/
	var interestType = $('#d_interestType').val();
	//按兴趣
	var interestTags = $('#d_interestTags').val();
    if(interestType=='0'){
        interestTags='999';
    }
    directParam['interestType']=interestType;
    directParam['interestTags']=interestTags;

    // 人群包定向ID
    var pTargetId = $('#d_pTargetId').val();
    directParam['pTargetId']=pTargetId;
    // 排除人群包
    var oppAudienceId = $('#d_oppAudienceId').val();
    directParam['oppAudienceId']=oppAudienceId;
	var delStatus= $('#d_delStatus').val();
    directParam['delStatus']=delStatus;
    var isValid = $('#d_isValid').val();
    directParam['isValid']=isValid;
	var creator = $('#d_creator').val();
    directParam['creator']=creator;
	var createTime = $('#d_createTime').val();
    directParam['createTime']=createTime;
	var modifyor = $('#modifyor').val();
    directParam['modifyor']=modifyor;

	return directParam;
}

//定向设置选择控制
function showType(self, oper){ //设置选项
	var $hidden = self.siblings('input[type=hidden]'),
		$item = self.parents('.item-child'),
		$sibDom = $item.siblings('div.item-box,div.item-other'),
		inx = self.data("inx");
	var $radio = $item.siblings(".item-radio");
	var dataId = self.attr('data-id');
	var tpLen = self.parents('.set-direction').length;
    var directionNumNew = 0; //获取已选择的次数
    if(oper=='ck'){ //点击操作
		if(self.hasClass('active')){
			return;
		}
		if(tpLen > 0){
			if(self.text() !='不限'){
				if(self.siblings('.active').text() == '不限' || self.siblings('.active').length == 0){
					directionNum ++;
				}
                directionNumNew = getSelectNum(directionNum); //获取已选择的次数
				if( directionNumNew > 4){
					directionNum = directionNum - 1;
					return layer.msg('定向选择不能超过4项');
				}
			}else{
				directionNum --;
				if(directionNum < 0){
					directionNum = 0;
				}
			}
		}
		if(self.text() != '不限' && $sibDom.length > 0){
			if ($sibDom.length == 1) {
				$sibDom.removeClass('none');
			} else {
				$sibDom.addClass('none');
				var $showdiv = self.parents('.item-child').siblings("div[data-id='" + dataId + "']");
				$showdiv.find('.active').removeClass('active');
				$showdiv.removeClass('none').addClass('active');
			}
			if($radio.length > 0){
				$radio.removeClass("none");
			}

		}else{
			$sibDom.length > 0 && $sibDom.addClass('none');
			if($radio.length > 0 && !$radio.hasClass("none")){
				$radio.addClass('none');
			}
		}
		self.addClass('active').siblings('.type-block').removeClass('active');
		$hidden.val(dataId);
	}else if(oper=='init'){ //编辑 初始化
		if(self.text() != '不限' && $hidden.val() != 0){
			if($radio.length > 0){
				$radio.removeClass("none");
			}
			if(self.hasClass("active")){
				if($sibDom.length > 0){
					if(inx && $sibDom.length > 1){
						$sibDom.eq(inx-1).removeClass('none').addClass('active');
					}else{
						$sibDom.removeClass('none').addClass('active');
					}
				}
				if(tpLen > 0){
					directionNum ++;
				}
			}
		}else{
			$sibDom.removeClass('active').addClass('none');
		}
	}
}

//左右栏目展示
function showOrientBox(optionclass, optionId, data, v, max) {
	var arr = [];
	if (v) {
		arr = v;
	}
	if(typeof arr == 'string'){
		arr = [];
	}
	$('.' + optionclass).selectArea({
		'data': data,
		'max': max,
		'selectArr': arr
	}, function(num, dom) {
		var value = "";
		var optionArr = "";
		for (var i = 0; i < num.length; i++) {
			if (!value)
				value = num[i];
			else
				value = value + "," + num[i];
		}
		dom.find("input[type=hidden]").val(value);
		if(optionId.indexOf(",") > -1){
			optionArr = optionId.split(",");
			for(var j=0; j<optionArr.length; j++){
				if($("."+$.trim(optionArr[j])+"_radio").length > 0 && $("."+$.trim(optionArr[j])+"_radio").is(":checked")){
					$("#" + $.trim(optionArr[j])).val(value);
				}
			}
		}else{
			$("#" + optionId).val(value);
		}
	});
}

function showOrientItem(optionId, type, v, lev) {
	if (type == "0")
		return;

	var self = $("#" + optionId),
	$divl = self.parents(".item-other");
	if(lev=="one"){
	   self.siblings(".type-block").removeClass("active");
	   self.siblings("span[data-id='" + v + "']").addClass("active");
	}else if(lev=="two"){
	   self.siblings(".type-block").removeClass("active");
	   self.siblings("span[data-id='" + v.split("-")[0] + "']").addClass("active");
	}

	if ($divl.length > 0) {
		if ($divl.length == 1) {
			$divl.removeClass("none");
			if (v) {
				$divl.find("span.type-block").each(function(i, n) {
					var id = $(this).data("id");
					if (v.indexOf(id) > -1) {
						$(this).addClass("active");
					}
				});
			}
		} else {
			var $showdiv = self.parents(".item-child").siblings("div[data-id='" + type + "']");
			$showdiv.removeClass("none");

			if (v) {
				$showdiv.find("span.type-block").each(function(i, n) {
					var id = $(this).data("id");
					if (v.indexOf(id) > -1) {
						$(this).addClass("active");
					}
				});
			}
		}
	}
}

function initPeopleFn() { //人群包 select 初始化
    // 人群包定向ID
    var pTargetId = $('#d_pTargetId').val();
    selectPeopleNumFn(pTargetId); //选择值后
    // 排除人群包
    var oppAudienceId = $('#d_oppAudienceId').val();
    excloudPeopleNumFn(oppAudienceId);  //选择值后
    excludeFn($('#d_pTargetId'), "#d_oppAudienceId"); //人群包 互斥
    excludeFn($('#d_oppAudienceId'), "#d_pTargetId"); //人群包 互斥
}

function excludeFn($t, eId) { //人群包 选择与排除 内容互斥
    var selfVal = $t.val();
    var $oppAudienceId = $(eId),
        $menu = $oppAudienceId.siblings(".dropdown-menu");
    var inx = $t.find("option:selected").index(); //选中值的索引
    // $oppAudienceId.val("");
    $oppAudienceId.find("option.none").removeClass("none");
    $menu.find("li.none").removeClass("none");
    $menu.find("a.none").removeClass("none");
    if (selfVal != "" && selfVal != "0") {//如果选中的值不为空，更新非人群包显示
        $oppAudienceId.find("option").eq(inx).addClass("none");
        $menu.find("li").eq(inx).addClass("none");
    } else {
        $t.siblings(".dropdown-menu").find("li.none").removeClass("none");
    }
}

function getSelectNum(num) { //获取已选择的个数
    return excloudPeopleNum + selectPeopleNum + num;
}

var excloudPeopleNum = 0,   //人群包排除 是否已选择 1:已选择 0:请选择
    selectPeopleNum = 0;    //选择人群包 是否已选择 1:已选择 0:请选择+++
function excloudPeopleNumFn(selfVal) {
    if (selfVal != "0") { //选择值后
        excloudPeopleNum = 1;
    } else {
        excloudPeopleNum = 0;
    }
}
function selectPeopleNumFn(selfVal) {
    if (selfVal != "0") { //选择值后
        selectPeopleNum = 1;
    } else {
        selectPeopleNum = 0;
    }
}
//人群包 选择
$("#d_pTargetId").on("change", function(){
    var $t = $(this),
        selfVal = $t.val();
    selectPeopleNumFn(selfVal); //选择值后
    var num = getSelectNum(directionNum);
    if (num > 4) {
        selectPeopleNum = 0;
        $t.val("0");
        return layer.msg('定向选择不能超过4项');
    }
    excludeFn($t, "#d_oppAudienceId");
});
//人群包 排除
$("#d_oppAudienceId").on("change", function(){
    var $t = $(this),
        selfVal = $t.val();
    excloudPeopleNumFn(selfVal);  //选择值后
    var num = getSelectNum(directionNum);
    if (num > 4) {
        excloudPeopleNum = 0;
        $t.val("0");
        return layer.msg('定向选择不能超过4项');
    }
    excludeFn($t, "#d_pTargetId");
});

