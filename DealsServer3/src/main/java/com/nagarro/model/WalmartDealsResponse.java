package com.nagarro.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WalmartDealsResponse {
	private String categoryName;
    private List<WalmartDealItem> dealItems;
	public WalmartDealsResponse(String categoryName, List<WalmartDealItem> dealItems) {
		super();
		this.categoryName = categoryName;
		this.dealItems = dealItems;
	}
}
