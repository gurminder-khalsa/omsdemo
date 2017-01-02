/**
 * 
 */
package com.lms.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lms.exception.LMSException;
import com.lms.rest.model.ApplyLeave;
import com.lms.rest.model.api.IApplyLeave;
import com.lms.service.api.ILeaveManagementService;
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
	
	@RequestMapping(value = "/applyLeaveForm",method = RequestMethod.GET)
	public ModelAndView applyLeaveForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("leaveTypes", leaveManagementService.getLeaveTypes());
		model.addObject("applyLeave",new ApplyLeave());
		model.setViewName("applyLeaveForm");
		return model;

	}
	
	@RequestMapping(value = "/submitLeaveForm",method = RequestMethod.POST)
	public ModelAndView submitLeaveForm(@ModelAttribute("applyLeave") ApplyLeave applyLeave ) {
		System.out.println("Apply leave called: "+applyLeave.getLeaveReason());
		ModelAndView model = new ModelAndView();
		try {
			leaveManagementService.submitLeaveDetails(applyLeave);
		} catch (LMSException e) {
			return showErrorPage(model, e);
		}
		model.addObject("leaveData", applyLeave);
		return getLeaveDetails();

	}

	private ModelAndView showErrorPage(ModelAndView model, LMSException e) {
		model.addObject("error", e.getMessage());
		model.setViewName("error");
		return model;
	}
	
	@RequestMapping(value = "/leaveDetails",method = RequestMethod.GET)
	public ModelAndView getLeaveDetails() {
		ModelAndView model = new ModelAndView();
		List<IApplyLeave> leaveDetails = null;
		try {
			leaveDetails = leaveManagementService.getLeaveDetailsForLoggedInUser();
		} catch (LMSException e) {
			return showErrorPage(model, e);
		}
		model.addObject("leaveDetails", leaveDetails);
		model.setViewName("leaveDetails");
		return model;

	}
	
	@RequestMapping(value = "/manager/leaveDetails",method = RequestMethod.GET)
	public ModelAndView getReportingLeaveDetails() {
		ModelAndView model = new ModelAndView();
		List<IApplyLeave> leaveDetails = null;
		List<LeaveStatus> leaveStatus = Arrays.asList(LeaveStatus.values());
		model.addObject("leaveStatus", leaveStatus);
		try {
			leaveDetails = leaveManagementService.getLeaveDetailsForReportingUsers();
		} catch (LMSException e) {
			return showErrorPage(model, e);
		}
		model.addObject("leaveDetails", leaveDetails);
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
	
	
	
	

}
