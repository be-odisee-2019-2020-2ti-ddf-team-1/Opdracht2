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

        model.addAttribute("activiteitenWithEvaluatiefiches", travelbaseService.getActiviteitenWithEvaluatiefiches());
        model.addAttribute("entryData", entryData);
        //ocalDate theDatum = LocalDate.parse(entryData.getDateTime());
        //model.addAttribute("entries", travelbaseService.getEntriesFromDate(theDatum) );
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
                message = "Correct input errors, please";
                throw new IllegalArgumentException();
            }
            // Check how many projects have been selected for this entry
            long numberNonzero = Arrays.stream(entryData.getEvaluatieficheIds()).filter(x -> x > 0).count();
            // There should have been one and only one project selected, if not throw an exception
            if (numberNonzero != 1) {
                message = "Unacceptable, there must be 1 and only 1 project";
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


}




