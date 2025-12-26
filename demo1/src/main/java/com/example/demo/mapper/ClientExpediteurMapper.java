package com.example.demo.mapper;


import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientExpediteurMapper {

    ClientExpediteurDTO toDto(ClientExpediteur entity);

    ClientExpediteur toEntity(ClientExpediteurDTO dto);
}
