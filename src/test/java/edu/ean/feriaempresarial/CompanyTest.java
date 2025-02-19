package edu.ean.feriaempresarial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import edu.ean.feriaempresarial.model.Company;

class CompanyTest {

    @Test
    void testGetIdReturnsName() {
        // Arrange
        Company company = new Company("TechCorp", "Technology", "info@techcorp.com");

        // Act
        String id = company.getId();

        // Assert
        assertEquals("TechCorp", id, "getId() should return the company name");
    }

    @Test
    void testEqualsSameNameShouldBeEqual() {
        // Arrange
        Company company1 = new Company("TechCorp", "Technology", "info@techcorp.com");
        Company company2 = new Company("TechCorp", "Finance", "contact@techcorp.com"); // Different sector and email

        // Act & Assert
        assertEquals(company1, company2, "Companies with the same name should be equal");
    }

    @Test
    void testEqualsDifferentNameShouldNotBeEqual() {
        // Arrange
        Company company1 = new Company("TechCorp", "Technology", "info@techcorp.com");
        Company company2 = new Company("InnovateInc", "Technology", "contact@innovate.com");

        // Act & Assert
        assertNotEquals(company1, company2, "Companies with different names should not be equal");
    }

    @Test
    void testHashCodeSameNameShouldBeEqual() {
        // Arrange
        Company company1 = new Company("TechCorp", "Technology", "info@techcorp.com");
        Company company2 = new Company("TechCorp", "Finance", "contact@techcorp.com");

        // Act & Assert
        assertEquals(company1.hashCode(), company2.hashCode(), "Hash codes should be equal for companies with the same name");
    }

    @Test
    void testHashCodeDifferentNameShouldNotBeEqual() {
        // Arrange
        Company company1 = new Company("TechCorp", "Technology", "info@techcorp.com");
        Company company2 = new Company("InnovateInc", "Technology", "contact@innovate.com");

        // Act & Assert
        assertNotEquals(company1.hashCode(), company2.hashCode(), "Hash codes should not be equal for companies with different names");
    }
}
