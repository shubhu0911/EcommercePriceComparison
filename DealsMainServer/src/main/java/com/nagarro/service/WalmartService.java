package com.nagarro.service;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.nagarro.model.AggregatedDealResponse;
import com.nagarro.model.DealItem;
@Service
public class WalmartService {
    private final WebClient webClient;
	@Autowired
	public WalmartService(WebClient.Builder webClientBuilder) {
		this.webClient=webClientBuilder.baseUrl("http://localhost:8083/backendserver3/walmart/deals").build();
	}
	private static final org.slf4j.Logger logger=LoggerFactory.getLogger(WalmartService.class);
	public CompletableFuture<List<DealItem>>fetchDeals(String categoryName){
		String apiUrl="/{categoryName}";
		try {
            return webClient.get()
                    .uri(apiUrl, categoryName)
                    .retrieve()
                    .bodyToMono(AggregatedDealResponse.class)
                    .map(AggregatedDealResponse::getDealItems)
                    .doOnNext(response -> logger.info("Walmart API Response: {}", response))
                    .doOnError(error -> logger.error("Error fetching Walmart deals", error))
                    .toFuture();
        } catch (Exception e) {
            CompletableFuture<List<DealItem>> failedFuture = new CompletableFuture<>();
            failedFuture.completeExceptionally(e);
            return failedFuture;
        }
	}
}
