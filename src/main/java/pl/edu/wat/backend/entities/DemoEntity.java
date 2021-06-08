package pl.edu.wat.backend.entities;


import javax.persistence.*;

@Entity
@Table(name = "demo")
public class DemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private double value;
}
