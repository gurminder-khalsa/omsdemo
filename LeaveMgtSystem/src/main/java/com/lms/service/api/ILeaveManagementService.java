/**
 * 
 */
package com.lms.service.api;

import java.util.List;

import com.lms.rest.model.api.IApplyLeave;
import com.lms.rest.model.api.ILeaveType;

/**
 * @author gurminder.singh
 *
 */
public interface ILeaveManagementService {
	
	public List<ILeaveType> getLeaveTypes();
	public void submitLeaveDetails(IApplyLeave applyLeave);
	public List<IApplyLeave> getLeaveDetailsForUser(String userName);
	public List<IApplyLeave> getLeaveDetailsForLoggedInUser();

}
