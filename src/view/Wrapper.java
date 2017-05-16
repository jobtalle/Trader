package view;

import game.trader.Trader;
import view.balance.Balance;
import view.portofolio.Portfolio;
import view.stocks.Stocks;

import javax.swing.*;
import java.awt.*;

final class Wrapper extends JPanel {
    Wrapper(final Trader trader)
    {
        createUI(trader);
    }

    private void createUI(final Trader trader)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = constraints.gridy = 0;
        constraints.gridwidth = constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        add(new Stocks(trader), constraints);

        constraints.weighty = 0;
        constraints.gridy = 1;

        add(new Portfolio(trader), constraints);

        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 2;

        add(new Balance(trader), constraints);
    }
}
