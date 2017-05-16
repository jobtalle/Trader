package view.stocks;

import javax.swing.*;
import java.awt.*;

final class StocksGraph extends JPanel {
    private static final Color COLOR_BACKGROUND = Color.DARK_GRAY;
    private static final Color COLOR_GRID = Color.GRAY;
    private static final int RESOLUTION = 32;

    StocksGraph()
    {

    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(COLOR_GRID);

        for(int x = getWidth(); x > 0; x -= RESOLUTION)
            g.drawLine(x, 0, x, getHeight());

        for(int y = getHeight(); y > 0; y -= RESOLUTION)
            g.drawLine(0, y, getWidth(), y);
    }
}
