package pl.edu.wat.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "public_fiszka_set_table")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PublicFiszkaCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String face;
    @NonNull
    private String reverse;

    public PublicFiszkaCard(FiszkaCard card){
        this.face = card.getFace();
        this.reverse = card.getReverse();
    }
}
