package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    /**
     * Initializes a Customer object before each test.
     */
    @BeforeEach
    void setUp() {
        // Customer object initialization before each test
        customer = new Customer("Tifa Lockhart");
    }

    /**
     * This test checks the creation of a Customer object and verifies
     * that the customer's name is correctly initialized and retrieved.
     */
    @Test
    void testCustomerCreation() {
        // Check that customer name is correctly initialized and retrieve
        assertEquals("Tifa Lockhart", customer.getName());

        // Display customer name value
        System.out.println("Customer Name: " + customer.getName());
    }

    /**
     * This test checks that a Rental can be added to a Customer and verifies
     * that the rental is correctly associated with the customer.
     */
    @Test
    void testAddRental() {
        // Creating a Car and Rental object
        Car car = CarFactory.createCar("Test Car", Car.REGULAR);
        Rental rental = new Rental(car, 5);

        // Add rental to customer
        customer.addRental(rental);

        // Check that the rental has been added correctly
        assertEquals(5, rental.getDaysRented());
        assertEquals(car, rental.getCar());

        System.out.println("Days rented: " + rental.getDaysRented());
        System.out.println("Car: " + rental.getCar().getTitle());
    }

    /**
     * This test checks the generation of an invoice for a Customer
     * and verifies that the invoice content is correct
     * based on the rentals associated with the customer.
     */
    @Test
    void testInvoice() {
        // Creation of several Car and Rental objects
        Car car1 = CarFactory.createCar("Car 1", Car.REGULAR);
        Car car2 = CarFactory.createCar("Car 2", Car.NEW_MODEL);

        Rental rental1 = new Rental(car1, 5);
        Rental rental2 = new Rental(car2, 2);

        // Add rentals to the customer
        customer.addRental(rental1);
        customer.addRental(rental2);

        // Expected invoice result
        String expectedInvoice = "Rental Record for Tifa Lockhart\n" +
                "\tCar 1\t" + String.format("%.1f", car1.getPricingRule().calculateRentalAmount(5) / 100) + "\n" +
                "\tCar 2\t" + String.format("%.1f", car2.getPricingRule().calculateRentalAmount(2) / 100) + "\n" +
                "Amount owed is " + String.format("%.1f", (car1.getPricingRule().calculateRentalAmount(5) + car2.getPricingRule().calculateRentalAmount(2)) / 100) + "\n" +
                "You earned 3 frequent renter points\n";

        // Check that the generated invoice is correct
        assertEquals(expectedInvoice, customer.invoice());

        System.out.println("Generated Invoice:\n" + customer.invoice());
    }

    /**
     * This test checks the generation of an invoice for a Customer
     * in JSON format and verifies that the JSON content is correct
     * based on the rentals associated with the customer.
     */
    @Test
    void testInvoiceJSON() {
        // Creation of several Car and Rental objects
        Car car1 = CarFactory.createCar("Car 1", Car.REGULAR);
        Car car2 = CarFactory.createCar("Car 2", Car.NEW_MODEL);

        Rental rental1 = new Rental(car1, 5);
        Rental rental2 = new Rental(car2, 2);

        // Add rentals to the customer
        customer.addRental(rental1);
        customer.addRental(rental2);

        // Expected invoice result in JSON format
        String expectedInvoiceJson = "{\n" +
                "  \"record\": \"Rental Record for Tifa Lockhart\",\n" +
                "  \"rentals\": [\n" +
                "    {\n" +
                "      \"title\": \"Car 1\",\n" +
                "      \"amount\": \"" + String.format("%.1f", car1.getPricingRule().calculateRentalAmount(5) / 100) + "\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Car 2\",\n" +
                "      \"amount\": \"" + String.format("%.1f", car2.getPricingRule().calculateRentalAmount(2) / 100) + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"totalAmount\": \"" + String.format("%.1f", (car1.getPricingRule().calculateRentalAmount(5) + car2.getPricingRule().calculateRentalAmount(2)) / 100) + "\",\n" +
                "  \"frequentRenterPoints\": 3\n" +
                "}";

        // Check that the generated invoice is correct
        assertEquals(expectedInvoiceJson, customer.invoiceJSON());

        System.out.println("Invoice generated in Json format:\n" + customer.invoiceJSON());
    }
}
