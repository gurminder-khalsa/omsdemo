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

/**
 * @author gurminder.singh
 *
 */
@Repository
public interface ApplyLeaveRepository extends JpaRepository<ApplyLeave, Long> {
	
	@Query(value="from ApplyLeave aplyLeave where aplyLeave.user.userName=:userName order by aplyLeave.startDate")
	List<ApplyLeave> getLeaveDetailsForUser(@Param("userName")String userName);

}
