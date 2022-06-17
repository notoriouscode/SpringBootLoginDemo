package com.stock.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.stock.entity.StockUserEntity;

public interface UserRepo extends JpaRepository<StockUserEntity, Long> {
	List<StockUserEntity> findByUserName(String username);
}
