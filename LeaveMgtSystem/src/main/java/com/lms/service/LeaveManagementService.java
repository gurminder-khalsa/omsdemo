/**
 * 
 */
package com.lms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.lms.converter.EntityConverter;
import com.lms.db.model.User;
import com.lms.db.model.UserLeaves;
import com.lms.exception.LMSException;
import com.lms.repository.ApplyLeaveRepository;
import com.lms.repository.LeaveTypeRepository;
import com.lms.repository.UserLeavesRepository;
import com.lms.repository.UserRepository;
import com.lms.rest.model.ApplyLeave;
import com.lms.rest.model.LeaveType;
import com.lms.rest.model.UserLeave;
import com.lms.rest.model.api.IApplyLeave;
import com.lms.rest.model.api.ILeaveType;
import com.lms.rest.model.api.IUser;
import com.lms.rest.model.api.IUserLeave;
import com.lms.service.api.ILeaveManagementService;
import com.lms.utils.ExceptionKey;
import com.lms.utils.LeaveStatus;
import com.lms.utils.LeaveTypes;

/**
 * @author gurminder.singh
 *
 */
@Service
public class LeaveManagementService implements ILeaveManagementService {
	@Autowired
	private ApplyLeaveRepository applyLeaveRepository;
	
	@Autowired
	private LeaveTypeRepository leaveTypeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserLeavesRepository userLeavesRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EntityConverter entityConverter;
	
	 @Autowired
	 JavaMailSender mailSender;
	 
	 @Autowired
	 private VelocityEngine velocityEngine;

	/* (non-Javadoc)
	 * @see com.lms.service.api.ILeaveManagementService#getLeaveTypes()
	 */
	@Override
	public List<ILeaveType> getLeaveTypes() {
		List<com.lms.db.model.LeaveType> leaveTypes = leaveTypeRepository.findAll();
		return leaveTypes.stream().map(leaveTypeEntity -> convertLeaveTypeDto(leaveTypeEntity)).collect(Collectors.toList());
	}

	private ILeaveType convertLeaveTypeDto(com.lms.db.model.LeaveType leaveTypeEntity) {
		ILeaveType leaveType = new LeaveType();
		entityConverter.convert(leaveType, leaveTypeEntity);
		return leaveType;
	}

	/* (non-Javadoc)
	 * @see com.lms.service.api.ILeaveManagementService#submitLeaveDetails(com.lms.rest.model.api.IApplyLeave)
	 */
	@Override	
	@Transactional
	public void submitLeaveDetails(IApplyLeave applyLeave)  throws LMSException{
		Long appliedLeaveId = applyLeave.getId();
		UserLeaves userLeave = getUserLeaves(applyLeave);
		if(appliedLeaveId == null && (userLeave.getNumberOfLeaves() == 0)){
			throw new LMSException(ExceptionKey.Leaves_Exhausted,"No. of Leaves Exhausted");
		}
		com.lms.db.model.ApplyLeave applyLeaveEntity = new com.lms.db.model.ApplyLeave();
		entityConverter.convert(applyLeaveEntity, applyLeave);
		if(userLeave.getLeaveType().getLeaveType().equals(LeaveTypes.LOSS_OFF_PAY.getLeaveType())){
			applyLeaveEntity.setLeaveType(userLeave.getLeaveType());
		}else{
			applyLeaveEntity.setLeaveType(leaveTypeRepository.findByLeaveType(applyLeave.getAppliedLeaveType()));
		}
		applyLeaveEntity.setStatus(LeaveStatus.PENDING.getStatus());
		org.springframework.security.core.userdetails.User loggedInUser = getLoggedInUser();
		User user = userRepository.findByUserName(loggedInUser.getUsername());
		applyLeaveEntity.setUser(user);		
		applyLeaveRepository.save(applyLeaveEntity);	
		if(appliedLeaveId == null && !userLeave.getLeaveType().getLeaveType().equals(LeaveTypes.LOSS_OFF_PAY.getLeaveType())){
			userLeave.setNumberOfLeaves(Long.valueOf(userLeave.getNumberOfLeaves().longValue() - 1));
			userLeavesRepository.save(userLeave);
		}
		
		sendLeaveAppliedMailForApproval(user, applyLeaveEntity);
		sendLeaveAppliedMailToApplier(user, applyLeaveEntity);
		getLeaveDetailsForLoggedInUser();
	}

