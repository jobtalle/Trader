package view.portofolio;

import game.trader.Trader;

import javax.swing.*;

public final class Portfolio extends JPanel {
    private static final String TITLE = "Portfolio";

    public Portfolio(final Trader trader)
    {
        createUI();
    }

    private void createUI()
    {
        setBorder(BorderFactory.createTitledBorder(TITLE));
    }
}
