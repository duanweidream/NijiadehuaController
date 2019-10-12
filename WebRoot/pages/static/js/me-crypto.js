/**
 *  依赖  <script src="../scripts/crypto/crypto-js.js"></script>
 *
 * */

// public static final String KEY = "PwaWQ5rEFEl3u9yR";
// public static final String IV = "F4vfSfhx3nPaLSeP";
var MeCrypto = function () {
    var key = CryptoJS.enc.Utf8.parse("PwaWQ5rEFEl3u9yR");
    var iv   = CryptoJS.enc.Utf8.parse("F4vfSfhx3nPaLSeP");
    return {
        //算法 AES /模式CBC / 补码方式 Pkcs7
        encrypt: function (src) {
            var srcs = CryptoJS.enc.Utf8.parse(src);
            var encrypted = CryptoJS.AES.encrypt(srcs, key, { iv: iv,mode:CryptoJS.mode.CBC});
            return encrypted.toString();
        },
        decrypt: function (srcs) {
            var decrypt = CryptoJS.AES.decrypt(srcs, key, {iv:iv,mode:CryptoJS.mode.CBC,padding: CryptoJS.pad.Pkcs7});
            return decrypt.toString(CryptoJS.enc.Utf8);
        }
    };
}();


