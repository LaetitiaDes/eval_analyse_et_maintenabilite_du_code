package org.example;

/**
 * Rental represents a rental of a car for a specified number of days.
 */
public class Rental {
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
