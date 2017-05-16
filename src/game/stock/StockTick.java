package game.stock;

public final class StockTick {
    private long price;
    private int bought = 0;

    public StockTick(final long price)
    {
        this.price = price;
    }

    public void buy()
    {
        ++bought;
    }

    public void sell()
    {
        --bought;
    }

    public int getBought()
    {
        return bought;
    }

    public long getPrice()
    {
        return price;
    }
}
