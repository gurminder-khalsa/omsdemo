/**
 * 
 */
package com.lms.rest.model;

import com.lms.rest.model.api.ILeaveType;

/**
 * @author gurminder.singh
 *
 */
public class LeaveType implements ILeaveType {
	
	private Long id;
	
	private String leaveType;
	
	private String leaveDescription;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/**
	 * @return the leaveDescription
	 */
	public String getLeaveDescription() {
		return leaveDescription;
	}

	/**
	 * @param leaveDescription the leaveDescription to set
	 */
	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}
	
	

}
