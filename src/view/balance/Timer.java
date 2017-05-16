package view.balance;

import game.trader.Trader;
import view.utils.graphics.StringRenderUtils;
import view.utils.ui.UIDefaults;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

final class Timer extends JPanel implements Observer {
    private static final int DIAMETER = 180;
    private static final int ANGLE_ANCHOR = 90;
    private static final int PADDING = 4;
    private static final int INNER_RADIUS = 50;
    private static final Color COLOR_BACKGROUND = Color.GRAY;
    private static final Color COLOR_TIME_FLOWING = Color.ORANGE;
    private static final Color COLOR_TIME_PAUSED = Color.DARK_GRAY;
    private static final Color COLOR_BORDER = Color.DARK_GRAY;
    private static final Color COLOR_TEXT_FLOWING = Color.BLACK;
    private static final Color COLOR_TEXT_PAUSED = Color.WHITE;
    private static final BasicStroke STROKE_BORDER = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

    private float timePassed = 0;
    private boolean paused = true;
    private Trader trader;

    Timer(final Trader trader)
    {
        this.trader = trader;
        trader.addObserver(this);

        createUI();
    }

    public void start()
    {
        trader.setPaused(false);
    }

    public void stop()
    {
        trader.setPaused(true);
    }

    public boolean isPaused()
    {
        return paused;
    }

    @Override
    public void update(Observable o, Object arg) {
        final Trader trader = (Trader) o;

        switch((Trader.Change)arg) {
            case TICK:
            case INITIALIZED:
                timePassed = (float)trader.getTick() / trader.getMaxTicks();

                updateUI();
                break;
            case PAUSE:
                paused = true;

                updateUI();
                break;
            case RESUME:
                paused = false;

                updateUI();
                break;
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));

        g2d.setColor(COLOR_BACKGROUND);
        g2d.fillOval(PADDING, PADDING, getWidth() - PADDING * 2, getHeight()- PADDING * 2);

        if(paused)
            g2d.setColor(COLOR_TIME_PAUSED);
        else
            g2d.setColor(COLOR_TIME_FLOWING);
        g2d.fillArc(PADDING, PADDING, getWidth()- PADDING * 2, getHeight()- PADDING * 2,
                ANGLE_ANCHOR, (int)(timePassed * -360));
        g2d.fillOval(
                getWidth() / 2 - INNER_RADIUS,
                getHeight() / 2 - INNER_RADIUS,
                INNER_RADIUS * 2,
                INNER_RADIUS * 2);

        g2d.setFont(UIDefaults.FONT_BIG);
        if(paused)
            g2d.setColor(COLOR_TEXT_PAUSED);
        else
            g2d.setColor(COLOR_TEXT_FLOWING);
        StringRenderUtils.drawCenteredString(g2d, getCenterText(), getVisibleRect());

        g2d.setStroke(STROKE_BORDER);
        g2d.setColor(COLOR_BORDER);
        g2d.drawOval(PADDING, PADDING, getWidth()- PADDING * 2, getHeight()- PADDING * 2);
    }

    private String getCenterText()
    {
        return Integer.toString((int) (timePassed * 100)) + '%';
    }

    private void createUI()
    {
        setPreferredSize(new Dimension(DIAMETER, DIAMETER));
        setMinimumSize(new Dimension(DIAMETER, DIAMETER));
        setMaximumSize(new Dimension(DIAMETER, DIAMETER));
    }
}
