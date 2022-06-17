package com.stock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stock.entity.JwtBlackListedTokenDocument;
import com.stock.repo.UserMongoRepo;
@Service
public class StockUserMongoServiceImpl implements UserBlackListedTokenService {
	
	@Autowired
	UserMongoRepo userMongoRepo;

	@Override
	public void saveBlackListedToke(String token) {
		JwtBlackListedTokenDocument jwtBlackListedTokenDocument=new JwtBlackListedTokenDocument();
		jwtBlackListedTokenDocument.setJwtToken(token);
		userMongoRepo.save(jwtBlackListedTokenDocument);
	}

	@Override
	public List<JwtBlackListedTokenDocument> getAllBlackListedTokens() {
		return userMongoRepo.findAll();
	}
	
	@Override
	public void deleteToken(JwtBlackListedTokenDocument jwtBlackListedTokenDocument) {
		userMongoRepo.delete(jwtBlackListedTokenDocument);
	}	
}
