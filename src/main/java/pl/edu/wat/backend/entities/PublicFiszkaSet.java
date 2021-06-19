package pl.edu.wat.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PublicFiszkaSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String description;

    private long template_id;

    private String owner;
    // Don't store it in the database
    @Transient
    private Integer fiszkiCount;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<PublicFiszkaCard> fiszkaCards = new HashSet<>();

    public Integer getFiszkiCount() {
        return fiszkaCards.size();
    }

    public PublicFiszkaSet(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
    }

    public PublicFiszkaSet(FiszkaSet set, String owner){
        this.template_id = set.getId();
        this.description = set.getDescription();
        this.title = set.getTitle();
        this.owner = owner;
        for(FiszkaCard card : set.getFiszkaCards()){
            fiszkaCards.add(new PublicFiszkaCard(card));
        }
    }

    public void addFiszkaCard(PublicFiszkaCard card) {
        fiszkaCards.add(card);
    }

    public void addFiszkaCards(Set<PublicFiszkaCard> cards) {
        for (PublicFiszkaCard card : cards) {
            addFiszkaCard(card);
        }
    }
}
