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

import com.nagarro.model.EbayDealItem;
import com.nagarro.model.EbayDealsResponse;
import com.nagarro.service.EbayDealService;

@RestController
@RequestMapping("/backendserver2/ebay/deals")
public class EbayDealController {

	@Autowired
	private EbayDealService dealService;

	@GetMapping("/{categoryName}")
	public ResponseEntity<EbayDealsResponse> getDealsByCategory(@PathVariable String categoryName) {
		try {
			List<EbayDealItem> deals = dealService.getDealsByCategory(categoryName);
			return ResponseEntity.ok(new EbayDealsResponse(categoryName, deals));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/add-deal")
	public ResponseEntity<String> addDeal(@RequestBody EbayDealItem dealItem) {
		try{
			dealService.addDeal(dealItem);
			return ResponseEntity.ok("Deal added Successfully");

		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add deal");
		}
	}

}
