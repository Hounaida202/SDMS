package com.example.demo.mapper;

import com.example.demo.dto.ColisDTO;
import com.example.demo.entity.Colis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColisMapper {

    @Mapping(source = "idLivreur.id", target = "livreurId")
    @Mapping(target = "livreurNom",
            expression = "java(entity.getIdLivreur() != null ? entity.getIdLivreur().getNom() + \" \" + entity.getIdLivreur().getPrenom() : null)")

    @Mapping(source = "idClientExpediteur.id", target = "clientExpediteurId")
    @Mapping(target = "clientExpediteurNom",
            expression = "java(entity.getIdClientExpediteur().getNom() + \" \" + entity.getIdClientExpediteur().getPrenom())")

    @Mapping(source = "idDestinataire.id", target = "destinataireId")
    @Mapping(target = "destinataireNom",
            expression = "java(entity.getIdDestinataire().getNom() + \" \" + entity.getIdDestinataire().getPrenom())")

    @Mapping(source = "idZone.id", target = "zoneId")
    @Mapping(source = "idZone.nom", target = "zoneNom")
    ColisDTO toDto(Colis entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idLivreur", ignore = true)
    @Mapping(target = "idClientExpediteur", ignore = true)
    @Mapping(target = "idDestinataire", ignore = true)
    @Mapping(target = "idZone", ignore = true)
    Colis toEntity(ColisDTO dto);
}