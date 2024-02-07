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

import com.nagarro.model.EbayDealItem;
import com.nagarro.model.EbayDealsResponse;
import com.nagarro.service.EbayDealService;

@ExtendWith(MockitoExtension.class)
public class EbayDealControllerTest {

    @InjectMocks
    private EbayDealController ebayDealController;

    @Mock
    private EbayDealService ebayDealService;

    @Test
    public void testGetDealsByCategory_Success() {
        // Mocking service response
        List<EbayDealItem> mockDeals = new ArrayList<>();
        mockDeals.add(new EbayDealItem());
        Mockito.when(ebayDealService.getDealsByCategory(Mockito.anyString())).thenReturn(mockDeals);

        // Calling the controller method
        ResponseEntity<EbayDealsResponse> response = ebayDealController.getDealsByCategory("Jeans");

        // Assertions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Jeans", response.getBody().getCategoryName());
        Assert.assertEquals(mockDeals, response.getBody().getDealItems());
    }

    @Test
    public void testGetDealsByCategory_Exception() {
        // Mocking service to throw an exception
        Mockito.when(ebayDealService.getDealsByCategory(Mockito.anyString())).thenThrow(new RuntimeException());

        // Calling the controller method
        ResponseEntity<EbayDealsResponse> response = ebayDealController.getDealsByCategory("Jeans");

        // Assertions
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertNull(response.getBody());
    }

    @Test
    public void testAddDeal_Success() {
        // Mocking service response
        EbayDealItem mockDealItem = new EbayDealItem();
        Mockito.doNothing().when(ebayDealService).addDeal(mockDealItem);

        // Calling the controller method
        ResponseEntity<String> response = ebayDealController.addDeal(mockDealItem);

        // Assertions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("Deal added Successfully", response.getBody());
    }

    @Test
    public void testAddDeal_Exception() {
        // Mocking service to throw an exception
        Mockito.doThrow(new RuntimeException()).when(ebayDealService).addDeal(Mockito.any());

        // Calling the controller method
        ResponseEntity<String> response = ebayDealController.addDeal(new EbayDealItem());

        // Assertions
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertEquals("Failed to add deal", response.getBody());
    }
}

