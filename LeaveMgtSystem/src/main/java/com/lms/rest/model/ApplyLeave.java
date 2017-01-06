/**
 * 
 */
package com.lms.rest.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.lms.rest.model.api.IApplyLeave;
import com.lms.rest.model.api.IUser;

/**
 * @author gurminder.singh
 *
 */
public class ApplyLeave implements IApplyLeave{
	
	private Long id;
	
	private String appliedleaveType;
	
	@DateTimeFormat(pattern="mm-dd-yy")
	private Date startDate;
	
	@DateTimeFormat(pattern="mm-dd-yy")
	private Date endDate;
	
	private String leaveReason;
	
	private String status;
	
	private IUser appliedBy;

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

	/**
	 * @return the appliedBy
	 */
	public IUser getAppliedBy() {
		return appliedBy;
	}

	/**
	 * @param appliedBy the appliedBy to set
	 */
	public void setAppliedBy(IUser appliedBy) {
		this.appliedBy = appliedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
