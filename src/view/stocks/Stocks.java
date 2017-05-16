package view.stocks;

import game.trader.Trader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public final class Stocks extends JPanel implements Observer {
    private StocksGraph graph;

    public Stocks(final Trader trader)
    {
        trader.addObserver(this);

        setLayout(new GridLayout(1, 1));
        add(graph = new StocksGraph());
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
