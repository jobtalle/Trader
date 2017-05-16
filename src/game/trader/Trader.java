package game.trader;

import game.stock.Stock;

import java.util.Observable;
import java.util.Vector;

public final class Trader extends Observable {
    private static final int DEFAULT_STOCK_COUNT = 5;
    private static final int DEFAULT_FREQUENCY = 500;
    private static final int DEFAULT_BALANCE = 1000;
    private static final int DEFAULT_MAX_TICKS = 240;

    private boolean paused = true;
    private int tick = 0;
    private int maxTicks;
    private int stockCount;
    private int frequency;
    private int balance;

    private Vector<Stock> stocks = new Vector<>();

    public Trader()
    {
        this(DEFAULT_STOCK_COUNT, DEFAULT_FREQUENCY, DEFAULT_BALANCE, DEFAULT_MAX_TICKS);
    }

    public Trader(final int stockCount, final int frequency, final int balance, final int maxTicks)
    {
        this.stockCount = stockCount;
        this.frequency = frequency;
        this.balance = balance;
        this.maxTicks = maxTicks;

        reset();
    }

    private void reset()
    {
        stocks.clear();

        for(int i = 0; i < stockCount; ++i)
            addStock();
    }

    public void setPaused(final boolean paused)
    {
        this.paused = paused;
    }

    private void addStock()
    {
        stocks.add(new Stock());
    }

    private void update()
    {
        setChanged();
        notifyObservers();
    }
}
