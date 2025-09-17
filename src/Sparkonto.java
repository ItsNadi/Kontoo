public class Sparkonto extends Konto {

    public Sparkonto(String inhaber, String blz, String kontonummer) {
        super(inhaber, blz, kontonummer, "Sparkonto", 0, 0);
    }

    @Override
    public void monatlicheGebuehren() {
        System.out.println("Keine monatlichen Gebühren für das Sparkonto.");
    }
}
