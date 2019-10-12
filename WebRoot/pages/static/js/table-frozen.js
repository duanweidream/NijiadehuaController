function tableFn(dom){
	this.dom = $(dom);
	this.lftDom = null;
	this.setSkin();
}
tableFn.prototype.setSkin = function(){
	var table = this.dom,
		trHeader = table.find(".ads-table-header table tr"),
		thTxt = trHeader.find("th").eq(1).text(),
		$tableBody = table.find(".ads-table-body"),
		trList = $tableBody.find("tr");
	this.lftDom = $('<div class="ads-body-lft"></div>');
	var lftHeader = $('<div class="ads-table-header">'+
						'<table class="list-table">'+
							'<colgroup>'+
						        '<col style="width: 40px;">'+
						        '<col style="width: 240px;">'+
							'</colgroup>'+
							'<thead>'+
								'<tr style="height: '+ trHeader.height() +'px">'+
									'<th><div class="ads-table-cell"><input type="checkbox" class="chk-all"></div></th>'+
									'<th><div class="ads-table-cell ads-name">'+ thTxt +'</div></th>'+
								'</tr>'+
							'</thead>'+
						'</table>'+
					'</div>');
	var lftBodyHtml = '<div class="ads-table-body">'+
						'<table class="list-table">'+
							'<colgroup>'+
						        '<col style="width: 40px;">'+
						        '<col style="width: 240px;">'+
							'</colgroup>'+
							'<tbody>';
	var item = null;
	for(var i=0; i<trList.length; i++){
		item = trList.eq(i).find("td").eq(1).clone(true);
		lftBodyHtml += '<tr>'+
							'<td><div class="ads-table-cell"><input type="checkbox" class="chk-item" value="'+ trList.eq(i).find("input[type=checkbox]").val() +'"></div></td>'+
					    	'<td>'+ item.html() +'</td>'+
						'</tr>';
	}
	lftBodyHtml += '</tbody>'+
				'</table>'+
			'</div>'+
		'</div>';
	this.lftDom.append(lftHeader, $(lftBodyHtml));
	if(table.siblings(".ads-body-lft").length > 0){
		table.siblings(".ads-body-lft").remove();
	}
	table.after(this.lftDom);
	

	this.tableHeight($tableBody, this.lftDom);
	this.tableScroll($tableBody, this.lftDom);
	//全选
	var $chkAll = this.lftDom.find(".ads-table-header"),
		$tChkAll = this.dom.find(".ads-table-header");

	this.chkAllFn($chkAll, $tChkAll);
}
tableFn.prototype.tableHeight = function($tableBody, $lftBody){ //设置左侧列高度与主表列一致
	var mainCol = $tableBody.find("tr"), //主表 列
		lftCol = $lftBody.find(".ads-table-body tr"); //左侧 列
	var mainColHeight = 0,
		lftColHeight = 0;
	for(var i=0; i<mainCol.length; i++){
		mainColHeight = mainCol.eq(i).height();
		lftColHeight = lftCol.eq(i).height();
		if(mainColHeight != lftColHeight){
			lftCol.eq(i).css({"height": mainColHeight+"px"});
		}
	}
}
tableFn.prototype.tableScroll = function($tableBody, $lftDom){ //左右滚动 左侧、头部显示
	var $tHeader = $tableBody.siblings(".ads-table-header").find('table'),
		$lftBody = $lftDom.find(".ads-table-body");
	$tableBody.scroll(function(){ 
		var lft = this.scrollLeft;
		var top = this.scrollTop;
		$tHeader.css({'margin-left': '-'+lft+'px'});
		if(lft > 0){
			$lftDom.addClass("lft-show");
		}else{
			$lftDom.removeClass("lft-show");
		}
		if(top > 0){
			$lftBody.find("table").css({'margin-top': '-'+top+'px'}); //上下滚动 左侧显示
		}
	});
}

tableFn.prototype.chkAllFn = function($header, $tChkAll){
	var $chkItem = $header.siblings(".ads-table-body").find("input[type=checkbox].chk-item");
	$header.on("change", "input.chk-all", function(){
		var self = $(this);
		var bol = self.prop("checked");
		$chkItem.prop("checked", bol);
		$tChkAll.find("input.chk-all").change();
	});

	$chkItem.on("change", function(){ //单选
		var self = $(this),
			sp = self.parents(".ads-table-body"),
			spSib = sp.siblings(".ads-table-header").find("input.chk-all");
		var allLen = sp.find("input.chk-item").length,
			chkLen = sp.find("input:checked").length;
		
		if(allLen == chkLen){
			spSib.prop("checked", true);
		}else{
			spSib.prop("checked", false);
		}
	});
}

$.fn.extend({
	tableFrozen: function(){
		new tableFn(this);
	}
})