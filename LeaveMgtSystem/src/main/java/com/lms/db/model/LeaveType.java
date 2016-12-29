/**
 * 
 */
package com.lms.db.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author gurminder.singh
 *
 */
@Entity
@Table(name="lms_leave_type")
public class LeaveType {
	
	@Id
	@GeneratedValue
	@Column(name="leave_type_id",unique=true,nullable=false)
	private Long id;
	
	@Column(name="leave_type")
	private String leaveType;
	
	@Column(name="leave_description")
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
