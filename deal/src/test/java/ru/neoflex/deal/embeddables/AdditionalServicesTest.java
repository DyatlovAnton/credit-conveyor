package ru.neoflex.deal.embeddables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionalServicesTest {
    AdditionalServices services = new AdditionalServices(true, true);
    AdditionalServices services2 =new AdditionalServices();

    @Test
    void testToString() {
        System.out.println(services);
    }

    @Test
    void isInsuranceEnabled() {
        System.out.println(services.isInsuranceEnabled());
    }

    @Test
    void isSalaryClient() {
        System.out.println(services.isSalaryClient());
    }
}