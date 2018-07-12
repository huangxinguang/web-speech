package com.iflytek.speech.webspeech.enums;

/**
 * 返回状态响应枚举
 * @author DELL-5490
 *
 */
public enum ResponceStateEnum {
	
	SUCCESS_MSG(200,"成功"),
	ERROR_MSG(501,"失败"),
	;
	
	private int code;
	
	private String desc;

	private ResponceStateEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
    	
 
}
