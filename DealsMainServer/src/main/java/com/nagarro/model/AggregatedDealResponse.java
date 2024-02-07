package com.nagarro.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AggregatedDealResponse {
	private String categoryName;
	private List<DealItem>dealItems;
	public AggregatedDealResponse(String categoryName, List<DealItem> dealItems) {
		super();
		this.categoryName = categoryName;
		this.dealItems = dealItems;
	}
	
}
