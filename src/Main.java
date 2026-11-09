
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IBANFrame frame = new IBANFrame();
            frame.setVisible(true);
        });
    }
}
