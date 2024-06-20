package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarFactoryTest {

    /**
     * This test checks if the CarFactory correctly creates cars with the appropriate pricing rules.
     */
    @Test
    void testCarFactory() {
        Car regularCar = CarFactory.createCar("Regular Car", Car.REGULAR);
        Car newModelCar = CarFactory.createCar("New Model Car", Car.NEW_MODEL);

        // Check that the cars are created with the correct pricing rules
        assertInstanceOf(RegularPricingRule.class, regularCar.getPricingRule());
        assertInstanceOf(NewModelPricingRule.class, newModelCar.getPricingRule());
    }

    /**
     * This test checks if the rental amounts are correctly calculated for each type of car.
     */
    @Test
    void testRentalAmountCalculation() {
        Car regularCar = CarFactory.createCar("Regular Car", Car.REGULAR);
        Car newModelCar = CarFactory.createCar("New Model Car", Car.NEW_MODEL);

        Rental regularRental = new Rental(regularCar, 5);
        Rental newModelRental = new Rental(newModelCar, 2);


        // Check that the rental amounts are correctly calculated
        assertEquals(52500.0, regularCar.getPricingRule().calculateRentalAmount(regularRental.getDaysRented()));
        assertEquals(39000.0, newModelCar.getPricingRule().calculateRentalAmount(newModelRental.getDaysRented()));

        System.out.println("rental amount for regular car: " + regularCar.getPricingRule().calculateRentalAmount(regularRental.getDaysRented()));
        System.out.println("rental amount for new model car: " + newModelCar.getPricingRule().calculateRentalAmount(newModelRental.getDaysRented()));
    }
}
