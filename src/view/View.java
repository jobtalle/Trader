package view;

import game.trader.Trader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

final class View extends JFrame {
    private static final String TITLE = "Trader";
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    public static void main(String args[])
    {
        new View();
    }

    private View()
    {
        super(TITLE);

        createUI();
        addListeners();
    }

    private void setCentered()
    {
        Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation(
                (int)(resolution.getWidth() - getWidth()) / 2,
                (int)(resolution.getHeight() - getHeight()) / 2);
    }

    private void close()
    {
        System.exit(0);
    }

    private void addListeners()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    private void createUI()
    {
        final Trader trader = new Trader();

        setSize(WIDTH, HEIGHT);
        add(new Wrapper(trader));
        setCentered();

        trader.initialize();

        setVisible(true);
    }
}
