package com.wooboo.dsp.base.enums;
/**
 * 
 * @author duanwei
 *
 */
public enum NijiadehuaEnums {

	 /** * 操作成功 */
	SUCCESS(0, ""),
	NO_SUCH_SERVICE(1000, "没有对应的服务"),
    SERVICE_EXCEPTION(1111, "业务异常");

    ;
    
    /**
     * 枚举的值
     * */
    private long code;

    /**
     * 枚举的中文描述
     * */
    private String desc;


    private NijiadehuaEnums(int code, String desc) {
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
