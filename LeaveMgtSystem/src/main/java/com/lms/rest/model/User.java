/**
 * 
 */
package com.lms.rest.model;

import java.util.List;

import com.lms.rest.model.api.IUser;

/**
 * @author gurminder.singh
 *
 */
public class User implements IUser {
	
	private Long id;	
	
	private String userName;	
	
	private String password;	
	
	private String firstName;	
	
	private String lastName;
	
	private String managerUser;
	
	private List<String> reportingUsers;

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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the managerUser
	 */
	public String getManagerUser() {
		return managerUser;
	}

	/**
	 * @param managerUser the managerUser to set
	 */
	public void setManagerUser(String managerUser) {
		this.managerUser = managerUser;
	}

	/**
	 * @return the reportingUsers
	 */
	public List<String> getReportingUsers() {
		return reportingUsers;
	}

	/**
	 * @param reportingUsers the reportingUsers to set
	 */
	public void setReportingUsers(List<String> reportingUsers) {
		this.reportingUsers = reportingUsers;
	}

}
