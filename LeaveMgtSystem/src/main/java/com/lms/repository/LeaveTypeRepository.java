/**
 * 
 */
package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.db.model.ApplyLeave;
import com.lms.db.model.LeaveType;

/**
 * @author gurminder.singh
 *
 */
@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {
	
	@Query(value="from LeaveType where leaveType=:leaveType")
	public LeaveType findByLeaveType(@Param("leaveType") String leaveType);
	
}
