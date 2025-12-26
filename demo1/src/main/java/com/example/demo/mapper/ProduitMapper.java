package com.example.demo.mapper;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProduitMapper {

    ProduitDTO toDto(Produit entity);

    Produit toEntity(ProduitDTO dto);
}