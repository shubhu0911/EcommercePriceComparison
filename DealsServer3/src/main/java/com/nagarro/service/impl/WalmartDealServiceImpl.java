package com.nagarro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.model.WalmartDealItem;
import com.nagarro.repository.WalmartDealRepository;
import com.nagarro.service.WalmartDealService;

@Service
public class WalmartDealServiceImpl implements WalmartDealService {
	@Autowired
	private WalmartDealRepository dealRepository;
	@Override
	public List<WalmartDealItem> getDealsByCategory(String categoryName) {
		try {
			return dealRepository.findAllByCategoryName(categoryName);
		} catch (Exception e) {
			throw new RuntimeException("Failed to retrieve deals by category", e);
		}
	}
	@Override
	public void addDeal(WalmartDealItem dealItem) {
		try {
			dealRepository.save(dealItem);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add deal", e);
		}
	}
}
