package view.stocks;

import game.stock.Stock;
import game.trader.Trader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

final class StocksGraph extends JPanel implements Observer {
    private static final Color COLOR_BACKGROUND = Color.DARK_GRAY;
    private static final Color COLOR_GRID = Color.GRAY;
    private static final BasicStroke STROKE_GRAPH = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    private static final int RESOLUTION = 16;

    private Trader trader;

    StocksGraph(final Trader trader)
    {
        trader.addObserver(this);

        this.trader = trader;
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));

        g2d.setColor(COLOR_BACKGROUND);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(COLOR_GRID);

        for(int x = getWidth(); x > 0; x -= RESOLUTION)
            g2d.drawLine(x, 0, x, getHeight());

        for(int y = getHeight(); y > 0; y -= RESOLUTION)
            g2d.drawLine(0, y, getWidth(), y);

        for(final Stock s : trader.getStocks())
            drawGraph(g2d, s);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch((Trader.Change)arg) {
            case TICK:
            case BUY:
            case SELL:
            case INITIALIZED:
                updateUI();
                break;
        }
    }

    private void drawGraph(Graphics2D g, final Stock stock)
    {
        g.setStroke(STROKE_GRAPH);
        g.setColor(stock.getColor());

        final float priceScale = (float)getHeight() / Stock.MAX_VALUE;

        int tickIndex = stock.getTicks().size();
        for(int x = getWidth(); x > -RESOLUTION; x -= RESOLUTION) {
            if(--tickIndex < 1)
                break;

            g.drawLine(
                    x + RESOLUTION,
                    getHeight() - (int)(stock.getTicks().elementAt(tickIndex).getPrice() * priceScale),
                    x,
                    getHeight() - (int)(stock.getTicks().elementAt(tickIndex - 1).getPrice() * priceScale));
        }
    }
}
