package com.nagarro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.model.AmazonDealItem;
@Repository
public interface AmazonDealRepository extends JpaRepository<AmazonDealItem, String>{
	List<AmazonDealItem>findAllByCategoryName(String categoryName);

}
