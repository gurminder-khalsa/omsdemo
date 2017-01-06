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
	
	public Long getId();
	
	public void setId(Long id);
	
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
	
	public IUser getAppliedBy();
	
	public void setAppliedBy(IUser appliedBy);
	
}
