package game.stock;

import java.util.Vector;

public final class Stock {
    private String title;
    private long price;
    private Vector<StockTick> ticks = new Vector<>();

    public long getPrice()
    {
        return price;
    }

    public void tick()
    {

    }
}
