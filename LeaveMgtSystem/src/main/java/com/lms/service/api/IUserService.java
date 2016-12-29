/**
 * 
 */
package com.lms.service.api;

import java.util.List;

import com.lms.db.model.User;
import com.lms.rest.model.api.IUser;

/**
 * @author gurminder.singh
 *
 */
public interface IUserService {
	public List<User> getAllUser();
	
	public IUser getLoggedInUser() throws Exception;
}
