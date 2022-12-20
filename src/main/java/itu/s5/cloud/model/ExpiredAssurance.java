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
public class ExpiredAssurance {
    int idavion;
    String nom;
    int capacite;
    String photo;
    Date expiredassurance;
    int days;
}
