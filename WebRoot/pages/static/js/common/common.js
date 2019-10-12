var MenuBar= {

    initSidebar:function(){
        //绑定点击事件
        MenuBar.click_event();

        //设置菜单样式
        MenuBar.set_menustyle();


    },
    click_event:function(){

        //遍历以及菜单
        var lis = $(".page-sidebar-menu").children();
        $(lis).each(function(){
            var href = $(this).find("a").attr("href");
            //console.log(/^javascript.*$/.test(href));
            var sons = $(this).find("ul li");
            if(sons.length!=0){
                //菜单点击事件
                $(sons).each(function () {
                    $(this).find("a").click(function(){
                        var href = $(this).attr("href");
                        if(href.indexOf("?")>0){
                            href=href.substring(0,href.indexOf("?"))
                        }
                        if(window.sessionStorage){
                            window.sessionStorage.setItem("pathname",href);
                        }
                    });
                });
            }

        });
    },
    set_menustyle:function(){


        
        var pathname = window.location.pathname;
        var isCheck =  MenuBar.set_menustyle_with_path(pathname);

        if(!isCheck && window.sessionStorage){
            pathname=window.sessionStorage.getItem("pathname");
		}
        MenuBar.set_menustyle_with_path(pathname);


    },
	set_menustyle_with_path:function(pathname){

        var lis = $(".page-sidebar-menu").children();
        var isCheck =false;


        $(lis).each(function(){
            var p=this;
            var sons = $(this).find("ul li");
            if(sons.length!=0){
                //菜单点击事件
                $(sons).each(function () {
                    var href = $(this).find("a").attr("href");
                    if(pathname==href){
                        $(this).addClass("active open");
                        $(p).addClass("active open");
                        $(p).find("a").append("<span class=\"selected\"></span>");
                        isCheck=true;
                        return;
                    }

                });
            }else{
                var href = $(this).find("a").attr("href");
                if(pathname==href){
                    $(p).addClass("active open");
                    $(p).find("a").append("<span class=\"selected\"></span>");
                    isCheck=true;
                }
                return;
            }
        });
        return isCheck;

	}
}


MenuBar.initSidebar();

