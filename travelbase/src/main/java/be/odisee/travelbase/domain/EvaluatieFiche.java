package be.odisee.travelbase.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "EVALUATIEFICHE")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
public class EvaluatieFiche {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private final long id;

    @ManyToOne
    private Gebruiker gebruiker;

    @ManyToOne
    private Activiteit activiteit;


    private LocalDate dateTime;

    private String feedback;
    private String oordeel;
    private String beoordeling;
}