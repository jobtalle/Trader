package view.balance;

import game.trader.Trader;
import view.utils.ui.*;
import view.utils.ui.UIDefaults;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

final class Money extends JPanel implements Observer {
    private static final String TITLE_CASH = "Cash:";
    private static final String TITLE_INVESTED = "Invested:";
    private static final String TITLE_TOTAL = "Total:";

    private JTextField fieldCash = new JTextField();
    private JTextField fieldInvested = new JTextField();
    private JTextField fieldTotal = new JTextField();

    Money(final Trader trader)
    {
        trader.addObserver(this);

        createUI();
    }

    @Override
    public void update(Observable o, Object arg) {
        switch((Trader.Change) arg) {
            case TICK:
            case INITIALIZED:
                updateText((Trader) o);
                break;
        }
    }

    private void createUI()
    {
        setLayout(new SpringLayout());

        fieldCash.setEditable(false);
        fieldInvested.setEditable(false);
        fieldTotal.setEditable(false);

        add(new JLabel(TITLE_CASH, JLabel.TRAILING));
        add(fieldCash);

        add(new JLabel(TITLE_INVESTED, JLabel.TRAILING));
        add(fieldInvested);

        add(new JLabel(TITLE_TOTAL, JLabel.TRAILING));
        add(fieldTotal);

        for(Component c : getComponents())
            c.setFont(UIDefaults.FONT_MEDIUM);

        SpringUtilities.makeCompactGrid(this, getComponentCount() / 2, 4, 4, 4, 4);
    }

    private void updateText(final Trader trader)
    {
        final long cash = trader.getCash();
        final int invested = trader.getStocksWorth();

        fieldCash.setText(MoneyFormatter.intToMoney(cash));
        fieldInvested.setText(MoneyFormatter.intToMoney(invested));
        fieldTotal.setText(MoneyFormatter.intToMoney(cash + invested));
    }
}
