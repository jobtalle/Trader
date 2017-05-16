package view;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class View extends JFrame {
    private static final String TITLE = "Trader";
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    public static void main(String args[])
    {
        new View();
    }

    public View()
    {
        super(TITLE);

        createUI();
        addListeners();
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
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }
}
