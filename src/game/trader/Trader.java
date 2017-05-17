package game.trader;

import game.stock.Stock;

import java.awt.*;
import java.util.*;

public final class Trader extends Observable {
    private static final int DEFAULT_STOCK_COUNT = 6;
    private static final int DEFAULT_FREQUENCY = 500;
    private static final long DEFAULT_BALANCE = 100000L;
    private static final int DEFAULT_MAX_TICKS = 240;
    private static final float COLOR_STOCK_SATURATION = 0.6f;
    private static final float COLOR_STOCK_BRIGHTNESS = 0.9f;

    private boolean paused;
    private int tick;
    private int maxTicks, maxTicksInitial;
    private int stockCount, stockCountInitial;
    private int frequency, frequencyInitial;
    private long balance, balanceInitial;

    private Timer ticker;
    private Vector<Stock> stocks = new Vector<>();

    public enum Change {
        INITIALIZED,
        PAUSE,
        RESUME,
        TICK,
        ADD_STOCK,
        BUY,
        SELL
    }

    public Trader()
    {
        this(DEFAULT_STOCK_COUNT, DEFAULT_FREQUENCY, DEFAULT_BALANCE, DEFAULT_MAX_TICKS);
    }

    public Trader(final int stockCount, final int frequency, final long balance, final int maxTicks)
    {
        stockCountInitial = stockCount;
        frequencyInitial = frequency;
        balanceInitial = balance;
        maxTicksInitial = maxTicks;

        setup();
    }

    public void initialize()
    {
        ticker = new Timer();
        ticker.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tryTick();
            }
        }, 0, frequency);

        createStocks();

        update(Change.INITIALIZED);
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

    public void buy(final Stock stock)
    {
        if(balance >= stock.getPrice()) {
            balance -= stock.getPrice();
            stock.buy();

            update(Change.BUY);
        }
    }

    public void sell(final Stock stock)
    {
        if(stock.getOwned() > 0) {
            balance += stock.getPrice();
            stock.sell();

            update(Change.SELL);
        }
    }

    public Vector<Stock> getStocks()
    {
        return stocks;
    }

    public long getCash()
    {
        return balance;
    }

    public long getStocksWorth()
    {
        long total = 0;

        for(final Stock s : stocks)
            total += s.getPrice() * s.getOwned();

        return total;
    }

    public int getTick()
    {
        return tick;
    }

    public int getMaxTicks()
    {
        return maxTicks;
    }

    private void tryTick()
    {
        if(!paused) {
            if(++tick == maxTicks) {
                end();
            } else {
                update(Change.TICK);

                for (final Stock s : stocks)
                    s.tick();
            }
        }
    }

    private void end()
    {
        ticker.cancel();
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
    }

    private void createStocks()
    {
        Stack<Color> colors = getVaryingColorStack(stockCount, COLOR_STOCK_SATURATION, COLOR_STOCK_BRIGHTNESS);

        for(int i = 0; i < stockCount; ++i)
            addStock(colors.pop());
    }

    private Stack<Color> getVaryingColorStack(final int count, final float saturation, final float brightness)
    {
        Stack<Color> stack = new Stack<>();
        final float offset = (float) Math.random();

        for(int i = 0; i < count; ++i)
            stack.add(Color.getHSBColor(((float)i / count + offset) % 1, saturation, brightness));

        Collections.shuffle(stack);

        return stack;
    }

    private void addStock(final Color color)
    {
        stocks.add(new Stock(color, "Stock", stocks.size() + 1));

        update(Change.ADD_STOCK);
    }

    private void update(final Change change)
    {
        setChanged();
        notifyObservers(change);
    }
}
