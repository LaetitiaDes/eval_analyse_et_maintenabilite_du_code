package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    /**
     * This test checks the creation of a Car object and verifies that its title and price code are correctly initialized.
     */
    @Test
    void testCarCreation() {
        // Create a Car object with a title and price code
        Car car1 = CarFactory.createCar("Test Car 1", Car.REGULAR);

        // Check that title is correctly initialized
        assertEquals("Test Car 1", car1.getTitle());

        // Check that the price code is correctly initialized
        assertEquals(Car.REGULAR, car1.getPriceCode());
        assertEquals(0, car1.getPriceCode());

        System.out.println("Title car 1: " + car1.getTitle());
        System.out.println("Price Code car 1: " + car1.getPriceCode());
    }

    /**
     * This test checks if the price code can be modified for an existing Car object.
     */
    @Test
    void testSetPriceCode() {
        Car car2 = CarFactory.createCar("Test Car 2", Car.REGULAR);

        // Price code change
        car2.setPriceCode(Car.NEW_MODEL);

        // Check that the price code has been modified
        assertEquals(Car.NEW_MODEL, car2.getPriceCode());
        assertEquals(1, car2.getPriceCode());
        assertEquals("Test Car 2", car2.getTitle());

        System.out.println("Title car 2: " + car2.getTitle());
        System.out.println("Price Code car 2: " + car2.getPriceCode());
    }
}
