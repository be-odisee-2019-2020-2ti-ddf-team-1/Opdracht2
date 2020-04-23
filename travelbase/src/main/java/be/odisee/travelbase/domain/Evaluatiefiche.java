package be.odisee.travelbase.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

// dit is een test
@Entity
@Table(name = "EVALUATIEFICHES")
@Data
@RequiredArgsConstructor // generates constructor with required arguments - final fields and @NonNull-fields
@NoArgsConstructor(access= AccessLevel.PRIVATE,force=true)
public class Evaluatiefiche {

    @Id
    private final long id;

    private final String naam;

    @ManyToOne
    private final Activiteit activiteit;

    @ManyToOne
    private final Gebruiker gebruiker;
}

