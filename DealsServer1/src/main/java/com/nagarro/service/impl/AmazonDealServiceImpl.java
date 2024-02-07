package com.nagarro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.model.AmazonDealItem;
import com.nagarro.repository.AmazonDealRepository;
import com.nagarro.service.AmazonDealService;

@Service
public class AmazonDealServiceImpl implements AmazonDealService {

	@Autowired
	private AmazonDealRepository dealRepository;

	@Override
	public List<AmazonDealItem> getDealsByCategory(String categoryName) {
		try {
			return dealRepository.findAllByCategoryName(categoryName);
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve deals by category", e);
		}
	}

	@Override
	public void addDeal(AmazonDealItem dealItem) {
		try {
			dealRepository.save(dealItem);

		} catch (Exception e) {
			throw new RuntimeException("Failed to add deal", e);
		}
	}
}
