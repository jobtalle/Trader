package view.portofolio;

import game.stock.Stock;
import view.utils.ui.MoneyFormatter;

import javax.swing.*;
import java.awt.*;

final class StockViewPrice extends JPanel {
    private static final Color COLOR_FIELD_BACKGROUND = Color.DARK_GRAY;
    private static final Color COLOR_FIELD_TEXT = Color.WHITE;
    private static final Color COLOR_FIELD_TEXT_UP = Color.GREEN;
    private static final Color COLOR_FIELD_TEXT_DOWN = Color.ORANGE;
    private static final int COLUMNS = 5;

    private JTextField fieldPrice = new JTextField();
    private JTextField fieldDifference = new JTextField();

    StockViewPrice()
    {
        createUI();
    }

    public void setPrice(final Stock stock)
    {
        fieldPrice.setText(MoneyFormatter.intToMoney(stock.getPrice()));

        final long diff = stock.getDifference();

        if(diff >= 0)
            fieldDifference.setForeground(COLOR_FIELD_TEXT_UP);
        else
            fieldDifference.setForeground(COLOR_FIELD_TEXT_DOWN);
        fieldDifference.setText(MoneyFormatter.intToMoney(diff));
    }

    private void createUI()
    {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        fieldPrice.setColumns(COLUMNS);
        fieldPrice.setEditable(false);
        fieldPrice.setBackground(COLOR_FIELD_BACKGROUND);
        fieldPrice.setForeground(COLOR_FIELD_TEXT);
        fieldDifference.setColumns(COLUMNS);
        fieldDifference.setEditable(false);
        fieldDifference.setBackground(COLOR_FIELD_BACKGROUND);
        fieldDifference.setForeground(COLOR_FIELD_TEXT);

        add(fieldPrice);
        add(fieldDifference);
    }
}
