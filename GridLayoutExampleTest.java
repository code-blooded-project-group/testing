package Layouts;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class GridLayoutExampleTest {

    @Test
    void testCreateFrameProperties() {
        GridLayoutExample example = new GridLayoutExample();
        JFrame frame = example.createFrame();

        assertNotNull(frame);
        assertEquals("GridLayout Demo", frame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertTrue(frame.isVisible());

        frame.dispose();
    }

    @Test
    void testCreateMainPanelLayout() {
        GridLayoutExample example = new GridLayoutExample();
        JPanel mainPanel = example.createMainPanel();

        assertNotNull(mainPanel);
        assertInstanceOf(GridLayout.class, mainPanel.getLayout());

        GridLayout layout = (GridLayout) mainPanel.getLayout();
        assertEquals(1, layout.getRows());
        assertEquals(3, layout.getColumns());
        assertEquals(3, mainPanel.getComponentCount());
    }

    @Test
    void testIndividualPanelColors() {
        GridLayoutExample example = new GridLayoutExample();

        assertEquals(Color.ORANGE, example.createPanelOne().getBackground());
        assertEquals(Color.GRAY, example.createPanelTwo().getBackground());
        assertEquals(Color.MAGENTA, example.createPanelThree().getBackground());
    }
}