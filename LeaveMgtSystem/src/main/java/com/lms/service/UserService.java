package com.lms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.converter.EntityConverter;
import com.lms.db.model.User;
import com.lms.db.model.UserRole;
import com.lms.repository.UserRepository;
import com.lms.rest.model.api.IUser;
import com.lms.service.api.IUserService;


@Service
public class UserService implements IUserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityConverter entityConverter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
	        throw new UsernameNotFoundException("...");
	    } 
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
	    return buildUserForAuthentication(user, authorities);
	}
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				true, true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
	    Set<GrantedAuthority> setAuths = new HashSet<>();
	    // Build user's authorities
	    for (UserRole userRole : userRoles) {
	        setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
	    }
	    return new ArrayList<>(setAuths);
	}

	@Override
	public List<User> getAllUser() {		
		return userRepository.findAll();
	}
	
	@Override
	public IUser getLoggedInUser() throws Exception{
		if(!((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof org.springframework.security.core.userdetails.User)){
			throw new Exception("User not logged in");
		}
		org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userEntity = userRepository.findByUserName(loggedInUser.getUsername());
		IUser user = new com.lms.rest.model.User();
		entityConverter.convert(user, userEntity);		
		return user;
	}

}
