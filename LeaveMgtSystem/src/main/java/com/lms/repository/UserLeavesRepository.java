/**
 * 
 */
package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.db.model.UserLeaves;

/**
 * @author gurminder.singh
 *
 */
@Repository
public interface UserLeavesRepository extends JpaRepository<UserLeaves, Long> {
	
	@Query(value="from UserLeaves where user.userName=:userName")
	public List<UserLeaves> findLeavesForUser(@Param("userName") String userName);
	
	@Query(value="from UserLeaves where user.userName=:userName and leaveType.leaveType=:leaveType")
	public UserLeaves findLeavesForUserByLeaveType(@Param("userName") String userName, @Param("leaveType") String leaveType);
	
}
