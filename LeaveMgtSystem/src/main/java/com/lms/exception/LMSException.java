/**
 * 
 */
package com.lms.exception;

/**
 * @author gurminder.singh
 *
 */
public class LMSException extends Exception {

	private static final long serialVersionUID = -6576802182985246587L;
	private String message = null;
	
	public LMSException(String message){
		 super(message);
	     this.message = message;
	}
	
	public LMSException(String message, Exception ex){
		 super(message , ex);
	     this.message = message;
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

}
