import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

@SuppressWarnings({"WeakerAccess", "unused"})
public class ColorPanel extends JPanel {

    public static final double  FADE_SPEED = 0.0002;

    public static final boolean   SCROLL_COLOR = false;
    public static final Direction SCROLL_DIRECTION = Direction.LEFT;

    private int width;
    private int height;

    // numbers between 0 and 1
    private double hue;
    private double saturation;
    private double brightness;

    private Color color;

    public ColorPanel() {
        this(250, 250);
    }

    public ColorPanel(int size) {
        this(size, size);
    }

    public ColorPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));

        this.width = width;
        this.height = height;

        this.hue = 0.0;
        this.saturation = 1.0;
        this.brightness = 1.0;
        setColor();
    }

    public void tick() throws InterruptedException {
        tick(1);
    }

    public void tick(int millis) throws InterruptedException {
        Thread.sleep(millis);
        repaint();
    }

    private void setColor() {
        this.color = Color.getHSBColor((float) hue, (float) saturation, (float) brightness);
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

        setColor();
    }

    private void drawColor(Graphics g) {
        Color before = g.getColor();

        // TODO: implement this
        if (SCROLL_COLOR) {

            switch (SCROLL_DIRECTION) {
                case UP:
                    break;
                case DOWN:
                    break;
                case LEFT:
                    break;
                case RIGHT:
                    break;
                default:
                    break;
            }

        } else {
            g.setColor(color);
            g.fillRect(0, 0, width, height);
        }

        g.setColor(before);
    }

}
