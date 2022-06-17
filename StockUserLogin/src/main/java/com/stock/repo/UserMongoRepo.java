package com.stock.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stock.entity.JwtBlackListedTokenDocument;

public interface UserMongoRepo extends MongoRepository<JwtBlackListedTokenDocument, Integer> {

}
 