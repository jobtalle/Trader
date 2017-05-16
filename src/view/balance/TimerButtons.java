package view.balance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

final class TimerButtons extends JPanel {
    private static final String TITLE_START = "Start";
    private static final String TITLE_STOP = "Stop";
    private static final int KEY_TOGGLE_PAUSE = KeyEvent.VK_SPACE;

    private JButton buttonStart = new JButton(TITLE_START);
    private JButton buttonStop = new JButton(TITLE_STOP);

    TimerButtons(final Timer timer)
    {
        createUI(timer);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if(e.getID() == KeyEvent.KEY_PRESSED)
                    if(e.getKeyCode() == KEY_TOGGLE_PAUSE)
                        if(timer.isPaused())
                            buttonStart.doClick();
                        else
                            buttonStop.doClick();

                return false;
            }
        });
    }

    private void createUI(final Timer timer)
    {
        setLayout(new GridLayout(1, 2));

        buttonStart.setFocusable(false);
        buttonStop.setFocusable(false);

        add(buttonStart);
        add(buttonStop);

        buttonStart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        buttonStop.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
    }
}
