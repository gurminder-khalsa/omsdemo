/**
 * 
 */
package com.lms.utils;

/**
 * @author admin
 *
 */
public enum LeaveStatus {
	PENDING("Pending"),

    APPROVED("Approved"),

    REJECTED("Rejected"),
	
	CANCELLED("Cancelled");

    private String status;

    private LeaveStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
