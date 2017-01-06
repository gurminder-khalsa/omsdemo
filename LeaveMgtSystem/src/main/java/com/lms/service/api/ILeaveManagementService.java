/**
 * 
 */
package com.lms.service.api;

import java.util.List;

import com.lms.exception.LMSException;
import com.lms.rest.model.ApplyLeave;
import com.lms.rest.model.api.IApplyLeave;
import com.lms.rest.model.api.ILeaveType;

/**
 * @author gurminder.singh
 *
 */
public interface ILeaveManagementService {
	
	public List<ILeaveType> getLeaveTypes();
	public void submitLeaveDetails(IApplyLeave applyLeave) throws LMSException;
	public List<IApplyLeave> getLeaveDetailsForUser(String userName) throws LMSException;
	public List<IApplyLeave> getLeaveDetailsForLoggedInUser() throws LMSException;
	public List<IApplyLeave> getLeaveDetailsForReportingUsers() throws LMSException;
	public IApplyLeave getLeaveDetailsByLeaveId(Long appliedLeaveId);
	public void updateLeaveStatus(List<ApplyLeave> applyLeaves);
	public void cancelLeave(IApplyLeave applyLeave) throws LMSException;

}
