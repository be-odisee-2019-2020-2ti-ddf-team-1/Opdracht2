package be.odisee.travelbase.controller;

import be.odisee.travelbase.formdata.EntryData;
import be.odisee.travelbase.service.TravelbaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
@Controller
@RequestMapping("/travelbase")
public class TravelbaseController {

    @Autowired
    private TravelbaseService travelbaseService;

    /**
     * Prepare form for create
     */
    @GetMapping
    public String entryCreateForm(Model model) {

        EntryData entryData = travelbaseService.prepareNewEntryData();
        prepareForm(entryData, model);
        return "entry";
    }

    /**
     * Prepares the form with data for projects- and objectives comboboxes
     */
    private void prepareForm(EntryData entryData, Model model) {

        model.addAttribute("gebruikersnaam", travelbaseService.getAuthenticatedUsername());
        model.addAttribute("activiteitenWithEvaluatiefiches", travelbaseService.getActiviteitenWithEvaluatiefiches());
        model.addAttribute("entryData", entryData);
        model.addAttribute("entries", travelbaseService.getEntries());
        //LocalDate theDatum = LocalDate.parse(entryData.getDateTime());
        //model.addAttribute("entries", travelbaseService.get(theDatum) );
    }

    /**
     * Process the form
     */
    @PostMapping(params = "submit")
    public String processEntry(@Valid EntryData entryData, Errors errors, Model model) {
        String message = "";
        try {
            // Are there any input validation errors detected by JSR 380 bean validation?
            if (errors.hasErrors()) {
                message = "Correct input errors, aub";
                throw new IllegalArgumentException();
            }
            // Check how many projects have been selected for this entry
            long numberNonzero = Arrays.stream(entryData.getEvaluatieficheIds()).filter(x -> x > 0).count();
            // There should have been one and only one project selected, if not throw an exception
            if (numberNonzero != 1) {
                message = "Selecteer alleen een evaluatiefiche!";
                throw new IllegalArgumentException();
            }
            // Now that the input seems to be OK, let's create a new entry or update/delete an existing entry
            message = travelbaseService.processEntry(entryData);
            // Prepare form for new data-entry
            entryData = travelbaseService.prepareNewEntryData();
        } catch (IllegalArgumentException e) {
            // Nothing special needs to be done
        }
        prepareForm(entryData, model);
        model.addAttribute("message", message);
        return "entry";
    }

    /**
     * Prepare form for update or delete
     * @param id - the id of the entry to be updated or deleted
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String entryEditForm(@RequestParam("id") long id, Model model) {

        EntryData entryData = travelbaseService.prepareEntryDataToEdit(id);
        prepareForm(entryData, model);
        model.addAttribute("message", "Update of Verwijder deze entry - of Annuleer");
        return "entry";
    }

    /**
     * Delete the entry and prepare for creation of a new one
     * @return
     */
    @PostMapping(params = "delete")
    public String deleteEntry(EntryData entrydata, Model model) {

        travelbaseService.deleteEntry(entrydata.getId());
        EntryData entryData = travelbaseService.prepareNewEntryData();
        prepareForm(entryData, model);
        model.addAttribute("message", "Verwijderde entry geslaagd "+entrydata.getFeedback());
        return "entry";
    }

    /**
     * If user does not want to update or delete, he will be redirect to create
     * @return
     */
    @PostMapping(params = "cancel")
    public String redirectToCreate() {

        return "redirect:/travelbase";
    }

}




