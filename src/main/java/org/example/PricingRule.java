package org.example;

/**
 * PricingRule is an interface for calculating the rental amount based on the number of days rented.
 */
public abstract class PricingRule {

    protected double baseAmount;
    protected double dailyRate;
    protected int discountThreshold;
    protected double discountRate;

    public double calculateRentalAmount(int daysRented) {
        double thisAmount = baseAmount + daysRented * dailyRate;
        if (daysRented > discountThreshold) {
            thisAmount -= (daysRented - discountThreshold) * discountRate;
        }
        return thisAmount;
    }
}
