package Layouts;

import javax.swing.JTextField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TabbedApplicationTest {

    private TabbedApplication app;
    private JTextField field1;
    private JTextField field2;

    @BeforeEach
    void setUp() {
        app = new TabbedApplication();
        field1 = new JTextField();
        field2 = new JTextField();
    }

    @Test
    void testCalculations() {
        field1.setText("10");
        field2.setText("5");

        assertEquals("15.0", app.calculate(field1, field2, "+"), "Addition failed");
        assertEquals("5.0", app.calculate(field1, field2, "-"), "Subtraction failed");
        assertEquals("50.0", app.calculate(field1, field2, "*"), "Multiplication failed");
        assertEquals("2.0", app.calculate(field1, field2, "/"), "Division failed");
    }

    @Test
    void testDivisionByZero() {
        field1.setText("10");
        field2.setText("0");
        assertEquals("Cannot divide by zero", app.calculate(field1, field2, "/"));
    }

    @Test
    void testInvalidInput() {
        field1.setText("abc");
        field2.setText("5");
        assertEquals("Invalid input", app.calculate(field1, field2, "+"));
    }
}