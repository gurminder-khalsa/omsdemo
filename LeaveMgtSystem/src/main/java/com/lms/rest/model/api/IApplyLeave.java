/**
 * 
 */
package com.lms.rest.model.api;

import java.util.Date;

/**
 * @author gurminder.singh
 *
 */
public interface IApplyLeave {
	
	public String getAppliedLeaveType();
	
	public void setAppliedLeaveType(String leaveType);
	
	public Date getStartDate();
	
	public void setStartDate(Date startDate);
	
	public Date getEndDate();
	
	public void setEndDate(Date endDate);
	
	public String getLeaveReason();
	
	public void setLeaveReason(String leaveReason);
	
	public String getStatus();
	
	public void setStatus(String status);
	
}
