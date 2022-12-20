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

public class DetailsAvion {
    int idavion;
    String nom;
    int capacite;
    String photo;
    double kilometragefait;
    String daty;
}
