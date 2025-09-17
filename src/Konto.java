public abstract class Konto {
    protected String inhaber;
    protected String blz;
    protected String kontonummer;
    protected double ueberziehungsrahmen;
    protected double gebuehren;
    protected double kontostand;
    protected String kontoart;

    public Konto(String inhaber, String blz, String kontonummer, String kontoart,
                 double ueberziehungsrahmen, double gebuehren) {
        this.inhaber = inhaber;
        this.blz = blz;
        this.kontonummer = kontonummer;
        this.kontoart = kontoart;
        this.ueberziehungsrahmen = ueberziehungsrahmen;
        this.gebuehren = gebuehren;
        this.kontostand = 0.0;
    }

    public void einzahlen(double betrag) {
        if (betrag > 0) {
            kontostand += betrag;
            System.out.println(betrag + "€ eingezahlt. Neuer Kontostand: " + kontostand + "€");
        } else {
            System.out.println("Ungültiger Betrag!");
        }
    }

    public void abheben(double betrag) {
        if (kontostand - betrag >= -ueberziehungsrahmen) {
            kontostand -= betrag;
            System.out.println(betrag + "€ abgehoben. Neuer Kontostand: " + kontostand + "€");
        } else {
            System.out.println("Abhebung nicht möglich – Überziehungsrahmen überschritten!");
        }
    }

    public void kontoauszug() {
        System.out.println("--- Kontoauszug ---");
        System.out.println("Inhaber: " + inhaber);
        System.out.println("Kontonummer: " + kontonummer);
        System.out.println("Kontoart: " + kontoart);
        System.out.println("Kontostand: " + kontostand + "€");
        System.out.println("------------------");
    }

    public void ueberweisen(Konto zielkonto, double betrag) {
        if (kontostand - betrag >= -ueberziehungsrahmen) {
            abheben(betrag);
            zielkonto.einzahlen(betrag);
            System.out.println("Überweisung von " + betrag + "€ an " + zielkonto.inhaber + " erfolgreich.");
        } else {
            System.out.println("Überweisung nicht möglich – zu wenig Guthaben.");
        }
    }

    // Abstrakte Methode: jede Unterklasse muss sie implementieren
    public abstract void monatlicheGebuehren();
}
