/**
 * 
 */
package com.lms.rest.model;

import java.util.Date;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import com.lms.rest.model.api.IApplyLeave;

/**
 * @author gurminder.singh
 *
 */
public class ApplyLeave implements IApplyLeave{
	
	private String appliedleaveType;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	private String leaveReason;
	
	private String status;

	/**
	 * @return the leaveType
	 */
	public String getAppliedLeaveType() {
		return appliedleaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setAppliedLeaveType(String leaveType) {
		this.appliedleaveType = leaveType;
	}
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the leaveReason
	 */
	public String getLeaveReason() {
		return leaveReason;
	}

	/**
	 * @param leaveReason the leaveReason to set
	 */
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
