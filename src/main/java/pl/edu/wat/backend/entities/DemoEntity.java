package pl.edu.wat.backend.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="demo_table")
public class DemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private double value;
}
