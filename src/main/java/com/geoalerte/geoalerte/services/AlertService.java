package com.geoalerte.geoalerte.services;

import com.geoalerte.geoalerte.models.Alert;

import java.util.List;

public interface AlertService {
    Alert Creer (Alert alert);
    Alert Modifier (long id, Alert alert);
    String Supprimer (long id);
    List<Alert> getAll();
    Alert GetId(long id);
    Alert GetByLibelle(String libelle);
    boolean existsByLibelle(String libelle);

}
