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

import com.nagarro.model.WalmartDealItem;
import com.nagarro.model.WalmartDealsResponse;
import com.nagarro.repository.WalmartDealRepository;
import com.nagarro.service.WalmartDealService;

@RestController
@RequestMapping("/backendserver3/walmart/deals")
public class WalmartDealsController {
	@Autowired
	private WalmartDealRepository dealRepository;
	@Autowired
	private WalmartDealService dealService;
	@GetMapping("/{categoryName}")
    public ResponseEntity<WalmartDealsResponse> getDealsByCategory(@PathVariable String categoryName) {
        try {
		List<WalmartDealItem> deals = dealRepository.findAllByCategoryName(categoryName);
        return ResponseEntity.ok(new WalmartDealsResponse(categoryName, deals));
        }catch(Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	@PostMapping("/add-deal")
	public ResponseEntity<String>addDeal(@RequestBody WalmartDealItem dealItem){
		try{
			dealService.addDeal(dealItem);
			return ResponseEntity.ok("Deal added Successfully");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add deal");
		}
	}
}
