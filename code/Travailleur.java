import java.time.LocalDate;
import java.util.*;

enum TypeTravail {
    ENSEIGNEMENT, ADMINISTRATION, COMMUNICATION, RD, ABS_PAYÉE, ABS_NON_PAYÉE
}

abstract class Travailleur {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String telephone;
    protected Map<LocalDate, Pointage> pointages;

    public Travailleur(int id, String nom, String prenom, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.pointages = new HashMap<>();
    }

    public void ajouterPointage(Pointage pointage) {
        pointages.put(pointage.getDate(), pointage);
    }
