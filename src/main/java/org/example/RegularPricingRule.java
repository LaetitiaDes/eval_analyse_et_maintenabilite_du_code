package org.example;

/**
 * RegularPricingRule implements the pricing rule for regular cars.
 */
public class RegularPricingRule extends PricingRule {

    public RegularPricingRule() {
        baseAmount = 5000;
        dailyRate = 9500;
        discountThreshold = 5;
        discountRate = 10000;
    }
}
