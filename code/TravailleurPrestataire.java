public class TravailleurPrestataire extends Travailleur {
    private double tjmInitial;
    private List<Promotion> promotions;

    public TravailleurPrestataire(int id, String nom, String prenom, String email,
                                  String telephone, double tjmInitial) {
        super(id, nom, prenom, email, telephone);
        this.tjmInitial = tjmInitial;
        this.promotions = new ArrayList<>();
    }

    public void ajouterPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public double getTjmActuel(LocalDate date) {
        double tjm = tjmInitial;

        for (Promotion promotion : promotions) {
            if (promotion.getDate().isBefore(date) || promotion.getDate().isEqual(date)) {
                tjm = promotion.getNouvelleValeur();
            } else {
                break;
            }
        }
        return tjm;
    }


    //Calculer salaire d'un prestataire donné en parametre entre deux dates
    public double calculerSalaire(LocalDate dateDebut, LocalDate dateFin) {
        double salaireTotal = 0.0;

        for (Map.Entry<LocalDate, Pointage> entry : pointages.entrySet()) {
            LocalDate datePointage = entry.getKey();
            Pointage pointage = entry.getValue();

            // Vérifier si la date est dans la plage
            if ((datePointage.isEqual(dateDebut) || datePointage.isAfter(dateDebut)) &&
                    (datePointage.isEqual(dateFin) || datePointage.isBefore(dateFin))) {

                double quotaJour = 0.0;

                // Calculer le total pour ce jour, excluant les absences non payées
                for (Mission mission : pointage.getMissions()) {
                    TypeTravail type = mission.getTypeTravail();
                    if (type != TypeTravail.ABS_NON_PAYÉE) {
                        quotaJour += mission.getQuotaTemps();
                    }
                }

                double tjm = getTjmActuel(datePointage);
                salaireTotal += quotaJour * tjm;
            }
        }

        return salaireTotal;
    }
}