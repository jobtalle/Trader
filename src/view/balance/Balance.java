package view.balance;

import game.trader.Trader;

import javax.swing.*;

public final class Balance extends JPanel {
    private static final String TITLE = "Balance";

    public Balance(final Trader trader)
    {
        createUI();
    }

    private void createUI()
    {
        setBorder(BorderFactory.createTitledBorder(TITLE));
    }
}
