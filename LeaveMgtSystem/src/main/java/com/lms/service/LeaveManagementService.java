/**
 * 
 */
package com.lms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lms.converter.EntityConverter;
import com.lms.db.model.User;
import com.lms.repository.ApplyLeaveRepository;
import com.lms.repository.LeaveTypeRepository;
import com.lms.repository.UserRepository;
import com.lms.rest.model.LeaveType;
import com.lms.rest.model.api.IApplyLeave;
import com.lms.rest.model.api.ILeaveType;
import com.lms.rest.model.api.IUser;
import com.lms.service.api.ILeaveManagementService;
import com.lms.utils.LeaveStatus;

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
	private ModelMapper modelMapper;
	
	@Autowired
	private EntityConverter entityConverter;

	/* (non-Javadoc)
	 * @see com.lms.service.api.ILeaveManagementService#getLeaveTypes()
	 */
	@Override
	public List<ILeaveType> getLeaveTypes() {
		List<com.lms.db.model.LeaveType> leaveTypes = leaveTypeRepository.findAll();
		return leaveTypes.stream().map(leaveTypeEntity -> convertToDto(leaveTypeEntity)).collect(Collectors.toList());
	}

	private ILeaveType convertToDto(com.lms.db.model.LeaveType leaveTypeEntity) {
		ILeaveType leaveType = new LeaveType();
		entityConverter.convertToDto(leaveType, leaveTypeEntity);
		return leaveType;
	}

	/* (non-Javadoc)
	 * @see com.lms.service.api.ILeaveManagementService#submitLeaveDetails(com.lms.rest.model.api.IApplyLeave)
	 */
	@Override	
	@Transactional
	public void submitLeaveDetails(IApplyLeave applyLeave) {
		com.lms.db.model.ApplyLeave applyLeaveEntity = new com.lms.db.model.ApplyLeave(); 
		applyLeaveEntity.setLeaveType(leaveTypeRepository.findByLeaveType(applyLeave.getAppliedLeaveType()));
		applyLeaveEntity.setStartDate(applyLeave.getStartDate());
		applyLeaveEntity.setEndDate(applyLeave.getEndDate());
		applyLeaveEntity.setLeaveReason(applyLeave.getLeaveReason());
		applyLeaveEntity.setStatus(LeaveStatus.PENDING.getStatus());
		org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUserName(loggedInUser.getUsername());
		applyLeaveEntity.setUser(user);
		applyLeaveRepository.save(applyLeaveEntity);
		getLeaveDetailsForLoggedInUser();
	}

	/* (non-Javadoc)
	 * @see com.lms.service.api.ILeaveManagementService#getLeaveDetailsForUser(java.lang.Long)
	 */
	@Override
	public List<IApplyLeave> getLeaveDetailsForUser(String userName) {
		List<com.lms.db.model.ApplyLeave> leaveDetails = applyLeaveRepository.getLeaveDetailsForUser(userName);
		List<IApplyLeave> leaveDetailsBO = new ArrayList<IApplyLeave>();
		for (com.lms.db.model.ApplyLeave applyLeaveEntity : leaveDetails) {
			IApplyLeave applyLeave = new com.lms.rest.model.ApplyLeave();
			entityConverter.convertToDto(applyLeave,applyLeaveEntity);
			applyLeave.setAppliedLeaveType(applyLeaveEntity.getLeaveType().getLeaveType());
			IUser appliedBy = new com.lms.rest.model.User();
			entityConverter.convertToDto(appliedBy,applyLeaveEntity.getUser());
			applyLeave.setAppliedBy(appliedBy);
			leaveDetailsBO.add(applyLeave);
		}
		return leaveDetailsBO;
	}

	@Override
	public List<IApplyLeave> getLeaveDetailsForLoggedInUser() {
		org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return getLeaveDetailsForUser(loggedInUser.getUsername());
	}

	@Override
	public List<IApplyLeave> getLeaveDetailsForReportingUsers() {
		org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<User> reportingUsers = userRepository.findUsersByManager(loggedInUser.getUsername());
		List<IApplyLeave> leaveDetails = new ArrayList<IApplyLeave>();
		for (User user : reportingUsers) {
			leaveDetails.addAll(getLeaveDetailsForUser(user.getUserName()));
		}
		return leaveDetails;
	}

}
