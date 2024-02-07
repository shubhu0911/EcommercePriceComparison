package com.nagarro.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EbayDealsResponse {

	
	private String categoryName;
    private List<EbayDealItem> dealItems;
	public EbayDealsResponse(String categoryName, List<EbayDealItem> dealItems) {
		super();
		this.categoryName = categoryName;
		this.dealItems = dealItems;
	}
}
