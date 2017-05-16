package game.stock;

import java.util.Vector;

public final class Stock {
    private String title;
    private int price;
    private Vector<StockTick> ticks = new Vector<>();

    public int getPrice()
    {
        return price;
    }

    public void tick()
    {

    }
}
