package com.nagarro.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nagarro.model.WalmartDealItem;
import com.nagarro.model.WalmartDealsResponse;
import com.nagarro.service.WalmartDealService;

@ExtendWith(MockitoExtension.class)
public class WalmartDealsControllerTest {

    @InjectMocks
    private WalmartDealsController walmartDealsController;

    @Mock
    private WalmartDealService walmartDealService;

    @Test
    public void testGetDealsByCategory_Success() {
        // Mocking service response
        List<WalmartDealItem> mockDeals = new ArrayList<>();
        mockDeals.add(new WalmartDealItem());
        Mockito.when(walmartDealService.getDealsByCategory(Mockito.anyString())).thenReturn(mockDeals);

        // Calling the controller method
        ResponseEntity<WalmartDealsResponse> response = walmartDealsController.getDealsByCategory("Jeans");

        // Assertions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Jeans", response.getBody().getCategoryName());
        Assert.assertEquals(mockDeals, response.getBody().getDealItems());
    }

    @Test
    public void testGetDealsByCategory_Exception() {
        // Mocking service to throw an exception
        Mockito.lenient().when(walmartDealService.getDealsByCategory(Mockito.anyString())).thenThrow(new RuntimeException());

        // Calling the controller method
        ResponseEntity<WalmartDealsResponse> response = walmartDealsController.getDealsByCategory("Jeans");

        // Assertions
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void testAddDeal_Success() {
        // Mocking service response
        WalmartDealItem mockDealItem = new WalmartDealItem();
        Mockito.doNothing().when(walmartDealService).addDeal(mockDealItem);

        // Calling the controller method
        ResponseEntity<String> response = walmartDealsController.addDeal(mockDealItem);

        // Assertions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Deal added Successfully", response.getBody());
    }

    @Test
    public void testAddDeal_Exception() {
        // Mocking service to throw an exception
        Mockito.doThrow(new RuntimeException()).when(walmartDealService).addDeal(Mockito.any());

        // Calling the controller method
        ResponseEntity<String> response = walmartDealsController.addDeal(new WalmartDealItem());

        // Assertions
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertEquals("Failed to add deal", response.getBody());
    }
}
