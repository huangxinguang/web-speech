package com.iflytek.speech.webspeech.util;


import com.iflytek.speech.webspeech.enums.ResponceStateEnum;

/**
 * @author DELL-5490
 *
 */
public class ResultUtil {
	
	private static class ResultEmpty{
		
		private static final Result result = new Result();
		
	}
	
	public static Result getResultSuccess(){
		return getResultSuccess(ResponceStateEnum.SUCCESS_MSG.getCode(),ResponceStateEnum.SUCCESS_MSG.getDesc() , ResultEmpty.result);
	}
	
	public static <T> Result getResultSuccess(String msg){
		return getResultSuccess(ResponceStateEnum.SUCCESS_MSG.getCode(),msg,ResultEmpty.result);
	}
	
	public static <T> Result getResultSuccess(T data){
		return getResultSuccess(ResponceStateEnum.SUCCESS_MSG.getCode(),ResponceStateEnum.SUCCESS_MSG.getDesc(),data);
	}
	
	public static <T> Result getResultSuccess(String msg,T data){
		return getResultSuccess(ResponceStateEnum.SUCCESS_MSG.getCode(),msg,data);
	}
	
	public static <T> Result getResultSuccess(int code,String msg,T data){
		return new Result(code,msg ,data);
	}
	
	
	public static Result getResultError(){
		return new Result(ResponceStateEnum.ERROR_MSG.getCode(),ResponceStateEnum.ERROR_MSG.getDesc() , ResultEmpty.result);
	}
	
	public static <T> Result getResultError(String msg){
		return getResultError(ResponceStateEnum.ERROR_MSG.getCode(),msg,ResultEmpty.result);
	}
	
	public static <T> Result getResultError(T data){
		return getResultError(ResponceStateEnum.ERROR_MSG.getCode(),ResponceStateEnum.ERROR_MSG.getDesc(),data);
	}
	
	public static <T> Result getResultError(String msg,T data){
		return getResultError(ResponceStateEnum.ERROR_MSG.getCode(),msg,data);
	}
	
	public static <T> Result getResultError(int code,String msg,T data){
		return new Result(code,msg ,data);
	}
	

}
