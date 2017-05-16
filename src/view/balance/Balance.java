package view.balance;

import game.trader.Trader;

import javax.swing.*;
import java.awt.*;

public final class Balance extends JPanel {
    private static final String TITLE = "Balance";

    public Balance(final Trader trader)
    {
        createUI(trader);
    }

    private void createUI(final Trader trader)
    {
        final Timer timer = new Timer(trader);
        final TimerButtons timerButtons = new TimerButtons(timer);

        setBorder(BorderFactory.createTitledBorder(TITLE));

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.NORTH;

        add(new Money(trader), constraints);

        constraints.weighty = 1;
        constraints.gridy = 1;

        add(new JPanel(), constraints);

        constraints.gridy = 2;
        constraints.weighty = 0;

        JPanel timerWrapper = new JPanel();
        timerWrapper.add(timer);

        add(timerWrapper, constraints);

        constraints.gridy = 3;

        add(timerButtons, constraints);
    }
}
