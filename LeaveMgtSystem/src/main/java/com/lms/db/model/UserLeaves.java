/**
 * 
 */
package com.lms.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author gurminder.singh
 *
 */
@Entity
@Table(name="user_leaves")
public class UserLeaves {
	
	@Id
	@GeneratedValue
	@Column(name="user_leave_id",unique=true,nullable=false)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private LeaveType leaveType;
	
	@Column(name="number_of_leaves")
	private Long numberOfLeaves;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Long getNumberOfLeaves() {
		return numberOfLeaves;
	}

	public void setNumberOfLeaves(Long numberOfLeaves) {
		this.numberOfLeaves = numberOfLeaves;
	}
	
}
