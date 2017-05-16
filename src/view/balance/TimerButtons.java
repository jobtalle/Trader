package view.balance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

final class TimerButtons extends JPanel {
    private static final String TITLE_START = "Start";
    private static final String TITLE_STOP = "Stop";

    private JButton buttonStart = new JButton(TITLE_START);
    private JButton buttonStop = new JButton(TITLE_STOP);

    TimerButtons(final Timer timer)
    {
        createUI(timer);
    }

    private void createUI(final Timer timer)
    {
        setLayout(new GridLayout(1, 2));

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
