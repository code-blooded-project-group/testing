package Layouts;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GridLayoutExample example = new GridLayoutExample();
            example.createFrame().setVisible(true);
            
            new MainApplication();
            new TabbedApplication();
            new EmployeeRegistrationSystem();
        });
    }
}
