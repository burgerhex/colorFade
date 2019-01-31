import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


@SuppressWarnings({"WeakerAccess", "unused"})
public class ColorPanel extends JPanel {

    public static final double  FADE_SPEED = 0.0005;

    public static final boolean   SCROLL_COLOR = true;
    public static final Direction SCROLL_DIRECTION = Direction.UP;

    private int width;
    private int height;

    // numbers between 0 and 1
    private double hue;
    private double saturation;
    private double brightness;

    private Color color;

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public ColorPanel() {
        this(250, 250);
    }

    public ColorPanel(int size) {
        this(size, size);
    }

    public ColorPanel(int width, int height) {
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        setSize(size);

        this.width = width;
        this.height = height;

        this.hue = 0.0;
        this.saturation = 1.0;
        this.brightness = 1.0;

        setColor(hue, saturation, brightness);
        setBackground(color);
    }

    public void tick() throws InterruptedException {
        tick(1);
    }

    public void tick(int millis) throws InterruptedException {
        Thread.sleep(millis);
        repaint();
    }

    private Color getHSB(double h, double s, double b) {
        return Color.getHSBColor((float) h, (float) s, (float) b);
    }

    private void setColor(double hue, double saturation, double brightness) {
        this.color = getHSB(hue, saturation, brightness);
    }

    private Color getColorBefore(int turns) {
        double oldHue = hue - turns * FADE_SPEED;
        while (oldHue < 0) oldHue += 1;
        return getHSB(oldHue, saturation, brightness);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateColor();

        drawColor(g);
    }

    private void updateColor() {
        hue += FADE_SPEED;
        hue %= 1;

        setColor(hue, saturation, brightness);
    }

    private void drawColor(Graphics g) {
        Color before = g.getColor();

        // TODO: this is really bad
        if (SCROLL_COLOR) {

            switch (SCROLL_DIRECTION) {
                case UP:
                    for (int i = 0; i < height; i++) {
                        Color color = getColorBefore(height - 1 - i);
                        g.setColor(color);
                        g.fillRect(0, i, width, 1);
                    }

                    break;
                case DOWN:
                    for (int i = 0; i < height; i++) {
                        Color color = getColorBefore(i);
                        g.setColor(color);
                        g.fillRect(0, i, width, 1);
                    }

                    break;
                case LEFT:
                    for (int i = 0; i < width; i++) {
                        Color color = getColorBefore(width - 1 - i);
                        g.setColor(color);
                        g.fillRect(i, 0, 1, height);
                    }

                    break;
                case RIGHT:
                    for (int i = 0; i < width; i++) {
                        Color color = getColorBefore(i);
                        g.setColor(color);
                        g.fillRect(i, 0, 1, height);
                    }

                    break;
                default:
                    break;
            }

        } else {
            g.fillRect(0, 0, width, height);
        }

        g.setColor(before);
    }

}