	private void sendLeaveAppliedMailForApproval(User user, com.lms.db.model.ApplyLeave applyLeaveEntity) {
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	MimeMessageHelper helper;
                helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(new InternetAddress(user.getUserContact().getEmailAddress()));
                helper.setTo(new InternetAddress(user.getManager().getUserContact().getEmailAddress()));
                
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("user", user);
                model.put("leaveDetails", applyLeaveEntity);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/applyMailTemplateForApproval.vm", model);
                helper.setText(text,true);                               
                helper.setSubject("Leave Applied");
            }
        };
		
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            ex.printStackTrace();
        }
		
	}
	
	private void sendLeaveAppliedMailToApplier(User user, com.lms.db.model.ApplyLeave applyLeaveEntity) {
		MimeMessagePreparator preparator = new MimeMessagePreparator(){
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	MimeMessageHelper helper;
                helper = new MimeMessageHelper(mimeMessage, true);
                helper.setFrom(new InternetAddress("donotreply@lms.com"));
                helper.setTo(new InternetAddress(user.getUserContact().getEmailAddress()));
                
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("user", user);
                model.put("leaveDetails", applyLeaveEntity);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/applyMailTemplateToApplier.vm", model);
                helper.setText(text,true);                               
                helper.setSubject("Leave Applied");
            }
        };
		
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            ex.printStackTrace();
        }
		
	}


	private UserLeaves getUserLeaves(IApplyLeave applyLeave) throws LMSException{
		org.springframework.security.core.userdetails.User loggedInUser = getLoggedInUser();
		UserLeaves userLeave = userLeavesRepository.findLeavesForUserByLeaveType(loggedInUser.getUsername(),applyLeave.getAppliedLeaveType());
		if(userLeave == null){
			userLeave = userLeavesRepository.findLeavesForUserByLeaveType(loggedInUser.getUsername(),LeaveTypes.LOSS_OFF_PAY.getLeaveType());
		}
		if(userLeave == null){
			userLeave = new UserLeaves();
			userLeave.setLeaveType(leaveTypeRepository.findByLeaveType(LeaveTypes.LOSS_OFF_PAY.getLeaveType()));
			userLeave.setUser(userRepository.findByUserName(loggedInUser.getUsername()));
			userLeave.setNumberOfLeaves(-1l);
			userLeavesRepository.save(userLeave);
		}
		return userLeave;
	}

	private org.springframework.security.core.userdetails.User getLoggedInUser()  throws LMSException{
		if(SecurityContextHolder.getContext().getAuthentication() == null){
			throw new LMSException("User not logged in");	
		}else if(!((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof org.springframework.security.core.userdetails.User)){
			throw new LMSException("User not logged in");			
		}
		org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return loggedInUser;
	}

	/* (non-Javadoc)
	 * @see com.lms.service.api.ILeaveManagementService#getLeaveDetailsForUser(java.lang.Long)
	 */
	@Override
	public List<IApplyLeave> getLeaveDetailsForUser(String userName)  throws LMSException{
		List<com.lms.db.model.ApplyLeave> leaveDetails = applyLeaveRepository.getLeaveDetailsForUser(userName);
		List<IApplyLeave> leaveDetailsBO = new ArrayList<IApplyLeave>();
		for (com.lms.db.model.ApplyLeave applyLeaveEntity : leaveDetails) {
			IApplyLeave applyLeave = getAppliedLeaveDetailsBO(applyLeaveEntity);
			leaveDetailsBO.add(applyLeave);
		}
		return leaveDetailsBO;
	}

	private IApplyLeave getAppliedLeaveDetailsBO(com.lms.db.model.ApplyLeave applyLeaveEntity) {
		IApplyLeave applyLeave = new com.lms.rest.model.ApplyLeave();
		entityConverter.convert(applyLeave,applyLeaveEntity);
		applyLeave.setAppliedLeaveType(applyLeaveEntity.getLeaveType().getLeaveType());
		IUser appliedBy = new com.lms.rest.model.User();
		entityConverter.convert(appliedBy,applyLeaveEntity.getUser());
		applyLeave.setAppliedBy(appliedBy);
		return applyLeave;
	}

	@Override
	public List<IApplyLeave> getLeaveDetailsForLoggedInUser()  throws LMSException{
		org.springframework.security.core.userdetails.User loggedInUser = getLoggedInUser();
		return getLeaveDetailsForUser(loggedInUser.getUsername());
	}

	@Override
	public List<IApplyLeave> getLeaveDetailsForReportingUsers()  throws LMSException{
		org.springframework.security.core.userdetails.User loggedInUser = getLoggedInUser();
		List<User> reportingUsers = userRepository.findUsersByManager(loggedInUser.getUsername());
		List<IApplyLeave> leaveDetails = new ArrayList<IApplyLeave>();
		for (User user : reportingUsers) {
			leaveDetails.addAll(getLeaveDetailsForUser(user.getUserName()));
		}
		return leaveDetails;
	}

	@Override
	public IApplyLeave getLeaveDetailsByLeaveId(Long appliedLeaveId) {
		com.lms.db.model.ApplyLeave appliedLeaveEntity = applyLeaveRepository.findOne(appliedLeaveId);
		return getAppliedLeaveDetailsBO(appliedLeaveEntity);
		
	}

	@Override
	@Transactional
	public void updateLeaveStatus(List<ApplyLeave> applyLeaves) {
		List<com.lms.db.model.ApplyLeave> applyLeaveEntities = new ArrayList<com.lms.db.model.ApplyLeave>();
		for (ApplyLeave applyLeave : applyLeaves) {
			com.lms.db.model.ApplyLeave appliedLeaveEntity = applyLeaveRepository.findOne(applyLeave.getId());
			appliedLeaveEntity.setStatus(applyLeave.getStatus());
			applyLeaveEntities.add(appliedLeaveEntity);
		}
		applyLeaveRepository.save(applyLeaveEntities);
		
	}

	@Override
	@Transactional
	public void cancelLeave(IApplyLeave applyLeave) throws LMSException {
		com.lms.db.model.ApplyLeave applyLeaveEntity = applyLeaveRepository.findOne(applyLeave.getId());
		applyLeaveEntity.setStatus(LeaveStatus.CANCELLED.getStatus());
		applyLeaveRepository.save(applyLeaveEntity);
		UserLeaves userLeave = getUserLeaves(applyLeave);
		userLeave.setNumberOfLeaves(Long.valueOf(userLeave.getNumberOfLeaves().longValue() + 1));
		userLeavesRepository.save(userLeave);
	}

	@Override
	public List<IUserLeave> getUserLeaves(String userName) {
		List<UserLeaves> userLeaveEntities = userLeavesRepository.findLeavesForUser(userName);
		List<IUserLeave> userLeaves = new ArrayList<IUserLeave>(); 
		for (UserLeaves userLeave : userLeaveEntities) {
			IUserLeave userLeaveModel = new UserLeave();
			userLeaveModel.setLeaveType(convertLeaveTypeDto(userLeave.getLeaveType()));
			userLeaveModel.setNumberOfLeaves(userLeave.getNumberOfLeaves());
			userLeaves.add(userLeaveModel);
		}
		return userLeaves;
	}
}
