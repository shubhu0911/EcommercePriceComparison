package com.nagarro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.model.EbayDealItem;
import com.nagarro.repository.EbayDealRepository;
import com.nagarro.service.EbayDealService;

@Service
public class EbayDealServiceImpl implements EbayDealService {

	@Autowired
	private EbayDealRepository dealRepository;

	@Override
	public List<EbayDealItem> getDealsByCategory(String categoryName) {
		try {
			return dealRepository.findAllByCategoryName(categoryName);

		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve deals by category", e);
		}
	}

	@Override
	public void addDeal(EbayDealItem dealItem) {
		try {
			dealRepository.save(dealItem);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add deal", e);
		}
	}


}