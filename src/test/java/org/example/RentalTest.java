package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    @Test
    void testRentalCreation() {
        // Creating a Car and Rental object
        Car car = new Car("Test Car", Car.REGULAR);
        Rental rental = new Rental(car, 5);

        // Check that rental days are correctly initialized and retrieve
        assertEquals(5, rental.getDaysRented());

        // Check that car is correctly initialized and retrieve
        assertEquals(car, rental.getCar());

        System.out.println("Days Rented: " + rental.getDaysRented());
        System.out.println("Car Title: " + rental.getCar().getTitle());
    }
}
