package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;

/**
 * PricingRule is an interface for calculating the rental amount based on the number of days rented.
 */
interface PricingRule {
    double calculateRentalAmount(int daysRented);
}

/**
 * RegularPricingRule implements the pricing rule for regular cars.
 */
class RegularPricingRule implements PricingRule {
    @Override
    public double calculateRentalAmount(int daysRented) {
        double thisAmount = 5000 + daysRented * 9500;
        if (daysRented > 5) {
            thisAmount -= (daysRented - 2) * 10000;
        }
        return thisAmount;
    }
}

/**
 * NewModelPricingRule implements the pricing rule for new model cars.
 */
class NewModelPricingRule implements PricingRule {
    @Override
    public double calculateRentalAmount(int daysRented) {
        double thisAmount = 9000 + daysRented * 15000;
        if (daysRented > 3) {
            thisAmount -= (daysRented - 2) * 10000;
        }
        return thisAmount;
    }
}

/**
 * CarFactory is responsible for creating Car objects with the appropriate pricing rule based on the price code.
 */
class CarFactory {
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

/**
 * Car represents a car available for rental.
 */
class Car {
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

/**
 * Rental represents a rental of a car for a specified number of days.
 */
class Rental {
    private Car car;
    private int daysRented;

    public Rental(Car car, int daysRented) {
        this.car = car;
        this.daysRented = daysRented;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public Car getCar() {
        return car;
    }
}

/**
 * Customer represents a customer who rents cars.
 */
class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    /**
     * Generates a textual invoice for the customer's rentals.
     */
    public String invoice() {
        double totalAmount = 0.0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental each : rentals) {
            double thisAmount = each.getCar().getPricingRule().calculateRentalAmount(each.getDaysRented());

            // Add frequent renter points
            frequentRenterPoints++;

            // Add bonus for a two day new release rental
            if (each.getCar().getPriceCode() == Car.NEW_MODEL && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // Show figures for this rental
            result.append("\t").append(each.getCar().getTitle()).append("\t").append(String.format("%.1f", thisAmount / 100)).append("\n");
            totalAmount += thisAmount;
        }

        // Add loyalty points based on the total rental amount
        frequentRenterPoints = (int) Math.round((totalAmount / 100) * 0.1);

        // Add footer lines
        result.append("Amount owed is ").append(String.format("%.1f", totalAmount / 100)).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");

        return result.toString();
    }

    /**
     * Generates a JSON invoice for the customer's rentals.
     */
    public String invoiceJSON() {
        double totalAmount = 0.0;
        int frequentRenterPoints = 0;
        List<Map<String, Object>> rentalsList = new ArrayList<>();

        for (Rental each : rentals) {
            double thisAmount = each.getCar().getPricingRule().calculateRentalAmount(each.getDaysRented());

            frequentRenterPoints++;

            if (each.getCar().getPriceCode() == Car.NEW_MODEL && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // Add rental info to list
            Map<String, Object> rentalMap = new LinkedHashMap<>();
            rentalMap.put("title", each.getCar().getTitle());
            rentalMap.put("amount", String.format("%.1f", thisAmount / 100));
            rentalsList.add(rentalMap);

            totalAmount += thisAmount;
        }

        // Add loyalty points based on the total rental amount
        frequentRenterPoints = (int) Math.round((totalAmount / 100) * 0.1);

        // Create final map
        Map<String, Object> invoiceMap = new LinkedHashMap<>();
        invoiceMap.put("record", "Rental Record for " + getName());
        invoiceMap.put("rentals", rentalsList);
        invoiceMap.put("totalAmount", String.format("%.1f", totalAmount / 100));
        invoiceMap.put("frequentRenterPoints", frequentRenterPoints);

        // Convert map to JSON string
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(invoiceMap);
    }
}

/**
 * Main class demonstrating the creation of cars, rentals, and customers, and generating invoices.
 */
// Example usage:
public class Main {
    public static void main(String[] args) {
        Car car1 = CarFactory.createCar("Car 1", Car.REGULAR);
        Car car2 = CarFactory.createCar("Car 2", Car.NEW_MODEL);

        Rental rental1 = new Rental(car1, 3);
        Rental rental2 = new Rental(car2, 2);

        Customer customer = new Customer("John Doe");
        customer.addRental(rental1);
        customer.addRental(rental2);

        System.out.println(customer.invoice());
        System.out.println(customer.invoiceJSON());
    }
}
