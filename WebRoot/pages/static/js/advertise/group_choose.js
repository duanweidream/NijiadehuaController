
function changeDirectionPackage(self){
	var dpkId = $(self).val();
	$.ajax({
		type : 'POST',
		url : '/direction/findById',
		cache : false,
		data : JSON.stringify({'targetId':dpkId}),
		dataType : 'json',
		contentType:'application/json;charset=UTF-8',
		success : function(resp) {
			directionNum = 0;
			if (resp.code == 200) {
				var direction = resp.data.direction;
                // 如果选择了已有的定向包，则
				if(dpkId != 0){
                    /** 人群包 **/
					$('#d_id').val(direction.id);
				    $('#d_targetId').val(direction.targetId);

					var pTargetId = direction.pTargetId;
                    set_select_checked('d_pTargetId',pTargetId);
                    var oppAudienceId = direction.oppAudienceId;
                    set_select_checked_('d_oppAudienceId',oppAudienceId);

					/** 地域初始化 **/
					$('#d_regionType').val(direction.regionType);
					var regionType = $('#d_regionType').val();
					var permanent = direction.permanent;

					if(permanent=='0'){
                        $('#d_areaValue').val(direction.region);
					}else if(permanent=='1'){
                        $('#d_areaValue').val(direction.permanentRegion);
					}
                    var areaValue = $('#d_areaValue').val();
                    if (areaValue != null && areaValue != '') {
                        areaValue = areaValue.indexOf(',') > -1 ? areaValue.split(',') : [areaValue];
                    } else {
                        areaValue = [];
                    }
                    showOrientItemType('d_regionType', regionType == '0' ? '0' : regionType);
                    showOrientBox('area-select', 'd_areaValue', _oppo_area, areaValue, 500);
                    
					/** 性别初始化 **/
                    $('#d_sex').val(direction.sex);
					var sex = $('#d_sex').val();
					showOrientItemType('d_sex', sex=='999' ? '999' : sex);

					/** 年龄初始化 **/
                    $('#d_ageType').val(direction.ageType);
					var ageType = $('#d_ageType').val();
                    $('#d_age').val(direction.age == '999'?'':direction.age);
					var age = $('#d_age').val();
                    showOrientItemType('d_ageType', ageType == '0' ? '0' : ageType, 'd_age');
                    showOrientItem('d_age', ageType == '0' ? '0' : ageType, age);

					/** 网络初始化 **/
                    $('#d_networkType').val(direction.networkType);
					var networkType = $('#d_networkType').val();
                    $('#d_network').val(direction.network?'':direction.network);
					var network = $('#d_network').val();
					showOrientItemType('d_networkType', networkType == '0' ? '0' : networkType, 'd_network');
                    showOrientItem('d_network', networkType == '0' ? '0' : networkType, network);

					/** 机型价格初始化 **/
                    $('#d_mobileType').val(direction.mobileType);
					var mobileType = $('#d_mobileType').val();
                    $('#d_mobilePrice').val(direction.mobilePrice?'':direction.mobilePrice);
					var mobilePrice = $('#d_mobilePrice').val();
					showOrientItemType('d_mobileType', mobileType == '0' ? '0' : mobileType, 'd_mobilePrice');
                    showOrientItem('d_mobilePrice', mobileType == '0' ? '0' : mobileType, mobilePrice);

					/** APP安装初始化 **/
                    $('#d_appInstallType').val(direction.appInstallType);
                    var appInstallType = $('#d_appInstallType').val();
                    if(appInstallType == 1){
                        $('#d_appInstValue').val(direction.installAppList);
					}else if(appInstallType == 2){
                        $('#d_appInstValue').val(direction.notInstallAppList);
					}else if(appInstallType == 3){
                        $('#d_appInstValue').val(direction.neverInstallAppList);
					}
					var appInstValue = $('#d_appInstValue').val();
                    appInstValue = appInstValue.indexOf(',') > -1 ? appInstValue.split(',') : [appInstValue];
                    showOrientItemType('d_appInstallType', appInstallType=='0' ? '0' : appInstallType, appInstallType);
                    if (appInstallType != 0) {
                        showOrientBox('app-select', 'd_appInstValue', _oppo_app, appInstValue, 30);
                    } else {
                        showOrientBox('app-select', 'd_appInstValue', _oppo_app, '', 30);
                    }

					/** 用户活跃初始化 **/
					$('#d_appActiveType').val(direction.appActiveType);
					var appActiveType = $('#d_appActiveType').val();
                    showOrientItemType("d_appActiveType", appActiveType=="0" ? "0" : appActiveType);
                    // 按APP类别初始化
                    if (appActiveType == '1') {
                        $('#d_appCatActive').val(direction.appCatActive);
                        var appCatactive = $('#d_appCatActive').val();
                        appCatactive = appCatactive.indexOf(',') > -1 ? appCatactive.split(',') : [appCatactive];
                        showOrientBox('active-app-type-select', 'd_appCatActive', _oppo_app_type, appCatactive, 30);
                        showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, '', 30);
                    }
                    // 按APP初始化
                    else if (appActiveType == '2') {
                        $('#d_isUserActivity').val(direction.isUserActivity);
                        var isUserActivity = $('#d_isUserActivity').val();
                        // 按活跃初始化
                        if (isUserActivity == '0') {
                        	$('#d_appActiveDays').val(direction.appActiveDays);
                            $('#d_appActive').val(direction.appActive);
                            var appActive = $('#d_appActive').val();
                            appActive = appActive.indexOf(',') > -1 ? appActive.split(',') : [appActive];
                            //alert("按活跃初始化-appActive=" + appActive);
                            showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, appActive, 1);
                        }
                        // 按非活跃初始化
                        else if (isUserActivity == '1') {
                            $('#d_appNotActiveDays').val(direction.appNotActiveDays);
                            $('#d_appNotActive').val(direction.appNotActive);
                            var appNotActive = $('#d_appNotActive').val();
                            appNotActive = appNotActive.indexOf(',') > -1 ? appNotActive.split(',') : [appNotActive];
                            //alert('按非活跃初始化-appNotActive=' + appNotActive);
                            showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, appNotActive, 1);
                        }
                        showOrientBox('active-app-type-select', 'd_appCatActive', _oppo_app_type, '', 30);
                    } else {
                        //新增操作时
                        showOrientBox('active-app-type-select', 'd_appCatActive', _oppo_app_type, '', 30);
                        showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, '', 1);
                    }

					/** 用户兴趣数据初始化 **/
                    $('#d_interestType').val(direction.interestType);
					var interestType = $('#d_interestType').val();
                    showOrientItemType('d_interestType', interestType == '0' ? '0' : interestType);
                    $('#d_interestTags').val(direction.interestTags);
					var interestTags = $('#d_interestTags').val();
                    if (interestType == '1') {
                        interestTags = interestTags.indexOf(',') > -1 ? interestTags.split(',') : [interestTags];
                        showOrientBox('interest-select', 'd_interestTags', _oppo_user_interest, interestTags, 200);
                    } else {
                        showOrientBox('interest-select', 'd_interestTags', _oppo_user_interest, '', 200);
                    }

                    // APP数据切换
                    $('.active-app-box input.selctRadio').change(function () {
                        var self = $(this),
                            sibRadio = self.parent().siblings('label').find('input.selctRadio');
                        var selfClass = self.attr('class').replace('selctRadio ', '').replace('_radio', ''),
                            sibClass = sibRadio.attr('class').replace('selctRadio ', '').replace('_radio', '');
                        var selfId = $('#' + selfClass),
                            sibId = $('#' + sibClass);
                        if (sibId.val() != '') {
                            selfId.val('');sibId.val(sibId.val());//zxf更改
                        }
                    });

                    // 单选按钮
                    $('.selctRadio').on('click', function () {
                        var self = $(this),
                            radVal = self.val(),
                            $parent = self.parent(),
                            $hidden = $parent.siblings('input[type=hidden]'); //隐藏框
                        $hidden.val(radVal);
                    });

				}
                // 如果未选择已有的定向包，则
				else{
                    $('#d_id').val(null);
                    $('#d_targetId').val(null);
                    $('#d_pTargetId').val(0);
                    $('#d_oppAudienceId').val(0);
                    set_select_checked('d_pTargetId',0);
                    set_select_checked_('d_oppAudienceId',0);

					/** 地域初始化 **/
					$('#d_regionType').val('0');
					$('#d_areaValue').val('');
                    showOrientItemType('d_regionType', '0');
					showOrientBox('area-select', 'd_areaValue', _oppo_area, '', 500);

					/** 性别初始化 **/
					$('#d_sex').val('999');
					showOrientItemType('d_sex', '999');

					/** 年龄初始化 **/
					$('#d_ageType').val('0');
                    $('#d_age').val('');
                    showOrientItemType('d_ageType', '0');

                    /** 网络初始化 **/
					$('#d_networkType').val('0');
					$('#d_network').val('');
					showOrientItemType('d_networkType', '0');

					/** 机型价格初始化 **/
					$('#d_mobileType').val('0');
					$('#d_mobilePrice').val('');
					showOrientItemType('d_mobileType', '0');

					/** APP安装初始化 **/
					$('#d_appInstallType').val('0');
					$('#d_appInstValue').val('');
                    showOrientItemType('d_appInstallType', '0');
					showOrientBox('app-select', 'd_appInstValue', _oppo_app, '', 30);

					/** 用户活跃初始化 **/
					$('#d_appActiveType').val('0');
					$('#d_appCatActive').val('');
					$('#d_appActive').val('');
                    $('#d_appActiveDays').val(0);
					$('#d_appNotActive').val('');
					$('#d_appNotActiveDays').val(0);
                    showOrientItemType('d_appActiveType', '0');
                    showOrientBox('active-app-type-select', 'd_appCatActive', _oppo_app_type, '', 30);
                    showOrientBox('active-app-select', 'd_appActive, d_appNotActive', _oppo_app, '', 1);
					
					//用户兴趣数据初始化
					$('#d_interestType').val('0');
					$('#d_interestTags').val('');
                    showOrientItemType('d_interestType', '0');
					showOrientBox('interest-select', 'd_interestTags', _oppo_user_interest, '', 200);
				}
				var $areaInp = $("#d_regionType"),
					areaDom = $areaInp.parents(".item-child"),
					areaRadio = areaDom.siblings(".item-radio");
				if ($areaInp.val() == "0" && areaRadio.length > 0 && !areaRadio.hasClass("none")) {
					areaRadio.addClass("none");
				}
				$('.direction-dom .type-block').each(function(){
					showType($(this), 'init', true);
				});
			}
            // 人群包 select 初始化
            initPeopleFn();
            $('#d_pTargetId').selectpicker('refresh');
            $('#d_oppAudienceId').selectpicker('refresh');
		},
		error: function() {
			layer.msg('操作失败');
		}
	})
}

