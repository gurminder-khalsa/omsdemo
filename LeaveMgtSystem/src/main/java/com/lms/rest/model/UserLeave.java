/**
 * 
 */
package com.lms.rest.model;

import com.lms.rest.model.api.ILeaveType;
import com.lms.rest.model.api.IUserLeave;

/**
 * @author gurminder.singh
 *
 */
public class UserLeave implements IUserLeave {
	
	private ILeaveType leaveType;
	private Long numberOfLeaves;

	/* (non-Javadoc)
	 * @see com.lms.rest.model.api.IUserLeave#getLeaveType()
	 */
	@Override
	public ILeaveType getLeaveType() {
		return leaveType;
	}

	/* (non-Javadoc)
	 * @see com.lms.rest.model.api.IUserLeave#setLeaveType(com.lms.rest.model.api.ILeaveType)
	 */
	@Override
	public void setLeaveType(ILeaveType leaveType) {
		this.leaveType = leaveType;
	}

	/* (non-Javadoc)
	 * @see com.lms.rest.model.api.IUserLeave#getNumberOfLeaves()
	 */
	@Override
	public Long getNumberOfLeaves() {
		return numberOfLeaves;
	}

	/* (non-Javadoc)
	 * @see com.lms.rest.model.api.IUserLeave#setNumberOfLeaves(java.lang.Long)
	 */
	@Override
	public void setNumberOfLeaves(Long numberOfLeaves) {
		this.numberOfLeaves = numberOfLeaves;
	}

}
