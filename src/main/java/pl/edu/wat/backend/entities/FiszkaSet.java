package pl.edu.wat.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FiszkaSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    // Don't store it in the database
    @Transient
    private Integer fiszkiCount;

    private Integer lastGood = 0;
    private Integer lastWrong = 0;

    @OneToMany(mappedBy = "fiszkaSet", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<FiszkaCard> fiszkaCards = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_table_id", nullable = false)
    @JsonIgnore
    private UserImpl myUser;

    public Integer getFiszkiCount() {
        return fiszkaCards.size();
    }

    public FiszkaSet(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
    }

    public void addFiszkaCard(FiszkaCard card) {
        fiszkaCards.add(card);
        card.setFiszkaSet(this);
    }

    public void addFiszkaCards(Set<FiszkaCard> cards) {
        for (FiszkaCard card : cards) {
            addFiszkaCard(card);
        }
    }
}
