import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        System.out.println("=== TESTS SIMPLES ASARED ===\n");

        //Données pour les tests
        // 1. Test pointageRouge
        TravailleurSalarie jean = new TravailleurSalarie(1, "Dupont", "Jean", "jean@hei.fr", "123", 3000);

        LocalDate aujourdhui = LocalDate.of(2024, 1, 15);
        Pointage pointage = new Pointage(aujourdhui);
        pointage.ajouterMission(new Mission(aujourdhui, TypeTravail.ENSEIGNEMENT, 0.5, "Cours", "bleu"));
        pointage.ajouterMission(new Mission(aujourdhui, TypeTravail.ADMINISTRATION, 0.5, "Réunion"));
        jean.ajouterPointage(pointage);

        boolean correct = jean.pointageRouge(aujourdhui);
        System.out.println("Pointage correct (0.5 + 0.5 = 1): " + correct);

        // 2. Test getDaysRed
        double jours = jean.getDaysRed(LocalDate.of(2024, 1, 15), LocalDate.of(2024, 1, 15));
        System.out.println("Jours prestés: " + jours);

        // Test calcul salaire prestataire
        TravailleurPrestataire marie = new TravailleurPrestataire(2, "Martin", "Marie", "marie@hei.fr", "456", 400);

        LocalDate demain = LocalDate.of(2024, 1, 16);
        Pointage pointage2 = new Pointage(demain);
        pointage2.ajouterMission(new Mission(demain, TypeTravail.RD, 1.0, "Recherche"));
        marie.ajouterPointage(pointage2);

        double salaire = marie.calculerSalaire(LocalDate.of(2024, 1, 16), LocalDate.of(2024, 1, 16));
        System.out.println("Salaire pour 1 jour à 400€/jour: " + salaire + "€");

        System.out.println("\n=== TESTS TERMINÉS ===");
    }
}

}

