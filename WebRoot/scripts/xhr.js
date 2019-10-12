/**
 *@auther luoyouhua
 *@date 2008-09-15
 *@comments ajax js
*/


function createxhr(){
  if(typeof XMLHttpRequest!='undefined'){
      return new XMLHttpRequest();
  }else{
        var arrSignatures = [ 'Microsoft.XMLHTTP', 'MSXML.XMLHTTP','Microsoft.XMLHTTP', 'Msxml2.XMLHTTP.7.0','Msxml2.XMLHTTP.6.0', 'Msxml2.XMLHTTP.5.0','Msxml2.XMLHTTP.4.0', 'MSXML2.XMLHTTP.3.0','MSXML2.XMLHTTP' ];
        for (var i=0; i < arrSignatures.length; i++) {
            try {return new ActiveXObject(arrSignatures[i]);} catch (oError) { }
        }          
        throw new Error("MSXML is not installed on your system.");         
  }
  throw new Error("MSXML is not installed on your system.");           
}
  
function _push(r,e){
	var tagName = e.tagName;
	if(tagName.toLowerCase()=="input"){
		if((e.type.toLowerCase()=="checkbox")||(e.type.toLowerCase()=="radio")){
			if(e.checked){
			    r.push({key:e.name,value:encodeURIComponent(e.value)});	
			}
		}else{
			r.push({key:e.name,value:encodeURIComponent(e.value)});
		}
	}else{
		if((e.name!=undefined)&&(e.value!=undefined)){
			r.push({key:e.name,value:encodeURIComponent(e.value)});
		}
	}
}
function appUrlPar(url,params){
   for(var i=0;i<params.length;i++){
      var str = (i==0)?("?"+params[i].key+"="+params[i].value):("&"+params[i].key+"="+params[i].value);
      url+=str;
   }
   return url;
}
function appUrlArr(url,r){
   var k=(url.indexOf('?')==-1)?"?":"&";
   for(var i=0;i<r.length;i++){
      var key = r[i].key;var p=r[i].params
      for(var j=0;j<p.length;j++){
        var str=((i==0)&&(j==0))?(k+key+"="+p[j]):("&"+key+"="+p[j]);
        url+=str;
      }
   }
   return url;
}
function xhr(url,fn,b){
  this.fullURL = null;
  this.callback = fn; 
  this.params = [];
  this.r =[];
  this.browser={
      isMozilla : (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument!='undefined'),
	  isIE      : window.ActiveXObject?true:false,
	  isOpera   : (navigator.userAgent.toLowerCase().indexOf("opera")!=-1)
  };
  this.setCallback = function(){
     this.callback=arguments[0];
  };
  this.setParameter=function(k,v){
     var b = false;
     for(var i=0,e;e=this.params[i];i++){
        if(e.key==k){
           e.value = encodeURIComponent(v); b=true;
        }
     }
     if(!b) this.params.push({key:k,value:encodeURIComponent(v)});
     return this;
  };
  this.setArray = function(k,arr){
     this.r.push({key:k,params:arr});return this;
  }
  
  this.setForm = function(form){
  	var arr = form.elements;
  	for(var i=0;i<arr.length;i++){
  		_push(this.params,arr[i]);
  	}
  	return this;
  }
  this.createFullURL=function(){this.fullURL=appUrlArr(appUrlPar(url,this.params),this.r)};
  this.execute = function(){
       this.createFullURL();
       var xhr = createxhr();
       xhr.onreadystatechange=function(){
       if(xhr.readyState == 4) {           
           var data = eval("(" + xhr.responseText.replace(/\\"/g,"'")+")");
           fn(data);
       }
     }
     if(b){
        xhr.open('POST', url,true);
        //xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xhr.send(appUrlArr(appUrlPar("",this.params),this.r).slice(1));
     }else{
        xhr.open('POST', this.fullURL,true);
        xhr.send(null);
     }
  }
}
