package be.odisee.travelbase.service;

import be.odisee.travelbase.domain.Activiteit;
import be.odisee.travelbase.domain.EvaluatieFiche;
import be.odisee.travelbase.formdata.EvaluatieFicheData;

import javax.validation.Valid;
import java.util.List;

public interface TravelbaseService {

    public List<Activiteit> getActiviteiten();

    public List<EvaluatieFiche> getEvaluatieFiches();

    public EvaluatieFicheData prepareNewEvaluatieFicheData();

    public String processEvaluatieFiche(@Valid EvaluatieFicheData evaluatieFicheData);

    public EvaluatieFicheData prepareEvaluatieFicheDataToEdit(long id);

    public void deleteEvaluatieFiche(long id);

    public String getAuthenticatedFullname();
}
