package com.stock.service;

import com.stock.dto.StockUserDtoResponse;

public interface StockUserService {
	StockUserDtoResponse findByUsername(String username);
}
