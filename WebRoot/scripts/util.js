var broswer = {  
       isIE:navigator.userAgent.toLowerCase().match(/msie ([\d.]+)/)?true:false,  
       ie6:navigator.userAgent.toLowerCase().match(/msie 6/)?true:false,  
       ie7:navigator.userAgent.toLowerCase().match(/msie 7/)?true:false,  
       ie8:navigator.userAgent.toLowerCase().match(/msie 8/)?true:false,  
       isMozilla:navigator.userAgent.toLowerCase().match(/firefox\/([\d.]+)/)?true:false,  
       isOpera:(navigator.userAgent.toLowerCase().indexOf('opera')!=-1)  
  }  
String.prototype.trim = function(){
   return this.replace(/(^\s*)||(\s*$)/g,'');
}
var util = {
	  _stopPropagation:function() {
	    var e = window.event ? window.event: util._stopPropagation.caller.arguments[0];
	    if (window.event) {
	        window.event.cancelBubble = true
	    } else if (e && e.stopPropagation) {
	        e.stopPropagation()
	    }
	},
  $:function(e){return document.getElementById(e)},
  to_array:function(arr){
    var r = [];
    util.each(arr,function(e){r.push(e);});
    return r;
  },
  add_event:function(t,e,fn){
	if(window.addEventListener){
		t.addEventListener(e,fn,false)
	}else if(window.attachEvent){
		t.attachEvent("on"+e,fn)
	}else{
		t["on"+e]=fn
	}
  },
  _apply:function(){
		var a=util.to_array(arguments),o=a.shift(),f=a.shift();
		return function(){
		   return f.apply(o,a);
		}		
  },
  each:function(r,fn,bn,en){
      var  b=bn||0,e=en||r.length;
      for(var i=b;i<e;i++){
         var result = fn(r[i],i);
         if(result!=undefined) return result;
      }
  },
  cElem: function(t,o,html){
   if(broswer.isIE&&(t.toLowerCase()=='input')&&(!!o)&&(!!o.type)&&(o.type.toLowerCase()=="radio")){
     var s = "";
     for(var p in o){s+= (p=="cssText")?("style='"+o[p]+"'"):(p+"='"+o[p]+"'");}
     return document.createElement("<input type='radio' "+s+"  />");
   }else{
      var tag = document.createElement(t);
      for(var p in o){
         if(p=="cssText"){
            tag.style.cssText = o[p];
         }else if(p=="class"||p=="className"){
            tag.className = o[p];
         }else{
            tag[p] = o[p];
         }
	  };
	  html&&(tag.innerHTML = html);
	  return tag;
   }
  },
  radioValue:function(n){
    var r = document.getElementsByName(n);
    return util.each(r,function(e){if(e.checked) return e.value;})
  },
  selectValue:function(sel){
    var ops = sel.options;var val="";
    util.each(ops,function(op){ op.selected&&(val=op.value);});  
    return val;
  },
  checkboxValue:function(n){
	  var r = document.getElementsByName(n);
	  var vals=[];
	   util.each(r,function(e){if(e.checked) vals.push(e.value);});
	  return vals;
  },
  justNum: function(passCode){
	        var evt = window.event?window.event:util.justNum.caller.arguments[0];
		    var code = window.event?event.keyCode:evt.which;
		    var b0=((code>=48)&&(code<=57)||(code==8));var b1=false;
		    if(!!passCode){
		        for(var i=0;i<passCode.length;i++){
		           if(code==passCode[i]) b1=true;
		        }
		    }
		      if(!(b0||b1)){
		         window.event?window.event.keyCode=0:evt.preventDefault();
		      }
  },
  checkAll:function(th,n){
     var r = document.getElementsByName(n);
     util.each(r,function(e){e.checked = th.checked;})
  },
  allCheck:function(n,id){
     var r = document.getElementsByName(n);var b = true;
     util.each(r,function(e) {if(!e.checked) {b=false; return "break"}});
     util.$(id).checked = b;
  },
  isCheck:function(n){
      var r = document.getElementsByName(n);
      if(!!r){
    	  var b=false;
    	  util.each(r,function(e){if(e.checked){b=true;}});
    	  return b;
      }else{
    	  return false;
      }
  },
  getParameter : function(p){
	  var parseStr =  window.location.search.slice(1);
	  var paraList = parseStr.split(/\&/g);
	  for(var i=0;i<paraList.length;i++){
	     var pattern = new RegExp("^"+p+"[?=\\=]","g");
	     if(pattern.test(paraList[i])){
	        return decodeURIComponent(paraList[i].split(/\=/g)[1]);
	     }
	  }
	  return null;
	},
	removeAll : function(e){
	  var o = null;
	  while(o=e.firstChild){
	    e.removeChild(e.firstChild);
	  }
	},
	call:function(){
	   var args = util.toArray(arguments);
	   var fn = args.shift();
	   var o = args.shift();
	   return function(){
	      fn.apply(o,args);
	   }
	},
	toArray:function(a){
	    var r=[];
        util.each(a,function(e){r.push(e)});
        return r
	},
	setPosition:function(div,e,pos){
	   div.style.position = "absolute";
	   var a = e.getBoundingClientRect();
	   var l=document.documentElement.scrollLeft+0,t=document.documentElement.scrollTop+0;
	   var ln=document.documentElement.clientLeft||0;var tn = document.documentElement.clientTop||0;
	   if(pos=="UP"){
	        l += a.left;
	        t += (a.top -div.offsetHeight);
	   }else if(pos=="RIGHT"){
	        l += (a.left+e.offsetWidth) ;
	        t += (a.top-div.offsetHeight+div.offsetHeight);
	   }else if(pos=="LEFT"){
	        l = a.left-div.offsetWidth;
	        t += (a.top-div.offsetHeight+div.offsetHeight);
	   }else{
	        l += a.left-ln;
	        t += (a.top+e.offsetHeight-tn);
	   }
       div.style.left = l+"px";div.style.top = t+"px";
	},
	keyCode:function(evt){
	    var evt = window.event?window.event:evt;
		return window.event?event.keyCode:evt.which;
    },
    fixed: function (x, n) {
		with (Math) {
			var N = Math.pow(10, n);
			return Math.round(x * N) / N;
		}
    },
    dusk_div:function(boxId){
       var box = document.getElementById(boxId);
       if(box){
          
	       var div = document.createElement("div");div.id = "market_dusk";
	       div.style.cssText = "position:absolute;top:0px;left:0px;width:1000px;height:1000px;z-index:1000; opacity:0.4;background:#C0C0C0;filter:alpha(opacity=40);";
	       div.style.width = document.documentElement.scrollWidth+"px";
	       div.style.height = document.documentElement.scrollHeight +"px";
	       document.body.appendChild(div);
	     
           with(box.style){
              position = "absolute";
              zIndex = "1001";
              display = "";
           }
           box.style.top = document.documentElement.scrollTop+100+"px";
           box.style.left = (document.documentElement.clientWidth/2)-(box.offsetWidth/2)+"px";
       }
    },
    prew_image:function(upLoadImgFile,div,o){
        //set div class
        o && o.className && (div.className=o.className);
        
	    if(upLoadImgFile.tagName.toUpperCase()=='SELECT'){
	        append_img(div,util.selectValue(upLoadImgFile),o);
	        alert('1');
	     }else if(upLoadImgFile.type=='text'){
	        alert('2');
	        append_img(div,upLoadImgFile.value,o);
	     }else if(broswer.isIE){
			  div.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
			  if(broswer.isIE7 || broswer.isIE8){ 
				  alert('78');
				  upLoadImgFile.select();
				  var imgPath = document.selection.createRange().text; 
				  document.selection.empty(); 
				  alert(imgPath);
				  div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgPath;
			  }else{ 
			      alert('3'+upLoadImgFile.value);
			      append_img(div,upLoadImgFile.value,o);
			       div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = upLoadImgFile.value;
			  }
		  }else{
		    append_img(div,upLoadImgFile.files.item(0).getAsDataURL(),o);
		  }
    },
    close_dusk:function(boxId){
      var box = document.getElementById(boxId);
      if(box){
          var market_dusk = document.getElementById('market_dusk');
          market_dusk&&document.body.removeChild(market_dusk);
          box.style.display ="none";
      }
    },
    setForm:function(form,obj){
       var arr = form.elements;
       util.each(arr,function(e){
          var v = obj[e.name];
          v&&util.setValue(e,v);
       });
    },
    setValue:function(e,v){//e: element v:value
       var tn,t;
       e.tagName&&(tn=e.tagName.toUpperCase()); e.type&&(t=e.type.toUpperCase());
       if(tn=='INPUT'){
          if(t=='RADIO'){
	        var r = document.getElementsByName(e.name);
	        util.each(r,function(elm){elm.checked = elm.value==v?1:0;}); 
	      }else if(t=='CHECKBOX'){
	        var isArray =(v instanceof Array);//是否是数组
	        var r = document.getElementsByName(e.name);
	        if(!isArray){
	           util.each(r,function(elm){elm.checked = elm.value==v?1:0;}); 
	        }else{
	           util.each(r,function(elm){elm.checked = util.exist(v,r)?1:0;}); 
	        }
	      }else{
	        e.value=v;  
	      }
       }else if(tn=='SELECT'){
          util.each(e.options,function(op){
            op&&(op.value==v)&&(op.selected=1);
          })
       }else{
           e.innerHTML = v;
       }
    },
    exist:function(v,r){
      var t = false;
      util.each(r,function(e){(e==v)&&(t=true);});
      return t;
    },
    addOption:function(tar,value,text){
      var op = new Option(text,value);
       broswer.isMozilla?tar.appendChild(op):tar.add(op);
    },
    check_url: function (str_url) {// 验证url 
			var re = /(?:http|https):\/\/[^\.]+\..+$/;//new RegExp(strRegex); 
			return re.test(str_url); 
  } ,
  set_display:function(ids,t){
     
  },
  substring:function(str,n){
	return str.length>n?(str.substring(0,n)+'...'):str;  
  },
  dx:function(n){
  if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
	return "";
	var unit = "千百拾亿千百拾万千百拾元角分", str = "";
	n += "00";
	var p = n.indexOf('.');
	if (p >= 0)
	n = n.substring(0, p) + n.substr(p+1, 2);
	unit = unit.substr(unit.length - n.length);
	for (var i=0; i < n.length; i++)
	str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
	return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
  },
  parseFloat:function(n){
    if(n.length==0){
       return 0;
    }if(isNaN(n)){
       return 0;
    }else{
       return parseFloat(n);
    }
  },
  parseInt:function(n){
	  if(n.length==0){
	       return 0;
	    }if(isNaN(n)){
	       return 0;
	    }else{
	       return parseInt(n);
	    }
  },
  childNum:function(o){
	  var n=-1
	  if(o){
		  var p = o.parentNode; var tn=o.tagName;var r=[];
		  util.each(p.childNodes,function(e){
			  if(e.tagName&& (tn.toLocaleLowerCase()==e.tagName.toLocaleLowerCase())){
				  r.push(e);
			  }
		  });
		  util.each(r,function(th,i){(o==th)&&(n=i)});
	  }
      return n;
  }
}


