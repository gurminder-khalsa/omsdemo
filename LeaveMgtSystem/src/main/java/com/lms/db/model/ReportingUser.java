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
@Table(name="reporting_user")
public class ReportingUser {
	
	@Id
	@GeneratedValue
	@Column(name="reporting_user_id",unique=true,nullable=false)
	private Long id;
	
	@ManyToOne
	private User user;

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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	

}
