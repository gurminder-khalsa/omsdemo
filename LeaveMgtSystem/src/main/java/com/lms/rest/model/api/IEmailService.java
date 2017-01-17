/**
 * 
 */
package com.lms.rest.model.api;

import com.lms.db.model.User;

/**
 * @author gurminder.singh
 *
 */
public interface IEmailService {
	
	public void sendLeaveApplicationEmails(User user, com.lms.db.model.ApplyLeave applyLeaveEntity);
	
	public void sendLeaveAppliedMailForApproval(User user, com.lms.db.model.ApplyLeave applyLeaveEntity);
	
	public void sendLeaveAppliedMailToApplier(User user, com.lms.db.model.ApplyLeave applyLeaveEntity);

}
