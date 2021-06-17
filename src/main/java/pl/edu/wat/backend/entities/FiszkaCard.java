package pl.edu.wat.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fiszka_set_table")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class FiszkaCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String face;
    @NonNull
    private String reverse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fiszkaSet_id", nullable = false)
    @JsonIgnore
    private FiszkaSet fiszkaSet;
}
