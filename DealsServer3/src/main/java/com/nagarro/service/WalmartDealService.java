package com.nagarro.service;

import java.util.List;

import com.nagarro.model.WalmartDealItem;

public interface WalmartDealService {
	List<WalmartDealItem> getDealsByCategory(String categoryName);
	void addDeal(WalmartDealItem dealItem);
}
