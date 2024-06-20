package org.example;

/**
 * NewModelPricingRule implements the pricing rule for new model cars.
 */
public class NewModelPricingRule extends PricingRule {

    public NewModelPricingRule() {
        baseAmount = 9000;
        dailyRate = 15000;
        discountThreshold = 3;
        discountRate = 10000;
    }
}
