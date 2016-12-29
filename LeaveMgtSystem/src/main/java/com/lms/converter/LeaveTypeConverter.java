/**
 * 
 */
package com.lms.converter;

import javax.persistence.AttributeConverter;

import com.lms.rest.model.LeaveType;

/**
 * @author gurminder.singh
 *
 */
public class LeaveTypeConverter implements AttributeConverter<LeaveType, com.lms.db.model.LeaveType>{

	@Override
	public com.lms.db.model.LeaveType convertToDatabaseColumn(LeaveType attribute) {
		com.lms.db.model.LeaveType leaveTypeEntity = new com.lms.db.model.LeaveType();
		leaveTypeEntity.setId(attribute.getId());
		return null;
	}

	@Override
	public LeaveType convertToEntityAttribute(com.lms.db.model.LeaveType dbData) {
		// TODO Auto-generated method stub
		return null;
	}

}
