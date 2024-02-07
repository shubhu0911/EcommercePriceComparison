package com.nagarro.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DealItem {
    private String itemId;
    private String productTitle;
    private String size;
    private String brand;
    private Image image;
    private MarketingPrice marketingPrice;
    private Price price;
    private int stock;
    private LocalDateTime dealStartDate;
    private LocalDateTime dealEndDate;
}
