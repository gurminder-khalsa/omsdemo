/**
 * 
 */
package com.lms.db.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author gurminder.singh
 *
 */
@Entity
@Table(name="lms_user")
public class User {
	
	@Id
	@GeneratedValue
	@Column(name="user_id",unique=true,nullable=false)
	private Long id;
	
	@Column(name="username",unique=true,nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<UserRole> userRole;
			
	@OneToOne
	private User manager;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private UserContact userContact;
 
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
	 * @return the userRole
	 */
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	/**
	 * @return the manager
	 */
	public User getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(User manager) {
		this.manager = manager;
	}

	public UserContact getUserContact() {
		return userContact;
	}

	public void setUserContact(UserContact userContact) {
		this.userContact = userContact;
	}
	
}
