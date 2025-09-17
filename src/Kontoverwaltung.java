import java.util.ArrayList;
import java.util.Scanner;

public class Kontoverwaltung {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Konto> konten = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Kontoverwaltung ---");
            System.out.println("1. Konto anlegen");
            System.out.println("2. Konto auflösen");
            System.out.println("3. Einzahlen");
            System.out.println("4. Abheben");
            System.out.println("5. Kontoauszug");
            System.out.println("6. Überweisen");
            System.out.println("7. Konto ansehen");
            System.out.println("8. Beenden");
            System.out.print("Auswahl: ");
            int wahl = sc.nextInt();
            sc.nextLine();

            switch (wahl) {
                case 1 -> { // konto anlegen
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("BLZ: ");
                    String blz = sc.nextLine();
                    System.out.print("Kontonummer: ");
                    String kn = sc.nextLine();
                    System.out.print("Kontoart (giro/spar/kredit): ");
                    String art = sc.nextLine().toLowerCase();

                    Konto konto;
                    if (art.equals("giro")) {
                        konto = new Girokonto(name, blz, kn, 500, 5);
                    } else if (art.equals("spar")) {
                        konto = new Sparkonto(name, blz, kn);
                    } else if (art.equals("kredit")) {
                        konto = new Kreditkonto(name, blz, kn, 1000, 10);
                    } else {
                        System.out.println("Ungültige Kontoart!");
                        break;
                    }
                    konten.add(konto);
                    System.out.println("Konto erfolgreich angelegt!");
                }
                case 2 -> { // konto auflösen
                    System.out.print("Kontonummer: ");
                    String kn = sc.nextLine();
                    konten.removeIf(k -> k.kontonummer.equals(kn));
                    System.out.println("Konto aufgelöst (falls vorhanden).");
                }
                case 3 -> { // einzahlen
                    System.out.print("Kontonummer: ");
                    String kn = sc.nextLine();
                    System.out.print("Betrag: ");
                    double betrag = sc.nextDouble();
                    sc.nextLine();
                    for (Konto k : konten) {
                        if (k.kontonummer.equals(kn)) k.einzahlen(betrag);
                    }
                }
                case 4 -> { // abheben
                    System.out.print("Kontonummer: ");
                    String kn = sc.nextLine();
                    System.out.print("Betrag: ");
                    double betrag = sc.nextDouble();
                    sc.nextLine();
                    for (Konto k : konten) {
                        if (k.kontonummer.equals(kn)) k.abheben(betrag);
                    }
                }
                case 5 -> { // kontoauszug
                    System.out.print("Kontonummer: ");
                    String kn = sc.nextLine();
                    for (Konto k : konten) {
                        if (k.kontonummer.equals(kn)) k.kontoauszug();
                    }
                }
                case 6 -> { // überweisen
                    System.out.print("Ihre Kontonummer: ");
                    String von = sc.nextLine();
                    System.out.print("Zielkontonummer: ");
                    String zu = sc.nextLine();
                    System.out.print("Betrag: ");
                    double betrag = sc.nextDouble();
                    sc.nextLine();

                    Konto quelle = null, ziel = null;
                    for (Konto k : konten) {
                        if (k.kontonummer.equals(von)) quelle = k;
                        if (k.kontonummer.equals(zu)) ziel = k;
                    }
                    if (quelle != null && ziel != null) {
                        quelle.ueberweisen(ziel, betrag);
                    } else {
                        System.out.println("Kontonummer(n) nicht gefunden!");
                    }
                }
                case 7 -> { // konto ansehen
                    if (konten.isEmpty()) {
                        System.out.println("Keine Konten vorhanden.");
                        break;
                    }

                    // Konten nach Typ sortieren
                    ArrayList<Konto> giro = new ArrayList<>();
                    ArrayList<Konto> spar = new ArrayList<>();
                    ArrayList<Konto> kredit = new ArrayList<>();
                    for (Konto k : konten) {
                        switch (k.kontoart.toLowerCase()) {
                            case "girokonto" -> giro.add(k);
                            case "sparkonto" -> spar.add(k);
                            case "kreditkonto" -> kredit.add(k);
                        }
                    }

                    System.out.println("Deine Konten:");
                    int index = 1;
                    if (!giro.isEmpty()) {
                        System.out.println("\nGirokonten:");
                        for (Konto k : giro) System.out.println(index++ + ". " + k.kontonummer + " (" + k.inhaber + ")");
                    }
                    if (!spar.isEmpty()) {
                        System.out.println("\nSparkonten:");
                        for (Konto k : spar) System.out.println(index++ + ". " + k.kontonummer + " (" + k.inhaber + ")");
                    }
                    if (!kredit.isEmpty()) {
                        System.out.println("\nKreditkonten:");
                        for (Konto k : kredit) System.out.println(index++ + ". " + k.kontonummer + " (" + k.inhaber + ")");
                    }

                    // auswahl des kontos
                    System.out.print("Welches Konto willst du ansehen?");
                    int auswahl = sc.nextInt();
                    sc.nextLine();

                    if (auswahl < 1 || auswahl >= index) {
                        System.out.println("Ungültige Auswahl!");
                    } else {
                        // konto finden
                        index = 1;
                        Konto ausgewaehlt = null;
                        for (Konto k : konten) {
                            if (index == auswahl) {
                                ausgewaehlt = k;
                                break;
                            }
                            index++;
                        }
                        if (ausgewaehlt != null) {
                            ausgewaehlt.kontoauszug();
                        }
                    }
                }
                case 8 -> running = false; // Beenden
                default -> System.out.println("Ungültige Auswahl!");
            }
        }
        sc.close();
    }
}
