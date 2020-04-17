package be.odisee.travelbase.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "ACTIVITEITEN")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE,force=true)
public class Activiteit {

    @Id
    private final long id;

    private final String naam;
}

