/**
 * 
 */
package com.lms.rest.model;

import java.util.List;

/**
 * @author gurminder.singh
 *
 */
public class LeaveDetailsForm {
	
	private List<ApplyLeave> applyLeaves;

	public List<ApplyLeave> getApplyLeaves() {
		return applyLeaves;
	}

	public void setApplyLeaves(List<ApplyLeave> applyLeaves) {
		this.applyLeaves = applyLeaves;
	}

}
