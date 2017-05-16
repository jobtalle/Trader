package game.trader;

import game.stock.Stock;

import java.util.Observable;
import java.util.Vector;

public final class Trader extends Observable {
    private static final int DEFAULT_STOCK_COUNT = 5;
    private static final int DEFAULT_FREQUENCY = 500;
    private static final int DEFAULT_BALANCE = 120000;
    private static final int DEFAULT_MAX_TICKS = 240;

    private boolean paused;
    private int tick;
    private int maxTicks, maxTicksInitial;
    private int stockCount, stockCountInitial;
    private int frequency, frequencyInitial;
    private int balance, balanceInitial;

    private Vector<Stock> stocks = new Vector<>();

    public enum Change {
        INITIALIZED,
        PAUSE,
        RESUME,
        TICK
    }

    public Trader()
    {
        this(DEFAULT_STOCK_COUNT, DEFAULT_FREQUENCY, DEFAULT_BALANCE, DEFAULT_MAX_TICKS);
    }

    public Trader(final int stockCount, final int frequency, final int balance, final int maxTicks)
    {
        stockCountInitial = stockCount;
        frequencyInitial = frequency;
        balanceInitial = balance;
        maxTicksInitial = maxTicks;

        setup();
    }

    public void initialize()
    {
        update(Change.INITIALIZED);
    }

    private void setup()
    {
        tick = 0;
        paused = true;

        maxTicks = maxTicksInitial;
        stockCount = stockCountInitial;
        frequency = frequencyInitial;
        balance = balanceInitial;

        stocks.clear();

        for(int i = 0; i < stockCount; ++i)
            addStock();
    }

    public void setPaused(final boolean paused)
    {
        if(this.paused != paused) {
            this.paused = paused;

            if(paused)
                update(Change.PAUSE);
            else
                update(Change.RESUME);
        }
    }

    public int getCash()
    {
        return balance;
    }

    public int getStocksWorth()
    {
        int total = 0;

        for(final Stock s : stocks)
            total += s.getPrice();

        return total;
    }

    private void addStock()
    {
        stocks.add(new Stock());
    }

    private void update(final Change change)
    {
        setChanged();
        notifyObservers(change);
    }
}
