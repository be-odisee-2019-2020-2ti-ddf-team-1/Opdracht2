package be.odisee.travelbase.controller;

import be.odisee.travelbase.formdata.EvaluatieFicheData;
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
    public String evaluatieFicheCreateForm(Model model) {

        EvaluatieFicheData evaluatieFicheData = travelbaseService.prepareNewEvaluatieFicheData();
        prepareForm(evaluatieFicheData, model);
        model.addAttribute("message","Aub, creëer een nieuwe evaluatiefiche");
        return "evaluatieFiche";
    }

    /**
     * Prepares the form with data for projects- and objectives comboboxes
     */
    private void prepareForm(EvaluatieFicheData evaluatieFicheData, Model model) {

        model.addAttribute("naam", travelbaseService.getAuthenticatedFullname());
        model.addAttribute("activiteiten", travelbaseService.getActiviteiten());
        model.addAttribute("evaluatieFicheData", evaluatieFicheData);
        model.addAttribute("evaluatieFiches",travelbaseService.getEvaluatieFiches());
        //LocalDate theDatum = LocalDate.parse(EvaluatieFicheData.getDateTime());
        //model.addAttribute("entries", travelbaseService.get(theDatum) );
    }

    /**
     * Process the form
     */
    @PostMapping(params = "submit")
    public String processEvaluatieFiche(@Valid EvaluatieFicheData evaluatieFicheData, Errors errors, Model model) {
        String message = "";
        try {
            // Are there any input validation errors detected by JSR 380 bean validation?
            if (errors.hasErrors()) {
                message = "Correct input errors, aub";
                throw new IllegalArgumentException();
            }

            long selectedActivityId = evaluatieFicheData.getActiviteitId();
            // A project should be selected, if not throw an exception
            if (selectedActivityId <= 0) {
              message = "Selecteer een evaluatiefiche!";
              throw new IllegalArgumentException();
            }

            // Now that the input seems to be OK, let's create a new EvaluatieFiche or update/delete an existing EvaluatieFiche
            message = travelbaseService.processEvaluatieFiche(evaluatieFicheData);
            // Prepare form for new data-EvaluatieFiche
            evaluatieFicheData = travelbaseService.prepareNewEvaluatieFicheData();
        } catch (IllegalArgumentException e) {
            // Nothing special needs to be done
        }
        prepareForm(evaluatieFicheData, model);
        model.addAttribute("message", message);
        return "evaluatieFiche";
    }

    /**
     * Prepare form for update or delete
     * @param id - the id of the EvaluatieFiche to be updated or deleted
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String evaluatieFicheEditForm(@RequestParam("id") long id, Model model) {

        EvaluatieFicheData evaluatieFicheData = travelbaseService.prepareEvaluatieFicheDataToEdit(id);
        prepareForm(evaluatieFicheData, model);
        model.addAttribute("message", "Update of Verwijder deze EvaluatieFiche - of Annuleer");
        return "evaluatieFiche";
    }

    /**
     * Delete the EvaluatieFiche and prepare for creation of a new one
     * @return
     */
    @PostMapping(params = "delete")
    public String deleteEvaluatieFiche(EvaluatieFicheData evaluatieFicheData, Model model) {

        travelbaseService.deleteEvaluatieFiche(evaluatieFicheData.getId());
        EvaluatieFicheData newEvaluatieFicheData = travelbaseService.prepareNewEvaluatieFicheData();
        prepareForm(newEvaluatieFicheData, model);
        model.addAttribute("message", "Verwijderde EvaluatieFiche geslaagd "+evaluatieFicheData.getFeedback());
        return "evaluatieFiche";
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




