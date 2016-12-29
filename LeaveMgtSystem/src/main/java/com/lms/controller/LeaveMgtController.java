/**
 * 
 */
package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lms.rest.model.ApplyLeave;
import com.lms.rest.model.api.IApplyLeave;
import com.lms.service.api.ILeaveManagementService;

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
		leaveManagementService.submitLeaveDetails(applyLeave);
		model.addObject("leaveData", applyLeave);
		//model.setViewName("applyLeave");
		return getLeaveDetails();

	}
	
	@RequestMapping(value = "/leaveDetails",method = RequestMethod.GET)
	public ModelAndView getLeaveDetails() {
		ModelAndView model = new ModelAndView();
		List<IApplyLeave> leaveDetails = leaveManagementService.getLeaveDetailsForLoggedInUser();
		model.addObject("leaveDetails", leaveDetails);
		model.setViewName("leaveDetails");
		return model;

	}
	
	
	
	

}
