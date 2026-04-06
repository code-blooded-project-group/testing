package Layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainApplicationTest {

    private MainApplication app;
    private JFrame frame;

    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            app = new MainApplication();
            frame = app.CreateFrame();
            latch.countDown();
        });
        assertTrue(latch.await(5, TimeUnit.SECONDS), "GUI setup timed out");
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            if (frame != null) {
                frame.dispose();
            }
            latch.countDown();
        });
        assertTrue(latch.await(5, TimeUnit.SECONDS), "GUI tear down timed out");
    }

    @Test
    void testCreateFrameProperties() {
        assertNotNull(frame, "JFrame should not be null");
        assertEquals("Student Management System", frame.getTitle(), "Frame title should match");
        assertEquals(800, frame.getWidth(), "Frame width should be 800");
        assertEquals(600, frame.getHeight(), "Frame height should be 600");
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation(), "Default close operation should be EXIT_ON_CLOSE");
        assertTrue(frame.getLayout() instanceof BorderLayout, "Frame layout should be BorderLayout");
        assertTrue(frame.isVisible(), "Frame should be visible");
    }

    @Test
    void testMenuBarCreation() {
        JMenuBar menuBar = frame.getJMenuBar();
        assertNotNull(menuBar, "Menu bar should not be null");
        assertEquals(3, menuBar.getMenuCount(), "Menu bar should have 3 menus");

        JMenu fileMenu = menuBar.getMenu(0);
        assertNotNull(fileMenu, "File menu should exist");
        assertEquals("File", fileMenu.getText(), "File menu text should be 'File'");

        JMenu viewMenu = menuBar.getMenu(1);
        assertNotNull(viewMenu, "View menu should exist");
        assertEquals("View", viewMenu.getText(), "View menu text should be 'View'");

        JMenu helpMenu = menuBar.getMenu(2);
        assertNotNull(helpMenu, "Help menu should exist");
        assertEquals("Help", helpMenu.getText(), "Help menu text should be 'Help'");
    }

    @Test
    void testHeaderPanelCreation() {
        Component[] components = frame.getContentPane().getComponents();
        JPanel headerPanel = null;
        for (Component comp : components) {
            if (BorderLayout.NORTH.equals(((BorderLayout) frame.getContentPane().getLayout()).getConstraints(comp))) {
                headerPanel = (JPanel) comp;
                break;
            }
        }
        assertNotNull(headerPanel, "Header panel should be added to the frame");
        assertEquals(new Color(33, 97, 140), headerPanel.getBackground(), "Header panel background color should match");
        JLabel titleLabel = (JLabel) headerPanel.getComponent(0);
        assertEquals("Student Management System", titleLabel.getText(), "Header title label text should match");
    }

    @Test
    void testShowContentUpdatesMainPanelAndStatusLabel() throws InterruptedException {
        String testMessage = "This is a test message!";
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            app.showContent(testMessage);
            latch.countDown();
        });
        assertTrue(latch.await(5, TimeUnit.SECONDS), "showContent execution timed out");

        // Assertions must also be on the EDT if they interact with Swing components
        CountDownLatch assertLatch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            JPanel mainContentPanel = app.getMainContentPanel();
            JLabel statusLabel = app.getStatusLabel();

            assertNotNull(mainContentPanel, "Main content panel should not be null");
            assertEquals(1, mainContentPanel.getComponentCount(), "Main content panel should have one component (the new label)");
            assertTrue(mainContentPanel.getComponent(0) instanceof JLabel, "Component in main content panel should be a JLabel");
            JLabel contentLabel = (JLabel) mainContentPanel.getComponent(0);
            assertEquals(testMessage, contentLabel.getText(), "Content label text should match the message");

            assertNotNull(statusLabel, "Status label should not be null");
            assertEquals("  Status: " + testMessage, statusLabel.getText(), "Status label text should be updated");
            assertLatch.countDown();
        });
        assertTrue(assertLatch.await(5, TimeUnit.SECONDS), "Assertions timed out");
    }

    @Test
    void testSidePanelButtonCreation() {
        JButton button = app.CreateSideButton("Test Button", "Test Content");
        assertNotNull(button, "Button should not be null");
        assertEquals("Test Button", button.getText(), "Button text should match");
        assertEquals(new Color(52, 73, 94), button.getBackground(), "Button background color should match");
    }
}