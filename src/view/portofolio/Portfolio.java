package view.portofolio;

import game.trader.Trader;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public final class Portfolio extends JPanel implements Observer {
    private static final String TITLE = "Portfolio";

    private GridBagConstraints constraints;
    private JPanel wrapper = new JPanel();

    public Portfolio(final Trader trader)
    {
        trader.addObserver(this);

        createUI();
    }

    private void createUI()
    {
        setBorder(BorderFactory.createTitledBorder(TITLE));

        wrapper.setLayout(new GridBagLayout());

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = constraints.gridy = 0;

        JScrollPane scrollPane = new JScrollPane(wrapper);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        setLayout(new GridLayout(1, 1));
        add(scrollPane);
    }

    @Override
    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }

    @Override
    public void update(Observable o, Object arg) {
        switch((Trader.Change) arg) {
            case ADD_STOCK:
                constraints.gridx++;
                wrapper.add(new StockView((Trader)o, ((Trader)o).getStocks().lastElement()), constraints);
                break;
        }
    }
}
