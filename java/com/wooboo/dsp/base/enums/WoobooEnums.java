package com.wooboo.dsp.base.enums;
/**
 * 
 * @author duanwei
 *
 */
public enum WoobooEnums {

	 /** * 操作成功 */
    RESCODE_0(0, "操作成功"),
    /** *服务器发生异常 */
    RESCODE_1(1, "服务器发生异常"),
    /** *请求参数错误或消息体解析失败 */
    RESCODE_2(2, "请求参数错误或消息体解析失败"),
    /** *操作失败! */
    RESCODE_3(3, "操作失败"),
    
    RESCODE_4(4, "用户不存在"),
    
    RESCODE_5(5, "密码错误"),

    RESCODE_6(6, "用户名已存在"),
    
    RESCODE_7(7, "验证码错误"),
    RESCODE_8(8, "请选择上传的文件"),
    
    RESCODE_9(9, "此用户已不可使用"),

    RESCODE_10(10, "数据已经被使用"),
    RESCODE_11(11, "激活失败"),
    RESCODE_12(12, "激活链接已过期"),
    RESCODE_13(13, "激活成功"),
    RESCODE_14(14, "邮箱不存在"),
    RESCODE_15(15, "上传验证文件"),
    RESCODE_16(16, "邮件发送失败"),
    RESCODE_17(17, "公司名称已存在"),
    RESCODE_18(18, "一次性导入数据不能超过5万条"),
    ;
    
    /**
     * 枚举的值
     * */
    private long code;

    /**
     * 枚举的中文描述
     * */
    private String desc;


    public static String getDesc(long code) {
        for (WoobooEnums b : WoobooEnums.values()) {
            if (b.code == code) {
                return b.desc;
            }
        }
        return "";
    }


    /**
     * 匹配提示码 未匹配的返回错误码1
     * @param code
     * @return
     */
    public static WoobooEnums getEnum(long code) {
        for (WoobooEnums b : WoobooEnums.values()) {
            if (b.code == code) {
                return b;
            }
        }
        return WoobooEnums.RESCODE_1;
    }


    private WoobooEnums(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public long getCode() {
        return code;
    }


    public void setCode(long code) {
        this.code = code;
    }


    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
