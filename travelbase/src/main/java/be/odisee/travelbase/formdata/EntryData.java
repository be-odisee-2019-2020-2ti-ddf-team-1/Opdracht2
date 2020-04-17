package be.odisee.travelbase.formdata;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EntryData {

    // id is needed for updating
    private long id=0;

    @NotBlank(message="Date must be specified ")
    private String dateTime;

    private long[] evaluatieficheIds;

    @NotBlank(message="Feedback must be filled in ")
    private String feedback;

    @NotBlank(message="Oordeel must be filled in ")
    private String oordeel;

    @NotBlank(message="Beoordeling must be filled in ")
    private String beoordeling;
}
