/**
 * 
 */
package com.lms.rest.model.api;



/**
 * @author gurminder.singh
 *
 */
public interface IUserLeave {
	
	public ILeaveType getLeaveType();
	
	public void setLeaveType(ILeaveType leaveType);
	
	public Long getNumberOfLeaves();
	
	public void setNumberOfLeaves(Long numberOfLeaves);
}
