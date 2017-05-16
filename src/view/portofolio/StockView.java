package view.portofolio;

import game.stock.Stock;
import game.trader.Trader;
import view.utils.ui.UIDefaults;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

final class StockView extends JPanel implements Observer {
    private Stock stock;

    private JLabel title;
    private JLabel index;
    private StockViewPrice price = new StockViewPrice();
    private StockButtons buttons;

    StockView(final Trader trader, final Stock stock)
    {
        trader.addObserver(this);

        this.stock = stock;

        createUI(trader);
    }

    @Override
    public void update(Observable o, Object arg) {
        switch((Trader.Change) arg) {
            case TICK:
            case INITIALIZED:
                price.setPrice(stock);
                break;
        }
    }

    private void createUI(final Trader trader)
    {
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setBackground(stock.getColor());

        title = new JLabel(stock.getTitle());
        title.setFont(UIDefaults.FONT_BIG);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        index = new JLabel('#' + Integer.toString(stock.getIndex()));
        index.setFont(UIDefaults.FONT_MEDIUM);
        index.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons = new StockButtons(trader, stock);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(title);
        add(index);
        add(price);
        add(buttons);
    }
}
