package com.geoalerte.geoalerte.repositories;

import com.geoalerte.geoalerte.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    Alert findByLibelle(String libelle);
    boolean existsByLibelle(String libelle);

}
