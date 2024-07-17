package org.harmonicatabs.repository;

import org.harmonicatabs.model.entity.Harmonica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarmonicaRepository extends JpaRepository<Harmonica, Long> {

}
