package org.example;

/**
 * CarFactory is responsible for creating Car objects with the appropriate pricing rule based on the price code.
 */
public class CarFactory {
    public static Car createCar(String title, int priceCode) {
        PricingRule pricingRule;
        switch (priceCode) {
            case Car.REGULAR:
                pricingRule = new RegularPricingRule();
                break;
            case Car.NEW_MODEL:
                pricingRule = new NewModelPricingRule();
                break;
            default:
                throw new IllegalArgumentException("Invalid price code");
        }
        return new Car(title, priceCode, pricingRule);
    }
}
