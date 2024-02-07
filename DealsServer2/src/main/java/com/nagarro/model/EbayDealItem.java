package com.nagarro.model;

import java.time.LocalDateTime;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class EbayDealItem {
    @Id
    private String itemId;
    private String productTitle;
    private String size;
    private String brand;
    private String categoryName;
    @Embedded
    private Image image;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "marketing_original_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "marketing_original_currency"))
    })
    private MarketingPrice marketingPrice;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "price_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Price price;
    private int stock;
    private LocalDateTime dealStartDate;
    private LocalDateTime dealEndDate;
    }
@Getter
@Setter
@Embeddable
class Image {
    private String imageUrl;
}
@Getter
@Setter
@Embeddable
class MarketingPrice {
    @Embedded
    private OriginalPrice originalPrice;
    private double discountPercentage;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "discount_value")),
            @AttributeOverride(name = "currency", column = @Column(name = "discount_currency"))
    })
    private DiscountAmount discountAmount;
    private String priceTreatment;
    
}

@Getter
@Setter
@Embeddable
class OriginalPrice {
    private double value;
    private String currency;
}

@Getter
@Setter
@Embeddable
class DiscountAmount {
    private double value;
    private String currency;
}

@Getter
@Setter
@Embeddable
class Price {
    private double value;
    private String currency;
}
