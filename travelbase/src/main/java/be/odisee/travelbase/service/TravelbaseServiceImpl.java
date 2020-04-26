package be.odisee.travelbase.service;

import be.odisee.travelbase.dao.ActiviteitRepository;
import be.odisee.travelbase.dao.EvaluatieFicheRepository;
import be.odisee.travelbase.dao.GebruikerRepository;
import be.odisee.travelbase.domain.Activiteit;
import be.odisee.travelbase.domain.EvaluatieFiche;
import be.odisee.travelbase.domain.Gebruiker;
import be.odisee.travelbase.formdata.EvaluatieFicheData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class TravelbaseServiceImpl implements TravelbaseService {

    @Autowired
    private ActiviteitRepository activiteitRepository;

    @Autowired
    EvaluatieFicheRepository evaluatieFicheRepository;

    @Autowired
    GebruikerRepository gebruikerRepository;

    @Override
    public List<Activiteit> getActiviteiten() {
        return (List<Activiteit>) activiteitRepository.findAllByGebruikerOrderByNaam(findAuthenticatedUser());
    }

    @Override
    public List<EvaluatieFiche> getEvaluatieFiches() {

        return evaluatieFicheRepository.findAllActiviteitByGebruiker(findAuthenticatedUser());
    }

    public Activiteit getActiviteitByEvaluatieFicheId(long l)
    {
        int i = (int)l;
        Activiteit activiteit = getActiviteiten().get(i);

        return activiteit;
    }

    /**
     * Instantiates and initializes new EvaluatieFicheData-object
     * to be used in the form
     *
     * @return
     */
    public EvaluatieFicheData prepareNewEvaluatieFicheData() {

        Gebruiker theUser = findAuthenticatedUser();
        EvaluatieFiche lastEvaluatieFiche = evaluatieFicheRepository.findFirstByGebruikerOrderByIdDesc(theUser);
        return prepareEvaluatieFicheData(lastEvaluatieFiche);
    }

    /**
     * Prepares an EvaluatieFicheData-object based on an EvaluatieFiche-object
     *
     * @param theEvaluatieFiche the EvaluatieFiche-object
     * @return
     */
    private EvaluatieFicheData prepareEvaluatieFicheData(EvaluatieFiche theEvaluatieFiche) {

        if(theEvaluatieFiche != null) {
            EvaluatieFicheData evaluatieFicheData = new EvaluatieFicheData();

            List<Activiteit> activiteiten = getActiviteiten();

            // Pick up the other last EvaluatieFiche data and propose them in the form
            evaluatieFicheData.setDateTime(theEvaluatieFiche.getDateTime().toString());


            evaluatieFicheData.setFeedback(theEvaluatieFiche.getFeedback());
            evaluatieFicheData.setOordeel(theEvaluatieFiche.getOordeel());
            evaluatieFicheData.setBeoordeling(theEvaluatieFiche.getBeoordeling());
            return evaluatieFicheData;
        }
        return new EvaluatieFicheData();
    }

    @Override
    public String processEvaluatieFiche(EvaluatieFicheData evaluatieFicheData) {

        EvaluatieFiche evaluatieFiche;

        if (evaluatieFicheData.getId() == 0) evaluatieFiche = new EvaluatieFiche();
        else evaluatieFiche = evaluatieFicheRepository.findById(evaluatieFicheData.getId());

        if (evaluatieFiche.getGebruiker() == null) evaluatieFiche.setGebruiker(findAuthenticatedUser());

        int activiteitId = (int)evaluatieFicheData.getActiviteitId();

        Activiteit activiteit = getActiviteiten().get(activiteitId);
        evaluatieFiche.setActiviteit(activiteit);

        LocalDate dateTime = LocalDate.parse(evaluatieFicheData.getDateTime());
        evaluatieFiche.setDateTime(dateTime);

        String feedback = evaluatieFicheData.getFeedback();
        evaluatieFiche.setFeedback(feedback);

        String oordeel = evaluatieFicheData.getOordeel();
        evaluatieFiche.setOordeel(oordeel);

        String beoordeling = evaluatieFicheData.getBeoordeling();
        evaluatieFiche.setBeoordeling(beoordeling);

        // Save the newly created EvaluatieFiche
        evaluatieFicheRepository.save(evaluatieFiche);
        return evaluatieFiche.getDateTime() + " '" + evaluatieFiche.getFeedback() + " '" + evaluatieFiche.getOordeel() + " '" + evaluatieFiche.getBeoordeling() + " '" + "' has been processed";

    }

    @Override
    public EvaluatieFicheData prepareEvaluatieFicheDataToEdit(long id) {

        EvaluatieFiche theEvaluatieFiche = evaluatieFicheRepository.findById(id);
        EvaluatieFicheData theEvaluatieFicheData = prepareEvaluatieFicheData(theEvaluatieFiche);
        theEvaluatieFicheData.setId(id);
        return theEvaluatieFicheData;
    }

    @Override
    public void deleteEvaluatieFiche(long id) {

        EvaluatieFiche evaluatieFiche = evaluatieFicheRepository.findById(id);
        evaluatieFicheRepository.delete(evaluatieFiche);
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
