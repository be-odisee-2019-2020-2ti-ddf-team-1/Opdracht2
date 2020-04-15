package be.odisee.travelbase.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "VERKENNERS")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE,force=true)
public class Verkenner {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final long id;

    private String naam;
    private String userid;
    private String password;
}
