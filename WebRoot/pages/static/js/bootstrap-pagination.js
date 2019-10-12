(function($) {
    $.fn.yxBootstrapePagination=function(option){
        var activeClass="active";
        var $this=$(this);
        var pageSize=option.data.pageSize;

        $this.unbind();

        option=option || {};
        option.buttons=option.buttons || 1 ;

        option.type=option.type || "POST";
        option.dataType= option.dataType || "json";
        option.contentType=option.contentType || "application/json";
        if(typeof option.async=="undefined" || option.async==true){
            option.async= true;
        }else{
            option.async= false;
        }
        if(typeof option.isShowPagination=="undefined" || option.isShowPagination==true){
            option.isShowPagination= true;
        }else{
            option.isShowPagination= false;
        }

        getData(option.isShowPagination)

        function pagination(){
            var firstPageIndex=1,
                lastPageIndex=Math.ceil(option.count/pageSize)==0?1:Math.ceil(option.count/pageSize),
                pageIndex=firstPageIndex,
                maxShowButtons=option.buttons>lastPageIndex ? lastPageIndex :option.buttons;

            $this.html(createHtml(option,lastPageIndex));

            $this.on("click","li",function(){
                if ($(this).hasClass("disabled")) {
                    return;
                }
                switch ($(this).attr("data-sign")){
                    case "first":
                        pageClick(firstPageIndex);
                        break;
                    case "prev":
                        prevPageClick();
                        break;
                    case "next":
                        nextPageClick();
                        break;
                    case "last":
                        pageClick(lastPageIndex);
                        break;
                    default:
                        pageClick(parseInt($(this).text()));
                        break;
                }
            });

            function createHtml(option,lastPageIndex){
                var style=lastPageIndex==1?'class="disabled"':"";
                var toFirst='<li data-sign="first" class="disabled"><a href="javascript:void(0);" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>',
                    toPrev='<li data-sign="prev" class="disabled"><a href="javascript:void(0);">‹</a></li>',
                    toNext='<li data-sign="next" '+style+'><a href="javascript:void(0);">›</a></li>',
                    toLast='<li data-sign="last" '+style+'><a href="javascript:void(0);" aria-label="Next" ><span aria-hidden="true">&raquo;</span></a></li>';

                var html="";
                if(option.isShowFL || typeof option.isShowPrev  =="undefined"){
                    html=toFirst;
                }
                if(option.isShowPN || typeof option.isShowPN  =="undefined" ){
                    html+=toPrev;
                }

                for(var i=1;i<=maxShowButtons;i++){
                    html+='<li data-sign="pageNumber" '+ (i==1?'class="active"':'') +'><a href="javascript:void(0);">'+ i + '</a></li>';
                }
                if(option.isShowPN || typeof option.isShowPN  =="undefined"){
                    html+=toNext;
                }
                if(option.isShowFL || typeof option.isShowFL  =="undefined" ){
                    html+=toLast;
                }
                return html;
            }

            function getNumbers(pageNumber){
                var middleNumber=parseInt(maxShowButtons/2)+1;
                var numbers=[];
                for(var i=1;i<=maxShowButtons;i++){
                    numbers.push(pageNumber+i-middleNumber);
                }
                var absNumber=0;
                if(numbers[0] < 1){
                    absNumber=Math.abs(numbers[0])+1;
                    for(var i =0;i<numbers.length;i++){
                        numbers[i]=numbers[i]+absNumber;
                    }
                }
                if(numbers[numbers.length-1]>lastPageIndex){
                    absNumber=numbers[numbers.length-1]-lastPageIndex;
                    for(var i =0;i<numbers.length;i++){
                        numbers[i]=numbers[i]-absNumber;
                    }
                }

                return numbers;

            }

            function prevPageClick(){
                if(pageIndex == firstPageIndex){
                    return;
                }
                pageClick(pageIndex-1);

            }

            function nextPageClick(){
                if(pageIndex==lastPageIndex){
                    return;
                }
                pageClick(pageIndex+1);
            }

            function pageClick(currentPage){

                if(pageIndex==currentPage) {  return ;}

                var numbers=getNumbers(currentPage);
                $this.find('[data-sign="pageNumber"]').each(function(index){
                    $(this).find('a').text(numbers[index]);
                    if(numbers[index]==currentPage){
                        $this.find("."+activeClass).removeClass(activeClass);
                        $(this).addClass(activeClass);
                        pageIndex=currentPage;
                    }
                });

                if(currentPage==firstPageIndex && currentPage==lastPageIndex) {
                    $this.find('[data-sign="next"]').addClass("disabled");
                    $this.find('[data-sign="last"]').addClass("disabled");
                    $this.find('[data-sign="first"]').addClass("disabled");
                    $this.find('[data-sign="prev"]').addClass("disabled");
                } else if(currentPage==firstPageIndex){
                    $this.find('[data-sign="first"]').addClass("disabled");
                    $this.find('[data-sign="prev"]').addClass("disabled");
                    $this.find('[data-sign="next"]').removeClass("disabled");
                    $this.find('[data-sign="last"]').removeClass("disabled");
                } else if(currentPage==lastPageIndex){
                    $this.find('[data-sign="next"]').addClass("disabled");
                    $this.find('[data-sign="last"]').addClass("disabled");
                    $this.find('[data-sign="first"]').removeClass("disabled");
                    $this.find('[data-sign="prev"]').removeClass("disabled");
                } else{
                    $this.find('[data-sign="next"]').removeClass("disabled");
                    $this.find('[data-sign="last"]').removeClass("disabled");
                    $this.find('[data-sign="first"]').removeClass("disabled");
                    $this.find('[data-sign="prev"]').removeClass("disabled");
                }
                var result={
                    currentPage:currentPage,
                    pageSize:pageSize,
                    totalPage:lastPageIndex
                };
                option.data.pageIndex=currentPage;
                getData(false);
                if(option.callback){
                    option.callback();
                }
            }
        }

        function getData(isPagination){
            $.ajax({
                url:option.url,
                type:option.type,
                dataType:option.dataType,
                contentType:option.contentType,
                data:JSON.stringify(option.data),
                async:option.async,
                beforeSend:function(){
                    if(option.beforeSend){ option.beforeSend(); }
                },
                success:function(data){
                    option.success(data);
                    if(isPagination){
                        option.count=data.count | data.size;
                        pagination();
                    }
                },
                error:function(data){
                    if(option.eror){ option.error(data); }
                },
                complete:function(data){
                    if(option.complete){option.complete(data)}
                },
            });

        }
    };
})(jQuery);