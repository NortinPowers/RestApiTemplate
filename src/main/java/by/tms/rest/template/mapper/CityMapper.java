package by.tms.rest.template.mapper;

import by.tms.rest.template.domain.City;
import by.tms.rest.template.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDto convertToCityDto(City city);

    @Mapping(target = "id", ignore = true)
    City convertToCity(CityDto cityDto);
}
