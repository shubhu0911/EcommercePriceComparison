package com.nagarro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.model.WalmartDealItem;

public interface WalmartDealRepository extends JpaRepository<WalmartDealItem,String> {
	List<WalmartDealItem>findAllByCategoryName(String categoryName);
}
