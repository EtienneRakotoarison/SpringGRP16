package itu.s5.cloud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "kilometrage")
public class Kilometrage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  idkilometre;

    @Column(name = "idavion")
    private long idavion;

    @Column(name = "daty")
    private Date daty;

    @Column(name = "kilometre_debut")
    private int kilometre_debut;

    @Column(name = "kilometre_fin")
    private int kilometre_fin;
}
