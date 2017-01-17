/**
 * 
 */
package com.lms.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.lms.db.model.ApplyLeave;
import com.lms.db.model.User;
import com.lms.rest.model.api.IEmailService;

/**
 * @author gurminder.singh
 *
 */
@Service
public class EmailService implements IEmailService {
	
	@Autowired
	 JavaMailSender mailSender;
	 
	 @Autowired
	 private VelocityEngine velocityEngine;


	/* (non-Javadoc)
	 * @see com.lms.rest.model.api.IEmailService#sendLeaveApplicationEmails(com.lms.db.model.User, com.lms.db.model.ApplyLeave)
	 */
	@Override
	public void sendLeaveApplicationEmails(User user, ApplyLeave applyLeaveEntity) {
		sendLeaveAppliedMailToApplier(user, applyLeaveEntity);
		sendLeaveAppliedMailForApproval(user, applyLeaveEntity);
	}

	/* (non-Javadoc)
	 * @see com.lms.rest.model.api.IEmailService#sendLeaveAppliedMailForApproval(com.lms.db.model.User, com.lms.db.model.ApplyLeave)
	 */
	@Override
	public void sendLeaveAppliedMailForApproval(User user, ApplyLeave applyLeaveEntity) {
		
		String template = "templates/applyMailTemplateForApproval.vm";
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user);
        model.put("leaveDetails", applyLeaveEntity);
        
        EmailWrapper emailWrapper = new EmailWrapper();
        emailWrapper.setToEmail(user.getManager().getUserContact().getEmailAddress());
        emailWrapper.setFromEmail(user.getUserContact().getEmailAddress());
        emailWrapper.setAttributes(model);
        
		sendEmail(template, model, emailWrapper);
		
	}


	/* (non-Javadoc)
	 * @see com.lms.rest.model.api.IEmailService#sendLeaveAppliedMailToApplier(com.lms.db.model.User, com.lms.db.model.ApplyLeave)
	 */
	@Override
	public void sendLeaveAppliedMailToApplier(User user, ApplyLeave applyLeaveEntity) {

		String template = "templates/applyMailTemplateToApplier.vm";
		Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", user);
        model.put("leaveDetails", applyLeaveEntity);
        
        EmailWrapper emailWrapper = new EmailWrapper();
        emailWrapper.setToEmail(user.getUserContact().getEmailAddress());
        emailWrapper.setFromEmail("donotreply@lms.com");
        emailWrapper.setAttributes(model);
        
        sendEmail(template, model, emailWrapper);

	}
	
	private void sendEmail(String template, Map<String, Object> model, EmailWrapper emailWrapper) {
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	MimeMessageHelper helper;
                helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject("Leave Applied");
                helper.setFrom(new InternetAddress(emailWrapper.getFromEmail()));
                helper.setTo(new InternetAddress(emailWrapper.getToEmail()));               
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, model);
                helper.setText(text,true);                  
            }
        };
		
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            ex.printStackTrace();
        }
	}
	
	class EmailWrapper{
		
		private String toEmail;
		
		private String fromEmail;
		
		private String subject;
		
		private String text;
		
		private Map<String,Object> attributes;
		
		public EmailWrapper() {
			//Default Constructor;
		}
		
		public EmailWrapper(String toEmail, String fromEmail, String subject, String text, Map<String,Object> attributes) {
			this.toEmail = toEmail;
			this.fromEmail = fromEmail;
			this.subject = subject;
			this.text = text;
			this.attributes = attributes;		
		}

		public String getToEmail() {
			return toEmail;
		}

		public void setToEmail(String toEmail) {
			this.toEmail = toEmail;
		}

		public String getFromEmail() {
			return fromEmail;
		}

		public void setFromEmail(String fromEmail) {
			this.fromEmail = fromEmail;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Map<String, Object> getAttributes() {
			return attributes;
		}

		public void setAttributes(Map<String, Object> attributes) {
			this.attributes = attributes;
		}
		
	}

}
