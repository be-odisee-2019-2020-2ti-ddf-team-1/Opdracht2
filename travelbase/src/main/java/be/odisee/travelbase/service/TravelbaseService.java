package be.odisee.travelbase.service;

import be.odisee.travelbase.domain.Evaluatiefiche;
import be.odisee.travelbase.domain.Entry;
import be.odisee.travelbase.formdata.EntryData;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TravelbaseService {

    public Map<String, List<Evaluatiefiche>> getActiviteitenWithEvaluatiefiches();

    public EntryData prepareNewEntryData();

    public String processEntry(@Valid EntryData entryData);


    public EntryData prepareEntryDataToEdit(long id);

    public void deleteEntry(long id);
}
