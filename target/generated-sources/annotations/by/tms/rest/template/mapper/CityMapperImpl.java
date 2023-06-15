package by.tms.rest.template.mapper;

import by.tms.rest.template.domain.City;
import by.tms.rest.template.dto.CityDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-06T13:35:57+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public CityDto convertToCityDto(City city) {
        if ( city == null ) {
            return null;
        }

        CityDto cityDto = new CityDto();

        cityDto.setId( city.getId() );
        cityDto.setName( city.getName() );
        cityDto.setInfo( city.getInfo() );

        return cityDto;
    }

    @Override
    public City convertToCity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        City city = new City();

        city.setName( cityDto.getName() );
        city.setInfo( cityDto.getInfo() );

        return city;
    }
}
