package org.example;

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
