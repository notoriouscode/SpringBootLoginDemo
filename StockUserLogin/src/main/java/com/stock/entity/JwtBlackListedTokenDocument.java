package com.stock.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tokens")
public class JwtBlackListedTokenDocument {
	
	private String jwtToken;
	public JwtBlackListedTokenDocument() {}
	
	public JwtBlackListedTokenDocument(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
	
	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	@Override
	public String toString() {
		return "JwtBlackListedTokenEntity [jwtToken=" + jwtToken + "]";
	}
}
