package com.nagarro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.model.DealItem;
@Service
public class DealService {
	private final AmazonService amazonService;
	private final EbayService ebayService;
	private final WalmartService walmartService;
	@Autowired
	public DealService(EbayService ebayService, WalmartService walmartService,AmazonService amazonService) {
		this.amazonService=amazonService;
		this.ebayService = ebayService;
		this.walmartService = walmartService;
	}
	public CompletableFuture<List<DealItem>> fetchAndAggregateDeals(String categoryName) {
		CompletableFuture<List<DealItem>>amazonFuture=amazonService.fetchDeals(categoryName);
		CompletableFuture<List<DealItem>> ebayFuture = ebayService.fetchDeals(categoryName);
		CompletableFuture<List<DealItem>> walmartFuture = walmartService.fetchDeals(categoryName);
		 return CompletableFuture.allOf(ebayFuture, walmartFuture,amazonFuture)
	                .thenApply(ignored ->
	                        combineDeals(ebayFuture.join(), walmartFuture.join(),amazonFuture.join()));
	}
	private List<DealItem> combineDeals(List<DealItem> ebayDeals, List<DealItem> walmartDeals,List<DealItem>amazonDeals) {
		try {
            List<DealItem> combinedDeals = new ArrayList<>();
            combinedDeals.addAll(ebayDeals);
            combinedDeals.addAll(walmartDeals);
            combinedDeals.addAll(amazonDeals);
            return combinedDeals;
        } catch (Exception e) {
            throw new RuntimeException("Failed to combine deals", e);
        }
	}
	public List<DealItem> findBestDeals(List<DealItem> allDeals) {
		try {
            List<DealItem> bestDeals = allDeals.stream()
                    .filter(this::isDealActiveAndHasStock)
                    .sorted(Comparator
                            .<DealItem, Double>comparing(dealItem -> dealItem.getMarketingPrice().getDiscountPercentage(),
                                    Comparator.reverseOrder())
                            .thenComparing(dealItem -> dealItem.getPrice().getValue())
                            .thenComparing(dealItem -> dealItem.getStock(), Comparator.reverseOrder()))
                    .collect(Collectors.toList());
            return bestDeals;
        } catch (Exception e) {
            throw new RuntimeException("Failed to find best deals", e);
        }
	}
	 private boolean isDealActiveAndHasStock(DealItem dealItem) {
	        try {
	            LocalDateTime currentDate = LocalDateTime.now();
	            LocalDateTime dealStartDate = dealItem.getDealStartDate();
	            LocalDateTime dealEndDate = dealItem.getDealEndDate();
	            return currentDate.isAfter(dealStartDate) && currentDate.isBefore(dealEndDate) && dealItem.getStock() > 0;
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to check deal activity and stock", e);
	        }
	    }
}
