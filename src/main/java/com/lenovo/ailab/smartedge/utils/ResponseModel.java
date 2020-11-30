package com.lenovo.ailab.smartedge.utils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**  
 * Title: ResponseModel.java
 * Description:  
 * Copyright: AILib Lenovo Research  
 * Company: www.lenovo.com  
 * @author "chenpeng"
 * @date 2018年4月11日  
 */
public class ResponseModel implements Serializable {

    private int status;
    private Object result;
    private String message = "OK";

    public static ResponseModel ok(Object result){
        return  new ResponseModel(0,result);
    }
    
	public static ResponseModel fail(String errorMsg, int status) {
		if (StringUtils.isBlank(errorMsg))
			errorMsg = "failed";
		return new ResponseModel(status, "{}", errorMsg);
	}

	public static ResponseModel fail(Object result, int status) {
		return new ResponseModel(status, result, "failed");
	}

	public static ResponseModel failForInvalidParameter() {
		return fail(StatusCode.getMessage(StatusCode.INVALID_PARAMETER), StatusCode.INVALID_PARAMETER);
	}

    public static ResponseModel exception(String errorMsg,int status){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(status);
        responseModel.setResult("{}");
        responseModel.setMessage(errorMsg);
        return responseModel;
    }
    
    public static ResponseModel exception(Object result, int status){
        return new ResponseModel(status,result);
    }

    public ResponseModel(int status, Object result, String errorMsg) {
        this.status = status;
        this.result = result;
        this.message = errorMsg;
    }

    public ResponseModel(int status, Object result) {
        this.status = status;
        this.result = result;
    }
    public ResponseModel() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
