package org.example;

import java.util.ArrayList;
import java.util.List;

class Car {
    public static final int REGULAR = 0;
    public static final int NEW_MODEL = 1;

    private String title;
    private int priceCode;

    public Car(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }
}

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

    public String invoice() {
        double totalAmount = 0.0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental each : rentals) {
            double thisAmount = 0.0;

            // Determine amounts for each line
            switch (each.getCar().getPriceCode()) {
                case Car.REGULAR:
                    thisAmount += 5000 + each.getDaysRented() * 9500;
                    if (each.getDaysRented() > 5) {
                        thisAmount -= (each.getDaysRented() - 2) * 10000;
                    }
                    break;
                case Car.NEW_MODEL:
                    thisAmount += 9000 + each.getDaysRented() * 15000;
                    if (each.getDaysRented() > 3) {
                        thisAmount -= (each.getDaysRented() - 2) * 10000;
                    }
                    break;
            }

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

        // Add footer lines
        result.append("Amount owed is ").append(String.format("%.1f", totalAmount / 100)).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points\n");

        return result.toString();
    }
}

// Example usage:
public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("Car 1", Car.REGULAR);
        Car car2 = new Car("Car 2", Car.NEW_MODEL);

        Rental rental1 = new Rental(car1, 3);
        Rental rental2 = new Rental(car2, 2);

        Customer customer = new Customer("John Doe");
        customer.addRental(rental1);
        customer.addRental(rental2);

        System.out.println(customer.invoice());
    }
}
