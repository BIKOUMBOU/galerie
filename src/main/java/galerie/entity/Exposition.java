package galerie.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Exposition {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private String intitule;
    
    private LocalDate debut = LocalDate.now();    
    
    private Integer duree = 25;
    
    @ManyToOne
    @NonNull
    Galerie organisateur;

    @ManyToMany
    @ToString.Exclude
    List<Tableau> oeuvres = new LinkedList<>();
    
    @OneToMany(mappedBy = "lieuDeVente")
    @ToString.Exclude
    private List<Transaction> ventes = new LinkedList<>();
    
    public float CA() {
        float result =0.0f;
        for (Transaction vente : ventes)
            result += vente.getPrixVente();
        return result;

    }

}
