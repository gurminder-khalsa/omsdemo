/**
 * 
 */
package com.lms.utils;

/**
 * @author gurminder.singh
 *
 */
public enum ExceptionKey {
	Leaves_Exhausted("Leaves_Exhausted");
	
	String errorValue;
	
	ExceptionKey(String errorValue){
		this.errorValue = errorValue;
	}
	
	public String getExceptionValue(){
		return errorValue;
	}
	

}
