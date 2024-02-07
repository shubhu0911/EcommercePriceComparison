package com.nagarro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketingPrice {
    private OriginalPrice originalPrice;
    private double discountPercentage;
    private DiscountAmount discountAmount;
    private String priceTreatment;
}

