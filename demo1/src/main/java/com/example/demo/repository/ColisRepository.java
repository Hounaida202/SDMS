package com.example.demo.repository;

import com.example.demo.dto.ColisDTO;
import com.example.demo.entity.Colis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {

    Page<Colis> findByIdClientExpediteur_Id(Long clientId, Pageable pageable);

    List<Colis> findByIdLivreur_Id(Long livreurId);

    Page<Colis> findByStatutInAndIdZone_IdAndVilleDestinationContainingIgnoreCaseAndPrioriteIn(
            List<String> statuts,
            Long idZone,
            String ville,
            List<String> priorites,
            Pageable pageable
    );

    long countByIdLivreur_Id(Long livreurId);

    @Query("SELECT COALESCE(SUM(c.poids), 0) FROM Colis c WHERE c.idLivreur.id = :livreurId")
    BigDecimal getPoidsTotalParLivreur(@Param("livreurId") Long livreurId);

    List<Colis> findByIdLivreurIsNull();

}