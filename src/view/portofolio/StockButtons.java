package view.portofolio;

import game.stock.Stock;
import game.trader.Trader;

import javax.swing.*;
import java.awt.event.ActionEvent;

final class StockButtons extends JPanel {
    private static final String TITLE_BUY = "Buy";
    private static final String TITLE_SELL = "Sell";

    private JButton buttonBuy = new JButton(TITLE_BUY);
    private JButton buttonSell = new JButton(TITLE_SELL);

    StockButtons(final Trader trader, final Stock stock)
    {
        createUI(trader, stock);
    }

    private void createUI(final Trader trader, final Stock stock)
    {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        buttonBuy.setFocusable(false);
        buttonSell.setFocusable(false);

        buttonBuy.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trader.buy(stock);
            }
        });

        buttonSell.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trader.sell(stock);
            }
        });

        add(buttonBuy);
        add(buttonSell);
    }
}
