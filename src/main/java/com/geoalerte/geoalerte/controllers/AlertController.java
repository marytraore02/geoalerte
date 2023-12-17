package com.geoalerte.geoalerte.controllers;

import com.geoalerte.geoalerte.models.Alert;
import com.geoalerte.geoalerte.models.User;
import com.geoalerte.geoalerte.payload.response.MessageResponse;
import com.geoalerte.geoalerte.repositories.AlertRepository;
import com.geoalerte.geoalerte.repositories.UserRepository;
import com.geoalerte.geoalerte.services.AlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600, allowCredentials="true")
@Api(value = "hello", description = "CRUD ALERT")
@RestController
@RequestMapping("/alert")
public class AlertController {
    @Autowired
    AlertRepository alertRepository;
    @Autowired
    AlertService alertService;
    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "Creer alerte")
    @PostMapping("/creer/{id}")
    public ResponseEntity<Object> Creer(@PathVariable Long id,
                                        @RequestBody Alert alert){
        User user = this.userRepository.getReferenceById(id);
        alert.setUser(user);
        return new ResponseEntity(alertService.Creer(alert), HttpStatus.OK);
    }

    @ApiOperation(value = "Modifier une alerte")
    @PutMapping("/modifier/{id}")
    public ResponseEntity<Object> Modifier(@PathVariable("id") long id,  @RequestBody Alert alert){
         alertService.Modifier(id, alert);
        return new ResponseEntity(new MessageResponse("Mise à jour effectué"), HttpStatus.OK);
    }
    @ApiOperation(value = "Supprimer une alerte")
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object>  Supprimer(@PathVariable("id") long id ){
        alertService.Supprimer(id);
        return new ResponseEntity(new MessageResponse("Suppression éffectuée avec succes"), HttpStatus.OK);
    }

    @ApiOperation(value = "Details d'une alerte")
    @GetMapping("/get/{id}")
    public ResponseEntity<Alert> getId(@PathVariable("id") long id){
        if(!alertRepository.existsById(id))
            return new ResponseEntity(new MessageResponse("Id de l'alerte n'existe pas"), HttpStatus.NOT_FOUND);
        Alert alert = alertService.GetId(id);
        return new ResponseEntity(alert, HttpStatus.OK);
    }

    @ApiOperation(value = "Liste des alerte")
    @GetMapping("/list")
    public ResponseEntity<List<Alert>> getAll() {
        List<Alert> list = alertService.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

}
