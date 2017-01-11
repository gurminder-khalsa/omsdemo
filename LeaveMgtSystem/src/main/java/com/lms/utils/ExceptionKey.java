/**
 * 
 */
package com.lms.utils;

/**
 * @author gurminder.singh
 *
 */
public enum ExceptionKey {
	Leaves_Exhausted("Leaves_Exhausted"), User_Not_Found("User_Not_Found");
	
	String errorValue;
	
	ExceptionKey(String errorValue){
		this.errorValue = errorValue;
	}
	
	public String getExceptionValue(){
		return errorValue;
	}
	

}
