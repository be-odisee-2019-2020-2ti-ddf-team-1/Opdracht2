package be.odisee.travelbase.service;

import be.odisee.travelbase.dao.ActiviteitRepository;
import be.odisee.travelbase.dao.EvaluatieficheRepository;
import  be.odisee.travelbase.dao.EntryRepository;
import be.odisee.travelbase.dao.GebruikerRepository;
import be.odisee.travelbase.domain.Activiteit;
import be.odisee.travelbase.domain.Entry;
import be.odisee.travelbase.domain.Evaluatiefiche;
import be.odisee.travelbase.domain.Gebruiker;
import be.odisee.travelbase.formdata.EntryData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TravelbaseServiceImpl implements TravelbaseService {

    @Autowired
    private ActiviteitRepository activiteitRepository;

    @Autowired
    private EvaluatieficheRepository evaluatieficheRepository;

    @Autowired
    EntryRepository entryRepository;

    @Autowired
    GebruikerRepository gebruikerRepository;

    private List<Activiteit> getActiviteiten() {
        return (List<Activiteit>) activiteitRepository.findAllByGebruikerOrderByNaam(findAuthenticatedUser());
    }

    private List<Evaluatiefiche> getEvaluatiefiches() {
        return (List<Evaluatiefiche>) evaluatieficheRepository.findAllByGebruikerOrderByNaam(findAuthenticatedUser());
    }



    /**
     * Creates a map with categories as keys
     * and lists of projects belonging to that category
     *
     * @return the map
     */
    @Override
    public Map<String, List<Evaluatiefiche>> getActiviteitenWithEvaluatiefiches() {

        Map<String, List<Evaluatiefiche>> activiteitenWithEvaluatiefichesMap = new LinkedHashMap<String, List<Evaluatiefiche>>();

        List<Activiteit> activiteiten = getActiviteiten();
        List<Evaluatiefiche> evaluatiefiches = getEvaluatiefiches();

        for (Activiteit activiteit : activiteiten) {

            activiteitenWithEvaluatiefichesMap.put(activiteit.getNaam().toUpperCase(),
                    filterByActiviteit(evaluatiefiches, activiteit.getNaam()));
        }

        return activiteitenWithEvaluatiefichesMap;
    }

    /**
     * A Java-8-filter to filter projects per category
     */
    private List<Evaluatiefiche> filterByActiviteit(
            List<Evaluatiefiche> evaluatiefiches, String activiteit) {
        return evaluatiefiches
                .stream()
                .filter(x -> x.getActiviteit().getNaam().equals(activiteit))
                .collect(Collectors.toList());
    }

    @Override
    public List<Entry> getEntries() {

        return entryRepository.findByEvaluatieficheNotNullAndGebruiker(findAuthenticatedUser());
    }

    /**
     * Instantiates and initializes new EntryData-object
     * to be used in the form
     *
     * @return
     */
    public EntryData prepareNewEntryData() {

        Gebruiker theUser = findAuthenticatedUser();
        Entry lastEntry = entryRepository.findFirstByGebruikerOrderByIdDesc(theUser);
        return prepareEntryData(lastEntry);
    }

    /**
     * Prepares an EntryData-object based on an Entry-object
     *
     * @param theEntry  the Entry-object

     * @return
     */
    private EntryData prepareEntryData(Entry theEntry) {

        EntryData entryData = new EntryData();

        if (theEntry.getEvaluatiefiche() != null) {

            // Pick up the last entry project choice and adapt it to the form
            Evaluatiefiche lastEvaluatiefiche = theEntry.getEvaluatiefiche();
            List<Activiteit> activiteiten = getActiviteiten();
            List<Long> evaluatieficheIdsInList = new ArrayList<Long>();
            for (Activiteit activiteit : activiteiten) {
                if (activiteit.equals(lastEvaluatiefiche.getActiviteit()))
                    evaluatieficheIdsInList.add(lastEvaluatiefiche.getId());
                else
                    evaluatieficheIdsInList.add(0L);
            }
            // An array of long primitives is needed
            long[] evaluatieficheIds = evaluatieficheIdsInList.stream().mapToLong(l -> l).toArray();
            entryData.setEvaluatieficheIds(evaluatieficheIds);


            // Pick up the other last entry data and propose them in the form
            entryData.setDateTime( theEntry.getDateTime().toString());


            entryData.setFeedback( theEntry.getFeedback() );
            entryData.setOordeel( theEntry.getOordeel() );
            entryData.setBeoordeling( theEntry.getBeoordeling() );

        } else {
//            entryData.setObjectiveId(0);
            entryData.setDateTime(LocalDate.now().toString());
//            entryData.setStartTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
//            entryData.setEndTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        }
        return entryData;
    }

    @Override
    public String processEntry(EntryData entryData) {

        Entry entry;

        if (entryData.getId() == 0) entry = new Entry();
        else entry = entryRepository.findById( entryData.getId() );

        if (entry.getGebruiker() == null) entry.setGebruiker(findAuthenticatedUser());

        // The evaluatieficheId is that of the one and only selected
        long[] evaluatieficheIds = entryData.getEvaluatieficheIds();
        long evaluatieficheId = Arrays.stream(evaluatieficheIds).max().getAsLong();
        entry.setEvaluatiefiche( evaluatieficheRepository.findById(evaluatieficheId) );

        LocalDate dateTime = LocalDate.parse(entryData.getDateTime());
        entry.setDateTime(dateTime);

        String feedback = entryData.getFeedback();
        entry.setFeedback( feedback );

        String oordeel = entryData.getOordeel();
        entry.setOordeel( oordeel );

        String beoordeling = entryData.getBeoordeling();
        entry.setBeoordeling( beoordeling );

        // Save the newly created entry
        entryRepository.save(entry);
        return entry.getDateTime() + " '" + entry.getFeedback() + " '" + entry.getOordeel() + " '" + entry.getBeoordeling() + " '" + "' has been processed";

    }

    @Override
    public EntryData prepareEntryDataToEdit(long id) {

        Entry theEntry = entryRepository.findById(id);
        EntryData theEntryData = prepareEntryData(theEntry);
        theEntryData.setId(id);
        return theEntryData;
    }

    @Override
    public void deleteEntry(long id) {

        Entry entry = entryRepository.findById(id);
        entryRepository.delete(entry);
    }

    public String getAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }

    private Gebruiker findAuthenticatedUser() {

        String gebruiker = getAuthenticatedUsername();
        return gebruikerRepository.findByGebruikersnaam(gebruiker);
    }

    @Override
    public String getAuthenticatedFullname() {

        Gebruiker theUser = findAuthenticatedUser();
        return theUser.getNaam();
    }


}
