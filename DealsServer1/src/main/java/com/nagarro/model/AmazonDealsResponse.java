package com.nagarro.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmazonDealsResponse {
	private String categoryName;
    private List<AmazonDealItem> dealItems;
	public AmazonDealsResponse(String categoryName, List<AmazonDealItem> dealItems) {
		super();
		this.categoryName = categoryName;
		this.dealItems = dealItems;
	}
}
