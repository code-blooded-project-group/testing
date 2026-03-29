package Layouts;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LayoutsDemoTest {

    @Test
    void testFrameTitle() {
        JFrame frame = new JFrame();
        frame.setTitle("BorderLayout Demo");
        assertEquals("BorderLayout Demo", frame.getTitle());
    }

    @Test
    void testFrameSize() {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        assertEquals(600, frame.getWidth());
        assertEquals(400, frame.getHeight());
    }

    @Test
    void testPanelUsesGridLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));
        assertInstanceOf(GridLayout.class, panel.getLayout());
    }

    @Test
    void testButtonLabels() {
        JButton b1 = new JButton("Button One");
        JButton b2 = new JButton("Button Two");
        JButton b3 = new JButton("Button Three");
        assertEquals("Button One", b1.getText());
        assertEquals("Button Two", b2.getText());
        assertEquals("Button Three", b3.getText());
    }

    @Test
    void testScrollPaneWrapsTextArea() {
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        assertInstanceOf(JTextArea.class, scrollPane.getViewport().getView());
    }

    private JFrame frame;

    @BeforeEach
    void setUp() {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 400);
        frame.add(new JLabel("Application Title", JLabel.CENTER), BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
        panel.add(new JButton("Button One"));
        panel.add(new JButton("Button Two"));
        panel.add(new JButton("Button Three"));
        frame.add(panel, BorderLayout.WEST);
        frame.add(new JScrollPane(new JTextArea()), BorderLayout.CENTER);
        frame.add(new JButton("Submit"), BorderLayout.SOUTH);
    }

    @Test
    void testFrameLayoutIsBorderLayout() {
        assertInstanceOf(BorderLayout.class, frame.getContentPane().getLayout());
    }

    @Test
    void testAllRegionsPopulated() {
        BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
        assertNotNull(layout.getLayoutComponent(BorderLayout.NORTH));
        assertNotNull(layout.getLayoutComponent(BorderLayout.WEST));
        assertNotNull(layout.getLayoutComponent(BorderLayout.CENTER));
        assertNotNull(layout.getLayoutComponent(BorderLayout.SOUTH));
    }

    @Test
    void testWestPanelHasThreeButtons() {
        BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
        JPanel west = (JPanel) layout.getLayoutComponent(BorderLayout.WEST);
        assertEquals(3, west.getComponentCount());
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
    }
}