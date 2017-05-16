package view.utils.graphics;

import java.awt.*;

public final class StringRenderUtils {
    public static void drawCenteredString(final Graphics g, final String text, final Rectangle rect) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        final int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        final int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.drawString(text, x, y);
    }
}
