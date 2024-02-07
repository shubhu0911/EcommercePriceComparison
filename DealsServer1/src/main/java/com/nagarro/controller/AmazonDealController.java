package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.model.AmazonDealItem;
import com.nagarro.model.AmazonDealsResponse;
import com.nagarro.service.AmazonDealService;

@RestController
@RequestMapping("/backendserver1/amazon/deals")
public class AmazonDealController {
	@Autowired
	private AmazonDealService dealService;

	@GetMapping("/{categoryName}")
	public ResponseEntity<AmazonDealsResponse> getDealsByCategory(@PathVariable String categoryName) {
		try {
			List<AmazonDealItem> deals = dealService.getDealsByCategory(categoryName);
			return ResponseEntity.ok(new AmazonDealsResponse(categoryName, deals));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/add-deal")
	public ResponseEntity<String> addDeal(@RequestBody AmazonDealItem dealItem) {
		try {
			dealService.addDeal(dealItem);
			return ResponseEntity.ok("Deal added Successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add deal");
		}
	}

}
