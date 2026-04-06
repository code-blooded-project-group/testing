package Layouts;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeRegistrationSystemTest {

    private EmployeeRegistrationSystem system;
    private Date validDate;

    @BeforeEach
    void setUp() {
        system = new EmployeeRegistrationSystem();
        validDate = new Date();
    }

    @Test
    void testValidRegistration() {
        assertTrue(system.ValidateFields("Jane Doe", "jane@company.com", "securePass123", "Finance", validDate),
            "Valid data should return true");
    }

    @Test
    void testEmptyName() {
        assertFalse(system.ValidateFields("", "jane@company.com", "password", "IT", validDate),
            "Empty name should be invalid");
    }

    @Test
    void testInvalidEmail() {
        assertFalse(system.ValidateFields("Jane", "not-an-email", "password", "IT", validDate),
            "Email without @ or . should be invalid");
    }

    @Test
    void testShortPassword() {
        assertFalse(system.ValidateFields("Jane", "jane@email.com", "12345", "IT", validDate),
            "Password under 6 chars should be invalid");
    }

    @Test
    void testUnselectedDepartment() {
        assertFalse(system.ValidateFields("Jane", "jane@email.com", "password", "-- Select Department --", validDate),
            "Default selection should be invalid");
    }

    @Test
    void testNullDate() {
        assertFalse(system.ValidateFields("Jane", "jane@email.com", "password", "IT", null),
            "Null date of birth should be invalid");
    }
}