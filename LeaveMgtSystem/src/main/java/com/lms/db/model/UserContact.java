/**
 * 
 */
package com.lms.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author gurminder.singh
 *
 */
@Entity
@Table(name="lms_user_contact") 
public class UserContact {
	
	@Id
	@GeneratedValue
	@Column(name="user_contact_id",unique=true,nullable=false)
	private Long id;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="email_address")
	private String emailAddress;
	
	@OneToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
}
