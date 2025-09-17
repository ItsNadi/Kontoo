public class Girokonto extends Konto {

    public Girokonto(String inhaber, String blz, String kontonummer, double ueberziehungsrahmen, double gebuehren) {
        super(inhaber, blz, kontonummer, "Girokonto", ueberziehungsrahmen, gebuehren);
    }

    @Override
    public void monatlicheGebuehren() {
        kontostand -= gebuehren;
        System.out.println("Girokonto-Gebühren von " + gebuehren + "€ abgezogen. Neuer Kontostand: " + kontostand + "€");
    }
}
