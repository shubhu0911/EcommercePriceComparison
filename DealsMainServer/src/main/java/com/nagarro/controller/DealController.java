package com.nagarro.controller;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nagarro.model.AggregatedDealResponse;
import com.nagarro.model.DealItem;
import com.nagarro.service.DealService;
@RestController
@RequestMapping("/deals")
public class DealController {
	@Autowired
	private DealService dealService;
	@GetMapping("/{categoryName}")
	public ResponseEntity<AggregatedDealResponse> getDealsByCategory(@PathVariable String categoryName) {
		try {
			CompletableFuture<List<DealItem>> aggregatedDealsFuture = dealService.fetchAndAggregateDeals(categoryName);
			List<DealItem> aggregatedDeals = aggregatedDealsFuture.get();
			List<DealItem> bestDeals = dealService.findBestDeals(aggregatedDeals);
			AggregatedDealResponse response = new AggregatedDealResponse(categoryName, bestDeals);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
