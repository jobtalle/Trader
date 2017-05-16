package game.stock;

import java.util.Vector;

public final class Stock {
    private String title;
    private int owned = 0;
    private Vector<StockTick> ticks = new Vector<>();
}
