package com.geoalerte.geoalerte.ServicesImpl;

import com.geoalerte.geoalerte.models.Alert;
import com.geoalerte.geoalerte.repositories.AlertRepository;
import com.geoalerte.geoalerte.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class alertServiceImpl implements AlertService {
    @Autowired
    AlertRepository alertRepository;
    @Override
    public Alert Creer(Alert alert) {
        return alertRepository.save(alert);
    }

    @Override
    public Alert Modifier(long id, Alert alert) {
        return alertRepository.findById(id)
                .map(p->{
                            p.setLibelle(alert.getLibelle());
                            p.setDate(alert.getDate());
                            p.setUser(alert.getUser());
                            return alertRepository.save(p);
                        }
                ).orElseThrow(()->new RuntimeException("Modification a echoué"));
    }

    @Override
    public String Supprimer(long id) {
        alertRepository.deleteById(id);
        return "Supprimer avec succes";
    }

    @Override
    public List<Alert> getAll() {
        return alertRepository.findAll();
    }

    @Override
    public Alert GetId(long id) {
        return alertRepository.findById(id).get();
    }

    @Override
    public Alert GetByLibelle(String libelle) {
        return alertRepository.findByLibelle(libelle);
    }

    @Override
    public boolean existsByLibelle(String libelle) {
        return alertRepository.existsByLibelle(libelle);
    }
}
