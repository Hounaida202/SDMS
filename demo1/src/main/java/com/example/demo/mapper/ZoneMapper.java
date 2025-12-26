package com.example.demo.mapper;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ZoneMapper {

    ZoneDTO toDto(Zone entity);

    Zone toEntity(ZoneDTO dto);
}