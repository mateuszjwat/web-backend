package pl.edu.wat.backend.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "statstics")
@Data
@NoArgsConstructor
public class StatisticCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long parentId;
    private String setName;
    private int goodAns;
    private int wrongAns;
}
