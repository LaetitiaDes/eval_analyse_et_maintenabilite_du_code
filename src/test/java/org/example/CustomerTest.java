package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        // Customer object initialization before each test
        customer = new Customer("Tifa Lockhart");
    }

    @Test
    void testCustomerCreation() {
        // Check that customer name is correctly initialized and retrieve
        assertEquals("Tifa Lockhart", customer.getName());

        // Display customer name value
        System.out.println("Customer Name: " + customer.getName());
    }
    @Test
    void testAddRental() {
        // Creating a Car and Rental object
        Car car = new Car("Test Car", Car.REGULAR);
        Rental rental = new Rental(car, 5);

        // Add rental to customer
        customer.addRental(rental);

        // Check that the rental has been added correctly
        assertEquals(5, rental.getDaysRented());
        assertEquals(car, rental.getCar());

        System.out.println("Days rented: " + rental.getDaysRented());
        System.out.println("Car: " + rental.getCar().getTitle());
    }

    @Test
    void testInvoice() {
        // Creation of several Car and Rental objects
        Car car1 = new Car("Car 1", Car.REGULAR);
        Car car2 = new Car("Car 2", Car.NEW_MODEL);

        Rental rental1 = new Rental(car1, 5);
        Rental rental2 = new Rental(car2, 2);

        // Add rentals to the customer
        customer.addRental(rental1);
        customer.addRental(rental2);

        // Expected invoice result
        String expectedInvoice = "Rental Record for Tifa Lockhart\n" +
                "\tCar 1\t525,0\n" +
                "\tCar 2\t390,0\n" +
                "Amount owed is 915,0\n" +
                "You earned 3 frequent renter points\n";

        // Check that the generated invoice is correct
        assertEquals(expectedInvoice, customer.invoice());

        System.out.println("Generated Invoice:\n" + customer.invoice());
    }
}
