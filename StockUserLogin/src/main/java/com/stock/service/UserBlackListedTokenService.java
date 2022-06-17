package com.stock.service;

import java.util.List;

import com.stock.entity.JwtBlackListedTokenDocument;

public interface UserBlackListedTokenService {
	void saveBlackListedToke(String token);
	List<JwtBlackListedTokenDocument> getAllBlackListedTokens();
	void deleteToken(JwtBlackListedTokenDocument jwtBlackListedTokenDocument);
}