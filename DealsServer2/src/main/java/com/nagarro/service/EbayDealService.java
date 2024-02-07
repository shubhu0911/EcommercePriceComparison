package com.nagarro.service;

import java.util.List;

import com.nagarro.model.EbayDealItem;

public interface EbayDealService {
	List<EbayDealItem> getDealsByCategory(String categoryName);
	void addDeal(EbayDealItem dealItem);


}
