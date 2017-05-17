package game.stock;

import java.awt.*;
import java.util.Stack;

public final class Stock {
    public static final long MIN_VALUE = 1;
    public static final long MAX_VALUE = 40000;

    private Color color;
    private String title;
    private int owned = 0;
    private int index;
    private Stack<StockTick> ticks = new Stack<>();
    private StockSampler sampler;

    public Stock(
            final Color color,
            final String title,
            final int index)
    {
        this.color = color;
        this.title = title;
        this.index = index;

        sampler = new StockSampler();

        ticks.push(new StockTick(sampler.sample()));
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
            return ticks.elementAt(ticks.size() - 1).getPrice() - ticks.elementAt(ticks.size() - 2).getPrice();
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
        ticks.push(new StockTick(sampler.sample()));
    }
}
