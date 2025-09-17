public class Kreditkonto extends Konto {

    public Kreditkonto(String inhaber, String blz, String kontonummer, double ueberziehungsrahmen, double gebuehren) {
        super(inhaber, blz, kontonummer, "Kreditkonto", ueberziehungsrahmen, gebuehren);
    }

    @Override
    public void monatlicheGebuehren() {
        kontostand -= gebuehren;
        System.out.println("Kreditkonto-Gebühren von " + gebuehren + "€ abgezogen. Neuer Kontostand: " + kontostand + "€");
    }
}
