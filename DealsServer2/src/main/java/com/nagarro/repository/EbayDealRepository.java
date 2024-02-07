package com.nagarro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.model.EbayDealItem;

public interface EbayDealRepository extends JpaRepository<EbayDealItem, String>{
	List<EbayDealItem>findAllByCategoryName(String categoryName);

}
