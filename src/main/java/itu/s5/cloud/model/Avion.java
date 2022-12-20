package itu.s5.cloud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "avion")
public class Avion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  idavion;

    @Column(name = "nom")
    private String nom;

    @Column(name = "kilometrage_initial")
    private long kilometrage_initial;

    @Column(name = "capacite")
    private long capacite;

    @Column(name = "photo")
    private String photo;

    public Avion(long idavion, String nom, String photo) {
        this.idavion = idavion;
        this.nom = nom;
        this.photo = photo;
    }
}