function chPkg(obj){
	if(obj.checked){
		$('#d_isAliasPkg').val('1');
		$('#d_aliasPkg').val('').attr('readonly', false);
	}else{
		$('#d_isAliasPkg').val('0');
		$('#d_aliasPkg').val('').attr('readonly', true);
	}
}

/**
 * 本方法用于各定向TYPE的切换，及部分定向清空选中样式操作
 * @param optionId 各定向类型ID
 * @param type
 * @param optionId2 部分按**定向的ID
 */
function showOrientItemType(optionId, type, optionId2) {
	var self = $('#' + optionId);
	if(optionId2 != null && optionId2 != undefined){
        var self2 = $('#' + optionId2);
		var $divl = self2.parents('.item-other');
        $divl.find('span.type-block').removeClass('active');
	}
	self.val(type);
	self.siblings('.type-block').removeClass('active');
	self.siblings("span[data-id='" + type + "']").addClass('active');
}

function showOrientItemType_(optionId, type) {
    var self = $('#' + optionId), sib = self.siblings('.type-block');
    self.val(type);
    if(sib.length == 0){
        sib = self.siblings('.add-form-rht').find('.type-block');
        sib.removeClass('active');
        sib.each(function() {
            var $this = $(this),
                id = $this.data('id');
            if (type.indexOf(id) > -1) {
                $this.addClass('active');
            }
        });
    }else{
        sib.removeClass('active');
        self.siblings("span[data-id='" + type + "']").addClass('active');
    }
}

/**
 * 设置select控件选中
 * @param selectId select的id值
 * @param checkValue 选中option的值
 */
function set_select_checked(selectId, checkValue){
    var select = document.getElementById(selectId);
    $('#'+selectId+' option:first').attr('selected','selected');
    $('.selectpicker').selectpicker('refresh');
    for (var i = 0; i < select.options.length; i++){
        select.options[i].selected = false;
        if (select.options[i].value == checkValue){
            select.options[i].selected = true;
            break;
        }
        $('.selectpicker').selectpicker('refresh');
    }

}

function set_select_checked_(selectId, checkValue){
    var select = document.getElementById(selectId);
    $('#'+selectId+' option:first').attr('selected','selected');
    $('.selectpicker').selectpicker('refresh');
    for (var i = 0; i < select.options.length; i++){
        select.options[i].selected = false;
        if (select.options[i].value == checkValue){
            select.options[i].selected = true;
            break;
        }
        $('.selectpicker').selectpicker('refresh');
    }

}


