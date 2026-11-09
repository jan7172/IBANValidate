
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IBANFrame extends JFrame {

    private JTextField ibanFeld;
    private JLabel ergebnisLabel;

    public IBANFrame() {

        setTitle("IBAN Validator");
        setSize(480, 290);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(25, 35, 25, 35));
        panel.setBackground(Color.WHITE);

        JLabel titel = new JLabel("IBAN-Prüfung");
        titel.setFont(new Font("Arial", Font.BOLD, 24));
        titel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel beschreibung = new JLabel(
                "Gib eine IBAN ein, um sie zu überprüfen."
        );
        beschreibung.setAlignmentX(Component.CENTER_ALIGNMENT);

        ibanFeld = new JTextField();
        ibanFeld.setFont(new Font("Arial", Font.PLAIN, 16));
        ibanFeld.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 40)
        );

        JButton pruefenButton = new JButton("IBAN prüfen");
        pruefenButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pruefenButton.setFocusPainted(false);

        ergebnisLabel = new JLabel(" ");
        ergebnisLabel.setFont(
                new Font("Arial", Font.BOLD, 15)
        );
        ergebnisLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Button und Enter-Taste
        pruefenButton.addActionListener(e -> pruefeIBAN());
        ibanFeld.addActionListener(e -> pruefeIBAN());

        panel.add(titel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(beschreibung);
        panel.add(Box.createVerticalStrut(25));
        panel.add(ibanFeld);
        panel.add(Box.createVerticalStrut(15));
        panel.add(pruefenButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(ergebnisLabel);

        add(panel);
    }

    private void pruefeIBAN() {

        String iban = ibanFeld.getText().trim();

        if (iban.isEmpty()) {
            ergebnisLabel.setText("Bitte eine IBAN eingeben.");
            ergebnisLabel.setForeground(Color.ORANGE);
            return;
        }

        if (IBANValidator.istGueltig(iban)) {
            ergebnisLabel.setText("Die IBAN ist gültig!");
            ergebnisLabel.setForeground(
                    new Color(0, 140, 60)
            );
        } else {
            ergebnisLabel.setText("Die IBAN ist ungültig!");
            ergebnisLabel.setForeground(Color.RED);
        }
    }
}
