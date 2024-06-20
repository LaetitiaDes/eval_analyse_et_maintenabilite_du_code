package org.example;

/**
 * Car represents a car available for rental.
 */
public class Car {
    public static final int REGULAR = 0;
    public static final int NEW_MODEL = 1;

    private String title;
    private int priceCode;

    private PricingRule pricingRule;

    public Car(String title, int priceCode, PricingRule pricingRule) {
        this.title = title;
        this.priceCode = priceCode;
        this.pricingRule = pricingRule;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public PricingRule getPricingRule() {
        return pricingRule;
    }

    public String getTitle() {
        return title;
    }
}
