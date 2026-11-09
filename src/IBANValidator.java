
import java.math.BigInteger;
import java.util.Locale;
import java.util.Map;

public class IBANValidator {

    private static final Map<String, Integer> LAENGEN =
            Map.ofEntries(
                    Map.entry("DE", 22),
                    Map.entry("AT", 20),
                    Map.entry("CH", 21),
                    Map.entry("FR", 27),
                    Map.entry("GB", 22),
                    Map.entry("NL", 18),
                    Map.entry("BE", 16),
                    Map.entry("ES", 24),
                    Map.entry("IT", 27),
                    Map.entry("PL", 28),
                    Map.entry("LU", 20)
            );

    public static boolean istGueltig(String iban) {

        if (iban == null) {
            return false;
        }

        iban = iban.replaceAll("\\s+", "")
                .toUpperCase(Locale.ROOT);

        // Grundaufbau prüfen
        if (!iban.matches("[A-Z]{2}[0-9]{2}[A-Z0-9]+")) {
            return false;
        }

        // Länderkennung und Länge prüfen
        String land = iban.substring(0, 2);
        Integer laenge = LAENGEN.get(land);

        if (laenge == null || iban.length() != laenge) {
            return false;
        }

        // Erste vier Zeichen ans Ende verschieben
        String verschoben = iban.substring(4)
                + iban.substring(0, 4);

        // Buchstaben in Zahlen umwandeln
        StringBuilder nummer = new StringBuilder();

        for (char zeichen : verschoben.toCharArray()) {

            if (Character.isLetter(zeichen)) {
                nummer.append(zeichen - 'A' + 10);
            } else {
                nummer.append(zeichen);
            }
        }

        // MOD-97-Prüfung
        BigInteger zahl = new BigInteger(nummer.toString());

        return zahl.mod(BigInteger.valueOf(97))
                .intValue() == 1;
    }
}
