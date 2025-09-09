public class Pointage {
    private LocalDate date;
    private List<Mission> missions;

    public Pointage(LocalDate date) {
        this.date = date;
        this.missions = new ArrayList<>();
    }

    public void ajouterMission(Mission mission) {
        missions.add(mission);
    }

    // Getters
    public LocalDate getDate() { return date; }
    public List<Mission> getMissions() { return missions; }


    //Methode de verification pointage
    public boolean pointage(LocalDate date){
        Pointage pointage = pointages.get(date);
        if (pointage == null) {
            return false;
        }

        double totalQuota = 0.0;

        for (Mission mission : pointage.getMissions()) {
            double quota = mission.getQuotaTemps();

            if (quota <= 0 || quota > 1) {
                throw new IllegalArgumentException("Le quota de temps doit être entre 0 (exclu) et 1 (inclus). Quota actuel: " + quota);
            }

            totalQuota += quota;
        }

        // Vérification si le total égale 1
        return Math.abs(totalQuota - 1.0);
    }



    //Methode pour calculer le nombre total de jours presté entre deux dates
    public double dateTotal(LocalDate dateDebut, LocalDate dateFin) {
        double totalJours = 0.0;

        for (Map.Entry<LocalDate, Pointage> entry : pointages.entrySet()) {
            LocalDate datePointage = entry.getKey();
            Pointage pointage = entry.getValue();

            if ((datePointage.isEqual(dateDebut) || datePointage.isAfter(dateDebut)) &&
                    (datePointage.isEqual(dateFin) || datePointage.isBefore(dateFin))) {

                double quotaJour = 0.0;

                for (Mission mission : pointage.getMissions()) {
                    TypeTravail type = mission.getTypeTravail();
                    if (type != TypeTravail.ABS_PAYÉE && type != TypeTravail.ABS_NON_PAYÉE) {
                        quotaJour += mission.getQuotaTemps();
                    }
                }

                totalJours += quotaJour;
            }
        }

        return totalJours;
    }
}