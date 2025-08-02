package com.example.demo.mappers;

import com.example.demo.entities.SosForm;
import generated.com.example.demo.api.model.SosFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses =  SosFormMapper.class)
public interface SosFormMapper {
    SosForm map(SosFormDTO sosFormDTO);
}
