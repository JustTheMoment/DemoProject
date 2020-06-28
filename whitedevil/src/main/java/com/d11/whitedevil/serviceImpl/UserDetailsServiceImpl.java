package com.d11.whitedevil.serviceImpl;

import java.util.Collection;
import java.util.Optional;
import static java.util.Collections.singletonList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d11.whitedevil.entity.UserInfo;
import com.d11.whitedevil.repository.UserInfoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserInfoRepository userInfoRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userOptional = userInfoRepository.findByUserName(username);

		UserInfo user = userOptional
				.orElseThrow(() -> new UsernameNotFoundException("No User Found with username : " + username));

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				user.isEnabled(), true, true, true, getAuthorities("USER"));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(String role) {
		return singletonList(new SimpleGrantedAuthority(role));
	}

}
