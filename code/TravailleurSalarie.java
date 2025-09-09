public class TravailleurSalarie extends Travailleur {
    private double salaireInitial;
    private List<Promotion> promotions;

    public TravailleurSalarie(int id, String nom, String prenom, String email,
                              String telephone, double salaireInitial) {
        super(id, nom, prenom, email, telephone);
        this.salaireInitial = salaireInitial;
        this.promotions = new ArrayList<>();
    }

    public void ajouterPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public double getSalaireActuel(LocalDate date) {
        double salaire = salaireInitial;

        for (Promotion promotion : promotions) {
            if (promotion.getDate().isBefore(date) || promotion.getDate().isEqual(date)) {
                salaire = promotion.getNouvelleValeur();
            } else {
                break;
            }
        }

        return salaire;
        }

        //Getters
    public double getSalaireInitial() {
        return salaireInitial;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }