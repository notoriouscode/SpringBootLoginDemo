package com.stock.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.dto.StockUserDtoResponse;
import com.stock.entity.StockUserEntity;
import com.stock.repo.UserRepo;

@Service
public class StockUserServiceImpl implements StockUserService {

	@Autowired
	private UserRepo userRepository;
	
	@Override
	public StockUserDtoResponse findByUsername(String username) {
		List<StockUserEntity> userEntityList = userRepository.findByUserName(username);
		if(userEntityList==null || userEntityList.size()==0) {
			return null;
		}
		//UserEntity userEntity = opUserEntity.isPresent() ? opUserEntity.get() : null;
		StockUserEntity userEntity = userEntityList.get(0);
		StockUserDtoResponse userDto = new StockUserDtoResponse();
		userDto.setUsername(userEntity.getUserName());
		return userDto;
	}
}