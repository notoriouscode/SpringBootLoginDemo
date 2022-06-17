package com.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stock.entity.StockUserEntity;
import com.stock.repo.UserRepo;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<StockUserEntity> studentEntityList = userRepo.findByUserName(username);
		if(studentEntityList==null || studentEntityList.size()==0) { //User not found
			throw new UsernameNotFoundException(username);
		}
		StockUserEntity studentEntity = studentEntityList.get(0);
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(studentEntity.getRoles()));
		User user = new User(studentEntity.getUserName(), studentEntity.getPassword(),authorities);
		return user;
	}
}
