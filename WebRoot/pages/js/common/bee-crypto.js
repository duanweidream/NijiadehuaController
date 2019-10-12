var BeeCrypto = function () {
	var key = CryptoJS.enc.Utf8.parse("abcdefgabcdefg12");   
	var iv   = CryptoJS.enc.Utf8.parse("0807060504030201");  
    return {
    	encrypt: function (src) {    	     
             var srcs = CryptoJS.enc.Utf8.parse(src); 
             var encrypted = CryptoJS.AES.encrypt(srcs, key, { iv: iv,mode:CryptoJS.mode.CBC});  
             return encrypted.toString(); 
        }
    };

}();