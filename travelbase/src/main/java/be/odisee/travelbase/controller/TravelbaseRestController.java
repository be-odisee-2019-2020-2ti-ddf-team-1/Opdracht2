package be.odisee.travelbase.controller;

import be.odisee.travelbase.dao.ActiviteitRepository;
import be.odisee.travelbase.dao.EvaluatieFicheRepository;
import be.odisee.travelbase.domain.Activiteit;
import be.odisee.travelbase.domain.EvaluatieFiche;
import be.odisee.travelbase.service.TravelbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/travelbaserest", produces = "application/json")
@CrossOrigin(origins="http://localhost:8443", maxAge = 3600, allowCredentials = "true")
public class TravelbaseRestController {

    @Autowired
    private EvaluatieFicheRepository evaluatieFicheRepository;
    @Autowired
    private ActiviteitRepository activiteitRepository;

    @Autowired
    private TravelbaseService travelbaseService;
/*
    @GetMapping("/{id}")
    public EvaluatieFiche getEvaluatieFicheById(@PathVariable("id") Long id) {
        return evaluatieFicheRepository.findById(id).get();
    }

    @GetMapping("/evaluatiefiches")
    public List<EvaluatieFiche> getEvaluatieFiches(){
        return evaluatieFicheRepository.findAllByGebruiker()
    }
*/

    @GetMapping("/activiteit/{id}")
    public Activiteit getActiviteitById(@PathVariable("id") Long id){
        return activiteitRepository.findById(id).get();
    }
}
