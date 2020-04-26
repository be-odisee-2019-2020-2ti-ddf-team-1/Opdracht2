package be.odisee.travelbase.formdata;

import be.odisee.travelbase.domain.Activiteit;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EvaluatieFicheData {

    // id is needed for updating
    private long id=0;

    @NotBlank(message="Datum moet gespecifieerd")
    private String dateTime;

    private long[] evaluatieficheIds;

    private long activiteitId;

    private Activiteit activiteit;

    @NotBlank(message="Feedback moet ingevuld zijn")
    private String feedback;

    @NotBlank(message="Oordeel moet ingevuld zijn")
    private String oordeel;

    @NotBlank(message="Beoordeling moet ingevuld zijn ")
    private String beoordeling;
/*
    public Activiteit getActiviteit() {
        return activiteit;
    }

 */
}