function insertAfter(newElement,targetElement) {
  var parent = targetElement.parentNode; 
  if (parent.lastChild == targetElement) {
      parent.appendChild(newElement); 
  } else {
      parent.insertBefore(newElement,targetElement.nextSibling); } 
  }
var D=document, Cookies = {//
		 /**n:cookies名 v:值 h:保存时间 m:domain p:path b:securce*/
		 set:function(n,v,h,m,p,b){
			var d = new Date();
			var hr = h||24;//默认保存24个小时
			var m=m||D.location.hostname;//D.domain;
			d.setTime(d.getTime()+hr*60*60*1000);
		    var cookieStr = n + '=' + encodeURIComponent(v)+ (d ? '; expires=' + d.toGMTString() : '')+ (p ? '; path=' + p : '; path=/')+ (m ? '; domain=' + m : '')+ (b ? '; secure' : '');
			 D.cookie =cookieStr;
		 },
		 get:function(n){
		    var cookies = D.cookie;
			var arr=cookies.split("; ");
		       for(var i=0;i<arr.length;i++){
			   var s = arr[i]; var sr = s.split("=");
			   if(sr[0]==n) return decodeURIComponent(sr[1].replace(/^["']|["']$/gm,''));
			};return null;
		 },
		 remove:function(n){
		     Cookies.set(n,"",-1);
		 }
	 };
	 