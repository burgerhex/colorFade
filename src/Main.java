import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("InfiniteLoopStatement")
public class Main {

    private static JFrame frame = new JFrame();
    private static ColorPanel panel = new ColorPanel();

    public static void main(String[] args) throws InterruptedException {

        SwingUtilities.invokeLater(Main::createAndShowGUI);

        while (true) {
            panel.tick();
        }

    }

    private static void createAndShowGUI() {
        frame.setTitle("Color Fade");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(panel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
