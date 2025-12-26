package com.example.demo.mapper;

import com.example.demo.dto.DestinataireDTO;
import com.example.demo.entity.Destinataire;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DestinataireMapper {

    DestinataireDTO toDto(Destinataire entity);

    Destinataire toEntity(DestinataireDTO dto);
}