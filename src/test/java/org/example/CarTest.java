package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testCarCreation() {
        // Create a Car object with a title and price code
        Car car1 = new Car("Test Car 1", Car.REGULAR);

        // Check that title is correctly initialized
        assertEquals("Test Car 1", car1.getTitle());

        // Check that the price code is correctly initialized
        assertEquals(Car.REGULAR, car1.getPriceCode());
        assertEquals(0, car1.getPriceCode());

        System.out.println("Title car 1: " + car1.getTitle());
        System.out.println("Price Code car 1: " + car1.getPriceCode());
    }

    @Test
    void testSetPriceCode() {
        Car car2 = new Car("Test Car 2", Car.REGULAR);

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
