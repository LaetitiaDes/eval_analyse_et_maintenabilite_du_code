package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalTest {

    /**
     * This test checks the creation of a Rental object and verifies
     * that the number of rental days and the associated Car object
     * are correctly initialized and retrieved
     */
    @Test
    void testRentalCreation() {
        // Creating a Car and Rental object
        Car car = CarFactory.createCar("Test Car", Car.REGULAR);
        Rental rental = new Rental(car, 5);

        // Check that rental days are correctly initialized and retrieve
        assertEquals(5, rental.getDaysRented());

        // Check that car is correctly initialized and retrieve
        assertEquals(car, rental.getCar());

        System.out.println("Days Rented: " + rental.getDaysRented());
        System.out.println("Car Title: " + rental.getCar().getTitle());
    }
}
