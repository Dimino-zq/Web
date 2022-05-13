package edu.hfu.broadcast.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface  CustomUserDetailsService {
	UserDetails loadUserByUsernameAndDomain(String username, String errorMsg) throws UsernameNotFoundException, Exception;
}
