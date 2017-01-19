/**
 * 
 */
package com.lms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lms.exception.LMSException;
import com.lms.rest.model.ApplyLeave;
import com.lms.rest.model.LeaveDetailsForm;
import com.lms.rest.model.api.IApplyLeave;
import com.lms.rest.model.api.IUser;
import com.lms.rest.model.api.IUserLeave;
import com.lms.service.api.ILeaveManagementService;
import com.lms.service.api.IUserService;
import com.lms.utils.ExceptionKey;
import com.lms.utils.LeaveStatus;

/**
 * @author gurminder.singh
 *
 */
@RestController
@RequestMapping(value="/lms")
public class LeaveMgtController {
	
	@Autowired
	private ILeaveManagementService leaveManagementService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private MessageSource messageSource;
	
	private ModelAndView getApplyLeaveModelAndView(ApplyLeave applyLeave) {
		ModelAndView modelAndView = new ModelAndView();				
		modelAndView.addObject("applyLeave",applyLeave);
		modelAndView.addObject("leaveTypes", leaveManagementService.getLeaveTypes());
		modelAndView.setViewName("applyLeaveForm");
		return modelAndView;
	}
	
	@RequestMapping(value = "/applyLeaveForm",method = RequestMethod.GET)
	public ModelAndView applyLeaveForm() {
		return getApplyLeaveModelAndView(new ApplyLeave());
	}
	
	@RequestMapping(value = "/submitLeaveForm",method = RequestMethod.POST)
	public ModelAndView submitLeaveForm(@RequestParam String actionButton, 
			@Valid @ModelAttribute("applyLeave") ApplyLeave applyLeave, BindingResult bindingResult ) {
		if(actionButton.equalsIgnoreCase("Cancel")){
			return calcelLeave(applyLeave);
		}
		if(!validateApplyLeave(applyLeave,bindingResult)){
			return getApplyLeaveModelAndView(applyLeave);
		}
		ModelAndView model = new ModelAndView();
		try {
			leaveManagementService.submitLeaveDetails(applyLeave);
		} catch (LMSException e) {
			if(e.getExceptionKey().equals(ExceptionKey.Leaves_Exhausted)){
				bindingResult.rejectValue("appliedLeaveType", "error.leaves.exhausted", null);
				return getApplyLeaveModelAndView(applyLeave);
			}
			return showErrorPage(model, e);
		}
		model.addObject("leaveData", applyLeave);
		return getAppliedLeaves();

	}

	
	
	private boolean validateApplyLeave(ApplyLeave applyLeave, BindingResult bindingResult) {
		/**
		 * Facing some problems with Spring validator framework. hence doing manual validation
		 */		
		if(applyLeave.getStartDate()==null){
			bindingResult.rejectValue("startDate", "error.startDateNull", "Start Date null");
		}
		if(applyLeave.getEndDate()==null){
			bindingResult.rejectValue("endDate", "error.endDateNull", "End Date null");
		}
		if(StringUtils.isBlank(applyLeave.getLeaveReason())){
			bindingResult.rejectValue("leaveReason", "error.leaveReasonNull", "Leave reason null");
		}
		if (bindingResult.hasErrors()) {
			return false;
		}else{
			return true;
		}
			
	}

	public ModelAndView calcelLeave(@ModelAttribute("applyLeave") ApplyLeave applyLeave ) {
		System.out.println("Apply leave called: "+applyLeave.getLeaveReason());
		try {
			leaveManagementService.cancelLeave(applyLeave);
		} catch (LMSException e) {
			e.printStackTrace();
		}
		return getAppliedLeaves();

	}

	private ModelAndView showErrorPage(ModelAndView model, LMSException e) {
		model.addObject("error", e.getMessage());
		model.setViewName("error");
		return model;
	}
	
	@RequestMapping(value = "/appliedLeaves",method = RequestMethod.GET)
	public ModelAndView getAppliedLeaves() {
		ModelAndView model = new ModelAndView();
		List<IApplyLeave> leaveDetails = null;
		try {
			leaveDetails = leaveManagementService.getLeaveDetailsForLoggedInUser();
		} catch (LMSException e) {
			return showErrorPage(model, e);
		}
		model.addObject("leaveDetails", leaveDetails);
		model.addObject("leaveTypes", leaveManagementService.getLeaveTypes());
		model.setViewName("leaveDetails");
		return model;

	}
	
	@RequestMapping(value = "/manager/leaveDetails",method = RequestMethod.GET)
	public ModelAndView getReportingLeaveDetails() {
		ModelAndView model = new ModelAndView();
		LeaveDetailsForm leaveDetailsForm = new LeaveDetailsForm();
		List<IApplyLeave> leaveDetails = null;
		List<LeaveStatus> leaveStatus = Arrays.asList(LeaveStatus.values());
		model.addObject("leaveStatus", leaveStatus);
		try {
			leaveDetails = leaveManagementService.getLeaveDetailsForReportingUsers();
		} catch (LMSException e) {
			return showErrorPage(model, e);
		}
		
		
		List<ApplyLeave> applyLeaves = new ArrayList<ApplyLeave>();
		for (IApplyLeave applyLeave : leaveDetails) {
			applyLeaves.add((ApplyLeave) applyLeave);
		}
		leaveDetailsForm.setApplyLeaves(applyLeaves);
		model.addObject("leaveDetailsForm", leaveDetailsForm);
		model.addObject("leaveTypes", leaveManagementService.getLeaveTypes());
		model.setViewName("leaveDetailsForReporting");
		return model;

	}
	
	@RequestMapping(value = "/editLeaveForm",method = RequestMethod.GET)
	public ModelAndView editLeaveForm(@RequestParam String apppliedLeaveId, Model m) {
		IApplyLeave appliedLeaveDetails = leaveManagementService.getLeaveDetailsByLeaveId(Long.valueOf(apppliedLeaveId));
		ModelAndView model = new ModelAndView();
		model.addObject("leaveTypes", leaveManagementService.getLeaveTypes());
		model.addObject("applyLeave",appliedLeaveDetails);
		model.setViewName("applyLeaveForm");
		return model;

	}
	
	@RequestMapping(value = "/manager/sendLeaveStatus",method = RequestMethod.POST)
	public ModelAndView submitLeaveStatus(@ModelAttribute("leaveDetailsForm") LeaveDetailsForm leaveDetailsForm) {
		List<ApplyLeave> applyLeaves = leaveDetailsForm.getApplyLeaves();
		leaveManagementService.updateLeaveStatus(applyLeaves);
		return getReportingLeaveDetails();
	}
		
	@RequestMapping(value = "/userProfile",method = RequestMethod.GET)
	public ModelAndView getUserProfile() {
		ModelAndView model = new ModelAndView();
		IUser user = null;
		try {
			user = userService.getLoggedInUser();
		} catch (LMSException e) {
			return showErrorPage(model, e);
		}
		List<IUserLeave> userLeaves = leaveManagementService.getUserLeaves(user.getUserName());
		model.addObject("userDetails", user);
		model.addObject("userLeaves", userLeaves);		
		model.setViewName("userProfile");
		return model;

	}

}
