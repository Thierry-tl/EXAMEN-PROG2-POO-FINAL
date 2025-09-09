public class Promotion {
    private LocalDate date;
    private double nouvelleValeur;
    private String raison;

    public Promotion(LocalDate date, double nouvelleValeur, String raison) {
        this.date = date;
        this.nouvelleValeur = nouvelleValeur;
        this.raison = raison;
    }

    public LocalDate getDate() { return date; }
    public double getNouvelleValeur() { return nouvelleValeur; }
    public String getRaison() { return raison; }
}