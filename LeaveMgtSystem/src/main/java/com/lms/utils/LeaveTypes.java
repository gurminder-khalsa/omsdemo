/**
 * 
 */
package com.lms.utils;

/**
 * @author admin
 *
 */
public enum LeaveTypes {
	SICK_LEAVE("SL"),

    PRIVILEGE_LEAVE("PL"),

    CASUAL_LEAVE("CL"),
	
	COMPENSATORY_OFF("CO"),
	
	LOSS_OFF_PAY("LOP");

    private String leaveType;

    private LeaveTypes(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveType() {
        return leaveType;
    }

}
