package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Customer represents a customer who rents cars.
 */
public class Customer {
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

    private double calculateTotalAmount() {
        double totalAmount = 0.0;
        for (Rental each : rentals) {
            totalAmount += each.getCar().getPricingRule().calculateRentalAmount(each.getDaysRented());
        }
        return totalAmount;
    }

    private int calculateFrequentRenterPoints() {
        int frequentRenterPoints = 0;
        for (Rental each : rentals) {
            frequentRenterPoints++;
            if (each.getCar().getPriceCode() == Car.NEW_MODEL && each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }
        }
        frequentRenterPoints = (int) Math.round((calculateTotalAmount() / 100) * 0.1);
        return frequentRenterPoints;
    }
    /**
     * Generates a textual invoice for the customer's rentals.
     */
    public String invoice() {
        double totalAmount = calculateTotalAmount();
        int frequentRenterPoints = calculateFrequentRenterPoints();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental each : rentals) {
            double thisAmount = each.getCar().getPricingRule().calculateRentalAmount(each.getDaysRented());
            result.append("\t").append(each.getCar().getTitle()).append("\t").append(String.format("%.1f", thisAmount / 100)).append("\n");
        }

        // Add footer lines
        result.append("Amount owed is ").append(String.format("%.1f", totalAmount / 100)).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");

        return result.toString();
    }

    /**
     * Generates a JSON invoice for the customer's rentals.
     */
    public String invoiceJSON() {
        double totalAmount = calculateTotalAmount();
        int frequentRenterPoints = calculateFrequentRenterPoints();
        List<Map<String, Object>> rentalsList = new ArrayList<>();

        for (Rental each : rentals) {
            double thisAmount = each.getCar().getPricingRule().calculateRentalAmount(each.getDaysRented());

            // Add rental info to list
            Map<String, Object> rentalMap = new LinkedHashMap<>();
            rentalMap.put("title", each.getCar().getTitle());
            rentalMap.put("amount", String.format("%.1f", thisAmount / 100));
            rentalsList.add(rentalMap);
        }

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
