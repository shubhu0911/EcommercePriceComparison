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

import com.nagarro.model.AmazonDealItem;
import com.nagarro.model.AmazonDealsResponse;
import com.nagarro.service.AmazonDealService;
@ExtendWith(MockitoExtension.class)
public class AmazonDealControllerTest {

    @InjectMocks
    private AmazonDealController amazonDealController;

    @Mock
    private AmazonDealService amazonDealService;

    @Test
    public void testGetDealsByCategory_Success() {
        // Mocking service response
        List<AmazonDealItem> mockDeals = new ArrayList<>();
        mockDeals.add(new AmazonDealItem());
        Mockito.when(amazonDealService.getDealsByCategory(Mockito.anyString())).thenReturn(mockDeals);

        // Calling the controller method
        ResponseEntity<AmazonDealsResponse> response = amazonDealController.getDealsByCategory("Jeans");

        // Assertions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Jeans", response.getBody().getCategoryName());
        Assert.assertEquals(mockDeals, response.getBody().getDealItems());
    }

    @Test
    public void testGetDealsByCategory_Exception() {
        // Mocking service to throw an exception
        Mockito.when(amazonDealService.getDealsByCategory(Mockito.anyString())).thenThrow(new RuntimeException());

        // Calling the controller method
        ResponseEntity<AmazonDealsResponse> response = amazonDealController.getDealsByCategory("Jeans");

        // Assertions
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void testAddDeal_Success() {
        // Mocking service response
        AmazonDealItem mockDealItem = new AmazonDealItem();
        Mockito.doNothing().when(amazonDealService).addDeal(mockDealItem);

        // Calling the controller method
        ResponseEntity<String> response = amazonDealController.addDeal(mockDealItem);

        // Assertions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Deal added Successfully", response.getBody());
    }

    @Test
    public void testAddDeal_Exception() {
        // Mocking service to throw an exception
        Mockito.doThrow(new RuntimeException()).when(amazonDealService).addDeal(Mockito.any());

        // Calling the controller method
        ResponseEntity<String> response = amazonDealController.addDeal(new AmazonDealItem());

        // Assertions
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertEquals("Failed to add deal", response.getBody());
    }
}

