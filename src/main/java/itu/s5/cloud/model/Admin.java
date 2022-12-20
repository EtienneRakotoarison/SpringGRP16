package itu.s5.cloud.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idadmin;

    @Column(name = "login")
    private String login;

    @Column(name = "mdp")
    private String mdp;

}
