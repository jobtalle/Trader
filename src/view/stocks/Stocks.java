package view.stocks;

import game.trader.Trader;

import javax.swing.*;
import java.awt.*;

public final class Stocks extends JPanel {
    private StocksGraph graph;

    public Stocks(final Trader trader)
    {
        setLayout(new GridLayout(1, 1));
        add(graph = new StocksGraph(trader));
    }
}
