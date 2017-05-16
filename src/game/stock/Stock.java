package game.stock;

import java.awt.*;
import java.util.Stack;

public final class Stock {
    private Color color;
    private String title;
    private int owned = 0;
    private int index;
    private Stack<StockTick> ticks = new Stack<>();

    public Stock(
            final Color color,
            final String title,
            final long price,
            final int index)
    {
        this.color = color;
        this.title = title;
        this.index = index;

        ticks.push(new StockTick(price));
    }

    public void buy()
    {
        ++owned;
    }

    public void sell()
    {
        --owned;
    }

    public long getPrice()
    {
        return ticks.peek().getPrice();
    }

    public long getDifference()
    {
        if(ticks.size() > 1)
            return ticks.elementAt(0).getPrice() - ticks.elementAt(1).getPrice();
        else
            return 0;
    }

    public int getOwned()
    {
        return owned;
    }

    public int getIndex()
    {
        return index;
    }

    public String getTitle()
    {
        return title;
    }

    public Color getColor()
    {
        return color;
    }

    public void tick()
    {

    }
}
