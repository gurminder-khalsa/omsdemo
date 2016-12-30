/**
 * 
 */
package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.db.model.User;

/**
 * @author gurminder.singh
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value="from User user where user.userName=:userName")
	public User findByUserName(@Param("userName") String username);
	
	@Query(value="from User user where user.manager.userName=:userName")
	public List<User> findUsersByManager(@Param("userName") String username);

}
