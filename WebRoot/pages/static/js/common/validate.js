
var Validate = {
    email:function(value){
        var b = /^([a-zA-Z0-9_\-\\.])+@\w+\..+$/.test(value);
        return b;
    },
    phone:function(value){
        var b = /^1[3456789]\d{9}$/.test(value);
        return b;
    },
    minlength:function(o){
        var th = o.elm,v = o.v;
        return th.value.trim().length>=v;
    },
    maxlength:function(o){
        var th = o.elm,v=o.v;
        return th.value.length<=v;
    },
    maxLength:function(o){//最大字节数
        var th = o.elm,v = o.v;
        return th.value.replace(/[\u4e00-\u9fa5]/,"**").length<=v;
    },
    equals:function(o){//密码比较
        var th = o.elm; var eq = o.elm.form[""+o.v+""];
        return th.value == eq.value;
    },
    isurl:function(o){//url验证
        var th = o.elm;
        if(th.value.length==0){
            return true;
        }
        return th.value.match(/(?:http|https):\/\/[^\.]+\..+$/i);
    },
    suffix:function(o){//后缀名判断
        var th = o.elm,exp = o.v;
        if(th.value.length==0){return true;}
        return th.value.match(exp);
    },
    isImgFile:function(o){//是否是图片文件
        var file = o.elm;
        if(file.value.length==0){
            return true;
        }
        return file.value.match(/.jpg|.gif|.png|.bmp/i);
    },
    isNumber:function(o){
        var inp = o.elm;
        if(inp.value.length==0){
            return true;
        }
        return /^[0-9]*$/.test(inp.value);
    },
    isFloat:function(o){
        var inp = o.elm;
        if(inp.value.length==0){
            return true;
        }
        return !isNaN(inp.value);
    }
}



	 