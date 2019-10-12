//当前菜单
var navJudge = {
	init: function(){
		var item = null, //循环出的节点
			itemHref = "", //循环出的节点的href
			lastIndex = -1, //最后一个/的位置
			itemHrefSub = ""; //截取后的url
		var navItem = $(".page-sidebar li.nav-item");
		for(var i=0; i<navItem.length; i++){
			item = $(navItem[i]);
			itemHref = item.find('a').attr("href").replace("./", "/");
			if(navJudge.addActive(item, itemHref)){ //添加active成功
				break;
			}else{
				lastIndex = itemHref.lastIndexOf("/");
				if(lastIndex > -1){
					itemHrefSub = itemHref.substring(0, lastIndex);
					if(navJudge.addActive(item, itemHrefSub)){ //添加active成功
						break;
					}
				}
			}
		}
	},
	addActive: function(item, url){
		var localHref = window.location.href; //当前url
		var itemP = item.parents(".nav-item");
		if(localHref.indexOf(url) > -1){
			if(itemP){
				itemP.addClass("active").siblings(".active").removeClass("active");
			}
			item.addClass("active").siblings(".active").removeClass("active");
			return true;
		}
		
	}
}

navJudge.init();