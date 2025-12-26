package com.example.demo.mapper;

import com.example.demo.dto.LivreurDTO;
import com.example.demo.entity.Livreur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LivreurMapper {

    @Mapping(source = "zoneAssignee.id", target = "zoneAssigneeId")
    @Mapping(source = "zoneAssignee.nom", target = "zoneAssigneeNom")
    LivreurDTO toDto(Livreur entity);

    @Mapping(target = "zoneAssignee", ignore = true)
    Livreur toEntity(LivreurDTO dto);
}