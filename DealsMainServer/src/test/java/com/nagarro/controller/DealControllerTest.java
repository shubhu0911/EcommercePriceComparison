package com.nagarro.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nagarro.model.AggregatedDealResponse;
import com.nagarro.model.DealItem;
import com.nagarro.service.DealService;

@ExtendWith(MockitoExtension.class)
public class DealControllerTest {

    @Mock
    private DealService dealService;

    @InjectMocks
    private DealController dealController;

    @Test
    public void testGetDealsByCategory_Success() throws Exception {
        // Mock data
        List<DealItem> mockDeals = new ArrayList<>();
        mockDeals.add(new DealItem());
        CompletableFuture<List<DealItem>> aggregatedDealsFuture = CompletableFuture.completedFuture(mockDeals);
        
        // Mocking service methods
        when(dealService.fetchAndAggregateDeals(anyString())).thenReturn(aggregatedDealsFuture);
        when(dealService.findBestDeals(anyList())).thenReturn(mockDeals);
        
        // Calling controller method
        ResponseEntity<AggregatedDealResponse> response = dealController.getDealsByCategory("Jeans");
        
        // Assertions
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody() != null;
        assert response.getBody().getCategoryName().equals("Jeans");
        assert response.getBody().getDealItems().equals(mockDeals);
    }

    @Test
    public void testGetDealsByCategory_Exception() throws Exception {
        // Mocking service method to throw an exception
        when(dealService.fetchAndAggregateDeals(anyString())).thenThrow(new RuntimeException());
        
        // Calling controller method
        ResponseEntity<AggregatedDealResponse> response = dealController.getDealsByCategory("Jeans");
        
        // Assertions
        assert response.getStatusCode().equals(HttpStatus.INTERNAL_SERVER_ERROR);
        assert response.getBody() == null;
    }
}

