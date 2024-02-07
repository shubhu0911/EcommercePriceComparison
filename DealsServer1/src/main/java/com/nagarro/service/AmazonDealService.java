package com.nagarro.service;

import java.util.List;

import com.nagarro.model.AmazonDealItem;

public interface AmazonDealService {
	List<AmazonDealItem> getDealsByCategory(String categoryName);
	void addDeal(AmazonDealItem dealItem);


}
