/**
 * 
 */
package com.lms.exception;

import com.lms.utils.ExceptionKey;

/**
 * @author gurminder.singh
 *
 */
public class LMSException extends Exception {

	private static final long serialVersionUID = -6576802182985246587L;
	private String message = null;
	private ExceptionKey exceptionKey;
	
	public LMSException(String message){
		 super(message);
	     this.message = message;
	}
	
	public LMSException(String message, Exception ex){
		 super(message , ex);
	     this.message = message;
	}
	
	public LMSException(ExceptionKey exceptionKey, String message){
		 super(message);
	     this.message = message;
	     this.exceptionKey = exceptionKey;
	}
	
	
	public LMSException(Throwable cause){
		 super(cause);
	}
	
	@Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
    
    public ExceptionKey getExceptionKey(){
    	return exceptionKey;
    }

}
