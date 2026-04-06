package Layouts;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class LayoutsDemotest {

    @Test
    void testCreateFrameProperties() {
        LayoutsDemo demo = new LayoutsDemo();
        JFrame frame = demo.createFrame();

        assertNotNull(frame);
        assertEquals("BorderLayout Demo", frame.getTitle());
        assertEquals(600, frame.getWidth());
        assertEquals(400, frame.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());

        frame.dispose();
    }

    @Test
    void testCreateWestPanelLayout() {
        LayoutsDemo demo = new LayoutsDemo();
        JPanel westPanel = demo.createWestPanel();

        assertNotNull(westPanel);
        assertInstanceOf(GridLayout.class, westPanel.getLayout());

        GridLayout layout = (GridLayout) westPanel.getLayout();
        assertEquals(3, layout.getRows());
        assertEquals(1, layout.getColumns());
        assertEquals(3, westPanel.getComponentCount());
    }

    @Test
    void testIndividualButtonLabels() {
        LayoutsDemo demo = new LayoutsDemo();

        assertEquals("Button One", demo.createButtonOne().getText());
        assertEquals("Button Two", demo.createButtonTwo().getText());
        assertEquals("Button Three", demo.createButtonThree().getText());
        assertEquals("Submit", demo.createSouthButton().getText());
    }

    @Test
    void testScrollPaneWrapsTextArea() {
        LayoutsDemo demo = new LayoutsDemo();
        JScrollPane scrollPane = demo.createCenterPane();

        assertNotNull(scrollPane);
        assertInstanceOf(JTextArea.class, scrollPane.getViewport().getView());
    }
}
